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
		//No se pueden repetir vuelos con los mismos aviones en un rango de 20 horas.

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
	
	private void searchFlight() {
		setTableData();
	}
	
	private void updateFlight() {
		
	}
	
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