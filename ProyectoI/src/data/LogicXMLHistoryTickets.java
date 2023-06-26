package data;

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


public class LogicXMLHistoryTickets {
	//Obtener un arrayList con de los tiquetes
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
				
						if(ticketNumberNode!= null && paspassportNode!= null && pasnameNode!= null && paslastNameNode!= null && emailNode!= null && dateofbirthNode!= null && phonenumberNode!= null && buydateNode!= null && airlineNode!= null && operationCenterNode!= null && idNode!= null && airplanearilineNode!= null && airplaneModelNode!= null && airplaneYearNode!= null && exitcityNode!= null && exitdateNode!= null && entercityNode != null && enterdateNode != null && ticketclassNode!= null && ticketpriceNode != null) {                                           
							
							String ticketNumber  = element.getElementsByTagName("TicketNumber").item(0).getTextContent();
							String paspassport  = element.getElementsByTagName("PassengerPassport").item(0).getTextContent();
							String pasname  = element.getElementsByTagName("Name").item(0).getTextContent();
							String paslastName  = element.getElementsByTagName("LastName").item(0).getTextContent();
							String email  = element.getElementsByTagName("Email").item(0).getTextContent();
							String dateofbirth  = element.getElementsByTagName("DateOfBirth").item(0).getTextContent();
							String phonenumber  = element.getElementsByTagName("PhoneNumber").item(0).getTextContent();
							String buydate  = element.getElementsByTagName("BuyTicketDate").item(0).getTextContent();
							String airline  = element.getElementsByTagName("AirlineName").item(0).getTextContent();
							String operationCenter  = element.getElementsByTagName("AirplaneOperationCenter").item(0).getTextContent();
							String id  = element.getElementsByTagName("AirplaneId").item(0).getTextContent();
							String airplaneariline  = element.getElementsByTagName("AirplaneAirline").item(0).getTextContent();
							String airplaneModel  = element.getElementsByTagName("AirplaneModel").item(0).getTextContent();
							String airplaneYear  = element.getElementsByTagName("AirplaneYear").item(0).getTextContent();
							String exitcity  = element.getElementsByTagName("ExitCity").item(0).getTextContent();
							String exitdate  = element.getElementsByTagName("ExitDate").item(0).getTextContent();
							String entercity  = element.getElementsByTagName("EnterCity").item(0).getTextContent();
							String enterdate  = element.getElementsByTagName("EnterDate").item(0).getTextContent();
							String ticketclass  = element.getElementsByTagName("TicketClass").item(0).getTextContent();
							String ticketprice  = element.getElementsByTagName("TicketPrice").item(0).getTextContent();
							
							boolean ticketExists = false;
							for (TicketsHistory existingTicket : ticketsList) {
								if (existingTicket.getTicketNumber() == ticketNumber) {
									ticketExists = true;
									break;
								}
							}
							if (!ticketExists) {
								TicketsHistory hticks  = new TicketsHistory(ticketNumber , paspassport , pasname 
										, paslastName , email , dateofbirth , phonenumber 
										, buydate, airline , operationCenter , id 
										, airplaneariline 
										, airplaneModel , airplaneYear , exitcity , exitdate, entercity , enterdate
										, ticketclass , Double.parseDouble(ticketprice));
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