package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.models.Attraction;
import com.duy.BackendDoAn.repositories.AttractionRepository;
import com.duy.BackendDoAn.responses.AttractionOfficeResponse;
import com.duy.BackendDoAn.responses.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public List<AttractionOfficeResponse> searchOffice(Long city, Long rental) {
        List<Attraction> result = attractionRepository.findAttractionsByCityAndRentalFacilityId(city, rental);
        return result.stream().map(AttractionOfficeResponse::fromAttractionOffice).collect(Collectors.toList());
    }

    public List<AttractionResponse> searchUnOffice(Long city){
        List<Attraction> result = attractionRepository.findAttractionsByCityAndExcludeTypeOffice(city);
        return result.stream().map(AttractionResponse::fromAttraction).collect(Collectors.toList());
    }
}
