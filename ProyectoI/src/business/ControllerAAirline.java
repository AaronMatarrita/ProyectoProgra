package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import data.LogicXMLAirline;
import domain.Airline;
import presentation.AirlineFrame;
import presentation.PopUpMessages;

public class ControllerAAirline implements ActionListener {
	private Airline Ar;
	private AirlineFrame aF;

	private CRUD crud;
	private LogicXML lXML;

	private XMLFiles xmlF;
	private LogicXMLAirline logicXMLAirline;

	private String fileName = "Airlines.xml";
	private String objectName = "airlines";

	private PopUpMessages pM;

	public ControllerAAirline(String userType) {
		aF = new AirlineFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		logicXMLAirline = new LogicXMLAirline();
		pM = new PopUpMessages();
		xmlF.createXML(fileName, objectName);
		setTableData();
		initializerAction();
	}

	private void setTableData() {
		List<Airline> airlines = logicXMLAirline.readXMLFile(fileName);
		aF.setJTableData(airlines);
	}

	public void initializerAction() {
		aF.getBAddAirline().addActionListener(this);
		aF.getBClear().addActionListener(this);
		aF.getBUpdate().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aF.getBAddAirline() == e.getSource())
		{
			addAirline();
		} else if (aF.getBUpdate() == e.getSource())
		{
			updateAirline();

		} else if (aF.getBClear() == e.getSource())
		{
			deleteAirline();
		}
	}

	private void addAirline() {
		String airline = aF.getTAirline().getText();

		if (airline.isEmpty() || aF.getTCountry().getText().isEmpty() ) {
			pM.showMessage( "Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName,objectName, "Name", airline)) {
			pM.showMessage( "La Aerolinea ya existe");
			return;
		} 
		//No pueden existir aerolíneas con el mismo nombre y no pueden eliminarse aerolíneas que 
		//estén asociadas a un avión

		String country = aF.getTCountry().getText();

		aF.clean();
		Ar = new Airline(airline, country);
		crud.addObject(fileName, objectName, Ar.getDataName(), Ar.getData());

		pM.showMessage( "Aerolinea agregada");
		setTableData();
	}

	private void updateAirline() {
		String airlineName = aF.getTAirline().getText();
		Airline  airline = logicXMLAirline.getAirlineFromFile(fileName, objectName, "user", airlineName);

		String newAirlineName = airlineName;

		if (airlineName.isEmpty()) {
			pM.showMessage("Por favor, ingrese el nombre de la aerolínea a modificar");
			return;
		} else if (!lXML.isAlreadyInFile(fileName,objectName, "Name", airlineName)) {
			pM.showMessage("La Aerolinea no existe");
			return;
		}

		if(pM.showConfirmationDialog("Desea modificar el nombre de la aerolínea?", "Modificar")) {
			newAirlineName = pM.getData("Ingrese el nombre de la nueva aerolínea:");
		}else {
			newAirlineName = airline.getName();
		}
		
		String newCountry =  aF.getTCountry().getText();

		if(newCountry.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar el centro de operaciones de la aerolínea?", "Modificar")) {
				newCountry = pM.getData("Ingrese el nuevo nombre del centro de operaciones de la aerolínea:");
			}else {
				newCountry = airline.getCountry();
			}
		}
		String[] newData = {newAirlineName, newCountry};
		crud.updateObject(fileName, objectName, "Name", airlineName , airline.getDataName(), newData);
		setTableData();
	}

	private void deleteAirline() {
		String airline = aF.getTAirline().getText();

		if (airline.isEmpty()) {
			pM.showMessage("Por favor, complete el nombre de la Aerolinea a eliminar");
			return;
		} else if (lXML.isAlreadyInFile("Airplanes.xml", "airplanes", "airline", airline))
		{
			pM.showMessage("La aerolínea no se puede eliminar debido a que esta asociada con un avión");
			return;
		} else if (!lXML.isAlreadyInFile(fileName, objectName	, "Name", airline))
		{
			pM.showMessage("No se puede eliminar debido a que no existe");
			return;
		}
		//No pueden existir aerolíneas con el mismo nombre y no pueden eliminarse aerolíneas que 
		//estén asociadas a un avión
		if(pM.showConfirmationDialog("¿Está seguro de eliminar la aerolínea?", "Eliminar")) {
			aF.clean();
			crud.deleteObject(fileName, objectName, "Name", airline);
			pM.showMessage("Aerolinea eliminada");
			setTableData();
		}
	}
}