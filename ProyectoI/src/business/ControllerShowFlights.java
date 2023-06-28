package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLAirline;
import data.LogicXMLAirplane;
import data.LogicXMLFlights;
import data.LogicXMLHistoryFlights;
import data.LogicXMLPassenger;
import data.LogicXMLTicket;
import data.XMLFiles;
import domain.Airline;
import domain.Airplane;
import domain.Flight;
import domain.Passenger;
import domain.Ticket;
import domain.TicketsHistory;
import presentation.ShowFlightsFrame;

public class ControllerShowFlights implements ActionListener{

	private String fileName = "HistoricFlights.xml";
	private String objectName = "HistoricFlights";

	private XMLFiles xmlF;	
	private ShowFlightsFrame sF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXML lXML;
	private LogicXMLPassenger lXMLP;
	private LogicXMLTicket lXMLT;
	private LogicXMLAirline logAirline;
	private LogicXMLFlights logFlight;
	private LogicXMLAirplane logAirplane;
	private LogicXMLHistoryFlights logHflights;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private CRUD crud;
	private TicketsHistory hticks;

	public ControllerShowFlights() {
		sF = new ShowFlightsFrame();
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		lXMLT = new LogicXMLTicket();
		logAirline = new LogicXMLAirline();
		lXMLP = new LogicXMLPassenger();
		logFlight = new LogicXMLFlights();
		logAirplane = new LogicXMLAirplane();
		logHflights = new LogicXMLHistoryFlights();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		sF.setJTableData(logHflights.readXMLFile(fileName));
		initializer();
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
			showFlights();
		}
	}
	private void showFlights() {
			 // ArrayLists
	        ArrayList<Passenger> passengers;
	        ArrayList<Flight> flights;
	        ArrayList<Airplane> airplanes;
	        ArrayList<Airline> airlines;
	        ArrayList<Ticket> tickets = lXMLT.readXMLFile("Tickets.xml");
	        
	        String fligth = (String) sF.getComboBox().getSelectedItem();
	        
	        // Datos del pasajero
	        String pasPassport = null;
	        String pasName = null;
	        String pasLastName = null;
	        String pasDateofbirth = null;
	        String pasEmail = null;
	        String pasphoneNumber = null;
	        //Datos fecha y hora
	        String buydate;
	        //Datos del vuelo
	        String exitCity = null;
	        String exitDate = null;
	        String enterCity = null;
	        String enterDate = null;
	        String airplane = null;
	        //Datos del avion
	        String id = null;
	        String year = null;
	        String airline = null;
	        String airplaneModel = null;
	        //Datos de la aerolinea
	        String name = null;
	        String operationCenter = null;
	        //Ticket
	        double ticketPrice = 0;
	        //index
	        
	            numberTickets.add(String.valueOf(ticket.getTicketNumber()));
	            String SnumberTickets = numberTickets.get(i);
	            //Si el ticket no esta en el xml se anade
				if(!lXML.isAlreadyInFile("HistoricTickets.xml", "HistoricTickets", "TicketNumber",SnumberTickets)) {
					//passport.add(ticket.getPassport()); 
		            String Spassport = ticket.getPassport();
		            //System.out.println(passport.length);
		            String ticketClass = ticket.getTickettype();
		            buydate = ticket.getBuyTicketDate();
		            
		            passengers = lXMLP.searchPassenger(Spassport);
		            for (Passenger pas : passengers) {
		                pasPassport = pas.getPassport();
		                pasName = pas.getName();
		                pasLastName = pas.getLastname();
		                pasDateofbirth = pas.getDateofbirth();
		                pasEmail = pas.getEmail();
		                pasphoneNumber = pas.getPhoneNumber();
		            }
		
		            String flightNumber = String.valueOf(ticket.getFlightNumber());
		            flights = logFlight.getArrayFlightFromXML("Flights.xml", flightNumber);
		            for (Flight fl : flights) {
		                exitCity = fl.getDepartureCity();
		                exitDate = fl.getDepartureDate();
		                enterCity = fl.getArrivalCity();
		                enterDate = fl.getArrivalDate();
		                airplane = fl.getAirplane();
		                if(ticket.getTickettype().equalsIgnoreCase("Ejecutivo")) {
			            	ticketPrice = fl.getBusinessClassSeatsPrice();
			            }else if (ticket.getTickettype().equalsIgnoreCase("Turista")) {
			            	ticketPrice = fl.getTouristClassSeatsPrice();
			            }else if (ticket.getTickettype().equalsIgnoreCase("Economico")) {
			            	ticketPrice = fl.getEconomyClassSeatsPrice();
			            }
		            }
		            
					airplanes = logAirplane.getArrayAirplaneFromXML("Airplanes.xml", airplane);
					for (Airplane ai : airplanes) {
		                id = ai.getId();
		                year = ai.getYear();
		                airline = ai.getAirline();
		                airplaneModel = ai.getAirplaneModel();
		            }

					airlines = logAirline.getArrayAirlineFromXML("Airlines.xml", airline);
					for (Airline ai : airlines) {
		                name = ai.getName();
		                operationCenter = ai.getCountry();
		            }

					hticks  = new TicketsHistory(SnumberTickets,pasPassport,pasName,pasLastName,
												pasEmail,pasDateofbirth,pasphoneNumber,buydate,
												name,operationCenter,id,airline,airplaneModel
												,year,exitCity,exitDate,enterCity,enterDate,ticketClass,ticketPrice);
					
					crud.addObject(fileName, objectName, hticks.getDataName(), hticks.getData());
					//JOptionPane.showMessageDialog(null, "Tiquete agregado");
					List<TicketsHistory> htickets = logHticks.readXMLFile("HistoricTickets.xml");
					tickets = lXMLT.readXMLFile("Tickets.xml");
					sF.setJTableData(logHflights.readXMLFile(fileName));
					sF.clean(); 
					
				}
			
	}
	
	//-------------------------------------------------------------------------------------------------------------------------
	
}