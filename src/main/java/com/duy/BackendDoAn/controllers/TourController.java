package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.TourDTO;
import com.duy.BackendDoAn.dtos.TourImageDTO;
import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.models.TourImage;
import com.duy.BackendDoAn.responses.tours.TourListResponse;
import com.duy.BackendDoAn.responses.tours.TourResponse;
import com.duy.BackendDoAn.services.TourService;
import com.duy.BackendDoAn.utils.FileUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/tours")
@RestController
public class TourController {
    private final TourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse> createTour(@Valid @RequestBody TourDTO tourDTO) throws Exception {
        Tour tour = tourService.addTour(tourDTO);
        TourResponse tourResponse = TourResponse.fromTour(tour);
        return ResponseEntity.ok(tourResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourResponse> updateTour(@PathVariable("id") long tourId, @Valid @RequestBody TourDTO tourDTO) throws Exception {
        Tour updatedTour = tourService.updateTour(tourId, tourDTO);
        TourResponse tourResponse = TourResponse.fromTour(updatedTour);
        return ResponseEntity.ok(tourResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourResponse> getTourById(@PathVariable("id") long tourId) throws Exception {
        Tour tour = tourService.getTourById(tourId);
        return ResponseEntity.ok(TourResponse.fromTour(tour));
    }

    @GetMapping
    public ResponseEntity<TourListResponse> searchTour(
            @RequestParam(defaultValue = "") String location,
            @RequestParam String date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100000") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("id").ascending()
        );

        LocalDate formatDate = null;
        if(date != null && !date.equals("")){
            DateTimeFormatter dateTimeFormatter = (DateTimeFormatter.ISO_LOCAL_DATE);
            formatDate = LocalDate.parse(date, dateTimeFormatter);
        }

        Page<TourResponse> tourPages = tourService.getAllTours(location, formatDate, pageRequest);
        List<TourResponse> responses = tourPages.getContent();
        int totalPages = tourPages.getTotalPages();
        return ResponseEntity.ok(TourListResponse.builder()
                .responses(responses)
                .totalPages(totalPages)
                .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourResponse> deleteTour(@PathVariable("id") long tourId) throws Exception {
        Tour deactivedTour = tourService.deleteTour(tourId);
        return ResponseEntity.ok(TourResponse.fromTour(deactivedTour));
    }

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(@PathVariable("id") long tourId, @RequestParam("files") List<MultipartFile> files) {
        try {
            Tour existingTour = tourService.getTourById(tourId);
            if (files.size() > 5) {
                return ResponseEntity.badRequest().body("More than 5 images for a hotel");
            }

            List<TourImage> tourImages = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }

                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File size bigger than 10MB");
                }

                String imageUrl = FileUtils.uploadToFirebase(file);
                TourImage tourImage = tourService.createTourImage(existingTour,
                        TourImageDTO.builder().tour(existingTour.getId()).imageUrl(imageUrl).build());
                tourImages.add(tourImage);
            }
            return ResponseEntity.ok().body(tourImages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
