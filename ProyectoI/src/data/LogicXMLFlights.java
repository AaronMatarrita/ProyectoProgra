package data;

import java.io.File;
/*import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;*/
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
	//Método para obtener un arrayList de tipo Flights
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

						if (isValidFlightElement(element)) {
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
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flights;
		}
	}
	//Método para verificar si los nodos son nulos
	private boolean isValidFlightElement(Element element) {
		Node flightNumber = element.getElementsByTagName("FlightNumber").item(0);
		Node departureCity = element.getElementsByTagName("DepartureCity").item(0);
		Node departureDate = element.getElementsByTagName("DepartureDate").item(0);
		Node departureTime = element.getElementsByTagName("DepartureTime").item(0);
		Node arrivalCity = element.getElementsByTagName("ArrivalCity").item(0);
		Node arrivalDate = element.getElementsByTagName("ArrivalDate").item(0);
		Node arrivalTime = element.getElementsByTagName("ArrivalTime").item(0);
		Node airplane = element.getElementsByTagName("Airplane").item(0);
		Node bussinessCSP = element.getElementsByTagName("BusinessClassSeatsPrice").item(0);
		Node touristCSP = element.getElementsByTagName("TouristClassSeatsPrice").item(0);
		Node economyCSP = element.getElementsByTagName("EconomyClassSeatsPrice").item(0);
		if(flightNumber != null && departureCity != null && departureDate != null
				&& departureTime != null && arrivalCity != null &&  arrivalDate != null 
				&& arrivalTime != null && airplane != null && bussinessCSP != null
				&& bussinessCSP != null && touristCSP != null && economyCSP != null)
		{
			return true;
		}

		return false;
	}
	//Método para obtener un arrayList de tipo String con los vuelos:
	//...

	//Método para validar el formato de las horas introducidas por el usuario
	public boolean isValidTime(String time) {
		try {
			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);

			if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
				return false;
			}

			return true;
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	//Método para verificar si el rango de los vuelos y sus aviones esta permitido
	/*public boolean checkFlightOverlap(ArrayList<Flights> flights, Flights flight2) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		 for (Flights existingFlight : flights) {
		        LocalDateTime departureDateTime1 = LocalDateTime.parse(existingFlight.getDepartureDate() + " " + existingFlight.getDepartureTime(), formatter);
		        LocalDateTime arrivalDateTime1 = LocalDateTime.parse(existingFlight.getArrivalDate() + " " + existingFlight.getArrivalTime(), formatter);
		        LocalDateTime departureDateTime2 = LocalDateTime.parse(flight2.getDepartureDate() + " " + flight2.getDepartureTime(), formatter);
		        LocalDateTime arrivalDateTime2 = LocalDateTime.parse(flight2.getArrivalDate() + " " + flight2.getArrivalTime(), formatter);

	        //Se verifica si el rango de tiempo entre vuelos esta permitido
	        boolean overlap = departureDateTime1.isBefore(arrivalDateTime2) && departureDateTime2.isBefore(arrivalDateTime1);

	        if (overlap) {
	            //Si el rango de los vuelos no esta permitido, se procede a verificar si tienen el mismo avión
	            boolean sameAirplane = existingFlight.getAirplane().equals(flight2.getAirplane());

	            if (sameAirplane) {
	                //Si tienen el mismo avión se verifica si el tiempo entre estos es permitido
	                LocalDateTime departureDateTime1Plus20Hours = departureDateTime1.plusHours(20);
	                LocalDateTime departureDateTime2Plus20Hours = departureDateTime2.plusHours(20);

	                boolean within20HoursRange = (departureDateTime1.isBefore(departureDateTime2Plus20Hours) && departureDateTime2.isBefore(arrivalDateTime1))
	                        || (departureDateTime2.isBefore(departureDateTime1Plus20Hours) && departureDateTime1.isBefore(arrivalDateTime2));

	                if (within20HoursRange) {
	                    return true; //El vuelo no esta permitido porque ya existe otro con el mismo avión
	                }
	            }
	        }
	    }

	    return false; //El vuelo esta permitido ya que NO existe otro con el mismo avión
	}*/
}