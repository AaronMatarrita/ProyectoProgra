package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.CRUD;
import data.LogicAirline;
import data.LogicXML;
import data.XMLFiles;
import domain.Airline;
import presentation.AirlineFrame;
import presentation.PopUpMessages;

public class ControllerAirline implements ActionListener {
	
	private String fileName = "Airlines.xml";
	private String objectName = "airlines";
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private XMLFiles xmlF;
	private CRUD crud;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private PopUpMessages pM;
	private AirlineFrame aF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXML lXML;
	private LogicAirline logicAirline;
	private LogicExecuteHTML logHTML;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private Airline Ar;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private String userType;
	private ArrayList<Airline> airlines = new ArrayList<Airline>();
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	

	public ControllerAirline(String userType) {
		this.userType = userType;
		aF = new AirlineFrame(userType);
		pM = new PopUpMessages();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		logHTML = new LogicExecuteHTML();
		logicAirline = new LogicAirline();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		initializerAction();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializerAction() {
		aF.getBAddAirline().addActionListener(this);
		aF.getBClear().addActionListener(this);
		aF.getBUpdate().addActionListener(this);
		aF.getBSearch().addActionListener(this);
		aF.getBHelp().addActionListener(this);
		aF.getBReturn().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aF.getBAddAirline() == e.getSource()){
			addAirline();
		} else if (aF.getBUpdate() == e.getSource()){
			updateAirline();
		} else if (aF.getBClear() == e.getSource()){
			deleteAirline();
		}else if(aF.getBSearch() == e.getSource()) {
			searchAirline();
		}else if(aF.getBHelp() == e.getSource()) {
			help();
		}else if(aF.getBReturn() == e.getSource()){
			aF.dispose();
			new ControllerMain(userType);
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void setTableData() {
		String airline = aF.getTAirline().getText().trim();
		if(airline.isEmpty()) {
			airlines = logicAirline.readXMLFile(fileName);
		}else {
			airlines.clear();
			Airline searchedAirline = logicAirline.getAirlineFromFile(fileName, airline);
			if(searchedAirline != null) {
				airlines.add(searchedAirline);
			}
		}
		aF.setJTableData(airlines);
	}
	//-------------------------------------------------------------------------------------------------------------------------
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
		setTableData();
		pM.showMessage( "Aerolínea agregada");
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void searchAirline() {
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updateAirline() {
		String airlineName = aF.getTAirline().getText();
		Airline  airline = logicAirline.getAirlineFromFile(fileName, airlineName);
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		String newAirlineName = airlineName;
		if (airlineName.isEmpty()) {
			pM.showMessage("Por favor, ingrese el nombre de la aerolínea a modificar");
			return;
		} else if (!lXML.isAlreadyInFile(fileName,objectName, "Name", airlineName)) {
			pM.showMessage("La Aerolínea no existe");
			return;
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if(pM.showConfirmationDialog("Desea modificar el nombre de la aerolínea?", "Modificar")) {
			newAirlineName = pM.getData("Ingrese el nombre de la nueva aerolínea:");
			if(newAirlineName.equals("null")) { newAirlineName = airline.getName();}
		}else {
			newAirlineName = airline.getName();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		String newCountry =  aF.getTCountry().getText();
		if(newCountry.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar el centro de operaciones de la aerolínea?", "Modificar")) {
				newCountry = pM.getData("Ingrese el nuevo nombre del centro de operaciones de la aerolínea:");
				if(newAirlineName.equals("null")) {newCountry = airline.getCountry();}
			}else {
				newCountry = airline.getCountry();
			}
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		String[] newData = {newAirlineName, newCountry};
		crud.updateObject(fileName, objectName, "Name", airlineName , airline.getDataName(), newData);
		setTableData();
		pM.showMessage("Aerolínea modificada");
	}
	//-------------------------------------------------------------------------------------------------------------------------
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
		//No pueden eliminarse aerolíneas que 
		//estén asociadas a un avión
		if(pM.showConfirmationDialog("¿Está seguro de eliminar la aerolínea?", "Eliminar")) {
			aF.clean();
			crud.deleteObject(fileName, objectName, "Name", airline);
			setTableData();
			pM.showMessage("Aerolínea eliminada");
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpAirline(userTypeString);
	}
}