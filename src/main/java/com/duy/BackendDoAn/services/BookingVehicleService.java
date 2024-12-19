package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.AccessoryBookingDTO;
import com.duy.BackendDoAn.dtos.bookingVehicleDTOs.BookingVehicleDTO;
import com.duy.BackendDoAn.dtos.bookingVehicleDTOs.DriverInfoDTO;
import com.duy.BackendDoAn.models.*;
import com.duy.BackendDoAn.repositories.*;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomResponse;
import com.duy.BackendDoAn.responses.bookingVehicles.BookingVehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingVehicleService {
    private final BookingVehicleRepository bookingVehicleRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VehicleRentalFacilityRepository vehicleRentalFacilityRepository;
    private final AccessoryRepository accessoryRepository;
    private final AccessoryBookingRepository accessoryBookingRepository;
    private final RentalFacilityRepository rentalFacilityRepository;
    public BookingVehicle createBooking(BookingVehicleDTO bookingVehicleDTO) throws Exception {

        VehicleRentalFacility vehicleRentalFacility = vehicleRentalFacilityRepository.findByVehicle_IdAndRentalFacility_Id(bookingVehicleDTO.getVehicle(), bookingVehicleDTO.getRental()).orElseThrow(()->new Exception("Rental vahicle not found!"));

        User user = userRepository.findById(bookingVehicleDTO.getUser()).orElseThrow(()-> new Exception("User not found"));
        BookingVehicle bookingVehicle = BookingVehicle.builder()
                .id(generateUniqueBookingVehicleId())
                .booking_date(LocalDate.now())
                .start_date(transform(bookingVehicleDTO.getPickup().getDate()))
                .start_time(LocalTime.of(9, 0))
                .start_address(bookingVehicleDTO.getPickup().getLocation())
                .return_date(transform(bookingVehicleDTO.getReturn_place().getDate()))
                .return_time(LocalTime.of(9, 0))
                .return_address(bookingVehicleDTO.getReturn_place().getLocation())
                .customerFullName(bookingVehicleDTO.getCustomerInfoDTO().getFullName())
                .customerCountry(bookingVehicleDTO.getCustomerInfoDTO().getCountry())
                .customerEmail(bookingVehicleDTO.getCustomerInfoDTO().getEmail())
                .customerPhoneNumber(bookingVehicleDTO.getCustomerInfoDTO().getPhone())
                .vehicleRentalFacility(vehicleRentalFacility)
                .user(user)
                .build();
        Long total = 0L;
        Long total_service = 0L;

        LocalDateTime startDateTime = LocalDateTime.of(bookingVehicle.getStart_date(), bookingVehicle.getStart_time());
        LocalDateTime returnDateTime = LocalDateTime.of(bookingVehicle.getReturn_date(), bookingVehicle.getReturn_time());
        total += vehicleRentalFacility.getPrice() * (ChronoUnit.DAYS.between(bookingVehicle.getStart_date(), bookingVehicle.getReturn_date()) +1 );

        List<AccessoryBooking> accessoryBookingList = new ArrayList<>();
        for(AccessoryBookingDTO accessoryBookingDTO: bookingVehicleDTO.getAccessoryBookings()){
            Accessory accessory = accessoryRepository.findById(accessoryBookingDTO.getAccessory()).orElseThrow(()-> new Exception("Accesssory not found"));
            AccessoryBooking accessoryBooking = AccessoryBooking.builder()
                    .amount(accessoryBookingDTO.getAmount())
                    .price_per(accessory.getPrice())
                    .accessory(accessory)
                    .bookingVehicle(bookingVehicle)
                    .build();
            total += accessoryBooking.getPrice_per();
            total_service += accessoryBooking.getPrice_per();
            accessoryBookingList.add(accessoryBooking);
        }
        bookingVehicle.setTotal_price(total);
        bookingVehicle.setTotalService(total_service);

        List<AdditionDriver> additionDriverList = new ArrayList<>();
        for (DriverInfoDTO driverInfoDTO : bookingVehicleDTO.getDriverInfoDTOS()) {
            AdditionDriver additionDriver = AdditionDriver.builder()
                    .title(driverInfoDTO.getTitle())
                    .fullName(driverInfoDTO.getFullName())
                    .phone(driverInfoDTO.getPhone())
                    .bookingVehicle(bookingVehicle)
                    .build();
            additionDriverList.add(additionDriver);
        }
        bookingVehicle.setAccessoryBookings(accessoryBookingList);
        bookingVehicle.setAdditionDrivers(additionDriverList);
        bookingVehicleRepository.save(bookingVehicle);
//        accessoryBookingRepository.saveAll(accessoryBookingList);
        return bookingVehicle;
    }

    private String generateUniqueBookingVehicleId() {
        String uniqueId;
        boolean isUnique;

        do {
            uniqueId = generateRandomId();
            isUnique = !bookingVehicleRepository.existsById(uniqueId);  // Kiểm tra xem ID đã tồn tại trong DB chưa
        } while (!isUnique);  // Nếu đã tồn tại, tiếp tục tạo lại ID mới

        return uniqueId;
    }

    // Hàm tạo ID ngẫu nhiên 20 ký tự
    private String generateRandomId() {
        // Sử dụng UUID để tạo ID ngẫu nhiên
        String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return randomId;
    }

    public static LocalDate transform(String dateString) {
        try {
            // Tạo DateTimeFormatter với định dạng mong muốn
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Chuyển đổi String sang LocalDate
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi: Không thể chuyển đổi chuỗi thành LocalDate. " +
                    "Vui lòng kiểm tra định dạng đầu vào.");
            return null; // Trả về null khi xảy ra lỗi
        }

    }

    public BookingVehicle getBookingById(String id) {
        try {
            return bookingVehicleRepository.findById(id).orElseThrow(()-> new Exception("There is no booking has that id!!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Page<BookingVehicleResponse> getBookingByUser(Long id, PageRequest pageRequest) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()-> new Exception("User not exist"));
        Page<BookingVehicle> bookingVehiclePage = bookingVehicleRepository.findByUser(user, pageRequest);
        return bookingVehiclePage.map(BookingVehicleResponse::fromBooking);
    }
}
