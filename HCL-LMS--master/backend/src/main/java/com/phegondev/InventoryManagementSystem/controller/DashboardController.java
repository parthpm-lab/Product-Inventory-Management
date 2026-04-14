package com.phegondev.InventoryManagementSystem.controller;

import com.phegondev.InventoryManagementSystem.dto.Response;
import com.phegondev.InventoryManagementSystem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller for handling dashboard-related API endpoints, primarily focused on providing aggregated data and summaries for the main dashboard view
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    //API for dashboard summary
    @GetMapping("/summary")
    public ResponseEntity<Response> summary() {
        return ResponseEntity.ok(dashboardService.getSummary());
    }
}
