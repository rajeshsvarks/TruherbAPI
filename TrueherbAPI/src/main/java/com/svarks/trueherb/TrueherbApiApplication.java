package com.svarks.trueherb;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Region;
import com.svarks.trueherb.common.TrueherbConstants;

@SpringBootApplication
@ComponentScan(TrueherbConstants.BASE_PACKAGE_NAME)
@EntityScan(TrueherbConstants.ENTITY_PACKAGE)
@EnableJpaRepositories(TrueherbConstants.BASE_PACKAGE_NAME)

public class TrueherbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrueherbApiApplication.class, args);
		
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

	    return builder.setConnectTimeout(Duration.ofMillis(300000))
	     .setReadTimeout(Duration.ofMillis(300000)).build();
	}
	
	@Bean("s3Client")
	public AmazonS3 s3Client() {
		try {
			return AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
		} catch (AmazonS3Exception e) {
			System.out.println("Failed to connect to s3 ");
			throw e;
		}
	}

}
