package data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Airplane;

public class LogicXMLAirplane {

	public LogicXMLAirplane() {}	

	public ArrayList<Airplane> readXMLFile(String filename) {
		ArrayList<Airplane> airplanes = new ArrayList<>();
		File file = new File(filename);
		if (!file.exists()) {
			return airplanes;
		} else {
			try {

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);
				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName("airplanes");

				if (nodeList.getLength() == 0) {
					return airplanes;
				}

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						String id = element.getElementsByTagName("id").item(0).getTextContent();
						String year = element.getElementsByTagName("year").item(0).getTextContent();
						String airline = element.getElementsByTagName("airline").item(0).getTextContent();
						String model = element.getElementsByTagName("model").item(0).getTextContent();

						boolean airplaneExists = false;
						for (Airplane existingAirplane : airplanes) {

							if (existingAirplane.getId().equals(id)) {
								airplaneExists = true;
								break;
							}
						}
						if (!airplaneExists) {
							Airplane air = new Airplane();
							air.setId(id);
							air.setYear(year);
							air.setAirline(airline);
							air.setAirplaneModel(model);
							airplanes.add(air);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return airplanes;
		}
	}

}