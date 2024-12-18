package com.example.purchaseorder.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  



@Entity
public class PurchaseOrder {
	
	//@Id
    //@Column(name="recordId")
	// @EmbeddedId
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recordId;

	private String taskId;
	private double partsPrice;
	private String status; 
	private double labourPrice;
	private double amount;
	private String updatedBy;
	private String createdBy;
	

	@ElementCollection
	private List<String> reviewSummary = new ArrayList<>();

	

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public double getPartsPrice() {
		return partsPrice;
	}

	public void setPartsPrice(double partsPrice) {
		this.partsPrice = partsPrice;
	}

	public double getLabourPrice() {
		return labourPrice;
	}

	public void setLabourPrice(double labourPrice) {
		this.labourPrice = labourPrice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<String> getReviewSummary() {
		return reviewSummary;
	}

	public void setReviewSummary(List<String> reviewSummary) {
		this.reviewSummary = reviewSummary;
	}

}
