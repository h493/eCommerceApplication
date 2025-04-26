package com.ecommerce.project.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at least 5 character")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be at least 5 character")
    private String buildingName;

    @NotBlank
    @Size(min = 4, message = "city name must be at least 4 character")
    private String city;

    @NotBlank
    @Size(min = 2, message = "state name must be at least 2 character")
    private String state;

    @NotBlank
    @Size(min = 2, message = "country name must be at least 2 character")
    private String country;

    @NotBlank
    @Size(min = 6, message = "PinCode name must be at least 6 character")
    private String pinCode;
}
