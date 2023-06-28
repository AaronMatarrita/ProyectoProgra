package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import domain.Airline;
import domain.Airplane;
import domain.Flight;
import domain.Passenger;
import domain.Ticket;
import domain.TicketsHistory;

import presentation.ShowTicketsFrame;

public class ControllerShowTickets implements ActionListener{

	private String fileName = "HistoricTickets.xml";
	private String objectName = "HistoricTickets";
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private XMLFiles xmlF;	
	private ShowTicketsFrame sF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXML lXML;
	private LogicPassenger lXMLP;
	private LogicTicket lXMLT;
	private LogicAirline logAirline;
	private LogicFlight logFlight;
	private LogicAirplane logAirplane;
	private LogicHistoryTickets logHticks;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private CRUD crud;
	private TicketsHistory hticks;

	public ControllerShowTickets() {
		sF = new ShowTicketsFrame();
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		lXMLT = new LogicTicket();
		logAirline = new LogicAirline();
		lXMLP = new LogicPassenger();
		logFlight = new LogicFlight();
		logAirplane = new LogicAirplane();
		logHticks = new LogicHistoryTickets();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		sF.setJTableData(logHticks.readXMLFile(fileName));
		initializer();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializer() {
		sF.getBSearch().addActionListener(this);
		sF.getBReturn().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(sF.getBSearch() == e.getSource()) {
			showTickets();
		}else if(sF.getBReturn() == e.getSource()){
			sF.dispose();
			new ControllerTicket("");
		}
	}
	private void showTickets() {
			 // ArrayLists
	        ArrayList<String> numberTickets = new ArrayList<>();
	        ArrayList<Passenger> passengers;
	        ArrayList<Flight> flights;
	        ArrayList<Airplane> airplanes;
	        ArrayList<Airline> airlines;
	        ArrayList<Ticket> tickets = lXMLT.readXMLFile("Tickets.xml");
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
	        int i = 0;
	        //Ciclo for para que se haga la cantidad de tickets que hayan
	        for (Ticket ticket : tickets) {
	            numberTickets.add(String.valueOf(ticket.getTicketNumber()));
	            String SnumberTickets = numberTickets.get(i);
	            //Si el ticket no esta en el xml se anade
				if(!lXML.isAlreadyInFile("HistoricTickets.xml", "HistoricTickets", "TicketNumber",SnumberTickets)) {

		            String Spassport = ticket.getPassport();
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
					sF.setJTableData(logHticks.readXMLFile("HistoricTickets.xml"));
				}
			i++;
		}		
	}
}