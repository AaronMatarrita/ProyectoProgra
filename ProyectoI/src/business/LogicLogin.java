package business;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LogicLogin {

	public LogicLogin() {}

	public boolean verify(String address,String usuario, String contraseña) {
		File file = new File(address);
		if (!file.exists()) {
			return false;
		} else {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document document = builder.parse(file);

				Element root = document.getDocumentElement();

				NodeList nodeList = root.getElementsByTagName("person");

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element elementoPerson = (Element) node;

						String usuarioXML = elementoPerson.getElementsByTagName("user").item(0).getTextContent();
						String contraseñaXML = elementoPerson.getElementsByTagName("password").item(0).getTextContent();
						String estadoDeUsuario = elementoPerson.getElementsByTagName("status").item(0).getTextContent();

						if (usuario.equals(usuarioXML) && contraseña.equals(contraseñaXML) && estadoDeUsuario.equalsIgnoreCase("true")) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public String getUserType(String address, String username) {
	    File file = new File(address);
	    if (!file.exists()) {
	        return null;
	    } else {
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(file);
	            Element root = document.getDocumentElement();
	            NodeList nodeList = root.getElementsByTagName("person");

	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elementPerson = (Element) node;
	                    String usernameXML = elementPerson.getElementsByTagName("user").item(0).getTextContent();
	                    String userTypeXML = elementPerson.getElementsByTagName("userType").item(0).getTextContent();

	                    if (username.equals(usernameXML)) {
	                        return userTypeXML;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}
	
	
	public String getUserStatus(String address, String username) {
	    File file = new File(address);
	    if (!file.exists()) {
	        return null;
	    } else {
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(file);
	            Element root = document.getDocumentElement();
	            NodeList nodeList = root.getElementsByTagName("person");

	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elementPerson = (Element) node;
	                    String usernameXML = elementPerson.getElementsByTagName("user").item(0).getTextContent();
	                    String userTypeXML = elementPerson.getElementsByTagName("status").item(0).getTextContent();

	                    if (username.equals(usernameXML)) {
	                        return userTypeXML;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}
}