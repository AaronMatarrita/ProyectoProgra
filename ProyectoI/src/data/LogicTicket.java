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
import domain.Ticket;


public class LogicTicket {
	//Obtener un arrayList con de los tiquetes
	public ArrayList<Ticket> readXMLFile(String filename) {
		ArrayList<Ticket> ticketsList = new ArrayList<>();
		File file = new File(filename);

		if (!file.exists()) {
			return ticketsList;
		} else {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);
				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName("Tickets");
				if (nodeList.getLength() == 0) {
					return ticketsList;
				}

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						Node ticketNumberNode = element.getElementsByTagName("TicketNumber").item(0);
						Node passportNode = element.getElementsByTagName("Passport").item(0);
						Node flightNumberNode = element.getElementsByTagName("FlightNumber").item(0);
						Node tickettypeNode = element.getElementsByTagName("TicketType").item(0);
						Node buydateNode = element.getElementsByTagName("BuyTicketDate").item(0);

						if(ticketNumberNode != null && passportNode != null && flightNumberNode != null && tickettypeNode != null && buydateNode != null) {                     

							String ticketNumber = ticketNumberNode.getTextContent();
							String passport = passportNode.getTextContent();
							String flightNumber = flightNumberNode.getTextContent();
							String tickettype = tickettypeNode.getTextContent();
							String buydate = buydateNode.getTextContent();

							boolean ticketExists = false;
							for (Ticket existingTicket : ticketsList) {
								if (existingTicket.getTicketNumber() == Integer.parseInt(ticketNumber)) {
									ticketExists = true;
									break;
								}
							}
							if (!ticketExists) {
								Ticket ticket = new Ticket(Integer.parseInt(ticketNumber), passport, Integer.parseInt(flightNumber),tickettype,buydate);
								ticketsList.add(ticket);
							}
						}
					}
				}
			} catch (ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ticketsList;
	}
	//Método para obtener un Ticket en especifico
	public Ticket getTicketFromXML(String fileName, String attributeValue) {
		ArrayList<Ticket> tickets = readXMLFile(fileName);

		for (Ticket ticket : tickets) {
			int number= ticket.getTicketNumber();
			if (String.valueOf(number).equals(attributeValue)) {
				return ticket;
			}
		}
		return null;
	}
	//Método para verificar la disponibilidad del espacio
	public boolean isSeatsAvailable(String fileName, String airplaneModel, AirplaneModel model) {
	    int businessClassSeats = model.getBusinessClassSeats();
	    int economyClassSeats = model.getEconomyClassSeats();
	    int touristClassSeats = model.getTouristClassSeats();
	    
	    ArrayList<Ticket> flightTicketsList = getFlightsByModel(fileName, airplaneModel, model);
	    
	    for (Ticket ticket : flightTicketsList) {
	        String ticketType = ticket.getTickettype();
	        
	        switch (ticketType) {
	            case "Ejecutivo":
	                businessClassSeats--;
	                break;
	            case "Economico":
	                economyClassSeats--;
	                break;
	            case "Turista":
	                touristClassSeats--;
	                break;
	        }
	    }
	    return (businessClassSeats > 0 || economyClassSeats > 0 || touristClassSeats > 0);
	}
	//Método para obtener la cantidad de vuelos con un modelo especifico
	private ArrayList<Ticket> getFlightsByModel(String fileName, String airplaneModel, AirplaneModel model) {
	    ArrayList<Ticket> flightTicketsList = new ArrayList<>();
	    ArrayList<Ticket> tickets = readXMLFile(fileName);

	    for (Ticket ticket : tickets) {
	        if (model != null && model.getName().equals(airplaneModel)) {
	            flightTicketsList.add(ticket);
	        }
	    }

	    return flightTicketsList;
	}
	//Obtener cantidad de asientos vendidos por tipo:
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public int bussinesSoldSeats(String fileName, String airplaneModel, AirplaneModel model) {
	    ArrayList<Ticket> flightTicketsList = getFlightsByModel(fileName, airplaneModel, model);
	    
	    int businessClassSeats = 0;
	    
	    for (Ticket ticket : flightTicketsList) {
	        String ticketType = ticket.getTickettype();
	        
	        if (ticketType.equals("Ejecutivo")) {
	            businessClassSeats++;
	        }
	    }
	    
	    return businessClassSeats;
	}
	
	public int touristSoldSeats(String fileName, String airplaneModel, AirplaneModel model) {
	    ArrayList<Ticket> flightTicketsList = getFlightsByModel(fileName, airplaneModel, model);
	    
	    int touristClassSeats = 0;
	    
	    for (Ticket ticket : flightTicketsList) {
	        String ticketType = ticket.getTickettype();
	        
	        if (ticketType.equals("Turista")) {
	        	touristClassSeats++;
	        }
	    }
	    
	    return touristClassSeats;
	}
	
	public int economicSoldSeats(String fileName, String airplaneModel, AirplaneModel model) {
	    ArrayList<Ticket> flightTicketsList = getFlightsByModel(fileName, airplaneModel, model);
	    
	    int economicClassSeats = 0;
	    
	    for (Ticket ticket : flightTicketsList) {
	        String ticketType = ticket.getTickettype();
	        
	        if (ticketType.equals("Economico")) {
	        	economicClassSeats++;
	        }
	    }
	    
	    return economicClassSeats;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
}