package data;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
//commit+-+-+-+
public class CRUD {

	public Document document;
	public Element rootElement;
	XMLFiles xmlFiles = new XMLFiles();
	public CRUD() {}

	//---------------------Método para agregar objetos al XML---------------------
	public void addObject(String fileName, String elementType, String[] dataName, String[] data) {
		xmlFiles.createXML(fileName, elementType);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));
			doc.getDocumentElement().normalize();

			Element rootElement = doc.getDocumentElement();
			Element ele = doc.createElement(elementType);
			rootElement.appendChild(ele);

			for (int i = 0; i < data.length; i++) {
				Element dato = doc.createElement(dataName[i]);
				dato.appendChild(doc.createTextNode(data[i]));
				ele.appendChild(dato);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);

			System.out.println("Registro Guardado");
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	//---------------------Método para buscar un objeto en el XML---------------------
	public String readObject(String fileName, String elementType, String identifierName, String identifierValue) {
	    try {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(new File(fileName));
	        doc.getDocumentElement().normalize();

	        NodeList nodeList = doc.getElementsByTagName(elementType);

	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Node node = nodeList.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                String value = element.getElementsByTagName(identifierName).item(0).getTextContent();
	                if (value.equals(identifierValue)) {
	                    StringBuilder sb = new StringBuilder();
	                    NodeList children = element.getChildNodes();
	                    for (int j = 0; j < children.getLength(); j++) {
	                        Node child = children.item(j);
	                        if (child.getNodeType() == Node.ELEMENT_NODE) {
	                            sb.append(child.getTextContent().trim());
	                            sb.append(",");
	                        }
	                    }
	                    String userInfo = sb.toString().trim();
	                    if (userInfo.endsWith(",")) {
	                        userInfo = userInfo.substring(0, userInfo.length() - 1);
	                    }
	                    return userInfo;
	                }
	            }
	        }
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	//---------------------Método para actualizar un objeto del XML------------------
	
	//---------------------Método para eliminar un objeto del XML--------------------
	public String deleteObject(String fileName, String elementType, String identifierName, String identifierValue) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName(elementType);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					Element identifierElement = (Element) element.getElementsByTagName(identifierName).item(0);
					if (identifierElement != null) {
						String value = identifierElement.getTextContent();
						if (value.equals(identifierValue)) {
							element.getParentNode().removeChild(element);
							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(doc);
							StreamResult result = new StreamResult(new File(fileName));
							transformer.transform(source, result);
							return "Registro eliminado";
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
		return "Registro no encontrado";
	}
}
