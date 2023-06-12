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

import domain.Tickets;

public class LogicXMLTicket {
public ArrayList<Tickets> readXMLFile(String filename) {
		ArrayList<Tickets> ticketsList = new ArrayList<>();
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
					if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						Node ticketNumberNode = element.getElementsByTagName("TicketNumber").item(0);
						Node passportNode = element.getElementsByTagName("Passport").item(0);
						Node flightNumberNode = element.getElementsByTagName("FlightNumber").item(0);

						// Verificar si los nodos existen antes de obtener su contenido
						String ticketNumber = (ticketNumberNode != null) ? ticketNumberNode.getTextContent() : "";
						String passport = (passportNode != null) ? passportNode.getTextContent() : "";
						String flightNumber = (flightNumberNode != null) ? flightNumberNode.getTextContent() : "";

						// Verificar si alguno de los campos requeridos está vacío
						if (ticketNumber.isEmpty() || passport.isEmpty() || flightNumber.isEmpty()) {
							continue; // Saltar este elemento y pasar al siguiente
						}

						boolean passengerExists = false;
						for (Tickets existingTicket : ticketsList) {
							if (existingTicket.getPassport().equals(passport)) {
								passengerExists = true;
								break;
							}
						}
						if (!passengerExists) {
							Tickets ticket = new Tickets(Integer.parseInt(ticketNumber), passport, Integer.parseInt(flightNumber));
							ticketsList.add(ticket);
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
}