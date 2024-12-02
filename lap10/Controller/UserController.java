package com.example.lap10.Controller;


import com.example.lap10.ApiResponse.ApiResponse;
import com.example.lap10.Model.User;
import com.example.lap10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService userService  ;

  @GetMapping("/get-all")
  public ResponseEntity getAll(){
      return ResponseEntity.status(200).body(userService.getAll());
  }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.add(user);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id ,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(userService.update(id,user)){
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if(userService.delete(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

}
