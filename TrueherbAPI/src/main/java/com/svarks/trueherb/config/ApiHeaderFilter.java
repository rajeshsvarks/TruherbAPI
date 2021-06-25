package com.svarks.trueherb.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.svarks.trueherb.common.TrueherbConstants;



//@Configuration
@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiHeaderFilter extends GenericFilterBean {
	
/*	@Autowired
	UserAuthDao userAuthService;
*/	
	private static final Logger log = LoggerFactory.getLogger(ApiHeaderFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization,authorization2,content-type, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	 String token =( (request.getHeader("Authorization") != null)?request.getHeader("Authorization"):"");
             /*log.info("***********************************************");
             log.info("***********HEADER VALIDATION TRIGGERED************");
             log.info("***********************************************");*/
             
          /*   log.info("*******TOKEN VALUE==>"+token);
             
            // log.info("request method==>"+request.getMethod());
             if(token !=null && !token.isEmpty()) {
                token=token.split(" ")[1];
                log.info("******* TOKEN VALUE==>"+token);
                log.info("is token valid==>"+userAuthService.findByToken(token));
             }else {
             	log.info("invalid authorization..!");
             }
             */
             String path = request.getRequestURI();
             log.info("Request URL path : {}, Token: {}", path,token);
             log.info("Token URL path : {}", token);
             
             if((path !=null && getFilterList().contains(path)) || (token != null && token.equals(TrueherbConstants.AUTHORIZATION_TOKEN))) {
             filterChain.doFilter(servletRequest,servletResponse);
             }else {
             	log.info("token value==>",token);
             	log.info("Unauthorized Request..!");
             	response.setStatus(401);
             	//response.setStatus(200, "");
             	((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
             }
        }

       
    }
    
    private List<String> getFilterList(){
    	List<String> strList = new ArrayList<>();
    	strList.add(TrueherbConstants.PING);
    	strList.add(TrueherbConstants.PRODUCT_IMAGE_API_BY_ITEM_ZOHO);
    	strList.add(TrueherbConstants.GET_DOCUMENT_INFO);
    	strList.add(TrueherbConstants.DOWNLOAD_USER_PROFILE);
    	strList.add(TrueherbConstants.DOWNLOAD_CONTACT_INFO);
    	strList.add(TrueherbConstants.DOWNLOAD_ENQUIRY_INFO);
    	return strList;
    	
    }
}