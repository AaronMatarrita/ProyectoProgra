package data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.User;

public class LogicUser {

	public LogicUser() {}	

	public ArrayList<User> readXMLFile(String filename) {
		ArrayList<User> users = new ArrayList<>();
		try {
			File file = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("person");

			if (nodeList.getLength() == 0) {
				return users;
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					Node nUser = element.getElementsByTagName("user").item(0);
					Node nPassword = element.getElementsByTagName("password").item(0);
					Node nUserType = element.getElementsByTagName("userType").item(0);
					Node nStatus = element.getElementsByTagName("status").item(0);

					if(nUser != null && nPassword != null && nUserType != null && nStatus != null) {

						String user = nUser.getTextContent();
						String password = nPassword.getTextContent();
						int userType = Integer.parseInt(nUserType.getTextContent());
						boolean status = Boolean.parseBoolean(nStatus.getTextContent());

						boolean userExists = false;
						for (User existingUser : users) {

							if (existingUser.getUser().equals(user)) {
								userExists = true;
								break;
							}
						}
						if (!userExists) {
							User person = new User();
							person.setUser(user);
							person.setPassword(password);
							person.setUserType(userType);
							person.setStatus(status);
							users.add(person);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	//Método para obtener un usario en especifico
	public User getUserFromFile(String fileName, String attributeValue) {
		ArrayList<User> users = readXMLFile(fileName);

		for (User user : users) {
			if (user.getUser().equals(attributeValue)) {
				return user;
			}
		}

		return null;
	}
}
