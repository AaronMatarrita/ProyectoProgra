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

public class LogicXML {

	public LogicXML() {}

	//Verificar si un objeto se encuentra en un archivo XML
	public boolean isAlreadyInFile(String fileName, String elementType, String identifierName, String identifierValue) {
		File inputFile = new File(fileName);
		if (!inputFile.exists()) {
			return false;
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputFile);
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName(elementType);

			if (nodeList.getLength() == 0) {
				return false;
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					NodeList identifierList = element.getElementsByTagName(identifierName);
					if (identifierList.getLength() > 0) {
						Node identifierNode = identifierList.item(0);
						if (identifierNode != null && identifierNode.getTextContent() != null) {
							String identifier = identifierNode.getTextContent();
							if (identifierValue.equals(identifier)) {
								return true;
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}