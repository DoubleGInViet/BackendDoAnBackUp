package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.BookedTicketDTO;
import com.duy.BackendDoAn.dtos.BookingTicketDTO;
import com.duy.BackendDoAn.models.*;
import com.duy.BackendDoAn.repositories.*;
import com.duy.BackendDoAn.responses.bookingTickets.BookingTicketPaymentLinkResponse;
import com.duy.BackendDoAn.responses.bookingTickets.BookingTicketResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingTicketService {
    private final PaymentService paymentService;
    private final UserRepository userRepository;
    private final TourScheduleRepository tourScheduleRepository;
    private final BookingTicketRepository bookingTicketRepository;
    private final TicketClassRepository ticketClassRepository;
    private final DailyTicketAvailabilityRepository dailyTicketAvailabilityRepository;


    public BookingTicketPaymentLinkResponse createBookingTicketPayment(BookingTicketDTO bookingTicketDTO, HttpServletRequest request) throws Exception {
        User user = userRepository.findById(bookingTicketDTO.getUserId())
                .orElseThrow(()-> new Exception("User not found!!"));
        String id = generateUniqueBookingTicketId();
        BookingTicket bookingTicket = BookingTicket.builder()
                .id(id)
                .customerFullName(bookingTicketDTO.getCustomerFullName())
                .customerEmail(bookingTicketDTO.getCustomerEmail())
                .customerPhoneNumber(bookingTicketDTO.getCustomerPhoneNumber())
                .customerCountry(bookingTicketDTO.getCustomerCountry())
                .booking_date(LocalDateTime.now())
                .user(user)
                .status("0")
                .build();

        Long total_price = 0L;
        List<BookedTicket> instance = new ArrayList<>();

        for(BookedTicketDTO bookedTicketDTO : bookingTicketDTO.getBookedTickets()){
            DailyTicketAvailability availability = dailyTicketAvailabilityRepository.findById(bookedTicketDTO.getTicketClassId())
                    .orElseThrow(()-> new Exception("Daily ticket availability not exist!!"));

            TicketClass ticketClass = ticketClassRepository.findById(availability.getTicketClass().getId())
                    .orElseThrow(()-> new Exception("Ticket class not found!!"));
            BookedTicket bookedTicket = BookedTicket.builder()
                    .quantity(bookedTicketDTO.getQuantity())
                    .priceWithQuantity(bookedTicketDTO.getQuantity() * ticketClass.getPrice())
                    .availability(availability)
                    .bookingTicket(bookingTicket)
                    .build();
            instance.add(bookedTicket);
            total_price += bookedTicket.getPriceWithQuantity();

            availability.setAvailableTicket(availability.getAvailableTicket() - bookedTicket.getQuantity());
            dailyTicketAvailabilityRepository.save(availability);
        }
        bookingTicket.setTotal_price(total_price);
        bookingTicket.setBookedTickets(instance);
        bookingTicketRepository.save(bookingTicket);
        BookingTicketResponse response = BookingTicketResponse.fromBooking(bookingTicket);

        //Payment
        String initPaymentResponse = (paymentService.createVnPayPaymentBooking(id, bookingTicket.getTotal_price(), "ticket", request));

        return BookingTicketPaymentLinkResponse.builder()
                .bookingTicket(response)
                .payment(initPaymentResponse)
                .build();
    }





    public Page<BookingTicketResponse> getBookingByUser(Long id, PageRequest pageRequest) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found!!"));
        Page<BookingTicket> bookingTicketPage = bookingTicketRepository.findByUser(user, pageRequest);
        return bookingTicketPage.map(BookingTicketResponse::fromBooking);
    }

    private String generateUniqueBookingTicketId() {
        String uniqueId;
        boolean isUnique;

        do {
            uniqueId = generateRandomId();
            isUnique = !bookingTicketRepository.existsById(uniqueId);  // Kiểm tra xem ID đã tồn tại trong DB chưa
        } while (!isUnique);  // Nếu đã tồn tại, tiếp tục tạo lại ID mới

        return uniqueId;
    }

    // Hàm tạo ID ngẫu nhiên 20 ký tự
    private String generateRandomId() {
        // Sử dụng UUID để tạo ID ngẫu nhiên
        String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return randomId;
    }

    public void setBookingTicketStatusPayment(String id) throws Exception {
        BookingTicket existing = bookingTicketRepository.findById(id)
                .orElseThrow(()-> new Exception("Booking not saved yet!!"));
        existing.setStatus("1");
        bookingTicketRepository.save(existing);
    }
}
