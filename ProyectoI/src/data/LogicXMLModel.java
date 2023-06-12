package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domain.AirplaneModel;

public class LogicXMLModel {

	public ArrayList<AirplaneModel> readXMLFile(String filename) {
	    ArrayList<AirplaneModel> airplaneModels = new ArrayList<>();
	    File file = new File(filename);
	    if (!file.exists()) {
	        return airplaneModels;
	    } else {
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(file);
	            doc.getDocumentElement().normalize();

	            NodeList nodeList = doc.getElementsByTagName("models");
	            if (nodeList.getLength() == 0) {
	                return airplaneModels;
	            }

	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    if (isValidModelElement(element)) {
	                        String modelName = element.getElementsByTagName("modelName").item(0).getTextContent();
	                        String brand = element.getElementsByTagName("brand").item(0).getTextContent();
	                        int businessClassSeats = Integer.parseInt(element.getElementsByTagName("BusinessClassSeats").item(0).getTextContent());
	                        int touristClassSeats = Integer.parseInt(element.getElementsByTagName("TouristClassSeats").item(0).getTextContent());
	                        int economyClassSeats = Integer.parseInt(element.getElementsByTagName("EconomyClassSeats").item(0).getTextContent());

	                        AirplaneModel airplaneModel = new AirplaneModel();
	                        airplaneModel.setName(modelName);
	                        airplaneModel.setBrand(brand);
	                        airplaneModel.setBusinessClassSeats(businessClassSeats);
	                        airplaneModel.setTouristClassSeats(touristClassSeats);
	                        airplaneModel.setEconomyClassSeats(economyClassSeats);

	                        boolean modelExists = false;
	                        for (AirplaneModel existingModel : airplaneModels) {
	                            if (existingModel.getName().equals(modelName)) {
	                                modelExists = true;
	                                break;
	                            }
	                        }
	                        if (!modelExists) {
	                            airplaneModels.add(airplaneModel);
	                        }
	                    }
	                }
	            }
	        } catch (ParserConfigurationException | SAXException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return airplaneModels;
	}

	private boolean isValidModelElement(Element element) {
	    return element.getElementsByTagName("modelName").getLength() > 0
	            && element.getElementsByTagName("brand").getLength() > 0
	            && element.getElementsByTagName("BusinessClassSeats").getLength() > 0
	            && element.getElementsByTagName("TouristClassSeats").getLength() > 0
	            && element.getElementsByTagName("EconomyClassSeats").getLength() > 0;
	}	
}
