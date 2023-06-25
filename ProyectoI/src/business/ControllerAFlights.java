package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import data.CRUD;
import data.LogicXML;
import data.LogicXMLAirplane;
import data.LogicXMLFlights;
import data.XMLFiles;
import domain.Flight;
import domain.Ticket;
import presentation.FlightsFrame;
import presentation.PopUpMessages;

public class ControllerAFlights implements ActionListener{

	private FlightsFrame fF;
	private CRUD crud;

	private Flight Fl;
	private LogicXML lXML;

	private XMLFiles xmlF;
	private LogicXMLFlights lXMLF;
	private LogicXMLAirplane lXMLA;
	private PopUpMessages pM;
	private String fileName = "Flights.xml";
	private String objectName = "flights";
	private ArrayList<Flight> flights = new ArrayList<Flight>();

	public ControllerAFlights(String userType) {
		fF = new FlightsFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		lXMLA = new LogicXMLAirplane();
		xmlF = new XMLFiles();
		lXMLF = new LogicXMLFlights();
		pM = new PopUpMessages();
		xmlF.createXML(fileName, objectName);
		fF.fillAirplaneComboBox(lXMLA.getAirplaneList("Airplanes.xml"));
		initializerAction();
	}

	private void setTableData() {
		String flightNumber =  String.valueOf(fF.getTFlightNum().getText().trim());
		if(flightNumber.isEmpty()) {
			flights = lXMLF.readXMLFile(fileName);
		}else {
			flights.clear();
			Flight searchedFlight = lXMLF.getFlightFromXML(fileName, flightNumber);
			if(searchedFlight != null) {
				flights.add(searchedFlight);
			}
		}
		fF.setJTableData(flights);
	}

	public void initializerAction() {
		//bF.addWindowListener(this);
		fF.getBAddFlights().addActionListener(this);
		fF.getBUpdate().addActionListener(this);
		fF.getBClear().addActionListener(this);
		fF.getBSearch().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (fF.getBAddFlights() == e.getSource())
		{
			addFlight();
		}else if(fF.getBUpdate() == e.getSource())
		{
			updateFlight();
		}else if(fF.getBClear() == e.getSource())
		{
			deleteFlight();
		}else if(fF.getBSearch() == e.getSource()) {
			searchFlight();
		}

	}
	
	private void addFlight() {
		String flightNumber = lXMLF.getTRandomFlightNumber();
		fF.getTFlightNum().setText(flightNumber);
		String exitCity = fF.getTExitCity().getText();
		String exitDate = fF.getTExitDate().getText();
		String exitTime = fF.getTExitTime().getText();
		String enterCity = fF.getTExitCity().getText();
		String enterDate = fF.getTEnterDate().getText();
		String enterTime = fF.getTEnterTime().getText();
		String priceEJE = fF.getTPriceEJE().getText();
		String priceTUR = fF.getTPriceTUR().getText();
		String priceECO = fF.getTPriceECO().getText();
		String airplane = (String) fF.getCBAirplane().getSelectedItem();
		
		if (exitCity.isEmpty() || exitDate.isEmpty() || exitTime.isEmpty() ||
				enterCity.isEmpty()|| enterDate.isEmpty()|| enterTime.isEmpty()||
				priceEJE.isEmpty() || priceECO.isEmpty() || airplane.isEmpty() || airplane.equals("Indefinido")) {
			pM.showMessage("Por favor, complete todos los campos");
			
			return;
		}else if(lXML.isAlreadyInFile(fileName, objectName, "FlightNumber" , flightNumber)) {
			pM.showMessage( "El vuelo ya existe");
			return;
		} else if (!lXMLF.isValidTime(exitTime) || !lXMLF.isValidTime(enterTime)) {
			pM.showMessage( "Ingrese la hora en el formato de 24 horas (Hours:minutes) (xx:xx)");
			return;
		}else if(!lXMLF.isValidDate(enterDate) || !lXMLF.isValidDate(exitDate)) {
			pM.showMessage("Ingrese la fecha en el formato (dd/MM/yyyy)\nEjemplo 01/01/2000 ");
			return;
		}
		fF.clean();
		Fl = new Flight((Integer.parseInt(flightNumber)), exitCity, exitDate ,exitTime, enterCity, enterDate, enterTime, airplane, Integer.parseInt(priceEJE),
				Integer.parseInt(priceTUR), Integer.parseInt(priceECO));
		
		if(lXMLF.checkFlightOverlap(lXMLF.readXMLFile(fileName), Fl)) {
			pM.showMessage( "No se puede agregar el vuelo debido a que ya existe otro con el mismo avión y no se ha superado el rango de 20 horas");
			return;
		}
		crud.addObject(fileName, objectName, Fl.getDataName(), Fl.getData());
		pM.showMessage( "Vuelo agregado");
		setTableData();
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void searchFlight() {
		setTableData();
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void updateFlight() {
		String flightNumber = fF.getTFlightNum().getText();
		String newFlightNumber = flightNumber;
		String exitCity = fF.getTExitCity().getText();
		String exitDate = fF.getTExitDate().getText();
		String exitTime = fF.getTExitTime().getText();
		String enterCity = fF.getTExitCity().getText();
		String enterDate = fF.getTEnterDate().getText();
		String enterTime = fF.getTEnterTime().getText();
		String priceEJE = fF.getTPriceEJE().getText();
		String priceTUR = fF.getTPriceTUR().getText();
		String priceECO = fF.getTPriceECO().getText();
		String airplane = (String) fF.getCBAirplane().getSelectedItem();
		
		Flight currentFlight = lXMLF.getFlightFromXML(fileName, flightNumber);

		if (flightNumber.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el numero de ticket a modificar");
			return;
		} else if (currentFlight == null) {
			pM.showMessage("El Ticket no existe");
			return;
		}

		if (exitCity.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la ciudad de salida?", "Modificar")) {
				exitCity = pM.getData("Ingrese la nueva ciudad de salida:");
			}
			}else {
				exitCity = currentFlight.getArrivalCity();
			}
		if (exitDate.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la fecha de salida?", "Modificar")) {
				exitDate = pM.getData("Ingrese la nueva fecha de salida:");
			}else {
				exitDate = currentFlight.getArrivalDate();
			}
		}
		
		if (exitTime.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la hora de salida?", "Modificar")) {
				exitTime = pM.getData("Ingrese la nueva hora de salida:");
			}else {
				exitTime = currentFlight.getArrivalTime();
			}
		}
		if (enterCity.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la ciudad de llegada?", "Modificar")) {
				enterCity = pM.getData("Ingrese la nueva ciudad de llegada:");
			}else {
				enterCity = currentFlight.getDepartureCity();
			}
		}
		if (enterDate.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la fecha de llegada?", "Modificar")) {
				enterDate = pM.getData("Ingrese la nueva fecha de llegada:");
			}else {
				enterDate = currentFlight.getDepartureDate();
			}
		}
		if (enterTime.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la hora de llegada?", "Modificar")) {
				enterTime = pM.getData("Ingrese la nueva hora de llegada:");
			}else {
				enterTime = currentFlight.getDepartureTime();
			}
		}
		if (priceEJE.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el monto de la clase Ejecutiva?", "Modificar")) {
				priceEJE = pM.getData("Ingrese el nuevo monto:");
			}else {
				priceEJE = String.valueOf(currentFlight.getBusinessClassSeatsPrice());
			}
		}
		if (priceTUR.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el monto de la clase Turista?", "Modificar")) {
				priceTUR = pM.getData("Ingrese el nuevo monto:");
			}else {
				priceTUR = String.valueOf(currentFlight.getTouristClassSeatsPrice());
			}
		}
		if (priceECO.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el monto de la clase Economica?", "Modificar")) {
				priceECO = pM.getData("Ingrese el nuevo monto:");
			}else {
				priceECO = String.valueOf(currentFlight.getEconomyClassSeatsPrice());
			}
		}
		if (airplane.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el avion", "Modificar")) {
				pM.showMessage("Por favor, seleccione el avion");
				airplane = (String) fF.getCBAirplane().getSelectedItem();
				if (airplane.equals("Indefinido")) {
					return;
				}
			} else {
				airplane = currentFlight.getAirplane();
			}
		}
		
		String[] newData = { flightNumber, exitCity, exitDate, exitTime, enterCity, enterDate, enterTime, priceEJE, priceTUR,priceECO,airplane };
		crud.updateObject(fileName, objectName, "FlightNumber", newFlightNumber, currentFlight.getDataName(), newData);
		fF.clean();
		pM.showMessage("Vuelo modificado");
		setTableData();
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private void deleteFlight() {

		String flightNumber =  String.valueOf(fF.getTFlightNum().getText());

		if (flightNumber.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el codigo de vuelo");
			return;
		}else if(!lXML.isAlreadyInFile(fileName, objectName, "FlightNumber" ,flightNumber)) {
			pM.showMessage("No se puede eliminar debido a que no existe");return;}
		else {
			xmlF.createXML(fileName, objectName);
			crud.deleteObject(fileName, objectName, "FlightNumber", flightNumber);
		}
		pM.showMessage("Vuelo eliminado");
		setTableData();
	}
}