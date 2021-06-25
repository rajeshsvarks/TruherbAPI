package com.svarks.trueherb.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.ProductQuantity;

public interface ProductQuantityDao extends JpaRepository<ProductQuantity, Integer>{
	
	
	boolean isProductExists(@Param("productName") String productName,@Param("warehouseName") String warehouseName);
	List<ProductQuantity> finByProductName(@Param("productName") String productName );

}
