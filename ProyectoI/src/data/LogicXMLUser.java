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

public class LogicXMLUser {

	public LogicXMLUser() {}	

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
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String user = element.getElementsByTagName("user").item(0).getTextContent();
					String password = element.getElementsByTagName("password").item(0).getTextContent();
					int userType = Integer.parseInt(element.getElementsByTagName("userType").item(0).getTextContent());
					boolean status = Boolean.parseBoolean(element.getElementsByTagName("status").item(0).getTextContent());

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
