package com.svarks.trueherb.common;

public interface TrueherbConstants {

		//BASE PACKAGE
		 String BASE_PACKAGE_NAME="com.svarks.trueherb.*";
		 String ENTITY_PACKAGE="com.svarks.trueherb.dao.entity";
		 String APPLICATION_JSON = "application/json";
		 String UNDER_SCORE="_";
		 
		 
	 String LOGIN_BUTTON="Login";
	 String USERNAME="Username:";
	 String PASSWORD="Password:";
	 String AUTHORIZATION_TOKEN="dHJ1ZWhlcmJAd2FyZXNob3Vl";
	 
	 
	// ERROR CODE
			int SUCCESS_STATUS = 200;
			int INTERNAL_SERVER_ERROR = 500;
			int NOT_FOUND = 404;
			
			// ERROR MESSAGES
			String SUCCESS_MSG = "success";
			String ERROR_MSG = "error";
			String INVALID_HEADER = "SAP EXCEL contains Invalid header::";
			String UNAUTHRORIZED_REQUEST="Un-Authorized request";
			String INVALID_PAYLOAD="Invalid payload..!";
			
			String EMAIL_ALREADY_EXISTS = "User already exists..!";
			String EMAIL_ALREADY_SUBSCRIBED = "User already subscribed..!";
			String ADDED_SUCCESSFULLY="Added successfully..!";
			String DELETED_SUCCESSFULLY="Deleted successfully..!";
			String CUSTOMER_ID_EXISTS = "Customer ID already exists..!";
			String ADD_SUCCESS = "Added successfully..!";
			String DUPLICATE_PRODUCT = "Product already exists.!";
			
			String DUPLICATE_ENTITY = "Legal Entity already exists.!";
			
			String DUPLICATE_BATCH_ENTITY = "Batch Details already exists.!";
			
			String DUPLICATE_PORT_ENTITY = "Port Details already exists.!";
			String DUPLICATE_ROAD_ENTITY = "Road time already exists.!";
			String NETWORK_ISSUE = "Network error..! Please try after sometime";
			String INSERT_SUCCESS = "Saved Successfully..!";
			String LOGIN_ERROR = "EmailId or Password is incorrect";
			String INVALID_USER = "Unauthorized request..!";
			
			String REGISTER_SUCCESS = "Customer registration success..!";
			String RESEND_MAIL_SUCCESS = "Email sent successfully..!";
			String SUBSCRIBE_SUCCESS = "Subscirbed successfully..!";
			String CONTACT_US_SUCCESS = "Thanks you for contacting US.We will get back to you shortly!";
			String ADMIN_CREATEION_SUCCESS = "Admin creation success..!";
			String INVALID_REQUEST = "Invalid request..!";
			String USERNAME_UNIQUE_MSG="Username already exists.Please provide another usernname..!";
			
			String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error.!";
			String EMAILCONFIRM = "Please verify your email address";
			String PROFILE_UPDATE_SUCCESS="Updated user profile successfully..!";
			String MARKING_TEXT_SUCCESS="Marking text added successfully..!";
			String UPDATE_SUCCESS="Updated Successfully..!";
		 
		 //USER TYPE AND ROLRES
		 
		 int SUPER_ADMIN=1;
		 int ADMIN=2;
		 int CUSTOMER=3;
		 
	 
	 
	//API ENDPOINT 
		 String VALIDATE_USER_API = "/validateUser";
		 String PING="/ping";
		 String WAREHOUSE_API="/warehouse";
		 String ENQUIRY_API="/enquiry";
		 String CREATE_NEW_USER="/registerUser";
		 String UPDATE_EMAIL="/changeEmail";
		 String RESEND_MAIL="/resendMail";
		 String SUBSCRIBE="/subscribe";
		 String CONTACT_US="/contactInfo";
		 String CREATE_NEW_ADMIN="/createAdmin";
		 String FORGOT_PASSWORD_API = "/forgotPassword";
		 String UPDATE_PASSWORD_API = "/updatePwd";
		 String PRODUCT_API="/product";
		 String PRODUCT_API_ZOHO="/getproductByZoho";
		 
		 String PRODUCT_API_BY_ITEM_ZOHO="/getproductByItem";
		 String PRODUCT_IMAGE_API_BY_ITEM_ZOHO="/getproductByImage";
		 String GET_ALL_WAREHOUSE="/getAllWarehouse";
		 String GET_ALL_CATEGORY="/getAllCategory";
		 String GET_PRODUCT_BY_CATEGORY="/getProductByCategory";
		// String GET_LAT_LONG="/getLatLongAddess";
		 
		 String PRODUCT_QUANTITY_API="/getProdQuantity";
		 String TRUE_HERB_GET_API="/getAllData";
		 String PRODUCT_QUANTITY="/prodQuantity";
		 String  LEGAL_ENTITY ="/legalEntity";
		 String RESET_PASSWORD_API = "/resetPassword";
		 
		 String UPDATE_PROFILE="/updateProfile";
		 String GET_USERS_LIST="/getAllUsersList";
		 String GET_DOCUMENT_INFO="/getDocument";
		 String GET_ENQUIRY_INFO="/getEnquiryData";
		 String BATCH_OPERATION="/batch";
		 String GET_BATCH_INFO="/getBatchInfo";
		 
		 String APPROVE_USER="/approveUser";
		 
		 String PORT_OPERATION="/port";
		 String GET_PORT_INFO="/getPortInfo";
		 
		 String MODE_TIMINGS="/modeTimings";
		 String ROAD_TIMINGS="/roadTimings";
		 String TRANSPORT_MODE="/tranportMode";
		 
		 String GET_USER_PROFILE="/getProfileDetails";
		 
		 String GET_PRODUCT_DOC="/getProdDoc";
		 String S3_BUCKET_NAME="truherb";
		 
		 String DOWNLOAD_USER_PROFILE="/downloadUserData";
		 String DOWNLOAD_CONTACT_INFO="/downloadContactInfo";
		 String DOWNLOAD_ENQUIRY_INFO="/downloadEnquiryInfo";
		 String CUSTOMER_DATA_FILE_NAME="CustomerInfo.xls";
		 String CONTACT_US_FILE_NAME="ContactUSInfo.xls";
		 String ENQUIRY_FILE_NAME="EnquiryInfo.xls";
		 String SHEET_NAME="Users Information";
		 String CONTACT_US_SHEET_NAME="Contact_US_Info";
		 String ENQUIRY_SHEET_NAME="Enquiry_info";
		 
		 String GET_SUBSCRIBE="/getSubscribe";
		 String GET_CONTACT_INFO="/getContactInfo";
		 
		//String EXCEL_LOCATION="D://";
		 
		 String EXCEL_LOCATION="/home/ubuntu/userExcel/";
		 
		 String GET_ALL_USER_DETAILS="/getAllUserDetails";
		 
	 //TEST
	// String LOGIN_URL="http://52.206.130.36:8080/lapp";
	 //PROD
	 String LOGIN_URL="http://65.0.175.58/";
	 String VERIFY_EMAIL_URL="http://65.0.175.58/verifyemail/";
	
	 String REGISTER_SUCCESS_MESSAGE="Your registration has been completed successfully..! Please find the login credentials below";
	 
	 
	 
	 //IMAGES URL
	 String S3_CLOUDFRONT_LOGO = "https://ordermarking.s3.amazonaws.com/lapp_logo.png";
	 String S3_CLOUD_BANNER = "https://ordermarking.s3.amazonaws.com/banner.png";

	 
	 String EMAIL_USER_ID="rajesh.svarks@gmail.com";
	 String SMTP_ENABLE="mail.smtp.starttls.enable";
	 String SMTP_HOST="mail.smtp.host";
	 String SMTP_PORT="mail.smtp.port";
	 String SMTP_HOST_VALUE="smtp.gmail.com";
	 String SMPT_PORT_VALUE="587";
	 String FROM_NAME="Tru-herb Warehouse";
	 
	 String FORGOT_PASSWORD_SUBJECT="Truherb Forgot Password Notification..!";
	 String LOGIN_SUBJECT="Congragulations..! Email verification success";
	 String ADMIN_CREATION_SUBJECT="Congragulations..! Registration success. Verify your email address";
	 String MARKING_TEXT_SUBJECT="Marking text update Notification..!";
	 String FORGOT_PASSWORD_CONTENT="Seems like you forgot your password tru-herb application.If this is true,Click the below to reset your password";
	 String FORGOT_PASSWORD_BUTTON="Reset My Password";
	 
	 String LOGIN_CONTENT="Your registration is successfull. Please click on below link to verify your email address";
	 
	 
	 
	 String LOGIN_BUTTON_NAME="Login";
	 String VERIFY_BUTTON_NAME="Verify";
	 //Test
	 //String FORGOT_PASSWORD_URL="http://52.206.130.36:8080/lapp/#/forgot-password?emailId=";
	 //PROD
	 String FORGOT_PASSWORD_URL="http://65.0.175.58/forgot-password?emailId=";
	 
}
