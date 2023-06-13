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
		ArrayList<Passenger> passengers = new ArrayList<>();
		try {
			File file = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("person");

			if (nodeList.getLength() == 0) {
				return passengers;
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String passport = element.getElementsByTagName("Passport").item(0).getTextContent();
					String name = element.getElementsByTagName("Name").item(0).getTextContent();
					String lastName = element.getElementsByTagName("Lastname").item(0).getTextContent();
					String dateOfBirth = element.getElementsByTagName("Dateofbirth").item(0).getTextContent();
					String email = element.getElementsByTagName("Email").item(0).getTextContent();
					String phoneNumber = element.getElementsByTagName("PhoneNumber").item(0).getTextContent();

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passengers;
	}
}