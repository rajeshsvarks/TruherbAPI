package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svarks.trueherb.dao.entity.ContactUSEntity;

public interface ContactUSDao extends JpaRepository<ContactUSEntity, Integer>{

//	boolean isBatchExists(@Param("batchName") String batchName);
//	Batch finByBatchName(@Param("batchName") String batchName );
}
