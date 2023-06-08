package data;

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
}
