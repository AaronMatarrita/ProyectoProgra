package data;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LogicXMLUser {
	
	public LogicXMLUser() {}
	
	public boolean verify(String address,String usuario, String contraseña) {
        try {
			File inputFile = new File(address);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(inputFile);
 
            Element root = document.getDocumentElement();

            NodeList nodeList = root.getElementsByTagName("person");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoPerson = (Element) node;
                    
                    String usuarioXML = elementoPerson.getElementsByTagName("user").item(0).getTextContent();
                    String contraseñaXML = elementoPerson.getElementsByTagName("password").item(0).getTextContent();

                    if (usuario.equals(usuarioXML) && contraseña.equals(contraseñaXML)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public boolean isAlreadyUser(String username, String xmlFilePath) {
        try {
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();
            
            NodeList nodeList = document.getElementsByTagName("person");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String user = element.getElementsByTagName("user").item(0).getTextContent();
                    if (username.equals(user)) {
                        return true;
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
