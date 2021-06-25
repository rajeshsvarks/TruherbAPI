package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svarks.trueherb.dao.entity.ProductsFileInfo;

@Repository
public interface ProductsFileDao extends JpaRepository<ProductsFileInfo, Integer>{

}
