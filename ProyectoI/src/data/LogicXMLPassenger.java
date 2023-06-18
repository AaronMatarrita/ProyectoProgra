package data;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import domain.Passenger;

public class LogicXMLPassenger {


	public ArrayList<Passenger> readXMLFile(String filename) {
		File file = new File(filename);
		ArrayList<Passenger> passengers = new ArrayList<>();
		if (!file.exists()) {
			return passengers;
		} else {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);

				doc.getDocumentElement().normalize();

				NodeList passengerList = doc.getElementsByTagName("person");

				if (passengerList.getLength() == 0) {
					return passengers;
				}

				for (int i = 0; i < passengerList.getLength(); i++) {
					Node node = passengerList.item(i);
					if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						Node passportNode = element.getElementsByTagName("Passport").item(0);
						Node nameNode = element.getElementsByTagName("Name").item(0);
						Node lastNameNode = element.getElementsByTagName("Lastname").item(0);
						Node dateOfBirthNode = element.getElementsByTagName("Dateofbirth").item(0);
						Node emailNode = element.getElementsByTagName("Email").item(0);
						Node phoneNumberNode = element.getElementsByTagName("PhoneNumber").item(0);

						if (passportNode != null && nameNode != null && lastNameNode != null && dateOfBirthNode != null && emailNode != null && phoneNumberNode != null) {
							String passport = passportNode.getTextContent();
							String name = nameNode.getTextContent();
							String lastName = lastNameNode.getTextContent();
							String dateOfBirth = dateOfBirthNode.getTextContent();
							String email = emailNode.getTextContent();
							String phoneNumber = phoneNumberNode.getTextContent();

							boolean passengerExists = false;
							for (Passenger existingUser : passengers) {
								if (existingUser.getPassport().equals(passport)) {
									passengerExists = true;
									break;
								}
							}
							if (!passengerExists) {
								Passenger passenger = new Passenger(passport, name, lastName, dateOfBirth, email, phoneNumber);
								passengers.add(passenger);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return passengers;
		}
	}
	
	public Passenger getPassengerFromXML(String fileName, String objectName, String attributeName, String attributeValue) {
	    ArrayList<Passenger> passengers = readXMLFile(fileName);

	    for (Passenger passenger : passengers) {
	        if (passenger.getPassport().equals(attributeValue)) {
	            return passenger;
	        }
	    }
	    Passenger p = new Passenger();
	    return p;
	}
}