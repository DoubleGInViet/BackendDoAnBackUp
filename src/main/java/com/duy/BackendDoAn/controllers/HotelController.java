package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.HotelDTO;
import com.duy.BackendDoAn.dtos.HotelImageDTO;
import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.HotelImage;
import com.duy.BackendDoAn.responses.hotels.HotelListResponse;
import com.duy.BackendDoAn.responses.hotels.HotelResponse;
import com.duy.BackendDoAn.services.HotelService;
import com.duy.BackendDoAn.utils.FileUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/hotels")
@RestController
public class HotelController {

    private final HotelService hotelService;
    @PostMapping("")
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody HotelDTO hotelDTO, BindingResult result){
        HotelResponse hotelResponse = new HotelResponse();
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(hotelResponse);
        }
        try {
            Hotel hotel = hotelService.requestAddHotel(hotelDTO);
            hotelResponse = HotelResponse.fromHotel(hotel);
            return ResponseEntity.ok(hotelResponse);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(hotelResponse);
        }
    }

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(@PathVariable("id") long hotelId, @RequestParam("files") List<MultipartFile> files) {
        try {
            Hotel existingHotel = hotelService.getHotelById(hotelId);
            files = files == null ? new ArrayList<>() : files;
            if (files.size() > 5) {
                return ResponseEntity.badRequest().body("More than 5 images for a hotel");
            }

            List<HotelImage> hotelImages = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }

                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File size bigger than 10MB");
                }

                String imageUrl = FileUtils.uploadToFirebase(file);
                HotelImage hotelImage = hotelService.createHotelImage(existingHotel.getId(),
                        HotelImageDTO.builder().hotel(existingHotel.getId()).imageUrl(imageUrl).build());
                hotelImages.add(hotelImage);
            }
            return ResponseEntity.ok().body(hotelImages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelDTO hotelDTO) throws Exception {
        HotelResponse hotelResponse = new HotelResponse();
        Hotel hotel = hotelService.updateHotel(id, hotelDTO);
        hotelResponse = HotelResponse.fromHotel(hotel);
        return ResponseEntity.ok(hotelResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(
            @PathVariable("id") Long id
    ) {
        try {
            Hotel existingHotel = hotelService.getHotelById(id);
            return ResponseEntity.ok(HotelResponse.fromHotel(existingHotel));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<HotelListResponse> searchHotel(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam int noRooms,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10000") int limit
    ) {
        if (limit >= 10000) {
            limit = Integer.MAX_VALUE;
        }

        PageRequest pageRequest = PageRequest.of(
            page, limit, Sort.by("id").ascending()
        );

        Page<HotelResponse> hotelPage =hotelService.getAllHotels(keyword, noRooms, pageRequest);
        int totalPages =hotelPage.getTotalPages();
        List<HotelResponse> hotels = hotelPage.getContent();
        return ResponseEntity.ok(HotelListResponse.builder()
                .hotels(hotels)
                .totalPages(totalPages)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HotelResponse> deleteHotel(@PathVariable("id") long id){
        try {
            Hotel deactivedHotel = hotelService.deleteHotel(id);
            return ResponseEntity.ok(HotelResponse.fromHotel(deactivedHotel));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }


}
