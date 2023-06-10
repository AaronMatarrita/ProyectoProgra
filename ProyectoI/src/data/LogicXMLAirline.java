package data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Airline;

public class LogicXMLAirline {

	public LogicXMLAirline() {}	

	public ArrayList<Airline> readXMLFile(String filename) {
		ArrayList<Airline> airlines = new ArrayList<>();
		try {
			File file = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("airline");

			if (nodeList.getLength() == 0) {
				return airlines;
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String country = element.getElementsByTagName("country").item(0).getTextContent();
					
					boolean airlineExists = false;
					for (Airline existingAirline : airlines) {
						if (existingAirline.getName().equals(name)) {
							airlineExists = true;
							break;
						}
					}
					if (!airlineExists) {
						Airline airline = new Airline();
						airline.setName(name);
						airline.setCountry(country);
						airlines.add(airline);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airlines;
	}

}