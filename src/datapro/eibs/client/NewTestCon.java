/*
 * Created on May 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;

import com.sun.net.ssl.HttpsURLConnection;

import datapro.eibs.generic.SoapEnvComposer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

// Added by eFunds

import org.apache.soap.Envelope;

/**
 * @author ogarcia
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NewTestCon {

	String userid = "dataprostagingwcp@datapro.com";
	String password = "123lemo";

	/**
	 * 
	 */
	public NewTestCon() {
		super();
		initSSL();
	}
	
	public void action() throws Exception {
		URL url =
			new URL("https://www11-at.efunds.com/WaStage/ProductInquiry");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/soap");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		OutputStream os = conn.getOutputStream();
		OutputStreamWriter out = new OutputStreamWriter(os);
		PrintWriter urlWriter = new PrintWriter(out);
		StringBuffer rqStrBuf = new StringBuffer();
		// Added By eFunds
		StringBuffer samlStrBuf = new StringBuffer();
		
		// Commented By eFunds
		/*
		rqStrBuf.append(
			"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schema.xmlsoap.org/soap/envelope/\"");
		rqStrBuf.append(
			"xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\"");
		rqStrBuf.append("xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"");
		rqStrBuf.append("	<SOAP-ENV:Header>");
		*/
		
		// Changed from rqStrBuf to samlStrBuf by eFunds
		samlStrBuf.append("<saml:AssertionList AssertionID=\"134.177.251.109.1086373435688\" IssueInstant=\"2004-06-04T18:23:55Z\" Issuer=\"dataprostagingwcp@datapro.com\" MajorVersion=\"1\" MinorVersion=\"0\" xmlns:saml=\"http://www.oasis-open.org/committees/security/docs/draft-sstc-schema-assertion-16.xsd\">		<saml:Conditions NotBefore=\"2004-06-04T18:16:58Z\" NotOnOrAfter=\"2004-06-04T18:31:58Z\">		<saml:AbstractCondition xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"AudienceRestrictionConditionType\">		<saml:Audience>www.efunds.com/service/message/processMessage</saml:Audience>		</saml:AbstractCondition>		</saml:Conditions>		<saml:AttributeStatement>		<saml:Subject>		<saml:NameIdentifier Name=\"dataprostagingwcp@datapro.com\" SecurityDomain=\"www.efunds-client.com\"/>		<saml:SubjectConfirmation>		<saml:ConfirmationMethod>http://www.oasis-open.org/committees/security/docs/draft-sstc-core-25/password</saml:ConfirmationMethod>		<saml:SubjectConfirmationData>123lemo</saml:SubjectConfirmationData>		</saml:SubjectConfirmation>		</saml:Subject>		<saml:Attribute AttributeName=\"AssertionType\" AttributeNamespace=\"http://www.efunds.com/message/assertion/type.xsd\">		<saml:AttributeValue>		<AssertionType>InquiryAuthentication</AssertionType>		</saml:AttributeValue>		</saml:Attribute>		</saml:AttributeStatement>		</saml:AssertionList>");		

		// Commented By eFunds
		/*
		rqStrBuf.append("	<SOAP-ENV:Header>");
		rqStrBuf.append("	<SOAP-ENV:Body>");
		rqStrBuf.append(
			"		<ns:processMessage xmlns:ns=\"http://www.efunds.com/soap/MessageService/processMessage\">");
		*/
		
		rqStrBuf.append("<Request xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'");
		rqStrBuf.append(" xsi:noNamespaceSchemaLocation='ecs_request.xsd'>");
		rqStrBuf.append("<Transaction>");
		rqStrBuf.append("<CustomerId>10000000</CustomerId>");
		rqStrBuf.append("<ChexSystemsVersionNbr>V006</ChexSystemsVersionNbr>");
		rqStrBuf.append("</Transaction>");
		rqStrBuf.append("<Consumer>");
		rqStrBuf.append("<PersonConsumer personConsumerType=\"First\">");
		rqStrBuf.append("<LastNm>TESTLASTAA</LastNm>");
		rqStrBuf.append("<FirstNm>EMMA</FirstNm>");
		rqStrBuf.append("<MiddleNm>J</MiddleNm>");
		rqStrBuf.append("<PersonIdentifiers>");
		rqStrBuf.append("<GovernmentNbr>521610000</GovernmentNbr>");
		rqStrBuf.append("</PersonIdentifiers>");
		rqStrBuf.append("</PersonConsumer>");
		rqStrBuf.append("<CurrentAddress>");
		rqStrBuf.append("<StreetAddressTxt>32245 XTRA WAY</StreetAddressTxt>");
		rqStrBuf.append("<CityNm>CHICAGO</CityNm>");
		rqStrBuf.append("<StateCd>IL</StateCd>");
		rqStrBuf.append("</CurrentAddress>");
		rqStrBuf.append("</Consumer>");
		rqStrBuf.append("</Request>");

			
		//Commented By eFunds
		/*
		rqStrBuf.append("		</ns:processMessage>");
		rqStrBuf.append("	</SOAP-ENV:Body>");
		rqStrBuf.append("</SOAP-ENV:Envelope>");
		*/
		Envelope env =
				SoapEnvComposer.createSoapEnvelope(
					samlStrBuf.toString(),
					rqStrBuf.toString());
					
					String envStr = SoapEnvComposer.soapEnvToString(env);

		urlWriter.println(envStr);
		System.out.println(envStr);
		
		// Added by eFunds
		
		urlWriter.close();

		// response
		if (conn.getResponseCode() == 200) {
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer rpStrBuf = new StringBuffer();
			String tempStr = null;
			while ((tempStr = br.readLine()) != null) {
				rpStrBuf.append(tempStr);
			}
			System.out.println("The Output is : " + rpStrBuf);
		}
	}

	public void initSSL() {
		// rel:/conf/client.keystore
		String KeyStore = "c:/aaa/client.keystore";
		// password
		String KeyStorePW = "password";

		try {
			//if the string starts with "rel:" treat it as a relative path otherwise treat as an 
			// absolute path
			StringTokenizer checkRel = new StringTokenizer(KeyStore, ":");
			if (checkRel.hasMoreTokens()) {
				String rel = checkRel.nextToken();
				if (rel.compareTo("rel") == 0) {
					// KeyStore = this.getServletContext().getRealPath(checkRel.nextToken());
				}
			}

			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			// Here's the trick!
			// Simply set the protocol handler property to use SSL.
			System.setProperty(
				"java.protocol.handler.pkgs",
				"com.sun.net.ssl.internal.www.protocol");
				//changed by efunds 
			System.setProperty("javax.net.ssl.keyStore",KeyStore);
			System.setProperty("javax.net.ssl.keyStorePassword", KeyStorePW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		NewTestCon tst = new NewTestCon();
		try {
			tst.action();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
