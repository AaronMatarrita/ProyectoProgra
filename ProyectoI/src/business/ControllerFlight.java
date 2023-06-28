package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import domain.Flight;
import presentation.FlightsFrame;
import presentation.PopUpMessages;

public class ControllerFlight implements ActionListener{

	private String fileName = "Flights.xml";
	private String objectName = "flights";
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private FlightsFrame fF;
	private PopUpMessages pM;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private XMLFiles xmlF;
	private CRUD crud;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private Flight Fl;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXML lXML;
	private LogicFlight logModel;
	private LogicAirplane logAirplane;
	private LogicExecuteHTML logHTML;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private String userType;
	private ArrayList<Flight> flights = new ArrayList<Flight>();
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	
	public ControllerFlight(String userType) {
		this.userType = userType;
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		fF = new FlightsFrame(userType);
		pM = new PopUpMessages();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		logAirplane = new LogicAirplane();
		logModel = new LogicFlight();
		logHTML = new LogicExecuteHTML();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		fF.fillAirplaneComboBox(logAirplane.getAirplaneList("Airplanes.xml"));
		initializerAction();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void setTableData() {
		String flightNumber =  String.valueOf(fF.getTFlightNum().getText().trim());
		if(flightNumber.isEmpty()) {
			flights = logModel.readXMLFile(fileName);
		}else {
			flights.clear();
			Flight searchedFlight = logModel.getFlightFromXML(fileName, flightNumber);
			if(searchedFlight != null) {
				flights.add(searchedFlight);
			}
		}
		fF.setJTableData(flights);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializerAction() {
		//bF.addWindowListener(this);
		fF.getBAddFlights().addActionListener(this);
		fF.getBUpdate().addActionListener(this);
		fF.getBClear().addActionListener(this);
		fF.getBSearch().addActionListener(this);
		fF.getBHelp().addActionListener(this);
		fF.getBReturn().addActionListener(this);
		fF.getBShowFlights().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (fF.getBAddFlights() == e.getSource()){
			addFlight();
		}else if(fF.getBUpdate() == e.getSource()){
			updateFlight();
		}else if(fF.getBClear() == e.getSource()){
			deleteFlight();
		}else if(fF.getBSearch() == e.getSource()) {
			searchFlight();
		}else if(fF.getBHelp() == e.getSource()) {
			help();
		}else if(fF.getBReturn() == e.getSource()){
			fF.dispose();
			new ControllerMain(userType);
		}else if(fF.getBShowFlights() == e.getSource()) {
			fF.dispose();
			new ControllerShowFlights();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addFlight() {
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		String flightNumber = logModel.getTRandomFlightNumber();
		fF.getTFlightNum().setText(flightNumber);
		String exitCity = fF.getTExitCity().getText();
		String exitDate = fF.getTExitDate().getText();
		String exitTime = fF.getTExitTime().getText();
		String enterCity = fF.getTEnterCity().getText();
		String enterDate = fF.getTEnterDate().getText();
		String enterTime = fF.getTEnterTime().getText();
		String priceEJE = fF.getTPriceEJE().getText();
		String priceTUR = fF.getTPriceTUR().getText();
		String priceECO = fF.getTPriceECO().getText();
		String airplane = (String) fF.getCBAirplane().getSelectedItem();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if (exitCity.isEmpty() || exitDate.isEmpty() || exitTime.isEmpty() ||
				enterCity.isEmpty()|| enterDate.isEmpty()|| enterTime.isEmpty()||
				priceEJE.isEmpty() || priceECO.isEmpty() || airplane.isEmpty() || airplane.equals("Indefinido")) {
			pM.showMessage("Por favor, complete todos los campos");

			return;
		}else if(lXML.isAlreadyInFile(fileName, objectName, "FlightNumber" , flightNumber)) {
			pM.showMessage( "El vuelo ya existe");
			return;
		} else if (!logModel.isValidTime(exitTime) || !logModel.isValidTime(enterTime)) {
			pM.showMessage( "Ingrese la hora en el formato de 24 horas (Hours:minutes) (xx:xx)");
			return;
		}else if(!logModel.isValidDate(enterDate) || !logModel.isValidDate(exitDate)) {
			pM.showMessage("Ingrese la fecha en el formato (dd/MM/yyyy)\nEjemplo 01/01/2000 ");
			return;
		}
		
		Fl = new Flight((Integer.parseInt(flightNumber)), exitCity, exitDate ,exitTime, enterCity, enterDate, enterTime, airplane, Double.parseDouble(priceEJE),
				Double.parseDouble(priceTUR), Double.parseDouble(priceECO));

		if(logModel.checkFlightOverlap(logModel.readXMLFile(fileName), Fl)) {
			pM.showMessage( "No se puede agregar el vuelo debido a que ya existe otro con el mismo avión y no se ha superado el rango de 20 horas");
			return;
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		crud.addObject(fileName, objectName, Fl.getDataName(), Fl.getData());
		fF.clean();
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
		String enterCity = fF.getTEnterCity().getText();
		String enterDate = fF.getTEnterDate().getText();
		String enterTime = fF.getTEnterTime().getText();
		String priceEJE = fF.getTPriceEJE().getText();
		String priceTUR = fF.getTPriceTUR().getText();
		String priceECO = fF.getTPriceECO().getText();
		String airplane = (String) fF.getCBAirplane().getSelectedItem();

		Flight currentFlight = logModel.getFlightFromXML(fileName, flightNumber);
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (flightNumber.isEmpty()) {
			pM.showMessage("Por favor, ingrese el número de vuelo a modificar");
			return;
		} else if (currentFlight == null) {
			pM.showMessage("El vuelo no existe");
			return;
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (exitCity.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar la ciudad de salida?", "Modificar")) {
				exitCity = pM.getData("Ingrese la nueva ciudad de salida:");
				if(exitCity.equals("null")) {exitCity = currentFlight.getDepartureCity();
				fF.getTExitCity().setText(exitCity); }
			} else {
				exitCity = currentFlight.getDepartureCity();
				fF.getTExitCity().setText(exitCity);
			}
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (exitTime.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar la hora de salida?", "Modificar")) {
				exitTime = pM.getData("Ingrese la nueva hora de salida:");
				if(exitTime.equals("null")){ 	exitTime = currentFlight.getDepartureTime();
				fF.getTExitTime().setText(exitTime);}
			} else {
				exitTime = currentFlight.getDepartureTime();
				fF.getTExitTime().setText(exitTime);
			}
		}
		if (!logModel.isValidTime(exitTime)) {
			pM.showMessage("Ingrese la hora en el formato de 24 horas (Hours:minutes) (xx:xx)");
			return;
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (exitDate.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar la fecha de salida?", "Modificar")) {
				exitDate = pM.getData("Ingrese la nueva fecha de salida:");
				if(exitDate.equals("null")) { exitDate = currentFlight.getDepartureDate();
				fF.getTExitDate().setText(exitDate); }
			} else {
				exitDate = currentFlight.getDepartureDate();
				fF.getTExitDate().setText(exitDate);
			}
		}
		if (!logModel.isValidDate(exitDate)) {
			pM.showMessage("Ingrese la fecha en el formato (dd/MM/yyyy)\nEjemplo 01/01/2000");
			return;
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (enterCity.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar la ciudad de llegada?", "Modificar")) {
				enterCity = pM.getData("Ingrese la nueva ciudad de llegada:");
				if(enterCity.equals("null") ) { enterCity = currentFlight.getArrivalCity();
				fF.getTEnterCity().setText(enterCity); }
			} else {
				enterCity = currentFlight.getArrivalCity();
				fF.getTEnterCity().setText(enterCity);
			}
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (enterTime.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar la hora de llegada?", "Modificar")) {
				enterTime = pM.getData("Ingrese la nueva hora de llegada:");
				if(enterTime.equals("null")) { enterTime = currentFlight.getArrivalTime();
				fF.getTEnterTime().setText(enterTime);}
			} else {
				enterTime = currentFlight.getArrivalTime();
				fF.getTEnterTime().setText(enterTime);
			}
		}
		if (!logModel.isValidTime(enterTime)) {
			pM.showMessage("Ingrese la hora en el formato de 24 horas (Hours:minutes) (xx:xx)");
			return;
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (enterDate.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar la fecha de llegada?", "Modificar")) {
				enterDate = pM.getData("Ingrese la nueva fecha de llegada:");
				if(enterDate.equals("null")) { enterDate = currentFlight.getArrivalDate();
				fF.getTEnterDate().setText(enterDate); }
			} else {
				enterDate = currentFlight.getArrivalDate();
				fF.getTEnterDate().setText(enterDate);
			}
		}
		if (!logModel.isValidDate(enterDate)) {
			pM.showMessage("Ingrese la fecha en el formato (dd/MM/yyyy)\nEjemplo 01/01/2000");
			return;
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (priceEJE.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar el monto de la clase Ejecutiva?", "Modificar")) {
				priceEJE = pM.getData("Ingrese el nuevo monto:");
				if(priceEJE.equals("null")) { priceEJE = String.valueOf(currentFlight.getBusinessClassSeatsPrice());
				fF.getTPriceEJE().setText(priceEJE); }
			} else {
				priceEJE = String.valueOf(currentFlight.getBusinessClassSeatsPrice());
				fF.getTPriceEJE().setText(priceEJE);
			}
		}
		if (priceTUR.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar el monto de la clase Turista?", "Modificar")) {
				priceTUR = pM.getData("Ingrese el nuevo monto:");
				if(priceTUR.equals("null")) { priceTUR = String.valueOf(currentFlight.getTouristClassSeatsPrice());
				fF.getTPriceTUR().setText(priceTUR); }
			} else {
				priceTUR = String.valueOf(currentFlight.getTouristClassSeatsPrice());
				fF.getTPriceTUR().setText(priceTUR);
			}
		}
		if (priceECO.isEmpty()) {
			if (pM.showConfirmationDialog("¿Desea modificar el monto de la clase Económica?", "Modificar")) {
				priceECO = pM.getData("Ingrese el nuevo monto:");
				if(priceECO.equals("null")) { priceECO = String.valueOf(currentFlight.getEconomyClassSeatsPrice());
				fF.getTPriceECO().setText(priceECO); }
			} else {
				priceECO = String.valueOf(currentFlight.getEconomyClassSeatsPrice());
				fF.getTPriceECO().setText(priceECO);
			}
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		if (airplane.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el avión?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el avión");
				airplane = (String) fF.getCBAirplane().getSelectedItem();
				if (airplane.equals("Indefinido")) {
					pM.showMessage("Por favor, seleccione el avión");
					return;
				}
			} else {
				airplane = currentFlight.getAirplane();
			}
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		String[] newData = {
				flightNumber, exitCity, exitDate, exitTime,
				enterCity, enterDate, enterTime, airplane, 
				priceEJE, priceTUR, priceECO};
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
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpFlight(userTypeString);
	}
}