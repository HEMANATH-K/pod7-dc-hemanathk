package com.donorconnect.donor.controller;

import com.donorconnect.donor.dto.request.ScreeningRequest;
import com.donorconnect.donor.dto.response.ScreeningResponse;
import com.donorconnect.donor.service.ScreeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    //Create Screening
    @PostMapping("/create")
    public ScreeningResponse createScreening(@RequestBody ScreeningRequest request) {
        return screeningService.createScreening(request);
    }

    //Retrieve by ScreeningId
    @GetMapping("/{screeningId}")
    public ScreeningResponse getScreening(@PathVariable String screeningId) {
        return screeningService.getScreening(screeningId);
    }

    //Retrieve by DonorId
    @GetMapping("/donor/{donorId}")
    public List<ScreeningResponse> getByDonor(@PathVariable String donorId){
        return screeningService.getScreeningsByDonorId(donorId);
    }

    //Update by ScreeningId
    @PutMapping("/{screeningId}")
    public ScreeningResponse updateScreening(@PathVariable String screeningId,
                                             @RequestBody ScreeningRequest request){
        return screeningService.updateScreening(screeningId,request);
    }
}
