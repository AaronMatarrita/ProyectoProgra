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
	//------------------------------------------------------------------
	//Método para obtener una lista de tipo AirplaneModel
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

						Node modelNameNode = element.getElementsByTagName("modelName").item(0);
						Node brandNode = element.getElementsByTagName("brand").item(0);
						Node businessClassSeatsNode = element.getElementsByTagName("BusinessClassSeats").item(0);
						Node touristClassSeatsNode = element.getElementsByTagName("TouristClassSeats").item(0);
						Node economyClassSeatsNode = element.getElementsByTagName("EconomyClassSeats").item(0);

						if (modelNameNode != null && brandNode != null && businessClassSeatsNode != null && touristClassSeatsNode != null && economyClassSeatsNode != null) {
							String modelName = modelNameNode.getTextContent();
							String brand = brandNode.getTextContent();
							int businessClassSeats = Integer.parseInt(businessClassSeatsNode.getTextContent());
							int touristClassSeats = Integer.parseInt(touristClassSeatsNode.getTextContent());
							int economyClassSeats = Integer.parseInt(economyClassSeatsNode.getTextContent());

							boolean modelExists = false;
							for (AirplaneModel existingModel : airplaneModels) {
								if (existingModel.getName().equals(modelName)) {
									modelExists = true;
									break;
								}
							}
							if (!modelExists) {
								AirplaneModel airplaneModel = new AirplaneModel();
								airplaneModel.setName(modelName);
								airplaneModel.setBrand(brand);
								airplaneModel.setBusinessClassSeats(businessClassSeats);
								airplaneModel.setTouristClassSeats(touristClassSeats);
								airplaneModel.setEconomyClassSeats(economyClassSeats);
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
	//Método para obtener un modelo en especifico
	public AirplaneModel getAirplaneModelFromXML(String fileName, String objectName, String attributeName, String attributeValue) {
	    ArrayList<AirplaneModel> airplaneModels = readXMLFile(fileName);
	    for (AirplaneModel airplaneModel : airplaneModels) {
	        if (airplaneModel.getName().equals(attributeValue)) {
	            return airplaneModel;
	        }
	    }
	    return null;
	}
	//------------------------------------------------------------------
	//Método para obtener una lista de TIPO String con los modelos
	public ArrayList<String> getAirplaneModels(String filename) {
	    ArrayList<String> models = new ArrayList<>();
	    ArrayList<AirplaneModel> airplaneModels = readXMLFile(filename);
	    for (AirplaneModel model : airplaneModels) {
	        models.add(model.getName());
	    }
	    return models;
	}
}
