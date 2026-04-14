package com.phegondev.InventoryManagementSystem.controller;

// Import DTO for creating purchase order
import com.phegondev.InventoryManagementSystem.dto.PurchaseOrderCreateRequest;

// Standard response wrapper
import com.phegondev.InventoryManagementSystem.dto.Response;

// Service layer for business logic
import com.phegondev.InventoryManagementSystem.service.PurchaseOrderService;

// For validation annotations like @Valid
import jakarta.validation.Valid;

// Lombok annotation to generate constructor automatically
import lombok.RequiredArgsConstructor;

// Spring imports
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController // Marks this class as REST API controller
@RequestMapping("/api/purchase-orders") // Base URL for all endpoints
@RequiredArgsConstructor // Generates constructor for final fields (dependency injection)
public class PurchaseOrderController {

    // Service dependency (injected via constructor)
    private final PurchaseOrderService purchaseOrderService;

    /**
     * GET API - Fetch all purchase orders
     * Optional filter by status (e.g., PENDING, APPROVED)
     * Accessible by multiple roles
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROCUREMENT_OFFICER', 'WAREHOUSE_MANAGER')")
    public ResponseEntity<Response> list(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(purchaseOrderService.listPurchaseOrders(status));
    }

    /**
     * GET API - Get summary of purchase orders
     * Example: total orders, approved count, pending count
     */
    @GetMapping("/summary")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROCUREMENT_OFFICER', 'WAREHOUSE_MANAGER')")
    public ResponseEntity<Response> summary() {
        return ResponseEntity.ok(purchaseOrderService.summary());
    }

    /**
     * GET API - Fetch single purchase order by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROCUREMENT_OFFICER', 'WAREHOUSE_MANAGER')")
    public ResponseEntity<Response> get(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrder(id));
    }

    /**
     * POST API - Create a new purchase order
     * @Valid ensures request body validation
     * Only ADMIN and PROCUREMENT_OFFICER can create
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROCUREMENT_OFFICER')")
    public ResponseEntity<Response> create(@RequestBody @Valid PurchaseOrderCreateRequest req) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(req));
    }

    /**
     * PUT API - Approve a purchase order
     * Only ADMIN can approve
     */
    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> approve(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.approvePurchaseOrder(id));
    }

    /**
     * PUT API - Mark purchase order as received
     * Usually done by warehouse after goods arrival
     */
    @PutMapping("/{id}/receive")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAREHOUSE_MANAGER')")
    public ResponseEntity<Response> receive(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.receivePurchaseOrder(id));
    }
}
