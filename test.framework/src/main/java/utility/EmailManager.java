package utility;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class EmailManager {


	/**
	 * 
	 * @return
	 */
	public static Map<String,String> getEmailManagerData() {
		JsonParser jsonParser =new JsonParser();
		JsonObject jsonObject=new JsonObject();
		Map<String, String> emailData = new HashMap<String, String>();
		try {
			jsonObject = (JsonObject) jsonParser.parse(new FileReader("C:\\Users\\lenovo\\Desktop\\eclipse\\test.framework\\src\\main\\resources\\configuration\\email_manager.json"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emailData.put("senderEmail" , jsonObject.get("senderEmail").getAsString());
		emailData.put("receiverEmail" , jsonObject.get("receiverEmail").getAsString());
		emailData.put("smtpHost" , jsonObject.get("smtpHost").getAsString());
		return emailData;
	}

	/**
	 * 
	 */
	public static void sendEmail(MimeMessage message)
	{ 
		try 
		{
			// Send email.
			Transport.send(message);
			System.out.println("Mail successfully sent");
		}
		catch (MessagingException mex) 
		{
			mex.printStackTrace();
		}
	}

	public static MimeMessage setupEmailBody() {
		
		Map<String, String> emailData = getEmailManagerData();
		// email ID of Recipient.
		String sender = emailData.get("senderEmail");

		// email ID of Sender.
		String recipient = emailData.get("receiverEmail");

		// using host as localhost
		String smtpHost = emailData.get("smtpHost");

		// Getting system properties
		Properties properties = System.getProperties();

		// Setting up mail server
		properties.setProperty("mail.smtp.host", smtpHost);

		// creating session object to get properties
		Session session = Session.getDefaultInstance(properties);
		// MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		try {
			// Set From Field: adding senders email to from field.
			message.setFrom(new InternetAddress(sender));

			// Set To Field: adding recipient's email to from field.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		

			// Set Subject: subject of the email
			message.setSubject("Automation run report");

			// creating first MimeBodyPart object
			BodyPart messageBodyPart1 = new MimeBodyPart(); 
			messageBodyPart1.setText("Please find the report attached");

			// creating second MimeBodyPart object
			BodyPart messageBodyPart2 = new MimeBodyPart(); 
			String filename = "C:\\Users\\lenovo\\Desktop\\eclipse\\test.framework\\test-output\\ExtentReportResults.html";
			DataSource source = new FileDataSource(filename);  
			messageBodyPart2.setDataHandler(new DataHandler(source));  
			messageBodyPart2.setFileName(filename);  

			// creating MultiPart object
			Multipart multipartObject = new MimeMultipart();  
			multipartObject.addBodyPart(messageBodyPart1);  
			multipartObject.addBodyPart(messageBodyPart2);



			// set body of the email.
			message.setContent(multipartObject);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}

