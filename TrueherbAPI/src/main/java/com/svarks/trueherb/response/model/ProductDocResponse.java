package com.svarks.trueherb.response.model;

import java.util.List;

public class ProductDocResponse extends BaseResponse{
	
	private String productName;
	private List<String> productUrlList;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<String> getProductUrlList() {
		return productUrlList;
	}
	public void setProductUrlList(List<String> productUrlList) {
		this.productUrlList = productUrlList;
	}
	@Override
	public String toString() {
		return "ProductDocResponse [productName=" + productName + ", productUrlList=" + productUrlList + "]";
	}
	

}
