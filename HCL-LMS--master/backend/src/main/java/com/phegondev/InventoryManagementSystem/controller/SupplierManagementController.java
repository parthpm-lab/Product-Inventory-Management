package com.phegondev.InventoryManagementSystem.controller;

import com.phegondev.InventoryManagementSystem.dto.Response;
import com.phegondev.InventoryManagementSystem.dto.SupplierManagementDTO;
import com.phegondev.InventoryManagementSystem.service.SupplierManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers/management")
@RequiredArgsConstructor
// Controller for handling supplier management-related API endpoints, including CRUD operations for suppliers and providing summary data for the supplier management section of the application
public class SupplierManagementController {

    private final SupplierManagementService supplierManagementService;

    @GetMapping("/summary")
    public ResponseEntity<Response> summary() {
        return ResponseEntity.ok(supplierManagementService.summary());
    }

    @GetMapping("/all")
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(supplierManagementService.list());
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Response> get(@PathVariable Long supplierId) {
        return ResponseEntity.ok(supplierManagementService.get(supplierId));
    }

    @PostMapping("/upsert")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> upsert(@RequestBody @Valid SupplierManagementDTO dto) {
        return ResponseEntity.ok(supplierManagementService.upsert(dto));
    }
}

