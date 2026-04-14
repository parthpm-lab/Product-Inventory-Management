package com.phegondev.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO (Data Transfer Object) for Purchase Order
 *
 * This class is used to transfer purchase order data between
 * client (API request/response) and server.
 *
 * It helps in:
 * - Hiding internal entity structure
 * - Controlling API response format
 * - Maintaining separation of concerns
 */
@Data // Generates getters, setters, toString, equals, hashCode
@Builder // Enables builder pattern for object creation
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Constructor with all fields
@JsonInclude(JsonInclude.Include.NON_NULL)
// Only include non-null fields in JSON response

public class PurchaseOrderDTO {

    // Unique identifier of purchase order
    private Long id;

    // Unique purchase order number (e.g., PO-1001)
    private String poNumber;

    // ID of the supplier associated with this order
    private Long supplierId;

    // Name of the supplier (used for display in response)
    private String supplierName;

    // Date by which the items are required
    private LocalDate requiredByDate;

    // Expected delivery date of the order
    private LocalDate expectedDeliveryDate;

    // Current status of purchase order (e.g., PENDING, APPROVED, RECEIVED)
    private String status;

    // Total monetary value of the purchase order
    private BigDecimal totalValue;

    // Priority level (e.g., LOW, MEDIUM, HIGH)
    private String priority;

    // Additional notes or remarks for the order
    private String notes;

    // Timestamp when the purchase order was created
    private LocalDateTime createdAt;

    // List of items included in this purchase order
    // Each item contains product details, quantity, price, etc.
    private List<PurchaseOrderItemDTO> items;
}
