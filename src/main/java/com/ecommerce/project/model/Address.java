package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be atleast 5 character")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be atleast 5 character")
    private String buildingName;

    @NotBlank
    @Size(min = 4, message = "city name must be atleast 4 character")
    private String city;

    @NotBlank
    @Size(min = 2, message = "state name must be atleast 2 character")
    private String state;

    @NotBlank
    @Size(min = 2, message = "country name must be atleast 2 character")
    private String country;

    @NotBlank
    @Size(min = 6, message = "pincode name must be atleast 6 character")
    private String pinCode;

    public Address(String street, String buildingName, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pincode;
    }

    // managed by addresses
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();
}
