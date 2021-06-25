package com.svarks.trueherb.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.svarks.trueherb.response.model.ZohoTokenResponse;
import com.svarks.trueherb.rest.controller.UserController;

@Service
public class ZohoTokenAPI {
	
	private static final Logger log = LoggerFactory.getLogger(ZohoTokenAPI.class);

	private String zohoToken;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${zoho.token.api.generation.url}")
	private String zohoTokenGenerationURL;
	
	public String getZohoTokenFromApi() {
		if(zohoToken != null) {
			log.info("***Token already generated*****");
			return zohoToken;
		}else {
			zohoToken="Zoho-oauthtoken "+getToken();
			log.info("***********************************");
			log.info("***Setting the token value*****");
			log.info("***********************************");
		}
		return zohoToken;
	}

	public String getZohoToken() {
		return zohoToken;
	}


	public void setZohoToken(String zohoToken) {
		this.zohoToken = zohoToken;
	}

	private String getToken() {
		log.info("******************************************************************");
		log.info("Token Generation URL={}",zohoTokenGenerationURL);
		log.info("****************************************************************");
		String token="";
		try {
		ResponseEntity<ZohoTokenResponse> response1 =restTemplate.postForEntity(zohoTokenGenerationURL,null, ZohoTokenResponse.class);
		token=response1.getBody().getAccess_token();
		log.info("Token generation success");
		}catch(Exception e) {
			log.error("Token failed to retrieve Exception occured="+e);
		}
		return token;
	}
}
