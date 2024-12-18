package com.example.purchaseorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.purchaseorder.constants.PurchaseOrderConstants;
import com.example.purchaseorder.entity.PurchaseOrder;
import com.example.purchaseorder.repo.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {
	@Autowired
	private PurchaseOrderRepository repository;

	@Override
	public List<PurchaseOrder> getOrdersByRole(String role) {
		switch (role) {
		
		case PurchaseOrderConstants.PUBLISHER:
			return repository.findByStatus("created");
		case PurchaseOrderConstants.REVIEWER:
			return repository.findByStatus("created");
		case PurchaseOrderConstants.APPROVER:
			return repository.findByStatus("reviewed");
		default:
			throw new IllegalArgumentException("Invalid role");
		}
	}

	@Override
	public PurchaseOrder createOrUpdateOrder(PurchaseOrder order, String username) {
		order.setUpdatedBy(username);
		return repository.save(order);
	}

	@Override
	public PurchaseOrder reviewOrApproveOrder(String recordId, String action, String username, List<String> comments) {
		PurchaseOrder order = repository.findById(recordId).orElseThrow(() -> new RuntimeException("Order not found"));
		if (action.equals("accept")) {
			order.setStatus(order.getStatus().equals("reviewed") ? "approved" : "reviewed");
		} else if (action.equals("reject")) {
			order.setStatus("rework");
		} else {
			throw new IllegalArgumentException("Invalid action");
		}
		order.setUpdatedBy(username);
		order.getReviewSummary().addAll(comments);
		return repository.save(order);
	}
}
