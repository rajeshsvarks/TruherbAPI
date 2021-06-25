package com.svarks.trueherb.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.LegalEntity;

public interface LegalEntityDao extends JpaRepository<LegalEntity, Integer>{
	
	boolean isLegalEntityExists(@Param("entityName") String entityName);
	List<LegalEntity> finByEntityName(@Param("entityName") String entityName );

}
