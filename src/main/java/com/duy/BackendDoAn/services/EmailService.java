package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.EmailDTO;
import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.repositories.HotelRepository;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomResponse;
import com.duy.BackendDoAn.responses.bookingRooms.SeperatedRoomResponse;
import com.duy.BackendDoAn.responses.bookingVehicles.BookingVehicleResponse;
import com.duy.BackendDoAn.responses.bookingVehicles.DriverResponse;
import com.duy.BackendDoAn.responses.bookingVehicles.ServiceResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailService {
    private final HotelRepository hotelRepository;
    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingEmail(BookingRoomResponse bookingRoomResponse) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("no-reply@hotelbooking.com", "Hotel Booking");
            helper.setTo(bookingRoomResponse.getCustomerResponse().getEmail());
            helper.setSubject("Booking Successfully");
            String emailContent = buildEmailBody(bookingRoomResponse);

            helper.setText(emailContent, true);  // true để xác định đây là HTML content

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending password reset email: " + e.getMessage());
        }
    }

    public void sendBookingVehicleMessage(BookingVehicleResponse response) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("no-reply@hotelbooking.com", "Hotel Booking");
            helper.setTo(response.getCustomerResponse().getEmail());
            helper.setSubject("Booking Vehicle Successfully");
            String emailContent = buildEmailBookingVehicleBody(response);

            helper.setText(emailContent, true);  // true để xác định đây là HTML content

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending password reset email: " + e.getMessage());
        }
    }

    public void sendPasswordResetEmail(String toEmail, String newPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Thêm thông tin người gửi và tiêu đề
            helper.setFrom("no-reply@hotelbooking.com", "Hotel Booking");
            helper.setTo(toEmail);
            helper.setSubject("Password Reset Notification");

            // Nội dung email dưới dạng HTML
            String emailContent = "<html><body>"
                    + "<h2>Password Reset Notification</h2>"
                    + "<p>Your password has been reset successfully. Your new password is:</p>"
                    + "<h3 style='color:#007bff;'>" + newPassword + "</h3>"
                    + "<p>Please change your password immediately after logging in for security purposes.</p>"
                    + "<p>Thank you,<br>Hotel Booking</p>"
                    + "</body></html>";

            helper.setText(emailContent, true); // true để gửi nội dung HTML

            mailSender.send(message);

            System.out.println("Password reset email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending password reset email: " + e.getMessage());
        }
    }

    public void sendDefaultMessage(EmailDTO emailDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( message, true, "UTF-8");
            helper.setFrom("no-reply@hotelbooking.com", "Hotel Booking");
            helper.setTo(emailDTO.getMail());
            helper.setSubject("Unknown purpose");
            helper.setText(emailDTO.getText());
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildEmailBody(BookingRoomResponse bookingResponse) {
        StringBuilder body = new StringBuilder();

        body.append("<h1>Thông tin đặt phòng của bạn</h1>");
        body.append("<p><strong>Khách sạn:</strong> ").append(bookingResponse.getHotel().getName()).append("</p>");
        body.append("<p><strong>Checkin:</strong> ").append(bookingResponse.getCheckInDate()).append("</p>");
        body.append("<p><strong>Checkout:</strong> ").append(bookingResponse.getCheckOutDate()).append("</p>");
        body.append("<p><strong>Số khách:</strong> ")
            .append(bookingResponse.getAdults()).append(" người lớn, ")
            .append(bookingResponse.getChildren()).append(" trẻ em</p>");

        // Thêm bảng hiển thị thông tin phòng
        body.append("<h2>Chi tiết các phòng đã đặt:</h2>");
        body.append("<table border='1' cellpadding='10' cellspacing='0' style='border-collapse: collapse;'>");
        body.append("<tr>")
            .append("<th>Loại phòng</th>")
            .append("<th>Số lượng</th>")
            .append("<th>Giá thuê mỗi phòng</th>")
            .append("</tr>");

        // Duyệt qua danh sách các phòng đã đặt
        for (SeperatedRoomResponse room : bookingResponse.getRoomSelectionResponse().getSelectedRooms()) {
            body.append("<tr>")
                .append("<td>").append(room.getName()).append("</td>")
                .append("<td>").append(room.getCount()).append("</td>")
                .append("<td>").append(room.getPrice()).append(" VND</td>")
                .append("</tr>");
        }
        body.append("</table>");

        // Giá tiền
        body.append("<p><strong>Tổng tiền:</strong> ").append(bookingResponse.getTotalPrice()).append(" VND</p>");

        return body.toString();
    }

    private String buildEmailBookingVehicleBody(BookingVehicleResponse bookingResponse) {
        StringBuilder body = new StringBuilder();

        body.append("<h1>Thông tin đặt xe của bạn</h1>");
        // Thông tin khách hàng
        body.append("<h2>Thông tin khách hàng</h2>");
        body.append("<p><strong>Họ tên:</strong> ").append(bookingResponse.getCustomerResponse().getFullName()).append("</p>");
        body.append("<p><strong>Email:</strong> ").append(bookingResponse.getCustomerResponse().getEmail()).append("</p>");
        body.append("<p><strong>Điện thoại:</strong> ").append(bookingResponse.getCustomerResponse().getPhone()).append("</p>");
        body.append("<p><strong>Quốc gia:</strong> ").append(bookingResponse.getCustomerResponse().getCountry()).append("</p>");

        // Thông tin xe
        body.append("<p><strong>Tên xe:</strong> ").append(bookingResponse.getVehicle().getName()).append("</p>");
        body.append("<p><strong>Hãng xe:</strong> ").append(bookingResponse.getVehicle().getBrand()).append("</p>");

        // Thông tin điểm đón và trả
        body.append("<h2>Điểm đón và trả xe</h2>");
        body.append("<p><strong>Điểm đón:</strong> ").append(bookingResponse.getPickUpResponse().getName()).append("</p>");
        body.append("<p><strong>Ngày đón:</strong> ").append(bookingResponse.getPickUpResponse().getDate()).append("</p>");
        body.append("<p><strong>Điểm trả:</strong> ").append(bookingResponse.getPickUpResponse().getName()).append("</p>");
        body.append("<p><strong>Ngày trả:</strong> ").append(bookingResponse.getPickUpResponse().getDate()).append("</p>");

        // Thông tin tài xế
        body.append("<h2>Thông tin tài xế</h2>");
        body.append("<p><strong>Tổng số tài xế:</strong> ").append(bookingResponse.getDriverListResponse().getTotalDriver()).append("</p>");
        body.append("<ol style='padding-left: 20px;'>");

        for (DriverResponse driver : bookingResponse.getDriverListResponse().getListDrivers()) {
            body.append("<li>")
                    .append(driver.getTitle()).append(" ").append(driver.getFullName())
                    .append(" (").append(driver.getPhone()).append(")")
                    .append("</li>");
        }

        body.append("</ol>");

        // Thông tin dịch vụ bổ sung
        body.append("<h2>Dịch vụ bổ sung</h2>");
        body.append("<table border='1' cellpadding='10' cellspacing='0' style='border-collapse: collapse;'>");
        body.append("<tr>")
                .append("<th>Tên dịch vụ</th>")
                .append("<th>Số lượng</th>")
                .append("<th>Giá</th>")
                .append("</tr>");

        for (ServiceResponse service : bookingResponse.getServicesResponse().getServices()) {
            body.append("<tr>")
                .append("<td>").append(service.getName()).append("</td>")
                .append("<td>").append(service.getCount()).append("</td>")
                .append("<td>").append(service.getPrice()).append(" VND</td>")
                .append("</tr>");
        }
        body.append("</table>");
        body.append("<p><strong>Tổng chi phí dịch vụ:</strong> ")
                .append(bookingResponse.getServicesResponse().getTotalServices()).append(" VND</p>");

        return body.toString();
    }


}
