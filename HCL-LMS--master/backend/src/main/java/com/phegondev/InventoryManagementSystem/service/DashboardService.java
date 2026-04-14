package com.phegondev.InventoryManagementSystem.service;

import com.phegondev.InventoryManagementSystem.dto.Response;

// Service interface for dashboard-related operations, primarily focused on aggregating and summarizing data for the main dashboard view
public interface DashboardService {

    Response getSummary();
}
