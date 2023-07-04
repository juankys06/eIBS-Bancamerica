package datapro.eibs.generic;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.soap.Body;
import org.apache.soap.Envelope;
import org.apache.soap.Header;
import org.apache.soap.SOAPException;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.rpc.SOAPContext;
import org.apache.soap.util.Bean;
import org.apache.soap.util.xml.NSStack;
import org.apache.soap.util.xml.Serializer;
import org.apache.soap.util.xml.XMLJavaMappingRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class SoapEnvComposer implements Serializer {

	private String mesgStr;
	
	public SoapEnvComposer(String mesgStr){
		this.mesgStr = mesgStr;
	}
	

	/**
	 * @see Serializer#marshall(String, Class, Object, Object, Writer, NSStack, XMLJavaMappingRegistry, SOAPContext)
	 */
	public void marshall(
		String inScopeEncStyle,
		Class javaType,
		Object src,
		Object context,
		Writer sink,
		NSStack snStack,
		XMLJavaMappingRegistry xjmr,
		SOAPContext ctx)
		throws IllegalArgumentException, IOException {
		snStack.pushScope();
		sink.write("<ns:processMessage xmlns:ns=\"http://www.efunds.com/soap/MessageService/processMessage\">");
		if( mesgStr != null ){
			sink.write(mesgStr);
		}
		sink.write("</ns:processMessage>");
		snStack.popScope();
	}

	public static Envelope createSoapEnvelope(String samlStr, String mesgStr){
		try{	
			Element assertionElement = null;
		
			if( samlStr != null ){
				
				StringReader strReader = new StringReader(samlStr);
				InputSource is = new InputSource(strReader);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setValidating(false);
				dbf.setIgnoringComments(true);
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(is);
				assertionElement = doc.getDocumentElement();
			}
		
			return createSoapEnvelope(assertionElement, mesgStr);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Envelope createSoapEnvelope(Element assertionElement, String mesgStr){
		try{	
			Envelope env = new Envelope();
		
			if( assertionElement != null ){
				
				Header header = new Header();
				Vector headEntries = new Vector();
				headEntries.add(assertionElement);
//				headEntries.add(samlStr);
				header.setHeaderEntries(headEntries);
				env.setHeader(header);
			}
			
		
			Body body = new Body();
			Vector bodyEntries = new Vector();
			bodyEntries.add(new Bean(SoapEnvComposer.class, new SoapEnvComposer(mesgStr)));
			body.setBodyEntries(bodyEntries);
			env.setBody(body);

//			System.out.println(soapEnvToString(env));
		
			return env;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static String soapEnvToString(Envelope env)throws SOAPException, IOException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		StringWriter payloadSW = new StringWriter();
		env.marshall(payloadSW, smr);
		return payloadSW.toString();
		
	}
}
