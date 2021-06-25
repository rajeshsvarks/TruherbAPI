package com.svarks.trueherb.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svarks.trueherb.dao.entity.Warehouse;


@Repository
public interface WarehouseDao extends JpaRepository<Warehouse, Integer>{

	boolean findByWarehouse(@Param("warehouseName") String warehouseName );
	List<Warehouse> getWarehouseByName(@Param("warehouseName") String warehouseName );
}
