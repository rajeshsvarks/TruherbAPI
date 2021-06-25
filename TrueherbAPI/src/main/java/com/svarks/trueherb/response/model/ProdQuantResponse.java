package com.svarks.trueherb.response.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.Batch;
import com.svarks.trueherb.dao.entity.Products;
import com.svarks.trueherb.dao.entity.Warehouse;

public class ProdQuantResponse extends BaseResponse{
	
	private List<Warehouse> warhouseList =new LinkedList<>();
	private List<Products> productList=new LinkedList<>();
	private List<Batch> batchList =new LinkedList<>();
	
	
	public List<Batch> getBatchList() {
		return batchList;
	}

	public void setBatchList(List<Batch> batchList) {
		this.batchList = batchList;
	}

	public List<Warehouse> getWarhouseList() {
		return warhouseList;
	}

	public void setWarhouseList(List<Warehouse> warhouseList) {
		this.warhouseList = warhouseList;
	}

	public List<Products> getProductList() {
		return productList;
	}

	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	

}
