package com.svarks.trueherb.response.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.ProductQuantity;

public class ProductQuantityResponse extends BaseResponse{
	
	private List<ProductQuantity> prodQuantityList =new LinkedList<>();

	public List<ProductQuantity> getProdQuantityList() {
		return prodQuantityList;
	}

	public void setProdQuantityList(List<ProductQuantity> prodQuantityList) {
		this.prodQuantityList = prodQuantityList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
