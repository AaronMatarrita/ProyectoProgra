package business;

import java.io.File;
import java.io.FileWriter;
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

import domain.FlightsHistory;

public class LogicHistoryFlights {
	//Obtener un arrayList con de los tiquetes
	public ArrayList<FlightsHistory> readXMLFile(String filename) {
		ArrayList<FlightsHistory> flList = new ArrayList<>();
		File file = new File(filename);
		
		if (!file.exists()) {
			return flList;
		} else {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);
				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName("HistoricFlights");
				if (nodeList.getLength() == 0) {
					return flList;
				}

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						Node flightNumberNode = element.getElementsByTagName("FlightNumber").item(0);
						Node airlineNode = element.getElementsByTagName("Airline").item(0);
						Node airplaneNode = element.getElementsByTagName("Airplane").item(0);
						Node exitcityNode = element.getElementsByTagName("ExitCity").item(0);
						Node exitdateNode = element.getElementsByTagName("ExitDate").item(0);
						Node entercityNode = element.getElementsByTagName("EnterCity").item(0);
						Node enterdateNode = element.getElementsByTagName("EnterDate").item(0);
						Node soldEJEseatsNode = element.getElementsByTagName("SoldBusinessClassSeats").item(0);
						Node totalEJEseatsNode = element.getElementsByTagName("TotalBusinessClassSeats").item(0);
						Node soldTOUseatsNode = element.getElementsByTagName("SoldTouristClassSeats").item(0);
						Node totalTOUseatsNode = element.getElementsByTagName("TotalTouristClassSeats").item(0);
						Node soldECOseatsNode = element.getElementsByTagName("SoldEconomicClassSeats").item(0);
						Node totalECOseatsNode = element.getElementsByTagName("TotalEconomicClassSeats").item(0);
						Node ejepriceNode = element.getElementsByTagName("BusinessPrice").item(0);
						Node toupriceNode = element.getElementsByTagName("TouristPrice").item(0);
						Node ecopriceNode = element.getElementsByTagName("EconomicPrice").item(0);
						Node totalflightNode = element.getElementsByTagName("TotalForFlight").item(0);

						if(flightNumberNode!= null && airlineNode != null && airplaneNode!= null && exitcityNode!= null && exitdateNode!= null && entercityNode!= null && enterdateNode!= null && soldEJEseatsNode!= null && totalEJEseatsNode!= null && soldTOUseatsNode!= null && totalTOUseatsNode!= null && soldECOseatsNode!= null && totalECOseatsNode!= null && ejepriceNode!= null && toupriceNode!= null && ecopriceNode!= null && totalflightNode != null){                                                                     
							
							String flightNumber = element.getElementsByTagName("FlightNumber").item(0).getTextContent();
							String airline = element.getElementsByTagName("Airline").item(0).getTextContent();
							String airplane = element.getElementsByTagName("Airplane").item(0).getTextContent();
							String exitcity = element.getElementsByTagName("ExitCity").item(0).getTextContent();
							String exitdate = element.getElementsByTagName("ExitDate").item(0).getTextContent();
							String entercity = element.getElementsByTagName("EnterCity").item(0).getTextContent();
							String enterdate = element.getElementsByTagName("EnterDate").item(0).getTextContent();
							int soldEJEseats = Integer.parseInt(element.getElementsByTagName("SoldBusinessClassSeats").item(0).getTextContent());
							int totalEJEseats = Integer.parseInt(element.getElementsByTagName("TotalBusinessClassSeats").item(0).getTextContent());
							int soldTOUseats = Integer.parseInt(element.getElementsByTagName("SoldTouristClassSeats").item(0).getTextContent());
							int totalTOUseats = Integer.parseInt(element.getElementsByTagName("TotalTouristClassSeats").item(0).getTextContent());
							int soldECOseats = Integer.parseInt(element.getElementsByTagName("SoldEconomicClassSeats").item(0).getTextContent());
							int totalECOseats = Integer.parseInt(element.getElementsByTagName("TotalEconomicClassSeats").item(0).getTextContent());
							double EJEprice = Double.parseDouble(element.getElementsByTagName("BusinessPrice").item(0).getTextContent());
							double TOUprice = Double.parseDouble(element.getElementsByTagName("TouristPrice").item(0).getTextContent());
							double ECOprice = Double.parseDouble(element.getElementsByTagName("EconomicPrice").item(0).getTextContent());
							double totalFlight = Double.parseDouble(element.getElementsByTagName("TotalForFlight").item(0).getTextContent());

							boolean flExists = false;
							for (FlightsHistory existingFl : flList) {
								if (existingFl.getFlightNumber() == flightNumber) {
									flExists = true;
									break;
								}
							}
							if (!flExists) {
								FlightsHistory flightsHistory = new FlightsHistory(flightNumber
										, airline, airplane, exitcity
										, exitdate, entercity, enterdate
										, soldEJEseats, totalEJEseats, soldTOUseats
										, totalTOUseats, soldECOseats, totalECOseats
										, EJEprice, TOUprice, ECOprice, totalFlight);
								flList.add(flightsHistory);
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
		return flList;
	}
	public void deleteALLXML(String rutaArchivo) {
	    try {
	        File archivoXML = new File(rutaArchivo);
	        FileWriter escritor = new FileWriter(archivoXML);
	        escritor.write("");
	        escritor.close();
	        System.out.println("Contenido del archivo XML borrado correctamente.");
	    } catch (IOException e) {
	        System.out.println("Error al borrar el contenido del archivo XML: " + e.getMessage());
	    }
	}
	
	public FlightsHistory getFlightFromFile(String fileName, String attributeValue) {
	    ArrayList<FlightsHistory> fls = (ArrayList<FlightsHistory>) readXMLFile(fileName);

	    for (FlightsHistory fl : fls) {
	        if (fl.getFlightNumber().equals(attributeValue)) {
	            return fl;
	        }
	    }
	    
	    return null;
	}
}