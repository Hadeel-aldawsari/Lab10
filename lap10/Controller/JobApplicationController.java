package com.example.lap10.Controller;


import com.example.lap10.ApiResponse.ApiResponse;
import com.example.lap10.Model.JobApplication;
import com.example.lap10.Service.JopApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-application")
public class JobApplicationController {
    private final JopApplicationService jopApplicationService;


    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(jopApplicationService.getAll());
    }

    @PostMapping("/apply")
    public ResponseEntity ApplyForJob(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        if(jopApplicationService.ApplyForJob(jobApplication))
            return ResponseEntity.status(200).body(new ApiResponse("submission successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("user id or job post id not found"));
    }



@DeleteMapping("/withdraw/{id}")
public ResponseEntity WithdrawJobApplication(@PathVariable Integer id){
        if(jopApplicationService.WithdrawJobApplication(id))
        return ResponseEntity.status(200).body(new ApiResponse("Withdraw Job Application with id:"+id+" successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Not found Job Application with id:"+id));
}

}
