package com.phegondev.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO used for creating a new Purchase Order
 *
 * This class represents the request body sent by the client
 * when creating a purchase order.
 *
 * It includes validation to ensure correct and valid input data.
 */
@Data // Generates getters, setters, toString, equals, hashCode
@AllArgsConstructor // Constructor with all fields
@NoArgsConstructor  // Default constructor
@JsonIgnoreProperties(ignoreUnknown = true)
// Ignores unknown fields in incoming JSON (prevents errors)

public class PurchaseOrderCreateRequest {

    /**
     * Supplier ID from whom items are being ordered
     * Validation:
     * - Must not be null
     * - Must be a positive number
     */
    @NotNull
    @Positive
    private Long supplierId;

    /**
     * Date by which items are required
     * (optional field)
     */
    private LocalDate requiredByDate;

    /**
     * Expected delivery date from supplier
     * (optional field)
     */
    private LocalDate expectedDeliveryDate;

    /**
     * Priority of the order
     * Example values: NORMAL, HIGH, URGENT
     * Validation:
     * - Must not be null
     */
    @NotNull
    private String priority;

    /**
     * Additional notes or instructions for the purchase order
     * (optional field)
     */
    private String notes;

    /**
     * Product ID being ordered
     * Validation:
     * - Must not be null
     * - Must be positive
     */
    @NotNull
    @Positive
    private Long productId;

    /**
     * Quantity of product to be ordered
     * Validation:
     * - Must not be null
     * - Must be positive
     */
    @NotNull
    @Positive
    private Integer quantity;

    /**
     * Price per unit of product
     * Validation:
     * - Must not be null
     */
    @NotNull
    private BigDecimal unitPrice;
}
