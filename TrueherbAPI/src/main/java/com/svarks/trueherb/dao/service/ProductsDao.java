package com.svarks.trueherb.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svarks.trueherb.dao.entity.Products;


@Repository
public interface ProductsDao extends JpaRepository<Products, Integer>{
	
	
	boolean isProductExists(@Param("productName") String productName,@Param("productCode") String productCode  );
	List<Products> finByProductName(@Param("productName") String productName );
	

}
