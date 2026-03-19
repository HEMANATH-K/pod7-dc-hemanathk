package com.donorconnect.donor.controller;

import com.donorconnect.donor.dto.request.DeferralRequest;
import com.donorconnect.donor.dto.response.DeferralResponse;
import com.donorconnect.donor.service.DeferralService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deferrals")
public class DeferralController {
    private final DeferralService service;

    public DeferralController(DeferralService service){
        this.service = service;
    }

    //Add deferral
    @PostMapping("/add")
    public DeferralResponse create(@RequestBody DeferralRequest request){

        return service.createDeferral(request);

    }

    //Retrieve by DonorId
    @GetMapping("/donor/{donorId}")
    public List<DeferralResponse> getByDonor(@PathVariable String donorId){

        return service.getDeferralsByDonor(donorId);
    }

    //Retrieve by active status
    @GetMapping("/active")
    public List<DeferralResponse> getActive(){

        return service.getActiveDeferrals();
    }

    //update the status to expire

    @PutMapping("/{id}/expire")
    public DeferralResponse expire(@PathVariable String id){

        return service.expireDeferral(id);
    }
}
