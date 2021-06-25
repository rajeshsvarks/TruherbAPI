package com.svarks.trueherb.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.svarks.trueherb.common.TrueherbConstants;
import com.svarks.trueherb.common.ZohoTokenAPI;
import com.svarks.trueherb.dao.entity.ContactUSEntity;
import com.svarks.trueherb.dao.entity.EnquiryEntity;
import com.svarks.trueherb.dao.entity.SubscribeEntity;
import com.svarks.trueherb.dao.entity.UserAuthInfo;
import com.svarks.trueherb.dao.entity.UserEntity;
import com.svarks.trueherb.dao.entity.UserProfileEntity;
import com.svarks.trueherb.dao.service.ContactUSDao;
import com.svarks.trueherb.dao.service.EnquiryDao;
import com.svarks.trueherb.dao.service.SubscribeDao;
import com.svarks.trueherb.dao.service.UserAuthDao;
import com.svarks.trueherb.dao.service.UserProfileDao;
import com.svarks.trueherb.dao.service.UserServiceDao;
import com.svarks.trueherb.request.model.ChangeEmailRequest;
import com.svarks.trueherb.request.model.ContactUSRequest;
import com.svarks.trueherb.request.model.ForgotPasswordRequest;
import com.svarks.trueherb.request.model.LoginRequest;
import com.svarks.trueherb.request.model.MailerRequest;
import com.svarks.trueherb.request.model.NewUserRequest;
import com.svarks.trueherb.request.model.ResendRequest;
import com.svarks.trueherb.request.model.ResetPasswordRequest;
import com.svarks.trueherb.request.model.ReviewEnquiryReqeust;
import com.svarks.trueherb.request.model.SubscribeRequest;
import com.svarks.trueherb.request.model.UpdatePasswordRequest;
import com.svarks.trueherb.response.model.BaseResponse;
import com.svarks.trueherb.response.model.ContactUSResponse;
import com.svarks.trueherb.response.model.EnquiryResponse;
import com.svarks.trueherb.response.model.LoginResponse;
import com.svarks.trueherb.response.model.NewUserResponse;
import com.svarks.trueherb.response.model.ProductDocResponse;
import com.svarks.trueherb.response.model.ProductListResponse;
import com.svarks.trueherb.response.model.SubscribeResponse;
import com.svarks.trueherb.response.model.UserProfileResponse;
import com.svarks.trueherb.response.model.ZohoTokenResponse;
import com.svarks.trueherb.service.TrueherbWareshouseService;

@RestController
//@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
public class UserController {
	
	
	@Autowired
	UserServiceDao userService;
	
	@Autowired
	UserAuthDao userAuthService;
	
	@Autowired
	UserProfileDao profileService;

	@Autowired
	TrueherbWareshouseService trueherbService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EnquiryDao enquiryDao;
	
	@Autowired
	ZohoTokenAPI zohoToken;
	
	@Autowired
	SubscribeDao subscribeService;
	
	@Autowired
	ContactUSDao contactService;
	
	@Autowired
   AmazonS3 s3Client;
	
	
	@Value("${zoho.token.api.generation.url}")
	private String zohoTokenGenerationURL;

	@Value("${zoho.product.api.base.url}")
	private String zohoProductApiURL;
	
	@Value("${zoho.product.item.api.base.url}")
	private String zohoProductItemApiURL;
	
	@Value("${zoho.get.warehouse.url}")
	private String zohoWarehouseURL;
	
	@Value("${zoho.get.document.url}")
	private String zohoDocumentURL;
	
	@Value("${zoho.get.category.prod.url}")
	private String zohoProductByCategoryURL;
	
	@Value("${zoho.get.category.url}")
	private String zohoCategoryURL;
	
	@Value("${aws.s3.bucket.base.url}")
    private String s3BaseURL;
	

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@GetMapping(value = "/ping", produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse testRestMessage() {
		
		BaseResponse response = new BaseResponse();
		try {
		response.setErrorMessage("No Error");
		response.setSuccessMessage("Trueherb warehouse...!Rest Controller working fine..!");
		response.setStatusMessage("success");
		response.setStatus(200);
		//response.setErrorMessage(s3Client.listBuckets());
	
		//sendMail();
		}catch(Exception e) {
			log.error("Exception while sending mail:"+e);
		}
		return response;
	}
	
	
	@GetMapping(value = TrueherbConstants.PRODUCT_API_ZOHO, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<String> getAllProductByZoho() {
		
		ProductListResponse response = new ProductListResponse();
		ResponseEntity<String> res=null;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Access-Control-Allow-Origin", "*");
			// String token= "Zoho-oauthtoken "+getToken();
			 headers.set("Authorization", zohoToken.getZohoTokenFromApi());

			for (int i = 1; i <= 2; i++) {
				try {
					HttpEntity entity = new HttpEntity(headers);
					log.info("Calling zoho api to fetch product info");
					res = restTemplate.exchange(zohoProductApiURL, HttpMethod.GET, entity,String.class);
					
					//String response1 = restTemplate.getForObject(zohoProductApiURL, String.class);
					//response.setZohoResponse(response1);
					log.info("zoho product response={}" + res);
					break;
				} catch (Exception e) {
				log.error("Exception Occured while invoking zoho API to get product details" + e);
					headers = new HttpHeaders();
					headers.set("Access-Control-Allow-Origin", "*");
					String token1 = "Zoho-oauthtoken " + getToken();
					zohoToken.setZohoToken(token1);
					headers.set("Authorization", token1);
				}
			}
			response.setStatusMessage("success");
			response.setStatus(200);
		return res;
	}
	
	@GetMapping(value = TrueherbConstants.PRODUCT_API_BY_ITEM_ZOHO, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<String> getAllProductByItemZoho(@RequestParam("itemId") String itemId) {
		ProductListResponse response = new ProductListResponse();
		ResponseEntity<String> res=null;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Access-Control-Allow-Origin", "*");
			headers.set("Authorization", zohoToken.getZohoTokenFromApi());

			for (int i = 1; i <= 2; i++) {
				try {
					HttpEntity entity = new HttpEntity(headers);
					String DynamicUrl=zohoProductItemApiURL+itemId+"?organization_id=60008012515";
					res = restTemplate.exchange(DynamicUrl, HttpMethod.GET, entity,String.class);
					log.info("zoho product response={}" + res);
					break;
				} catch (Exception e) {
				log.error("Exception Occured while invoking zoho API" + e);
					headers = new HttpHeaders();
					headers.set("Access-Control-Allow-Origin", "*");
					String token1 = "Zoho-oauthtoken " + getToken();
					zohoToken.setZohoToken(token1);
					headers.set("Authorization", token1);
				}
			}
			response.setStatusMessage("success");
			response.setStatus(200);
		return res;
	}
	
	@GetMapping(value = TrueherbConstants.GET_ALL_WAREHOUSE, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<String> getAllWarehouse() {
		ProductListResponse response = new ProductListResponse();
		ResponseEntity<String> res=null;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Access-Control-Allow-Origin", "*");
			headers.set("Authorization", zohoToken.getZohoTokenFromApi());
			
			for (int i = 1; i <= 2; i++) {
				try {
					HttpEntity entity = new HttpEntity(headers);
					res = restTemplate.exchange(zohoWarehouseURL, HttpMethod.GET, entity,String.class);
					
					log.info("zoho product response={}" + res);
					break;
				} catch (Exception e) {
				log.error("Exception Occured while invoking zoho API" + e);
					headers = new HttpHeaders();
					headers.set("Access-Control-Allow-Origin", "*");
					String token1 = "Zoho-oauthtoken " + getToken();
					zohoToken.setZohoToken(token1);
					headers.set("Authorization", token1);
				}
			}
			response.setStatusMessage("success");
			response.setStatus(200);
		return res;
	}
	
	
	@GetMapping(value = TrueherbConstants.GET_ALL_CATEGORY, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<String> getAllCategoryData() {
		ResponseEntity<String> res=null;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Access-Control-Allow-Origin", "*");
			headers.set("Authorization", zohoToken.getZohoTokenFromApi());
			
			for (int i = 1; i <= 2; i++) {
				try {
					HttpEntity entity = new HttpEntity(headers);
					res = restTemplate.exchange(zohoCategoryURL, HttpMethod.GET, entity,String.class);
					
					log.info("zoho product response={}" + res);
					break;
				} catch (Exception e) {
				log.error("Exception Occured while invoking zoho API" + e);
					headers = new HttpHeaders();
					headers.set("Access-Control-Allow-Origin", "*");
					String token1 = "Zoho-oauthtoken " + getToken();
					zohoToken.setZohoToken(token1);
					headers.set("Authorization", token1);
				}
			}
		return res;
	}
	
	
	@GetMapping(value = TrueherbConstants.GET_PRODUCT_BY_CATEGORY, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<String> getAllProductByCategoryData(@RequestParam("categoryName") String categoryName) {
		ResponseEntity<String> res=null;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Access-Control-Allow-Origin", "*");
			headers.set("Authorization", zohoToken.getZohoTokenFromApi());
			
			for (int i = 1; i <= 2; i++) {
				try {
					HttpEntity entity = new HttpEntity(headers);
					String url=zohoProductByCategoryURL+categoryName+"&organization_id=60008012515";
					res = restTemplate.exchange(url, HttpMethod.GET, entity,String.class);
					
					log.info("zoho product response={}" + res);
					break;
				} catch (Exception e) {
				log.error("Exception Occured while invoking zoho API" + e);
					headers = new HttpHeaders();
					headers.set("Access-Control-Allow-Origin", "*");
					String token1 = "Zoho-oauthtoken " + getToken();
					zohoToken.setZohoToken(token1);
					headers.set("Authorization", token1);
				}
			}
		return res;
	}
	
	
	@GetMapping(value = TrueherbConstants.PRODUCT_IMAGE_API_BY_ITEM_ZOHO, produces = "text/plain")
	public ResponseEntity<byte[]> getProductImageByItemZoho(@RequestParam("itemId") String itemId) {

		String fooResourceUrl = zohoProductItemApiURL+itemId+"/image"; //https://inventory.zoho.in/api/v1/items/494445000000036061/image";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Access-Control-Allow-Origin", "*");
		headers.set("Authorization", zohoToken.getZohoTokenFromApi());		
		
		
		for(int i=1;i<=2;i++) {
		try {
			HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<byte[]> response1 = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, entity, byte[].class);
		//Files.write(Paths.get("image.jpg"), response1.getBody());
		return response1;
		}catch(Exception e) {
			System.out.println("Exception Occured while invoking zoho API"+e);
			 headers = new HttpHeaders();
				headers.set("Access-Control-Allow-Origin", "*");
				String token1= "Zoho-oauthtoken "+getToken();
				zohoToken.setZohoToken(token1);
				headers.set("Authorization", zohoToken.getZohoToken());
		}
	}
		return null;
	}
	
	
	@GetMapping(value = TrueherbConstants.GET_DOCUMENT_INFO, produces = "text/plain")
	public ResponseEntity<byte[]> getDocumentInfo(@RequestParam("documentId") String documentId) {
		ResponseEntity<byte[]> res=null;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Access-Control-Allow-Origin", "*");
			headers.set("Authorization", zohoToken.getZohoTokenFromApi());
			for(int i=1;i<=2;i++) {
			try {
			
					HttpEntity entity = new HttpEntity(headers);
					zohoDocumentURL=zohoDocumentURL+documentId+"?organization_id=60008012515";
					res = restTemplate.exchange(zohoDocumentURL, HttpMethod.GET, entity,byte[].class);
				//	Files.write(Paths.get("image.jpg"), res.getBody());
					//Path path = Paths.get(file.getAbsolutePath());
				//	ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Files.write(Paths.get("image"), res.getBody())));
					
					HttpHeaders header = new HttpHeaders();
					header.add(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=" + documentId);
					header.add("Cache-Control", "no-cache, no-store, must-revalidate");
					header.add("Pragma", "no-cache");
					header.add("Expires", "0");
					return res; // ResponseEntity.ok().headers(header).contentLength(res.getBody().length)
							//.contentType(MediaType.parseMediaType("text/plain")).body(res.getBody());
				}catch(BadRequest e) {
					log.error("Exception Occured while invoking zoho API" + e);
				}catch (Exception e) {
				log.error("Exception Occured while invoking zoho API" + e);
				headers = new HttpHeaders();
				headers.set("Access-Control-Allow-Origin", "*");
				String token1= "Zoho-oauthtoken "+getToken();
				zohoToken.setZohoToken(token1);
				headers.set("Authorization", zohoToken.getZohoToken());
				}
			}
		return null;
	}
	
	
	
	/**
	 * Method is used to add and update warehouse information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.ENQUIRY_API, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse reviewEnquiry(@RequestBody List<ReviewEnquiryReqeust> warehouseReq) {
		BaseResponse res = new BaseResponse();
		try {
			
			if(warehouseReq != null && warehouseReq.size() !=0) {
				log.info("Review Enquiry==>"+warehouseReq.size());
				for(ReviewEnquiryReqeust rev:warehouseReq) {
					EnquiryEntity enquiryEntity = new EnquiryEntity();
					enquiryEntity.setCreatedDate(new Date());
					enquiryEntity.setModifiedDate(new Date());
					enquiryEntity.setLocation(rev.getLocation());
					enquiryEntity.setProductName(rev.getProductName());
					enquiryEntity.setQuantity(rev.getQuantity());
					enquiryEntity.setUsername(rev.getUsername());
					enquiryEntity.setWarehouseLocation(rev.getWarehouseLocation());
					enquiryEntity.setWarehouseName(rev.getWarehouseName());
					enquiryDao.save(enquiryEntity);
					res.setStatus(TrueherbConstants.SUCCESS_STATUS);
	            	res.setStatusMessage(TrueherbConstants.ADDED_SUCCESSFULLY);
				}
               }else {
            	   res.setErrorMessage("Invalid payload");
            	   res.setStatus(200);
            	   res.setStatusMessage("error");
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating warehouse details:",e);
		}
		return res;
	}
	
	
	@GetMapping(value = TrueherbConstants.GET_ENQUIRY_INFO, produces = TrueherbConstants.APPLICATION_JSON)
	public EnquiryResponse getEnquiryList() {
		EnquiryResponse res = new EnquiryResponse();
				try {
					 res.setEnquiryList(enquiryDao.findAll());
					 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
		            	res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
				} catch (Exception e) {
				log.error("Exception Occured while invoking zoho API" + e);
				}
		return res;
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
	
	
	
	
	
	/**
	 * Method is used to check whether the given user is valid or not
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.VALIDATE_USER_API, produces = TrueherbConstants.APPLICATION_JSON)
	public LoginResponse validateUser(@RequestBody LoginRequest user) {
		LoginResponse res = new LoginResponse();
		try {
			log.info("username==>"+user.getUsername());
			UserEntity userentity = null;
			if(user.getUsername() != null)
				userentity= userService.findUserInfoByCred(user.getUsername(), user.getPassword());
			
               if(userentity != null && userentity.getEmailId() != null) {
            	   if(userentity.isEmailVerified()) {
            	   res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	   res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
            	   res.setSuccessMessage("Login success");
            	   res.setUserType(userentity.getUtype());
            	   String token=generateToken(userentity.getEmailId());
            	   res.setToken(token);
            	  UserProfileEntity profile =  profileService.getProfileDataByEmail(userentity.getEmailId());
            	   res.setUsername(profile.getFirstname());
            	   res.setEmailId(userentity.getEmailId());
            	   res.setFirstTimeLogin(userentity.isFirstTimeLogin());
            	   //Store Session token info
            	   UserAuthInfo userAuthInfo = new UserAuthInfo();
            	   userAuthInfo.setEmailId(user.getUsername());
            	   userAuthInfo.setToken(token);
            	   userAuthInfo.setCreatedDate(new Date());
            	   
            	   userAuthService.save(userAuthInfo);
            	   }else {
            		   String errorMessage="Verification email already sent on ";
            		   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
            		   errorMessage+=formatter.format(userentity.getCreatedDate())+"  Please verify";
            		   res.setErrorMessage(errorMessage);
                	   res.setStatus(200);
                	   res.setStatusMessage("error");
            	   }
            	   
               }else {
            	   res.setErrorMessage("Invalid username or password");
            	   res.setStatus(200);
            	   res.setStatusMessage("error");
               }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	/**
	 * Method is used to check whether the given user is valid or not
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.CREATE_NEW_USER, produces = TrueherbConstants.APPLICATION_JSON)
	public NewUserResponse registerNewUser(@RequestBody NewUserRequest user) {
		NewUserResponse res = new NewUserResponse();
		try {
			log.info("registering new User==>"+user.getCompanyEmailId());
			UserEntity userentity = null;
				
			
               if(user != null && user.getCompanyEmailId() != null) {
            	   
            	     if( userService.findByUsername(user.getCompanyEmailId())) {
            	    	 
            	    	 res.setStatusMessage(TrueherbConstants.ERROR_MSG);
            	    	 res.setErrorMessage(TrueherbConstants.EMAIL_ALREADY_EXISTS);
            	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	     }else {
            	    	 userentity = new UserEntity();
            	    	 userentity.setEmailVerified(false);
            	    	 userentity.setCreatedDate(new Date());
            	    	 userentity.setFirstTimeLogin(true);
            	    	 userentity.setModifiedDate(new Date());
            	    	 userentity.setUtype(TrueherbConstants.CUSTOMER);
            	    	// String pwd=getAlphaNumericString(6,user.getCompanyname());
            	    	 userentity.setPassword(user.getPassword());
            	    	 userentity.setUsername(user.getCompanyEmailId());
            	    	 userentity.setEmailId(user.getCompanyEmailId());
            	    	 userService.save(userentity);
            	    	 
            	    	 UserProfileEntity userProfile=new UserProfileEntity();
            	       	 userProfile.setCompanyname(user.getCompanyname());
            	       	userProfile.setCompanyEmailId(user.getCompanyEmailId());
            	       	userProfile.setFirstname(user.getFirstname());
            	       	userProfile.setLastname(user.getLastname());
            	       	 userProfile.setCreatedDate(new Date());
            	       	 userProfile.setMobilenumber(user.getMobilenumber());
            	       	 userProfile.setModifiedDate(new Date());
            	       	userProfile.setReferalCode(user.getReferalCode());
            	       	 userProfile.setUserType(TrueherbConstants.CUSTOMER);
            	       	 
            	    	 profileService.save(userProfile);
            	    	 res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
              	    	 res.setSuccessMessage(TrueherbConstants.REGISTER_SUCCESS);
              	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
              	    	res.setUserProfile(userProfile);
              	    	 
              	    	MailerRequest mailRequest = new MailerRequest();
            			mailRequest.setButtonName(TrueherbConstants.VERIFY_BUTTON_NAME);
            			mailRequest.setP(TrueherbConstants.LOGIN_CONTENT);
            			mailRequest.setName(user.getFirstname());
            			mailRequest.setSubject(TrueherbConstants.ADMIN_CREATION_SUBJECT);
            			mailRequest.setUrl(TrueherbConstants.VERIFY_EMAIL_URL+user.getCompanyEmailId());
            			mailRequest.setLabel1(TrueherbConstants.USERNAME);
            			mailRequest.setP1(user.getCompanyEmailId());
            			//mailRequest.setLabel2(TrueherbConstants.PASSWORD);
            			//mailRequest.setP2(pwd);
            			mailRequest.setTo(user.getCompanyEmailId());
            			trueherbService.sendMail(mailRequest);
            	    	 
            	     }
            	   
               }else {
            	   res.setStatusMessage(TrueherbConstants.ERROR_MSG);
      	    	 res.setErrorMessage(TrueherbConstants.INVALID_REQUEST);
      	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
      	     }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Method is used to check whether the given user is valid or not
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.UPDATE_EMAIL, produces = TrueherbConstants.APPLICATION_JSON)
	public NewUserResponse updateEmailForUser(@RequestBody ChangeEmailRequest user) {
		NewUserResponse res = new NewUserResponse();
		try {
			
			
               if(user != null && user.getNewEmailID() != null) {
            	   
            	   log.info("Changing existng email==>"+user.getNewEmailID());
            	   
            	   if(!userService.findByUsername(user.getNewEmailID())) {
            	   userService.updateEmailId(user.getOldEmailId(), user.getNewEmailID(),user.getNewEmailID());
            	   
            	     profileService.changeEmail(user.getOldEmailId(), user.getNewEmailID());
            	    	 res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
              	    	 res.setSuccessMessage(TrueherbConstants.REGISTER_SUCCESS);
              	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
              	    	 
              	    	MailerRequest mailRequest = new MailerRequest();
            			mailRequest.setButtonName(TrueherbConstants.VERIFY_BUTTON_NAME);
            			mailRequest.setP(TrueherbConstants.LOGIN_CONTENT);
            			mailRequest.setName(user.getName());
            			mailRequest.setSubject(TrueherbConstants.ADMIN_CREATION_SUBJECT);
            			mailRequest.setUrl(TrueherbConstants.VERIFY_EMAIL_URL+user.getNewEmailID());
            			mailRequest.setLabel1(TrueherbConstants.USERNAME);
            			mailRequest.setP1(user.getNewEmailID());
            			//mailRequest.setLabel2(TrueherbConstants.PASSWORD);
            			//mailRequest.setP2(pwd);
            			mailRequest.setTo(user.getNewEmailID());
            			trueherbService.sendMail(mailRequest);
            	   }else {
            		   res.setStatusMessage(TrueherbConstants.ERROR_MSG);
            	    	 res.setErrorMessage("User Already Exists..!");
            	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	   }
            	    	 
               }else {
            	   res.setStatusMessage(TrueherbConstants.ERROR_MSG);
      	    	 res.setErrorMessage(TrueherbConstants.INVALID_REQUEST);
      	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
      	     }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	@PostMapping(value = TrueherbConstants.RESEND_MAIL, produces = TrueherbConstants.APPLICATION_JSON)
	public NewUserResponse resendVerifyEmail(@RequestBody ResendRequest user) {
		NewUserResponse res = new NewUserResponse();
		try {
			log.info("resend mail to ==>"+user.getEmailId());
               if(user != null && user.getEmailId() != null) {
            	   
              	    	MailerRequest mailRequest = new MailerRequest();
            			mailRequest.setButtonName(TrueherbConstants.VERIFY_BUTTON_NAME);
            			mailRequest.setP(TrueherbConstants.LOGIN_CONTENT);
            			mailRequest.setName(user.getName());
            			mailRequest.setSubject(TrueherbConstants.ADMIN_CREATION_SUBJECT);
            			mailRequest.setUrl(TrueherbConstants.VERIFY_EMAIL_URL+user.getEmailId());
            			mailRequest.setLabel1(TrueherbConstants.USERNAME);
            			mailRequest.setP1(user.getEmailId());
            			//mailRequest.setLabel2(TrueherbConstants.PASSWORD);
            			//mailRequest.setP2(pwd);
            			mailRequest.setTo(user.getEmailId());
            			trueherbService.sendMail(mailRequest);
            			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
             	    	 res.setSuccessMessage(TrueherbConstants.RESEND_MAIL_SUCCESS);
             	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	   
               }else {
            	   res.setStatusMessage(TrueherbConstants.ERROR_MSG);
      	    	 res.setErrorMessage(TrueherbConstants.INVALID_REQUEST);
      	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
      	     }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * Method is used to check whether the given user is valid or not
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.SUBSCRIBE, produces = TrueherbConstants.APPLICATION_JSON)
	public NewUserResponse subscribeUser(@RequestBody SubscribeRequest user) {
		NewUserResponse res = new NewUserResponse();
		try {
			log.info("Subscribing new User==>"+user.getEmailId());
				
			
               if(user != null && user.getEmailId() != null) {
            	   
            	     if( subscribeService.isEmailExists(user.getEmailId())) {
            	    	 res.setStatusMessage(TrueherbConstants.ERROR_MSG);
            	    	 res.setErrorMessage(TrueherbConstants.EMAIL_ALREADY_SUBSCRIBED);
            	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	     }else {
            	    	 SubscribeEntity sub = new SubscribeEntity();
            	    	 sub.setCreatedDate(new Date());
            	    	 sub.setEmailId(user.getEmailId());
            	    	 sub.setName(user.getName());
            	    	 subscribeService.save(sub);
            	    	 res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
              	    	 res.setSuccessMessage(TrueherbConstants.SUBSCRIBE_SUCCESS);
              	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
              	    	 
            	    	 
            	     }
            	   
               }else {
            	   res.setStatusMessage(TrueherbConstants.ERROR_MSG);
      	    	 res.setErrorMessage(TrueherbConstants.INVALID_REQUEST);
      	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
      	     }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	/**
	 * Method is used to check whether the given user is valid or not
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.CONTACT_US, produces = TrueherbConstants.APPLICATION_JSON)
	public NewUserResponse contactInfo(@RequestBody ContactUSRequest user) {
		NewUserResponse res = new NewUserResponse();
		try {
			log.info("Contact US company name==>"+user.getCompanyName());
				
			
               if(user != null && user.getCompanyName() != null) {
            	   
            	   
            	   	     ContactUSEntity contactUs = new ContactUSEntity();
            	   	     contactUs.setAddress(user.getAddress());
            	   	     contactUs.setBudget(user.getBudget());
            	   	     contactUs.setCompanyName(user.getCompanyName());
            	   	     contactUs.setDesignation(user.getDesignation());
            	   	     contactUs.setEmailid(user.getEmailid());
            	   	     contactUs.setMessage(user.getMessage());
            	   	     contactUs.setPhonenumber(user.getPhonenumber());
            	   	     contactUs.setPincode(user.getPincode());
            	   	     contactUs.setWebsiteLink(user.getWebsiteLink());
            	   	     contactUs.setCreatedDate(new Date());
            	   	     
            	   	     contactService.save(contactUs);
            	    	 res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
              	    	 res.setSuccessMessage(TrueherbConstants.CONTACT_US_SUCCESS);
              	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	    	 
            	     }else {
            	   res.setStatusMessage(TrueherbConstants.ERROR_MSG);
      	    	 res.setErrorMessage(TrueherbConstants.INVALID_REQUEST);
      	    	 res.setStatus(TrueherbConstants.SUCCESS_STATUS);
      	     }

		} catch (Exception e) {
			log.info("Exception occurred contact us insertion operation",e);
			e.printStackTrace();
		}
		return res;
	}
	@GetMapping(value = TrueherbConstants.GET_SUBSCRIBE, produces = TrueherbConstants.APPLICATION_JSON)
	private SubscribeResponse getSubscribeDetails() {
		SubscribeResponse res = new SubscribeResponse();
		res.setSublit(subscribeService.findAll());
		res.setStatus(TrueherbConstants.SUCCESS_STATUS);
		res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
		return res;
	}
	
	@GetMapping(value = TrueherbConstants.GET_CONTACT_INFO, produces = TrueherbConstants.APPLICATION_JSON)
	private ContactUSResponse getContactInfo() {
		ContactUSResponse res = new ContactUSResponse();
		res.setcEntityList(contactService.findAll());
		res.setStatus(TrueherbConstants.SUCCESS_STATUS);
		res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
		return res;
	}
	
	@GetMapping(value = TrueherbConstants.GET_USER_PROFILE, produces = TrueherbConstants.APPLICATION_JSON)
	public UserProfileResponse getUserprofile(@RequestParam(name = "username") String username) {
		UserProfileResponse res = new UserProfileResponse();
		UserProfileEntity userProfile = profileService.getProfileDataByEmail(username);
		res.setProfileEntity(userProfile);
		res.setStatus(TrueherbConstants.SUCCESS_STATUS);
		res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
		return res;
	}
	
	@GetMapping(value = TrueherbConstants.GET_PRODUCT_DOC, produces = TrueherbConstants.APPLICATION_JSON)
	public ProductDocResponse getProductObject(@RequestParam(name = "productName") String productName) {
		ProductDocResponse response = new ProductDocResponse();
		try {
			log.info("Fetching product document infomation form s3 bucket");
			if(productName != null && productName.trim().length() !=0) {
				log.info("Product Name={}",productName);
				String prodName=productName+"/";
				ObjectListing listing = s3Client.listObjects( TrueherbConstants.S3_BUCKET_NAME, prodName);
				List<S3ObjectSummary> summaries = listing.getObjectSummaries();
				List<String> prodDocURLList=new LinkedList<>();
				summaries.stream().filter(s-> !s.getKey().equals(prodName)).forEach(summaryObj->{
					String object_url= s3BaseURL + summaryObj.getKey();
					//String.format("https://%s.s3.%s.amazonaws.com/%s", "truherb", Regions.AP_SOUTH_1.toString().toLowerCase().replace("_", "-"),summaryObj.getKey());
					prodDocURLList.add(object_url);
					});
				
				response.setProductName(productName);
				response.setProductUrlList(prodDocURLList);
				response.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
				response.setStatus(TrueherbConstants.SUCCESS_STATUS);
			}else {
				response.setStatusMessage(TrueherbConstants.ERROR_MSG);
				response.setStatus(TrueherbConstants.INTERNAL_SERVER_ERROR);
				response.setErrorMessage("Product name cannot be null value");
			}
		}catch(Exception e) {
			log.error("Exception occured while fetching data from s3 bucket={}",e);
			e.printStackTrace();
			response.setStatusMessage(TrueherbConstants.ERROR_MSG);
			response.setStatus(TrueherbConstants.INTERNAL_SERVER_ERROR);
		}
		return response;
		
	}
	@GetMapping(value = TrueherbConstants.DOWNLOAD_USER_PROFILE, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<ByteArrayResource> downloadCustomerData() {
		log.info("calling getAllUserCreationCreatedBy ");
		try {

				List<UserProfileEntity> userProfileList = profileService.findAll();
				if (userProfileList != null && !userProfileList.isEmpty()) {
					createCustomerDataExcel(userProfileList);
					String filePath = TrueherbConstants.EXCEL_LOCATION
							+ TrueherbConstants.CUSTOMER_DATA_FILE_NAME;
					File file = new File(filePath);
					// InputStreamResource resource = new InputStreamResource(new
					// FileInputStream(filePath))

					Path path = Paths.get(file.getAbsolutePath());
					ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
					HttpHeaders header = new HttpHeaders();
					header.add(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=" + TrueherbConstants.CUSTOMER_DATA_FILE_NAME);
					header.add("Cache-Control", "no-cache, no-store, must-revalidate");
					header.add("Pragma", "no-cache");
					header.add("Expires", "0");
					return ResponseEntity.ok().headers(header).contentLength(file.length())
							.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
				}
			}
		 catch (Exception e) {

		}
		return null;
	}
	
	
	@GetMapping(value = TrueherbConstants.DOWNLOAD_CONTACT_INFO, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<ByteArrayResource> downloadContactInfo() {
		log.info("calling getAllUserCreationCreatedBy ");
		try {

				List<ContactUSEntity> userProfileList = contactService.findAll();
				if (!userProfileList.isEmpty()) {
					createContactUsDataExcel(userProfileList);
					String filePath = TrueherbConstants.EXCEL_LOCATION
							+ TrueherbConstants.CONTACT_US_FILE_NAME;
					File file = new File(filePath);

					Path path = Paths.get(file.getAbsolutePath());
					ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
					HttpHeaders header = new HttpHeaders();
					header.add(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=" + TrueherbConstants.CONTACT_US_FILE_NAME);
					header.add("Cache-Control", "no-cache, no-store, must-revalidate");
					header.add("Pragma", "no-cache");
					header.add("Expires", "0");
					return ResponseEntity.ok().headers(header).contentLength(file.length())
							.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
				}
			}
		 catch (Exception e) {

		}
		return null;
	}
	

	@GetMapping(value = TrueherbConstants.DOWNLOAD_ENQUIRY_INFO, produces = TrueherbConstants.APPLICATION_JSON)
	public ResponseEntity<ByteArrayResource> downloadEnquiryInfo() {
		log.info("calling downloadEnquiryInfo ");
		try {

				List<EnquiryEntity> userProfileList = enquiryDao.findAll();
				if (!userProfileList.isEmpty()) {
					createEnquiryDataExcel(userProfileList);
					String filePath = TrueherbConstants.EXCEL_LOCATION
							+ TrueherbConstants.ENQUIRY_FILE_NAME;
					File file = new File(filePath);

					Path path = Paths.get(file.getAbsolutePath());
					ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
					HttpHeaders header = new HttpHeaders();
					header.add(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=" + TrueherbConstants.ENQUIRY_FILE_NAME);
					header.add("Cache-Control", "no-cache, no-store, must-revalidate");
					header.add("Pragma", "no-cache");
					header.add("Expires", "0");
					return ResponseEntity.ok().headers(header).contentLength(file.length())
							.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
				}
			}
		 catch (Exception e) {

		}
		return null;
	}
	

private void createEnquiryDataExcel(List<EnquiryEntity> sapFileDataList) {
		
		try {
			if(sapFileDataList != null && !sapFileDataList.isEmpty()) {
		 String filename = TrueherbConstants.EXCEL_LOCATION+TrueherbConstants.ENQUIRY_FILE_NAME;
	     HSSFWorkbook workbook = new HSSFWorkbook();
	     HSSFSheet sheet = workbook.createSheet(TrueherbConstants.ENQUIRY_SHEET_NAME);  
	     int rowIndex=-1;

	     HSSFRow rowhead = sheet.createRow((short)++rowIndex);
	     rowhead.createCell(0).setCellValue("Product Name");
	     rowhead.createCell(1).setCellValue("Quantity");
	     rowhead.createCell(2).setCellValue("Email Id");
	     rowhead.createCell(3).setCellValue("Warehouse Name");
	     rowhead.createCell(4).setCellValue("Customer Name");
	     rowhead.createCell(5).setCellValue("Mobilenumber");
	     rowhead.createCell(6).setCellValue("Company Name");
	     rowhead.createCell(7).setCellValue("Enquiry Date");
	     
	     HSSFRow row = sheet.createRow((short)++rowIndex);
	     for(EnquiryEntity sapFile:sapFileDataList) {
	     row.createCell(0).setCellValue(sapFile.getProductName());
	     row.createCell(1).setCellValue(sapFile.getQuantity());
	     row.createCell(2).setCellValue(sapFile.getUsername());
	     row.createCell(3).setCellValue(sapFile.getWarehouseName());
	     log.info("username={}",sapFile.getUsername());
	     UserProfileEntity profile = profileService.getProfileDataByEmail(sapFile.getUsername());
	     if(profile != null) {
	     row.createCell(4).setCellValue(profile.getFirstname());
	     row.createCell(5).setCellValue(profile.getMobilenumber());
	     row.createCell(6).setCellValue(profile.getCompanyname());
	     CellStyle cellStyle = workbook.createCellStyle();
	     CreationHelper createHelper = workbook.getCreationHelper();
	     cellStyle.setDataFormat(
	    		    createHelper.createDataFormat().getFormat("dd/mm/yy h:mm"));
	     
	     Cell cell   = row.createCell(7);
	     cell.setCellStyle(cellStyle);
	     cell.setCellValue(profile.getCreatedDate());
	     }
	     row = sheet.createRow((short)++rowIndex);
	     }

	     FileOutputStream fileOut = new FileOutputStream(filename);
	     workbook.write(fileOut);
	     fileOut.close();
	     workbook.close();
	     log.info("****Enquiry Data Excel file generereated successfully*****");
			}

	 } catch ( Exception ex ) {
		 log.error("Exception while creating customer excel data==>",ex);
		 ex.printStackTrace();
	 }
		
	}	
	
	private void createContactUsDataExcel(List<ContactUSEntity> sapFileDataList) {
		
		try {
			if(sapFileDataList != null && !sapFileDataList.isEmpty()) {
		 String filename = TrueherbConstants.EXCEL_LOCATION+TrueherbConstants.CONTACT_US_FILE_NAME;
	     HSSFWorkbook workbook = new HSSFWorkbook();
	     HSSFSheet sheet = workbook.createSheet(TrueherbConstants.CONTACT_US_SHEET_NAME);  
	     int rowIndex=-1;

	     HSSFRow rowhead = sheet.createRow((short)++rowIndex);
	     rowhead.createCell(0).setCellValue("Company Name");
	     rowhead.createCell(1).setCellValue("Designation");
	     rowhead.createCell(2).setCellValue("Phone Number");
	     rowhead.createCell(3).setCellValue("Email Id");
	     rowhead.createCell(4).setCellValue("Website Link");
	     rowhead.createCell(5).setCellValue("Budget");
	     rowhead.createCell(6).setCellValue("Addresss");
	     rowhead.createCell(7).setCellValue("Pincode");
	     rowhead.createCell(8).setCellValue("Message");
	     rowhead.createCell(9).setCellValue("Date");
	     
	     HSSFRow row = sheet.createRow((short)++rowIndex);
	     for(ContactUSEntity sapFile:sapFileDataList) {
	     row.createCell(0).setCellValue(sapFile.getCompanyName());
	     row.createCell(1).setCellValue(sapFile.getDesignation());
	     row.createCell(2).setCellValue(sapFile.getPhonenumber());
	     row.createCell(3).setCellValue(sapFile.getEmailid());
	     row.createCell(4).setCellValue(sapFile.getWebsiteLink());
	     row.createCell(5).setCellValue(sapFile.getBudget());
	     row.createCell(6).setCellValue(sapFile.getAddress());
	     row.createCell(7).setCellValue(sapFile.getPincode());
	     row.createCell(8).setCellValue(sapFile.getMessage());
	     
	     CellStyle cellStyle = workbook.createCellStyle();
	     CreationHelper createHelper = workbook.getCreationHelper();
	     cellStyle.setDataFormat(
	     createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
	     Cell cell = row.createCell(9);
	     cell.setCellValue(sapFile.getCreatedDate());
	     cell.setCellStyle(cellStyle);
	     
	     row = sheet.createRow((short)++rowIndex);
	     }

	     FileOutputStream fileOut = new FileOutputStream(filename);
	     workbook.write(fileOut);
	     fileOut.close();
	     workbook.close();
	     log.info("****Contact us Data Excel file generereated successfully*****");
			}

	 } catch ( Exception ex ) {
		 log.error("Exception while creating customer excel data==>",ex);
		 ex.printStackTrace();
	 }
		
	}	
	
private void createCustomerDataExcel(List<UserProfileEntity> sapFileDataList) {
	
	try {
		if(sapFileDataList != null && !sapFileDataList.isEmpty()) {
	 String filename = TrueherbConstants.EXCEL_LOCATION+TrueherbConstants.CUSTOMER_DATA_FILE_NAME;
     HSSFWorkbook workbook = new HSSFWorkbook();
     HSSFSheet sheet = workbook.createSheet(TrueherbConstants.SHEET_NAME);  
     int rowIndex=-1;

     HSSFRow rowhead = sheet.createRow((short)++rowIndex);
     rowhead.createCell(0).setCellValue("Name");
     rowhead.createCell(1).setCellValue("Compnay Name");
     rowhead.createCell(2).setCellValue("Email ID");
     rowhead.createCell(3).setCellValue("Country Code");
     rowhead.createCell(4).setCellValue("Mobile Number");
     rowhead.createCell(5).setCellValue("Registration Date");
     rowhead.createCell(6).setCellValue("Verification Date");
     rowhead.createCell(7).setCellValue("Referal Code");
   //  rowhead.createCell(5).setCellValue("Registration Date");
     //rowhead.createCell(6).setCellValue("Created Date");
     
     HSSFRow row = sheet.createRow((short)++rowIndex);
     for(UserProfileEntity sapFile:sapFileDataList) {
     row.createCell(0).setCellValue(sapFile.getFirstname());
     row.createCell(1).setCellValue(sapFile.getCompanyname());
     row.createCell(2).setCellValue(sapFile.getCompanyEmailId());
     String []str=sapFile.getMobilenumber().split(" ");
     if(str.length > 1) {
    	 row.createCell(3).setCellValue(str[0]);
    	 row.createCell(4).setCellValue(str[1]);
     }else {
    	 row.createCell(3).setCellValue(" ");
    	 row.createCell(4).setCellValue(str[0]);
     }
     CellStyle cellStyle = workbook.createCellStyle();
     CreationHelper createHelper = workbook.getCreationHelper();
     cellStyle.setDataFormat(
         createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
     Cell cell = row.createCell(5);
     cell.setCellValue(sapFile.getCreatedDate());
     cell.setCellStyle(cellStyle);
     
     UserEntity user = userService.getUserDataByUsername(sapFile.getCompanyEmailId());
     if(user.getVerifiedDate() != null) {
     cell = row.createCell(6);
     
     cell.setCellValue(user.getVerifiedDate());
     cell.setCellStyle(cellStyle);
     }
     row.createCell(7).setCellValue(sapFile.getReferalCode());
     row = sheet.createRow((short)++rowIndex);
     
     
     }

     FileOutputStream fileOut = new FileOutputStream(filename);
     workbook.write(fileOut);
     fileOut.close();
     workbook.close();
     log.info("****Customer Data Excel file generereated successfully*****");
		}

 } catch ( Exception ex ) {
	 log.error("Exception while creating customer excel data==>",ex);
	 ex.printStackTrace();
 }
	
}	
//	@GetMapping(value = TrueherbConstants.GET_ALL_USER_DETAILS, produces = TrueherbConstants.APPLICATION_JSON)
//	private UserProfileResponse getAllUsers() {
//		UserListResponse res = new UserListResponse();
//		List<UserProfileEntity> userProfile = profileService.findAll();
//		res.setUserProfileDataList(userProfile);
//		res.setProfileEntity(userProfile);
//		res.setStatus(TrueherbConstants.SUCCESS_STATUS);
//		res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
//		return res;
//	}
	
	/**
	 * Method is used to send forgot password link to registered emailid
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.FORGOT_PASSWORD_API, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse forgotPassword(@RequestBody ForgotPasswordRequest forgotPassword) {
		BaseResponse res = new BaseResponse();
		try {
			log.info("Forgot password api is invoking");
			if (userService.findByUsername(forgotPassword.getUsename())) {
				log.info("Valid user ====>");	
				//Send email
				UserEntity user = userService.getUserDataByUsername(forgotPassword.getUsename());
				//if(user !=null && user.getEmailId() != null) {
				MailerRequest mailRequest = new MailerRequest();
				mailRequest.setButtonName(TrueherbConstants.FORGOT_PASSWORD_BUTTON);
				mailRequest.setP(TrueherbConstants.FORGOT_PASSWORD_CONTENT);
				mailRequest.setName(user.getUsername());
				mailRequest.setSubject(TrueherbConstants.FORGOT_PASSWORD_SUBJECT);
				mailRequest.setUrl(TrueherbConstants.FORGOT_PASSWORD_URL+user.getUsername());
				mailRequest.setTo(user.getEmailId());
				trueherbService.sendMail(mailRequest);
				res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
				res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			//} 
			}else {
				res.setStatusMessage(TrueherbConstants.ERROR_MSG);
				res.setStatus(200);
				res.setErrorMessage(TrueherbConstants.UNAUTHRORIZED_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatusMessage(TrueherbConstants.ERROR_MSG);
			res.setStatus(TrueherbConstants.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	
	/**
	 * Method is used to create a new password when user forgot old password
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.UPDATE_PASSWORD_API, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse updateNewpassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
		BaseResponse res = new BaseResponse();
		try {
			boolean user = userService.isValidUser(resetPasswordRequest.getUsername());
			if (user) {
				userService.resetNewPassword(resetPasswordRequest.getUsername(), resetPasswordRequest.getPassword());
				res=trueherbService.generateResponse(true, TrueherbConstants.SUCCESS_MSG);
				
			} else {
				res=trueherbService.generateResponse(true, TrueherbConstants.INVALID_USER);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			
		}
		return res;
	}

	
	/**
	 * Method is used to create a new password when user forgot old password
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.RESET_PASSWORD_API, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse resetPassword(@RequestBody UpdatePasswordRequest forgotPassword) {
		BaseResponse res = new BaseResponse();
		try {
			UserEntity user = userService.findUserByCredentials(forgotPassword.getUsername(), forgotPassword.getOldPassword());
			if (user != null && user.getEmailId() != null) {
				userService.resetNewPassword(forgotPassword.getUsername(), forgotPassword.getNewPassword());
				res=trueherbService.generateResponse(true, TrueherbConstants.SUCCESS_MSG);
			} else {
				res=trueherbService.generateResponse(false, TrueherbConstants.INVALID_USER);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
		}
		return res;
	}
	
	
	
	
	private String getBase64EncryptionPwd(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}

	

	private String generateUsername(String emailId) {
		return emailId.split("@")[0];
	}
	
	static String getAlphaNumericString(int n, String name) {
		String autoGeneratePwd = (name.length() > 3) ? name.substring(0, 4) : name.substring(0, name.length() - 1);
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return autoGeneratePwd + sb.toString();
	}

	private String generateToken(String username) {
		Date dt = new Date();
		String tokenStr = username+":"+dt.getTime();
		return Base64.getEncoder().encodeToString(tokenStr.getBytes());
	}
	

}
