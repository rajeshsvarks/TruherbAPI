package com.svarks.trueherb.request.model;

public class ReviewEnquiryReqeust {
	
	private String username;
	private String productName;
	private String quantity;
	private String location;
	private String warehouseName;
	private String warehouseLocation;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseLocation() {
		return warehouseLocation;
	}
	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}
	@Override
	public String toString() {
		return "ReviewEnquiryReqeust [username=" + username + ", productName=" + productName + ", quantity=" + quantity
				+ ", location=" + location + ", warehouseName=" + warehouseName + ", warehouseLocation="
				+ warehouseLocation + "]";
	}
}
