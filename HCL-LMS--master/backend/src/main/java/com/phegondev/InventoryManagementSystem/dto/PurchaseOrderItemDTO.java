package com.phegondev.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) for Purchase Order Item
 *
 * This class represents individual items within a purchase order.
 * Each purchase order can have multiple items.
 *
 * Purpose:
 * - Transfer item-level data between client and server
 * - Avoid exposing internal entity structure
 */
@Data // Generates getters, setters, toString, equals, hashCode
@Builder // Enables builder pattern for easy object creation
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Constructor with all fields
@JsonInclude(JsonInclude.Include.NON_NULL)
// Only include non-null fields in JSON response

public class PurchaseOrderItemDTO {

    // Unique identifier for the purchase order item
    private Long id;

    // ID of the product being ordered
    private Long productId;

    // Name of the product (used for display in response)
    private String productName;

    // Quantity of the product ordered
    private Integer quantity;

    // Price per unit of the product
    private BigDecimal unitPrice;

    /**
     * Total cost for this item
     * Calculated as: quantity × unitPrice
     * Usually computed in Service layer (not provided by user)
     */
    private BigDecimal lineTotal;
}
