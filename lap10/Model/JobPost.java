package com.example.lap10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title should not be empty")
    @Size(min = 5,message = "title length must be more than 4 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    @NotEmpty(message = "description should not be empty")
    @Column(columnDefinition = "varchar(400) not null")
    private String description;

    @NotEmpty(message = "location should not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String location;

    @NotNull(message = "salary can't be null")
    @Positive(message = "enter valid salary")
    @Column(columnDefinition = "decimal not null")
    private Double salary;

    @Column(columnDefinition = "timestamp not null default current_timestamp")
    private LocalDate posting_date;


    //I added part because (current_timestamp) doesn't work it shows empty
    //but it's working fine with @PrePersist
    @PrePersist
    public void setCurrentDate() {
        if (posting_date == null) {
            posting_date = LocalDate.now();
        }}

}
