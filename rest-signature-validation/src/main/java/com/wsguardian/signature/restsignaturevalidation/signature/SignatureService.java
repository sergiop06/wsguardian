package com.wsguardian.signature.restsignaturevalidation.signature;

import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.boot.spi.XmlMappingBinderAccess;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Component
public class SignatureService {
	
	
	public boolean validateSignature(String envelope) {
		
	
		byte[] decodedBytes = Base64.getDecoder().decode(envelope);
		String decodedString = new String(decodedBytes);
		System.out.println("decoded string::: "+decodedString);
		
		Document doc = convertStringToXMLDocument(decodedString);
		doc.getDocumentElement().normalize();
		
		Element root = doc.getDocumentElement();
		
		System.out.println("node name "+root.getNodeName());
		
        
		return true;	
	}
	
	
	private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
