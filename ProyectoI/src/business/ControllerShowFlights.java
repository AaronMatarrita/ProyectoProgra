package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.CRUD;
import data.LogicXMLAirplane;
import data.LogicXMLFlights;
import data.LogicXMLHistoryFlights;
import data.LogicXMLModel;
import data.XMLFiles;
import data.LogicXML;
import data.CRUD;
import domain.Airplane;
import domain.AirplaneModel;
import domain.Flight;
import domain.FlightsHistory;
import presentation.PopUpMessages;
import presentation.ShowFlightsFrame;

public class ControllerShowFlights implements ActionListener{

	private String fileName = "HistoricFlights.xml";
	private String objectName = "HistoricFlights";
	private ArrayList<FlightsHistory> fls = new ArrayList<FlightsHistory>();
	
	private XMLFiles xmlF;	
	private LogicXML xmlL;
	private CRUD cr;
	private ShowFlightsFrame sF;
	private PopUpMessages pM;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXMLFlights logFlight;
	private LogicXMLAirplane logAirplane;
	private LogicXMLHistoryFlights logHflights;
	private LogicXMLModel logModel;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private CRUD crud;

	public ControllerShowFlights() {
		sF = new ShowFlightsFrame();
		crud = new CRUD();
		xmlF = new XMLFiles();
		xmlL = new LogicXML();
		cr = new CRUD();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		logFlight = new LogicXMLFlights();
		logAirplane = new LogicXMLAirplane();
		logModel = new LogicXMLModel();
		pM = new PopUpMessages();
		logHflights = new LogicXMLHistoryFlights();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		sF.fillFlightsComboBox(logFlight.readFlightsumbersFromXML("Flights.xml"));
		setJTableData();
		initializer();
	}
	private void setJTableData() {
		if (sF.getComboBox().getSelectedItem().equals("Indefinido")) {
			fls.clear();
		} else {
			fls.clear();
			FlightsHistory searchedFl = logHflights.getFlightFromFile(fileName, String.valueOf(sF.getComboBox().getSelectedItem()));
			if (searchedFl != null) {
				fls.add(searchedFl);
			}
		}
		sF.setJTableData(fls);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializer() {
		sF.getBSearch().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(sF.getBSearch() == e.getSource()) {
			if (sF.getComboBox().getSelectedItem().equals("Indefinido")) {
				fls.clear();
				sF.setJTableData(fls);
				pM.showMessage("Por favor, ingrese el avion a consultar");
			}else {
				showFlights();
			}
			
		}
	}
	private void showFlights() {
			//ArrayLists
	        ArrayList<Flight> flights;
	        ArrayList<Airplane> airplanes;
	        ArrayList<AirplaneModel> models;
	        //Avion
	        String fligth = (String) sF.getComboBox().getSelectedItem();
	        //Datos del vuelo
	        String exitCity = null;
	        String exitDate = null;
	        String enterCity = null;
	        String enterDate = null;
	        String airplane = null;
	        //Datos del avion
	        String airline = null;
	        String airplaneModel = null;
	        //Datos de la aerolinea
	        int soldEJEseats = 0;
	        int totalEJEseats = 0;
	    	int soldTOUseats = 0;
	    	int totalTOUseats = 0;
	    	int soldECOseats = 0;
	    	int totalECOseats = 0;
	    	double EJEprice = 0;
	    	double TOUprice = 0;
	    	double ECOprice = 0;
	    	double totalflight= 0;
	    	
            flights = logFlight.getArrayFlightFromXML("Flights.xml", fligth);
 		
            for (Flight fl : flights) {
                exitCity = fl.getDepartureCity();
                exitDate = fl.getDepartureDate();
                enterCity = fl.getArrivalCity();
                enterDate = fl.getArrivalDate();
                airplane = fl.getAirplane();
                EJEprice = fl.getBusinessClassSeatsPrice();
    	    	TOUprice = fl.getTouristClassSeatsPrice();
    	    	ECOprice = fl.getEconomyClassSeatsPrice();           
            }
            
            airplanes = logAirplane.getArrayAirplaneFromXML("Airplanes.xml", airplane);
			for (Airplane ai : airplanes) {
                airline = ai.getAirline();
                airplaneModel = ai.getAirplaneModel();
            }
			
			models = logModel.getArrayAirplaneModelFromXML("Models.xml", airplaneModel);
			for (AirplaneModel model : models) {
		        totalEJEseats = model.getBusinessClassSeats();
		    	totalTOUseats = model.getTouristClassSeats();
		    	totalECOseats = model.getEconomyClassSeats();
		    	int EJE= model.getTotalBusinessClassSeats();
		    	soldEJEseats = model.getTotalBusinessClassSeats();
		    	soldTOUseats = model.getTotalTouristClassSeats() - model.getTouristClassSeats();
		    	soldECOseats = model.getTotalEconomyClassSeats() - model.getEconomyClassSeats();
            }
			
			totalflight = (soldEJEseats*EJEprice)+(soldTOUseats*TOUprice)+(soldECOseats*ECOprice);
			
			FlightsHistory hflights = new FlightsHistory(fligth, airline, airplane,exitCity,
					exitDate, enterCity, enterDate, soldEJEseats, totalEJEseats,
					soldTOUseats,totalTOUseats, soldECOseats, totalECOseats,
					EJEprice, TOUprice, ECOprice, totalflight);
			
					crud.addObject(fileName, objectName, hflights.getDataName(), hflights.getData());
					setJTableData();
					sF.clean(); 	
					cr.deleteObject(fileName, objectName, "FlightNumber", fligth);
	
	}

}