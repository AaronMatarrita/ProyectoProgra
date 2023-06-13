package data;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Brand;

public class LogicXMLBrand {
	//------------------------------------------------------------------
	//Método para obtener una lista de tipo Brand
	public ArrayList<Brand> readXMLFile(String fileName) {
		ArrayList<Brand> brands = new ArrayList<>();
		File file = new File(fileName);
		if (!file.exists()) {
			return brands;
		} else {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(file);

				document.getDocumentElement().normalize();

				NodeList brandList = document.getElementsByTagName("brands");

				if (brandList.getLength() == 0) {
					return brands;
				}

				for (int i = 0; i < brandList.getLength(); i++) {
					Node node = brandList.item(i);
					if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						NodeList brandNameList = element.getElementsByTagName("brand");
						if (brandNameList.getLength() > 0 && brandNameList.item(0) != null) {
							String brandName = brandNameList.item(0).getTextContent();

							boolean brandExists = false;
							for (Brand existingBrand : brands) {
								if (existingBrand.getBrand().equals(brandName)) {
									brandExists = true;
									break;
								}
							}
							if (!brandExists) {
								Brand brand = new Brand();
								brand.setBrand(brandName);
								brands.add(brand);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return brands;
		}
	}
	//------------------------------------------------------------------
		//Método para obtener una lista de TIPO String con las marcas
	public ArrayList<String> readBrandsFromXML(String fileName) {
	    ArrayList<String> brandNames = new ArrayList<>();
	    ArrayList<Brand> brands = readXMLFile(fileName);
	    for (Brand brand : brands) {
	        brandNames.add(brand.getBrand());
	    }
	    return brandNames;
	}
}
