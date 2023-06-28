package business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import domain.TicketsHistory;

public class LogicHistoryTickets {
	// Obtener un arrayList con de los tiquetes
	public List<TicketsHistory> readXMLFile(String filename) {
		ArrayList<TicketsHistory> ticketsList = new ArrayList<>();
		File file = new File(filename);

		if (!file.exists()) {
			return ticketsList;
		} else {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);
				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName("HistoricTickets");
				if (nodeList.getLength() == 0) {
					return ticketsList;
				}

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						Node ticketNumberNode = element.getElementsByTagName("TicketNumber").item(0);
						Node paspassportNode = element.getElementsByTagName("PassengerPassport").item(0);
						Node pasnameNode = element.getElementsByTagName("Name").item(0);
						Node paslastNameNode = element.getElementsByTagName("LastName").item(0);
						Node emailNode = element.getElementsByTagName("Email").item(0);
						Node dateofbirthNode = element.getElementsByTagName("DateOfBirth").item(0);
						Node phonenumberNode = element.getElementsByTagName("PhoneNumber").item(0);
						Node buydateNode = element.getElementsByTagName("BuyTicketDate").item(0);
						Node airlineNode = element.getElementsByTagName("AirlineName").item(0);
						Node operationCenterNode = element.getElementsByTagName("AirplaneOperationCenter").item(0);
						Node idNode = element.getElementsByTagName("AirplaneId").item(0);
						Node airplanearilineNode = element.getElementsByTagName("AirplaneAirline").item(0);
						Node airplaneModelNode = element.getElementsByTagName("AirplaneModel").item(0);
						Node airplaneYearNode = element.getElementsByTagName("AirplaneYear").item(0);
						Node exitcityNode = element.getElementsByTagName("ExitCity").item(0);
						Node exitdateNode = element.getElementsByTagName("ExitDate").item(0);
						Node entercityNode = element.getElementsByTagName("EnterCity").item(0);
						Node enterdateNode = element.getElementsByTagName("EnterDate").item(0);
						Node ticketclassNode = element.getElementsByTagName("TicketClass").item(0);
						Node ticketpriceNode = element.getElementsByTagName("TicketPrice").item(0);

						if (ticketNumberNode != null && paspassportNode != null && pasnameNode != null
								&& paslastNameNode != null && emailNode != null && dateofbirthNode != null
								&& phonenumberNode != null && buydateNode != null && airlineNode != null
								&& operationCenterNode != null && idNode != null && airplanearilineNode != null
								&& airplaneModelNode != null && airplaneYearNode != null && exitcityNode != null
								&& exitdateNode != null && entercityNode != null && enterdateNode != null
								&& ticketclassNode != null && ticketpriceNode != null) {

							String ticketNumber = ticketNumberNode.getTextContent();
							String paspassport = paspassportNode.getTextContent();
							String pasname = pasnameNode.getTextContent();
							String paslastName = paslastNameNode.getTextContent();
							String email = emailNode.getTextContent();
							String dateofbirth = dateofbirthNode.getTextContent();
							String phonenumber = phonenumberNode.getTextContent();
							String buydate = buydateNode.getTextContent();
							String airline = airlineNode.getTextContent();
							String operationCenter = operationCenterNode.getTextContent();
							String id = idNode.getTextContent();
							String airplaneariline = airplanearilineNode.getTextContent();
							String airplaneModel = airplaneModelNode.getTextContent();
							String airplaneYear = airplaneYearNode.getTextContent();
							String exitcity = exitcityNode.getTextContent();
							String exitdate = exitdateNode.getTextContent();
							String entercity = entercityNode.getTextContent();
							String enterdate = enterdateNode.getTextContent();
							String ticketclass = ticketclassNode.getTextContent();
							String ticketprice = ticketpriceNode.getTextContent();

							boolean ticketExists = false;
							for (TicketsHistory existingTicket : ticketsList) {
								if (existingTicket.getTicketNumber() == ticketNumber) {
									ticketExists = true;
									break;
								}
							}
							if (!ticketExists) {
								TicketsHistory hticks = new TicketsHistory(ticketNumber, paspassport, pasname,
										paslastName, email, dateofbirth, phonenumber, buydate, airline, operationCenter,
										id, airplaneariline, airplaneModel, airplaneYear, exitcity, exitdate, entercity,
										enterdate, ticketclass, Double.parseDouble(ticketprice));
								ticketsList.add(hticks);
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
}