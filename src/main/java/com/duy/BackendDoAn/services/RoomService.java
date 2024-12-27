package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.RoomDTO;
import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.Room;
import com.duy.BackendDoAn.repositories.HotelRepository;
import com.duy.BackendDoAn.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    public final HotelRepository hotelRepository;
    public final RoomRepository roomRepository;
    public Room addRoom(RoomDTO roomDTO) throws Exception{
        Hotel hotel = hotelRepository.findById(roomDTO.getHotel()).orElseThrow(() -> new Exception("Hotel ID not found"));
        Room newRoom = Room.builder()
                .name(roomDTO.getName())
                .type(roomDTO.getType())
                .room_size(roomDTO.getRoomSize())
                .max_guests(roomDTO.getMaxGuests())
                .typeBed1(roomDTO.getTypeBed1())
                .noBed1(roomDTO.getNoBed1())
                .typeBed2(roomDTO.getTypeBed2())
                .noBed2(roomDTO.getNoBed2())
                .price(roomDTO.getPrice())
                .available_room(roomDTO.getAvailableRooms())
                .active(true)
                .hotel(hotel)
                .build();
        return roomRepository.save(newRoom);
    }

    public Room getRoomById(long id) throws Exception{
        return roomRepository.findById(id).orElseThrow(() -> new Exception("Can't find room with ID = "+id));
    }

    public Room updateRoom(Long id, RoomDTO roomDTO) throws Exception {
        // Tìm phòng hiện có theo ID
        Room existingRoom = getRoomById(id);
        if (existingRoom != null) {
            // Tìm khách sạn hiện có theo ID từ DTO
            Hotel existingHotel = hotelRepository.findById(roomDTO.getHotel())
                    .orElseThrow(() -> new Exception("Can't find hotel"));

            // Cập nhật thông tin khách sạn cho phòng
            existingRoom.setHotel(existingHotel);

            // Kiểm tra và cập nhật từng thuộc tính của Room
            if (roomDTO.getName() != null && !roomDTO.getName().isEmpty()) {
                existingRoom.setName(roomDTO.getName());
            }

            if (roomDTO.getType() != null && !roomDTO.getType().isEmpty()) {
                existingRoom.setType(roomDTO.getType());
            }

            if (roomDTO.getRoomSize() != null && roomDTO.getRoomSize() > 0) {
                existingRoom.setRoom_size(roomDTO.getRoomSize());
            }

            if (roomDTO.getMaxGuests() != null && roomDTO.getMaxGuests() > 0) {
                existingRoom.setMax_guests(roomDTO.getMaxGuests());
            }

            if (roomDTO.getTypeBed1() != null && !roomDTO.getTypeBed1().isEmpty()) {
                existingRoom.setTypeBed1(roomDTO.getTypeBed1());
            }

            if (roomDTO.getNoBed1() != null && roomDTO.getNoBed1() >= 0) {
                existingRoom.setNoBed1(roomDTO.getNoBed1());
            }

            if (roomDTO.getTypeBed2() != null && !roomDTO.getTypeBed2().isEmpty()) {
                existingRoom.setTypeBed2(roomDTO.getTypeBed2());
            }

            if (roomDTO.getNoBed2() != null && roomDTO.getNoBed2() >= 0) {
                existingRoom.setNoBed2(roomDTO.getNoBed2());
            }

            if (roomDTO.getPrice() != null && roomDTO.getPrice() >= 0) {
                existingRoom.setPrice(roomDTO.getPrice());
            }

            if (roomDTO.getAvailableRooms() != null && roomDTO.getAvailableRooms() >= 0) {
                existingRoom.setAvailable_room(roomDTO.getAvailableRooms());
            }

            // Lưu thay đổi vào cơ sở dữ liệu
            return roomRepository.save(existingRoom);
        } else {
            throw new Exception("Room not found");
        }
    }


    public void deleteRoom(long id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        optionalRoom.ifPresent(roomRepository::delete);
    }
}
