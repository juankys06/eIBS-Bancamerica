/*
 * Created on Jun 12, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;

import datapro.eibs.generic.SimpleAthenticator;
import datapro.eibs.master.JSEIBSProp;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendEMail {

	//EMail Properties
	private String host = "";
	private String protocol = "";
	private String to = "";
	private String cc = "";
	private String toerror = "";
	private String from = "";
	private String fromalias = "";
	private String subject = "";
	private String text = "";
	private String user = "";
	private String password = "";
	private String path = "";
	private String filename = "";
	private String error = "0";
	
	
	public SendEMail() {
		super();
	}
	
	public SendEMail(String attach) {
		super();
		this.filename = attach;
	}
	
	/**
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return
	 */
	public String getFromalias() {
		return fromalias;
	}

	/**
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @return
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param string
	 */
	public void setCc(String string) {
		cc = string;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param string
	 */
	public void setFrom(String string) {
		from = string;
	}

	/**
	 * @param string
	 */
	public void setFromalias(String string) {
		fromalias = string;
	}

	/**
	 * @param string
	 */
	public void setHost(String string) {
		host = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public void setProtocol(String string) {
		protocol = string;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string) {
		subject = string;
	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

	/**
	 * @param string
	 */
	public void setTo(String string) {
		to = string;
	}

	/**
	 * @param string
	 */
	public void setUser(String string) {
		user = string;
	}
	
	/**
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param string
	 */
	public void setFilename(String string) {
		filename = string;
	}

	/**
	 * @param string
	 */
	public void setPath(String string) {
		path = string;
	}
	
	/**
	 * @return 
	 */
	public String getError() {
		return error;
	}
	
	public boolean hasError() {
		return !error.equals("0");
	}
	
	public void setProperties(String propertyFileName) {
		PropertyResourceBundle appProp = (PropertyResourceBundle) PropertyResourceBundle.getBundle(propertyFileName);
		// Read email pool sender properties
		host = (appProp.getString("email.server.host") == null) ? "" : appProp.getString("email.server.host");
		protocol = (appProp.getString("email.server.protocol") == null) ? "" : appProp.getString("email.server.protocol");
		from = (appProp.getString("email.from") == null) ? "" : appProp.getString("email.from");
		fromalias = (appProp.getString("email.fromalias") == null) ? "" : appProp.getString("email.fromalias");
		subject = (appProp.getString("email.subject") == null) ? "" : appProp.getString("email.subject");
		text = (appProp.getString("email.message") == null) ? "" : appProp.getString("email.message");
		user = (appProp.getString("email.username") == null) ? "" : appProp.getString("email.username");
		password = (appProp.getString("email.password") == null) ? "" : appProp.getString("email.password");
		path = (appProp.getString("email.path") == null) ? "" : appProp.getString("email.path");
		toerror = (appProp.getString("email.toerror") == null) ? "" : appProp.getString("email.toerror");
		cc = (appProp.getString("email.cc") == null) ? "" : appProp.getString("email.cc");
		to = (appProp.getString("email.to") == null) ? "" : appProp.getString("email.to");
		if (to.equals("")) to = toerror;
	}
	
	public void send() {
		Properties props = new Properties();
		// Setup mail server
		props.put("mail." + protocol.toLowerCase().trim() + ".host", host);
		// Athentication
		SimpleAthenticator sa = null;
		if (!user.equals("")) {
			sa = new SimpleAthenticator(user, password);
		}
		// Get session
		Session session = Session.getDefaultInstance(props, sa);
		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			// Set the from address
			message.setFrom(new InternetAddress(from, fromalias));
			// Set the to address
			if (to.equals("")) to = "erodriguez@datapromiami.com";
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set the cc address
			if (!cc.equals("")) message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			// Set the subject
			message.setSubject(subject);
			// Set the content
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(text);
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			// Set attachment
			if (filename != null) {
				MimeBodyPart mbp2 = new MimeBodyPart();
				File filetoattach = new File(path + filename);
				FileDataSource fds = new FileDataSource(filetoattach);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
			}
			message.setContent(mp);
			// Send message
			Transport.send(message);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			error = "UnsupportedEncodingException. See details in Log Files";
		} catch (MessagingException e) {
			e.printStackTrace();
			error = "MessagingException. See details in Log Files";
		}
		
	}
	
}
