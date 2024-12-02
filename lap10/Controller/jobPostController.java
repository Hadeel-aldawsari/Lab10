package com.example.lap10.Controller;

import com.example.lap10.ApiResponse.ApiResponse;
import com.example.lap10.Model.JobPost;
import com.example.lap10.Model.User;
import com.example.lap10.Service.JopPostService;
import com.example.lap10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-post")
public class jobPostController {
    private final JopPostService jopPostService  ;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(jopPostService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        jopPostService.add(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id , @RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(jopPostService.update(id,jobPost)){
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if(jopPostService.delete(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
}
