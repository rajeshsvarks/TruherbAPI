package com.svarks.trueherb.dao.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.Batch;

public interface BatchDao extends JpaRepository<Batch, Integer>{

	boolean isBatchExists(@Param("batchName") String batchName);
	Batch finByBatchName(@Param("batchName") String batchName );
}
