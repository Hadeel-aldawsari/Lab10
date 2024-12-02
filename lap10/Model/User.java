package com.example.lap10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role='job_seeker' or role='employer'")
@Check(constraints ="age > 21")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "user name should not be empty")
    @Size(min = 5,message = " name length must be more than 4 characters. ")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name should contain letters and spaces only")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @Email(message = "please enter valid email")
    @NotEmpty(message = "email should not be empty")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",message ="Password must contain at least one digit [0-9].\n" +
            "Password must contain at least one lowercase Latin character [a-z].\n" +
            "Password must contain at least one uppercase Latin character [A-Z].\n" +
            "Password must contain at least one special character like ! @ # & ( ).\n" +
            "Password must contain a length of at least 8 characters and a maximum of 20 characters." )
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotNull(message = "age should not be null")
    @Positive(message = "enter valid age")
    @Min(value = 22,message = "age Must be more than 21.")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "job_seeker|employer",message = "role should be employer or job_seeker")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;


}
