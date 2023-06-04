package data;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLFiles {
    public Document document;
    public Element rootElement;
    public String name;
    
    public XMLFiles(String name) {
    	this.name = name;
    }
    
    public XMLFiles() {}

    //---------------------Método para crear archivos XML---------------------
    public void createXML(String fileName, String objectName) {

		File file = new File(fileName);

		if(!file.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

				Document doc = dBuilder.newDocument();

				Element rootElement = doc.createElement(objectName);
				doc.appendChild(rootElement);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				DOMSource source = new DOMSource(doc);

				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);

				System.out.println("Archivo Creado");

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
    public boolean verify(String address,String usuario, String contraseña) {
        try {
			File inputFile = new File(address);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(inputFile);
 
            Element root = document.getDocumentElement();

            NodeList nodeList = root.getElementsByTagName("person");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoPerson = (Element) node;
                    
                    // Obtener el usuario y contraseña del elemento
                    String usuarioXML = elementoPerson.getElementsByTagName("user").item(0).getTextContent();
                    String contraseñaXML = elementoPerson.getElementsByTagName("password").item(0).getTextContent();

                    if (usuario.equals(usuarioXML) && contraseña.equals(contraseñaXML)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	public String readXMLToString(String address, String elementType) {
		StringBuilder information = new StringBuilder();
		
		try {
			File inputFile = new File(address);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			information.append("\nRaíz de los Elementos:" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName(elementType);
			information.append("\n----------------------------");

			for (int indice = 0; indice < nList.getLength(); indice++) {
				Node nNode = nList.item(indice);
				information.append("\nDatos: \n").append(nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					information.append("\nCedula: ").append(eElement.getAttribute("id"));
					information.append("\nNombre: ").append(eElement.getElementsByTagName("name").
							item(0).getTextContent()+"\n");
					
					information.append("Edad: ").append(eElement.getElementsByTagName("age").
							item(0).getTextContent()+"\n");
					information.append("Género: ").append(eElement.getElementsByTagName("gender").
							item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return information.toString();
	}
}