package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.TourDTO;
import com.duy.BackendDoAn.dtos.TourImageDTO;
import com.duy.BackendDoAn.models.Attraction;
import com.duy.BackendDoAn.models.City;
import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.models.TourImage;
import com.duy.BackendDoAn.repositories.AttractionRepository;
import com.duy.BackendDoAn.repositories.CityRepository;
import com.duy.BackendDoAn.repositories.TourImageRepository;
import com.duy.BackendDoAn.repositories.TourRepository;
import com.duy.BackendDoAn.responses.tours.TourResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final CityRepository cityRepository;
    private final TourImageRepository tourImageRepository;
    private final AttractionRepository attractionRepository;
    public Tour addTour(TourDTO tourDTO) throws Exception {
        Attraction attraction = attractionRepository.findById(tourDTO.getAttraction()).orElseThrow(() -> new Exception("This attraction not found!!"));
        Tour tour = Tour.builder()
                .name(tourDTO.getName())
                .address(tourDTO.getAddress())
                .description(tourDTO.getDescription())
                .attraction(attraction)
                .active(true)
                .build();
        return tourRepository.save(tour);
    }

    public Tour getTourById(long id) throws Exception {
        return tourRepository.findById(id).orElseThrow(()-> new Exception("Tour not found"));
    }

    public TourImage createTourImage(Tour tour, TourImageDTO tourImageDTO) throws Exception {
        TourImage tourImage = TourImage.builder()
                .tour(tour)
                .image_url(tourImageDTO.getImageUrl())
                .build();
        return tourImageRepository.save(tourImage);
    }



    public Tour updateTour(long tourId, TourDTO tourDTO) throws Exception {
        Tour tour = getTourById(tourId);
        if(tour != null){
            Attraction attraction = attractionRepository.findById(tourDTO.getAttraction()).orElseThrow(() -> new Exception("This attraction not found!!"));
            if(tourDTO.getName() != null && !tourDTO.getName().isEmpty()){
                tour.setName(tourDTO.getName());
            }
            if(tourDTO.getAddress() != null && !tourDTO.getAddress().isEmpty()){
                tour.setAddress(tourDTO.getAddress());
            }
            if(tourDTO.getDescription() != null && !tourDTO.getDescription().isEmpty()){
                tour.setDescription(tourDTO.getDescription());
            }
            tour.setAttraction(attraction);
            return tourRepository.save(tour);
        }
        return null;
    }

    public Tour deleteTour(long id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isPresent()){
            Tour tour = optionalTour.get();
            tour.setActive(false);
            return tourRepository.save(tour);
        }
        return null;
    }

    public Page<TourResponse> getAllTours(String location, LocalDate date, PageRequest pageRequest){
        Page<Tour> tourPage;
        tourPage = tourRepository.searchTours(location, date, pageRequest);
        return tourPage.map(TourResponse::fromTour);
    }
}
