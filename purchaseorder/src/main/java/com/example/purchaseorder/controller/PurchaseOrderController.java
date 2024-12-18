package com.example.purchaseorder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.purchaseorder.entity.PurchaseOrder;
import com.example.purchaseorder.service.IPurchaseOrderService;

@RestController("/main")
//@RequestMapping("/list")
public class PurchaseOrderController {
    @Autowired
    private IPurchaseOrderService service;

    @GetMapping("list")
    public ResponseEntity<?> listOrders(@RequestHeader("role") String role) {
        try {
            return ResponseEntity.ok(service.getOrdersByRole(role));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }
    
    @PostMapping("createorupdateorder")
	public ResponseEntity<?> createOrUpdateOrder(@RequestHeader("role") String role,
			@RequestHeader("username") String username, @RequestBody PurchaseOrder order) {
		if (!role.equals("PUBLISHER")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}
		order.setCreatedBy(username);
		return ResponseEntity.ok(service.createOrUpdateOrder(order, username));
	}

	@PatchMapping("revieworapproveorder")
	public ResponseEntity<?> reviewOrApproveOrder(@RequestHeader("role") String role,
			@RequestHeader("username") String username, @RequestBody Map<String, Object> payload) {
		try {
			String recordId = (String) payload.get("recordId");
			String action = (String) payload.get("action");
			List<String> comments = (List<String>) payload.get("reviewSummary");

			if (!role.equals("REVIEWER") && !role.equals("APPROVER")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
			}
			return ResponseEntity.ok(service.reviewOrApproveOrder(recordId, action, username, comments));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
		}
	}
}
