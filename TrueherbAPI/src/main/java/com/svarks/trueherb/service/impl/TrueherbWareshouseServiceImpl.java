package com.svarks.trueherb.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.svarks.trueherb.common.TrueherbConstants;
import com.svarks.trueherb.request.model.MailerRequest;
import com.svarks.trueherb.response.model.BaseResponse;
import com.svarks.trueherb.service.TrueherbWareshouseService;

@Service
public class TrueherbWareshouseServiceImpl implements TrueherbWareshouseService{
	
	private static final Logger log = LoggerFactory.getLogger(TrueherbWareshouseServiceImpl.class);
	@Autowired
	JavaMailSender sender;
	
	
	@Override
	public BaseResponse generateResponse(boolean isSuccess, String message) {
		BaseResponse res = new BaseResponse();
		if(isSuccess) {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setSuccessMessage(message);
		}else {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.ERROR_MSG);
			res.setErrorMessage(message);
		}
		return res;
		
	}

	
	

	public void sendMail(MailerRequest mailRequest) {

		try {
			log.info("Sending mail now...!");
			MimeMessage mail = sender.createMimeMessage();
			String body = generateHtmlEmailBody(mailRequest);
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setFrom(TrueherbConstants.EMAIL_USER_ID, TrueherbConstants.FROM_NAME);
			helper.setTo(mailRequest.getTo());
			helper.setSubject(mailRequest.getSubject());
			mail.setHeader("X-Priority", "3");
			helper.setText(body, true);
			sender.send(mail);
			log.info("Email sent successfully...");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error sending an Email"+e);
		}

		
	}

	/*public void sendTestMail() {
		String subject = "Mail Sender example testing..!";
		String to = "shilpakala@smartcommercesolutions.in";
		String msg = "welcome to java mail sender";

		MailerRequest mailRequest = new MailerRequest();
		mailRequest.setButtonName("Login");
		mailRequest.setTo("shilpakala@smartcommercesolutions.in");
		mailRequest.setLabel1("Username:");
		mailRequest.setP1("rajeshsavi123@gmail.com");
		mailRequest.setSubject(TrueherbConstants.REGISTER_SUCCESS_SUBJECT);
		mailRequest.setLabel2("Password:");
		mailRequest.setP2("lapp@1123");
		mailRequest
				.setP("Your registration has been completed successfully..! Please find the login credentials below");
		mailRequest.setUrl("http://3.17.182.133:8080");
		mailRequest.setName("Rajesh Gowda");

		sendFeedbackMail(mailRequest);
	}*/

	private String generateHtmlEmailBody(MailerRequest mailRequest) {
		String mailBody = "";

		mailBody += "<!doctype html>";
		mailBody += "<html lang='en'>";

		mailBody += "<head>";
		mailBody += "<meta charset='utf-8'>";
		mailBody += "<title>Tru Herb</title>";

		mailBody += "<meta name='viewport' content='width=device-width, initial-scale=1'>";
		mailBody += "<link rel='icon' type='image/x-icon' href='favicon.ico'>";

		mailBody += "</head>";

		mailBody += "<body style='margin: 0;padding: 0; background: #F6F6F6;'>";
		mailBody += "<div style='width: 100%; height: 100%; display: block;'>";
		mailBody += "<table border='0' cellspacing='0' cellpadding='0' style='width:600px;max-width:620px;margin:0 auto;background:#fff;'>";
		mailBody += "<tbody>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block;height:5px;'></td>";
		mailBody += "<td colspan='2'></td";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "<td colspan='2'>";
		mailBody += "<img src='https://truherb.s3.ap-south-1.amazonaws.com/logo.png' alt='Tru herb' style='height: 70px;' />";
		mailBody += "</td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "<td colspan='2' style='background: #e8bc8b; height: 1px;'></td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "<td colspan='2'>";
		//<!-- header start -->
		mailBody += "<br/><p style='width: 100%;margin: 0;padding: 0;'>";
		mailBody += " Hi <b>" + mailRequest.getName() + ",</b>";
		mailBody += "</p>";

		mailBody += "<br/>";

		if (mailRequest.getP() != null) {
		mailBody += "<p style='width: 100%;margin: 0;padding: 0;'>";
		mailBody += mailRequest.getP() ;
		mailBody += "</p>";
		}
		mailBody += "</br>";
		mailBody += "<p style='width: 100%;margin: 0;padding: 0;'>";
		mailBody += "<table border='0'>";

		if (mailRequest.getLabel1() != null && mailRequest.getP1() != null) {
		mailBody += "<tr>";
		mailBody += "<td>" + mailRequest.getLabel1() + "</td>";
		mailBody += "<td>" + mailRequest.getP1() + "</td>";
		mailBody += "</tr>";
		}
		if (mailRequest.getLabel2() != null && mailRequest.getP2() != null) {
		mailBody += "<tr>";
		mailBody += "<td>" + mailRequest.getLabel2() + "</td>";
		mailBody += "<td>" + mailRequest.getP2() + "</td>";
		mailBody += "</tr>";
		}
		if (mailRequest.getLabel3() != null && mailRequest.getP3() != null) {
		mailBody += "<tr>";
		mailBody += "<td>" + mailRequest.getLabel3() + "</td>";
		mailBody += "<td>" + mailRequest.getP3() + "</td>";
		mailBody += "</tr>";
		}
		mailBody += "</table>";
		mailBody += "</p>";
		mailBody += "<p style='width: 100%;margin: 0;padding: 0;'>";
		if(mailRequest.getButtonName() !=null) {
			mailBody += "<a href=" + mailRequest.getUrl() + " style='background: #474747;color: #fff;text-align: center;text-decoration: none;padding: 5px 15px;border-radius: 2px;'> "+mailRequest.getButtonName() +"</a>";	
		}else {
		mailBody += "<a href=" + mailRequest.getUrl() + " style='background: #474747;color: #fff;text-align: center;text-decoration: none;padding: 5px 15px;border-radius: 2px;'>Login</a>";
		}
		mailBody += "</p>";
		//<!-- body end -->
		mailBody += "<br/>";
		//<!-- footer start -->
		mailBody += "<p style='width: 100%;margin: 0;padding: 0;font-size: 12px;'>";
		mailBody += "Regards,";
		mailBody += "</p>";
		mailBody += "<p style='width: 100%;margin: 0;padding: 0;font-size: 14px;'>";
		mailBody += "  Truherb";
		mailBody += "</p>";
		//<!-- footer end -->
		mailBody += "</td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "<td colspan='2'>&nbsp;</td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "<td colspan='2' style='background: #e8bc8b; height: 1px;'></td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "<td colspan='2' style='background: #fff;color: #151D41;padding: 5px;font-size: 12px;text-align: center;'>";
		mailBody += "© 2020&nbsp;&nbsp;|&nbsp;&nbsp;<a href='http://65.0.175.58/home' style='color: #151D41; text-decoration:none;'>www.truherb.com</a>";
		mailBody += "</td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "<tr>";
		mailBody += "<td style='width: 15px;display:block;height:5px;'></td>";
		mailBody += "<td colspan='2'></td>";
		mailBody += "<td style='width: 15px;display:block; '></td>";
		mailBody += "</tr>";
		mailBody += "</tbody>";
		mailBody += "</table>";
		mailBody += "</div>";
		mailBody += "</body>";

		mailBody += "</html>";


		return mailBody;
	}




	@Override
	public Date convertStringToDate(String date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        try {
        	
        	Date dt = sdf1.parse(date);
        	sdf1.format(dt);
        	log.info("After converting date value={}",dt);
            return dt;

        } catch (ParseException e) {
        	log.error("Error occured while parsing the string to date error={}",e);
        	return null;
        }		
	}

}
