package com.example.purchaseorder.service;

import java.util.List;

import com.example.purchaseorder.entity.PurchaseOrder;

public interface IPurchaseOrderService {

	public List<PurchaseOrder> getOrdersByRole(String role);

	public PurchaseOrder createOrUpdateOrder(PurchaseOrder order, String username);

	public PurchaseOrder reviewOrApproveOrder(String recordId, String action, String username, List<String> comments);

}
