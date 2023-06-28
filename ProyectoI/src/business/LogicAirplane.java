package business;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Airplane;

public class LogicAirplane {

	public LogicAirplane() {}	
	//------------------------------------------------------------------
	//Método para obtener una lista de TIPO Airplane
	public ArrayList<Airplane> readXMLFile(String filename) {
	    ArrayList<Airplane> airplanes = new ArrayList<>();
	    File file = new File(filename);
	    if (!file.exists()) {
	        return airplanes;
	    } else {
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(file);
	            doc.getDocumentElement().normalize();

	            NodeList nodeList = doc.getElementsByTagName("airplanes");

	            if (nodeList.getLength() == 0) {
	                return airplanes;
	            }

	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    
	                    Node idNode = element.getElementsByTagName("id").item(0);
	                    Node yearNode = element.getElementsByTagName("year").item(0);
	                    Node airlineNode = element.getElementsByTagName("airline").item(0);
	                    Node modelNode = element.getElementsByTagName("model").item(0);

	                    if (idNode != null && yearNode != null && airlineNode != null && modelNode != null) {
	                        String id = idNode.getTextContent();
	                        String year = yearNode.getTextContent();
	                        String airline = airlineNode.getTextContent();
	                        String model = modelNode.getTextContent();

	                        boolean airplaneExists = false;
	                        for (Airplane existingAirplane : airplanes) {
	                            if (existingAirplane.getId().equals(id)) {
	                                airplaneExists = true;
	                                break;
	                            }
	                        }
	                        if (!airplaneExists) {
	                            Airplane air = new Airplane();
	                            air.setId(id);
	                            air.setYear(year);
	                            air.setAirline(airline);
	                            air.setAirplaneModel(model);
	                            airplanes.add(air);
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return airplanes;
	    }
	}
	//------------------------------------------------------------------
	//Método para obtener una lista de TIPO String con los aviones
	public ArrayList<String> getAirplaneList(String filename) {
	    ArrayList<String> airplaneList = new ArrayList<>();
	    ArrayList<Airplane> airplanes = readXMLFile(filename);
	    for (Airplane airplane : airplanes) {
	        airplaneList.add(airplane.getId());
	    }
	    return airplaneList;
	}
	//Método para obtener un avión en especifico
	public Airplane getAirplaneFromXML(String fileName, String attributeValue) {
	    ArrayList<Airplane> airplanes = readXMLFile(fileName);
	    for (Airplane airplane : airplanes) {
	        if (airplane.getId().equals(attributeValue)) {
	            return airplane;
	        }
	    }
	    return null;
	}
	//Metodo para obtener un ARRAYLIST de un avión en especifico
		public ArrayList<Airplane> getArrayAirplaneFromXML(String fileName, String attributeValue) {
		    ArrayList<Airplane> airplanes = readXMLFile(fileName);
		    ArrayList<Airplane> matchingAirplanes = new ArrayList<>();

		    for (Airplane airplane : airplanes) {
		        if (airplane.getId().equals(attributeValue)) {
		            matchingAirplanes.add(airplane);
		        }
		    }

		    return matchingAirplanes;
		}
}