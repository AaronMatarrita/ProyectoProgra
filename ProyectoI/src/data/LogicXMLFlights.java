package data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Flights;

public class LogicXMLFlights {

	public LogicXMLFlights() {}	

	public ArrayList<Flights> readXMLFile(String filename) {
	    ArrayList<Flights> flights = new ArrayList<>();
	    File file = new File(filename);
	    if (!file.exists()) {
	        return flights;
	    } else {
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(file);
	            doc.getDocumentElement().normalize();

	            NodeList nodeList = doc.getElementsByTagName("flights");

	            if (nodeList.getLength() == 0) {
	                return flights;
	            }

	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    int flightNumber = Integer.parseInt(element.getElementsByTagName("FlightNumber").item(0).getTextContent());
	                    String depCity = element.getElementsByTagName("DepartureCity").item(0).getTextContent();
	                    String depDate = element.getElementsByTagName("DepartureDate").item(0).getTextContent();
	                    String depTime = element.getElementsByTagName("DepartureTime").item(0).getTextContent();
	                    String arrCity = element.getElementsByTagName("ArrivalCity").item(0).getTextContent();
	                    String arrDate = element.getElementsByTagName("ArrivalDate").item(0).getTextContent();
	                    String arrTime = element.getElementsByTagName("ArrivalTime").item(0).getTextContent();
	                    String airplane = element.getElementsByTagName("Airplane").item(0).getTextContent();
	                    double businessPrice = Double.parseDouble(element.getElementsByTagName("BusinessClassSeatsPrice").item(0).getTextContent());
	                    double touristPrice = Double.parseDouble(element.getElementsByTagName("TouristClassSeatsPrice").item(0).getTextContent());
	                    double economyPrice = Double.parseDouble(element.getElementsByTagName("EconomyClassSeatsPrice").item(0).getTextContent());

	                    boolean flightExists = false;
	                    for (Flights existingFlight : flights) {
	                        if (existingFlight.getFlightNumber() == flightNumber) {
	                            flightExists = true;
	                            break;
	                        }
	                    }
	                    if (!flightExists) {
	                        Flights flight = new Flights();
	                        flight.setFlightNumber(flightNumber);
	                        flight.setDepartureCity(depCity);
	                        flight.setDepartureDate(depDate);
	                        flight.setDepartureTime(depTime);
	                        flight.setArrivalCity(arrCity);
	                        flight.setArrivalDate(arrDate);
	                        flight.setArrivalTime(arrTime);
	                        flight.setAirplane(airplane);
	                        flight.setBusinessClassSeatsPrice(businessPrice);
	                        flight.setTouristClassSeatsPrice(touristPrice);
	                        flight.setEconomyClassSeatsPrice(economyPrice);
	                        flights.add(flight);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return flights;
	    }
	}
}