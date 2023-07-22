package com.coober.modal;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripBookingId;
    @NotNull(message = "Starting Location can not be null")
    @NotBlank(message = "Starting Location can not be Blank")
    private String fromLocation;
    @NotNull(message = "To Location can not be null")
    @NotBlank(message = "Ending Location can not be Blank")
    private String toLocation;

    @NotNull(message = "Starting Date and Time can not be null")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fromDateTime;

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull(message = "Ending Date and Time can not be null")
    private LocalDateTime toDateTime;

    private Boolean status;

//    @NotNull(message = "Distance can not be null")
    private Float distanceInKm;

    private Float bill;

    //RelationShip Start
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Driver driver;



}