package com.svarks.trueherb.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svarks.trueherb.dao.service.UserProfileDao;
import com.svarks.trueherb.dao.service.UserServiceDao;
import com.svarks.trueherb.response.model.UsersProfileData;
import com.svarks.trueherb.service.IQueryService;

@Service
public class QueryServiceImpl implements IQueryService {

	@Autowired
	UserServiceDao userService;

	@Autowired
	UserProfileDao userProfileService;

	@Autowired
	EntityManagerFactory emf;

	private static final Logger log = LoggerFactory.getLogger(QueryServiceImpl.class);

	@Override
	public List<UsersProfileData> getUserInfoList() {

		EntityManager em = emf.createEntityManager();
		// em.getTransaction().begin( );

		String query1 = "select new com.svarks.trueherb.response.model.UsersProfileData  (d.profileId,s.emailId as emailId,s.username,s.utype, d.firstname,d.lastname,"
				+ "d.mobilenumber,d.referalCode as referalCode,d.companyname,s.verifiedDate) from UserEntity"
				+ " s inner join UserProfileEntity d on s.emailId=d.companyEmailId";

		Query query = em.createQuery(query1);
		@SuppressWarnings("unchecked")
		List<UsersProfileData> list = (List<UsersProfileData>) query.getResultList();
		System.out.println("******************************");
		System.out.println("****List==>" + list);
		System.out.println("******************************");
		log.info("Student Name result :{}", list);
		em.close();

		return list;
	}

}
