package com.ecommerce.project.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    @NotBlank
    @Size(min = 3, message = "Product name must contain atleast 3 characters")
    private String productName;
    private String image;
    private Integer quantity;
    @NotBlank
    @Size(min = 6, message = "Product name must contain atleast 3 characters")
    private String description;
    private double price;
    private double discount;
    private double specialPrice;
}
