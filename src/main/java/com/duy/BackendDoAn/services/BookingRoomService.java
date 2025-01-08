package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.components.JwtTokenUtil;
import com.duy.BackendDoAn.dtos.BookedRoomDTO;
import com.duy.BackendDoAn.dtos.BookingRoomDTO;
import com.duy.BackendDoAn.models.BookedRoom;
import com.duy.BackendDoAn.models.BookingRoom;
import com.duy.BackendDoAn.models.User;
import com.duy.BackendDoAn.models.Room;
import com.duy.BackendDoAn.repositories.BookedRoomRepository;
import com.duy.BackendDoAn.repositories.BookingRoomRepository;
import com.duy.BackendDoAn.repositories.RoomRepository;
import com.duy.BackendDoAn.repositories.UserRepository;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomPaymentLinkResponse;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingRoomService {
    private final UserRepository userRepository;
    private final BookingRoomRepository bookingRoomRepository;
    private final RoomRepository roomRepository;
    private final BookedRoomRepository bookedRoomRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PaymentService paymentService;

    public BookingRoom createBooking (BookingRoomDTO bookingRoomDTO) throws Exception {
        User user = userRepository.findById(bookingRoomDTO.getUser()).orElseThrow(()-> new Exception("User not found"));
        String id = generateUniqueBookingRoomId();
        BookingRoom newBookingRoom = BookingRoom.builder()
                .id(id)
                .booking_date(LocalDateTime.now())
                .adults(bookingRoomDTO.getAdults())
                .children(bookingRoomDTO.getChildren())
                .check_in_date(bookingRoomDTO.getCheckInDate())
                .check_out_date(bookingRoomDTO.getCheckOutDate())
                .customerFullName(bookingRoomDTO.getCustomerFullName())
                .customerEmail(bookingRoomDTO.getCustomerEmail())
                .customerPhoneNumber(bookingRoomDTO.getCustomerPhoneNumber())
                .customerCountry(bookingRoomDTO.getCustomerCountry())
                .status(bookingRoomDTO.getStatus())
                .specialRequest(bookingRoomDTO.getSpecialRequest())
                .arrivalTime(bookingRoomDTO.getArrivalTime())
                .user(user)
                .build();
        Long total = 0L;
        Long totalRoom = 0L;
        long days = ChronoUnit.DAYS.between(newBookingRoom.getCheck_in_date(), newBookingRoom.getCheck_out_date());
        List<BookedRoom> instanceBookedRoom = new ArrayList<>();
        for (BookedRoomDTO bookedRoomDTO : bookingRoomDTO.getBookedRooms()){
            Room room = roomRepository.findById(bookedRoomDTO.getRoom()).orElseThrow(()-> new Exception("Room not found"));
            BookedRoom bookedRoom = BookedRoom.builder()
                    .amount(bookedRoomDTO.getAmount())
                    .price_per(bookedRoomDTO.getAmount() * room.getPrice() * days)
                    .room(room)
                    .bookingRoom(newBookingRoom)
                    .build();
            instanceBookedRoom.add(bookedRoom);
            if(room.getAvailable_room() - bookedRoom.getAmount() >= 0){
                room.setAvailable_room(room.getAvailable_room() - bookedRoom.getAmount());
            }
            roomRepository.save(room);
            totalRoom += bookedRoom.getAmount();
            total += bookedRoom.getPrice_per();
        }
        newBookingRoom.setTotal_price(total);
        newBookingRoom.setTotal_rooms(totalRoom);
        newBookingRoom.setBookedRooms(instanceBookedRoom);
        bookingRoomRepository.save(newBookingRoom);
        return newBookingRoom;
    }


    public BookingRoomPaymentLinkResponse createBookingPayment(BookingRoomDTO bookingRoomDTO, HttpServletRequest request) throws Exception {
        User user = userRepository.findById(bookingRoomDTO.getUser()).orElseThrow(()-> new Exception("User not found"));
        String id = generateUniqueBookingRoomId();
        BookingRoom newBookingRoom = BookingRoom.builder()
                .id(id)
                .booking_date(LocalDateTime.now())
                .adults(bookingRoomDTO.getAdults())
                .children(bookingRoomDTO.getChildren())
                .check_in_date(bookingRoomDTO.getCheckInDate())
                .check_out_date(bookingRoomDTO.getCheckOutDate())
                .customerFullName(bookingRoomDTO.getCustomerFullName())
                .customerEmail(bookingRoomDTO.getCustomerEmail())
                .customerPhoneNumber(bookingRoomDTO.getCustomerPhoneNumber())
                .customerCountry(bookingRoomDTO.getCustomerCountry())
                .status(bookingRoomDTO.getStatus())
                .specialRequest(bookingRoomDTO.getSpecialRequest())
                .arrivalTime(bookingRoomDTO.getArrivalTime())
                .user(user)
                .build();
        Long total = 0L;
        Long totalRoom = 0L;
        long days = ChronoUnit.DAYS.between(newBookingRoom.getCheck_in_date(), newBookingRoom.getCheck_out_date());
        List<BookedRoom> instanceBookedRoom = new ArrayList<>();
        for (BookedRoomDTO bookedRoomDTO : bookingRoomDTO.getBookedRooms()){
            Room room = roomRepository.findById(bookedRoomDTO.getRoom()).orElseThrow(()-> new Exception("Room not found"));
            BookedRoom bookedRoom = BookedRoom.builder()
                    .amount(bookedRoomDTO.getAmount())
                    .price_per(bookedRoomDTO.getAmount() * room.getPrice() * days)
                    .room(room)
                    .bookingRoom(newBookingRoom)
                    .build();
            instanceBookedRoom.add(bookedRoom);
            if(room.getAvailable_room() - bookedRoom.getAmount() >= 0){
                room.setAvailable_room(room.getAvailable_room() - bookedRoom.getAmount());
            }
            roomRepository.save(room);
            totalRoom += bookedRoom.getAmount();
            total += bookedRoom.getPrice_per();
        }
        newBookingRoom.setTotal_price(total);
        newBookingRoom.setTotal_rooms(totalRoom);
        newBookingRoom.setBookedRooms(instanceBookedRoom);
        bookingRoomRepository.save(newBookingRoom);
        BookingRoomResponse response = BookingRoomResponse.fromBooking(newBookingRoom);

        String initPaymentResponse = paymentService.createVnPayPaymentBooking(id, total, "room", request);
        return BookingRoomPaymentLinkResponse.builder()
                .bookingRoom(response)
                .payment(initPaymentResponse)
                .build();
    }


    public Page<BookingRoomResponse> getAllBookingRoom(String token, PageRequest pageRequest) throws Exception {
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new Exception("Token is expired!");
        }
        String email = jwtTokenUtil.extractEmail(token);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new Exception("User not found"));
        if(user.getRole().equals("ADMIN")) {
            Page<BookingRoom> bookingRoomPage = bookingRoomRepository.findAll(pageRequest);
            return bookingRoomPage.map(BookingRoomResponse::fromBooking);
        } else if (user.getRole().equals("USER")) {
            Page<BookingRoom> bookingRoomPage = bookingRoomRepository.findByUser(user, pageRequest);
            return bookingRoomPage.map(BookingRoomResponse::fromBooking);
        }
        return null;
    }

    public BookingRoom getBookingById(String id){
        return bookingRoomRepository.findById(id).orElseThrow(()-> new RuntimeException("Booking not found"));
    }

    public void deleteBookingRoom(String id) throws Exception {
        BookingRoom bookingRoom = bookingRoomRepository.findById(id).orElseThrow(()-> new Exception("Booking not found"));
        if(bookingRoom!= null){
            bookingRoom.setStatus("CANCELLED");
            bookingRoomRepository.save(bookingRoom);
        }
    }

    public BookingRoom updateStatusBooking(String id, BookingRoomDTO bookingRoomDTO) throws Exception {
        BookingRoom bookingRoom = bookingRoomRepository.findById(id).orElseThrow(()-> new Exception("Booking not found"));
        Set<String> validStatuses = Set.of("CONFIRMED", "WAITING", "REFUSED");
        if(!validStatuses.contains(bookingRoomDTO.getStatus().toUpperCase())){
            throw new Exception("Status not found");
        }
        bookingRoom.setStatus(bookingRoomDTO.getStatus());
        return bookingRoomRepository.save(bookingRoom);
    }

    private String generateUniqueBookingRoomId() {
        String uniqueId;
        boolean isUnique;

        do {
            uniqueId = generateRandomId();
            isUnique = !bookingRoomRepository.existsById(uniqueId);  // Kiểm tra xem ID đã tồn tại trong DB chưa
        } while (!isUnique);  // Nếu đã tồn tại, tiếp tục tạo lại ID mới

        return uniqueId;
    }

    // Hàm tạo ID ngẫu nhiên 20 ký tự
    private String generateRandomId() {
        // Sử dụng UUID để tạo ID ngẫu nhiên
        String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return randomId;
    }

    public Page<BookingRoomResponse> getBookingByUser(Long id, PageRequest pageRequest) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()-> new Exception("User not exist"));
        Page<BookingRoom> bookingRoomPage = bookingRoomRepository.findByUser(user, pageRequest);
        return bookingRoomPage.map(BookingRoomResponse::fromBooking);
    }

    public void setStatusAfterPayment(String id) throws Exception {
            BookingRoom existing = bookingRoomRepository.findById(id).orElseThrow(()-> new Exception("Booking not save in db yet!!"));
            existing.setStatus("1");
            bookingRoomRepository.save(existing);
    }

    public String getPaymentStatus(String id) throws Exception {
        BookingRoom bookingRoom = bookingRoomRepository.findById(id)
                .orElseThrow(()-> new Exception("Booking not save yet!!"));
        if(bookingRoom.getStatus().equals("1")) return "1";
        return "0";
    }
}
