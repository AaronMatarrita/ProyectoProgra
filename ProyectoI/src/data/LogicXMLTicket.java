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


public class LogicXMLTicket {
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

						if(ticketNumberNode != null && passportNode != null && flightNumberNode != null && tickettypeNode != null) {
							
							String ticketNumber = element.getElementsByTagName("TicketNumber").item(0).getTextContent();
							String passport = element.getElementsByTagName("Passport").item(0).getTextContent();
							String flightNumber = element.getElementsByTagName("FlightNumber").item(0).getTextContent();
							String tickettype = element.getElementsByTagName("TicketType").item(0).getTextContent();
							
							boolean ticketExists = false;
							for (Ticket existingTicket : ticketsList) {
								if (existingTicket.getTicketNumber() == Integer.parseInt(ticketNumber)) {
									ticketExists = true;
									break;
								}
							}
							if (!ticketExists) {
								Ticket ticket = new Ticket(Integer.parseInt(ticketNumber), passport, Integer.parseInt(flightNumber),tickettype);
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
	public boolean isSeastAvaiable(AirplaneModel model) {
		if(model.getBusinessClassSeats() >= 1 || model.getEconomyClassSeats() >= 1 || model.getTouristClassSeats() >= 1) {
			return true;
		}
		return false;
	}
}