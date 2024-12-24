package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.BookedRoomDTO;
import com.duy.BackendDoAn.dtos.BookedTicketDTO;
import com.duy.BackendDoAn.dtos.BookingTicketDTO;
import com.duy.BackendDoAn.models.*;
import com.duy.BackendDoAn.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingTicketService {
    private final UserRepository userRepository;
    private final TourScheduleRepository tourScheduleRepository;
    private final BookingTicketRepository bookingTicketRepository;
    private final TicketClassRepository ticketClassRepository;
    private final DailyTicketAvailabilityRepository dailyTicketAvailabilityRepository;


    public BookingTicket createBookingTicket(BookingTicketDTO bookingTicketDTO) throws Exception {
        User user = userRepository.findById(bookingTicketDTO.getUserId())
                .orElseThrow(()-> new Exception("User not found!!"));
        TourSchedule tourSchedule = tourScheduleRepository.findById(bookingTicketDTO.getTourScheduleId())
                .orElseThrow(()-> new Exception("Tour schedule not found!!"));
        String id = generateUniqueBookingTicketId();
        BookingTicket bookingTicket = BookingTicket.builder()
                .id(id)
                .customerFullName(bookingTicketDTO.getCustomerFullName())
                .customerEmail(bookingTicketDTO.getCustomerEmail())
                .customerPhoneNumber(bookingTicketDTO.getCustomerPhoneNumber())
                .customerCountry(bookingTicketDTO.getCustomerCountry())
                .tourSchedule(tourSchedule)
                .booking_date(LocalDate.now())
                .user(user)
                .status("0")
                .build();

        Long total_price = 0L;
        List<BookedTicket> instance = new ArrayList<>();
        for(BookedTicketDTO bookedTicketDTO : bookingTicketDTO.getBookedTickets()){
            TicketClass ticketClass = ticketClassRepository.findById(bookedTicketDTO.getTicketClassId())
                    .orElseThrow(()-> new Exception("Ticket class not found!!"));
            BookedTicket bookedTicket = BookedTicket.builder()
                    .quantity(bookedTicketDTO.getQuantity())
                    .priceWithQuantity(bookedTicketDTO.getQuantity() * ticketClass.getPrice())
                    .ticketClass(ticketClass)
                    .bookingTicket(bookingTicket)
                    .build();
            instance.add(bookedTicket);
            total_price += bookedTicket.getPriceWithQuantity();
            DailyTicketAvailability availability = dailyTicketAvailabilityRepository
                    .findByTourScheduleAndTicketClass(tourSchedule, ticketClass)
                    .orElseThrow(()-> new Exception("Daily ticket not found!!"));
            availability.setAvailableTicket(availability.getAvailableTicket() - bookedTicket.getQuantity());
            dailyTicketAvailabilityRepository.save(availability);
        }
        bookingTicket.setTotal_price(total_price);
        bookingTicket.setBookedTickets(instance);
        return bookingTicketRepository.save(bookingTicket);
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
}
