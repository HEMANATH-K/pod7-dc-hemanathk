package com.donorconnect.donor.controller;

import com.donorconnect.donor.dto.request.DonorRequest;
import com.donorconnect.donor.dto.response.DonorResponse;
import com.donorconnect.donor.enums.DonorStatus;
import com.donorconnect.donor.service.DonorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {
    private final DonorService donorService;

    public DonorController(DonorService donorService){
        this.donorService = donorService;
    }
    // Register new donor
    @PostMapping("/add")
    public DonorResponse createDonor(@RequestBody DonorRequest request){
        return donorService.createDonor(request);
    }

    //Retrieve All Donor
    @GetMapping
    public List<DonorResponse> getAllDonors(){
        return donorService.getAllDonors();
    }

    //Retrieve Donor by donorId
    @GetMapping("/search/{donorId}")
    public DonorResponse getDonor(@PathVariable String donorId){
        return donorService.getDonor(donorId);
    }

    //Retrieve Donor by donorName
    @GetMapping("/search")
    public List<DonorResponse> searchDonors(@RequestParam String name){
        return donorService.searchDonorsByName(name);
    }

    //Update Donor by donorId
    @PutMapping("/{donorId}")
    public DonorResponse updateDonor(@PathVariable String donorId,@RequestBody DonorRequest request){
        return donorService.updateDonor(donorId,request);
    }

    //Delete Donor by donorId
    @DeleteMapping("/{donorId}")
    public void deleteDonor(@PathVariable String donorId){
        donorService.deleteDonor(donorId);
    }

    //Merge the Donor with 2 diff donorId and update Inactive status to duplicate
    @PostMapping("/merge")
    public DonorResponse mergeDonors(@RequestParam String sourceDonorId, @RequestParam String targetDonorId){
        return donorService.mergeDonors(sourceDonorId,targetDonorId);
    }

    //Retrieve Donors by bloodGroup
    @GetMapping("/blood-group/{bloodGroup}")
    public List<DonorResponse> getByBloodGroup(@PathVariable String bloodGroup){
        return donorService.getDonorsByBloodGroup(bloodGroup);
    }

    //Retrieve Donors by status
    @GetMapping("/status/{status}")
    public List<DonorResponse> getByStatus(@PathVariable DonorStatus status){
        return donorService.getDonorsByStatus(status);
    }

}
