package business;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Airline;

public class LogicAirline {

	public LogicAirline() {}	
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	//Método para obtener una lista de TIPO Airline
	public ArrayList<Airline> readXMLFile(String filename) {
		ArrayList<Airline> airlines = new ArrayList<>();
		try {
			File file = new File(filename);
			if (!file.exists()) {
				return airlines;
			}

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("airlines");

			if (nodeList.getLength() == 0) {
				return airlines;
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					Node nameNode = element.getElementsByTagName("Name").item(0);
					Node countryNode = element.getElementsByTagName("Country").item(0);

					if (nameNode != null && countryNode != null) {
						String name = nameNode.getTextContent();
						String country = countryNode.getTextContent();

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airlines;
	}
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	//Método para obtener una lista de TIPO String con las aerolíneas
	public ArrayList<String> getAirlineList(String filename) {
		ArrayList<String> airlineList = new ArrayList<>();
		ArrayList<Airline> airlines = readXMLFile(filename);
		for (Airline airline : airlines) {
			airlineList.add(airline.getName());
		}
		return airlineList;
	}
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	//Método para obtener la aerolinea con el nombre a busar en el xml
	public Airline getAirlineFromFile(String fileName, String attributeValue) {
		ArrayList<Airline> airlines = readXMLFile(fileName);
		for(Airline airline : airlines) {
			if(airline.getName().equals(attributeValue)) {
				return airline;
			}
		}
		return null;
	}
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	//Método para obtener un arraylist de la aerolinea a buscar
	public ArrayList<Airline> getArrayAirlineFromXML(String fileName, String attributeValue) {
		ArrayList<Airline> airlines = readXMLFile(fileName);
		ArrayList<Airline> matchingAirlines = new ArrayList<>();

		for (Airline airline : airlines) {
			if (airline.getName().equals(attributeValue)) {
				matchingAirlines.add(airline);
			}
		}
		return matchingAirlines;
	}
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
}