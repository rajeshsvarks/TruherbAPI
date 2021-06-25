package com.svarks.trueherb.request.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProductRequest {
	
	private int pid;
	private String name;
	private String productCode;
	private String description;
	private List<ProductFilesInfo> productFileInfoList;
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductFilesInfo> getProductFileInfoList() {
		return productFileInfoList;
	}

	public void setProductFileInfoList(List<ProductFilesInfo> productFileInfoList) {
		this.productFileInfoList = productFileInfoList;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
