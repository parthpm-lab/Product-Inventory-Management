package com.phegondev.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for Category
 *
 * This class is used to transfer category data between
 * client (API request/response) and server.
 *
 * NOTE:
 * DTO helps in hiding internal entity structure and ensures
 * clean separation between API layer and database layer.
 */
@Data // Generates getters, setters, toString, equals, hashCode automatically
@AllArgsConstructor // Generates constructor with all fields
@NoArgsConstructor  // Generates default constructor
@JsonInclude(JsonInclude.Include.NON_NULL) 
// Only include non-null fields in JSON response

@JsonIgnoreProperties(ignoreUnknown = true) 
// Ignores extra fields in incoming JSON request (prevents errors)

public class CategoryDTO {

    // Unique identifier of category
    private Long id;

    /**
     * Name of the category
     * Validation:
     * - Must not be blank (null or empty string not allowed)
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Number of products under this category
     * This is generally used for response purpose
     * (not required during creation)
     */
    private Long productCount;
}
