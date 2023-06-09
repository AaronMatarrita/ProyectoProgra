package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.CRUD;
import data.FilePDF;
import data.LogicAirline;
import data.LogicAirplane;
import data.LogicFlight;
import data.LogicModel;
import data.LogicPassenger;
import data.LogicTicket;
import data.LogicXML;
import data.XMLFiles;
import domain.Airline;
import domain.Airplane;
import domain.AirplaneModel;
import domain.Flight;
import domain.Passenger;
import domain.Ticket;
import presentation.PopUpMessages;
import presentation.TicketFrame;

public class ControllerTicket implements ActionListener{

	private String fileName = "Tickets.xml";
	private String objectName = "Tickets";
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private XMLFiles xmlF;
	private TicketFrame tF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXML lXML;
	private LogicPassenger logPassenger;
	private LogicTicket logTicket;
	private LogicAirline logAirline;
	private LogicFlight logFlight;
	private LogicAirplane logAirplane;
	private LogicModel logAModel;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private Ticket ticket;
	private CRUD crud;
	private PopUpMessages pM;
	private FilePDF pdf;
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	private LogicExecuteHTML logHTML;
	private String userType;

	public ControllerTicket(String userType) {
		this.userType = userType;
		logHTML = new LogicExecuteHTML();
		tF = new TicketFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		logTicket = new LogicTicket();
		logAirline = new LogicAirline();
		logPassenger = new LogicPassenger();
		logFlight = new LogicFlight();
		logAirplane = new LogicAirplane();
		logAModel = new LogicModel();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		pdf = new FilePDF();
		xmlF.createXML(fileName, objectName);
		pM = new PopUpMessages();
		tF.fillFlightNumberComboBox(logFlight.readFlightsumbersFromXML("Flights.xml"));
		tF.fillPassportComboBox(logPassenger.readPassengerPassportsFromXML("Passengers.xml"));
		initializer();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void setTableData() {
		String ticketNumber = tF.getTTicketNumber().getText();
		if(ticketNumber.isEmpty()) {
			tickets = logTicket.readXMLFile(fileName);
		}else {
			tickets.clear();
			Ticket searchedTicket = logTicket.getTicketFromXML(fileName, ticketNumber);
			if(searchedTicket != null) {
				tickets.add(searchedTicket);
			}
		}
		tF.setJTableData(tickets);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializer() {
		tF.getBAddTickets().addActionListener(this);
		tF.getBUpdate().addActionListener(this);
		tF.getBClear().addActionListener(this);
		tF.getBSearch().addActionListener(this);
		tF.getBSaveTicket().addActionListener(this);
		tF.getBHelp().addActionListener(this);
		tF.getBShowTickets().addActionListener(this);
		tF.getBReturn().addActionListener(this);

	}
	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tF.getBAddTickets() == e.getSource()) {
			addTickets();
		}else if(tF.getBUpdate() == e.getSource()) {
			updateTickets(); 
			setTableData();
		}else if(tF.getBClear() == e.getSource()) {
			deleteTickets();
		}else if(tF.getBSearch() == e.getSource()) {
			searchTickets();
		}else if(tF.getBSaveTicket() == e.getSource()) {
			downloadTicket();
		}else if(tF.getBHelp() == e.getSource()) {
			help();
		}else if(tF.getBShowTickets() == e.getSource()) {
			tF.dispose();
			new ControllerShowTickets();
		}else if(tF.getBReturn() == e.getSource()){
			tF.dispose();
			new ControllerMain(userType);
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addTickets() {
		String passport = String.valueOf(tF.getCbPassport().getSelectedItem());
		String ticketNumber = tF.getTTicketNumber().getText();
		String flightNumber = String.valueOf(tF.getCbFlightNumber().getSelectedItem());
		String ticketType = (String) tF.getCBTicketType().getSelectedItem();
		String buyticketdate = tF.getCurrentDateTime();

		if (passport.isEmpty() || ticketNumber.equals("Indefinido") || flightNumber.equals("Indefinido")  ||ticketType.equals("Indefinido") ) {
			pM.showMessage("Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "TicketNumber", ticketNumber)) {
			pM.showMessage("El Tiquete ya existe");
			return;
		}	else if (ticketType.equals("Indefinido")) {
			pM.showMessage("Por favor, seleccione el estado del usuario");
			return;
		}
		tF.clean();
		ticket  = new Ticket(Integer.parseInt(ticketNumber), passport, Integer.parseInt(flightNumber),ticketType,buyticketdate);
		
		Flight flight = logFlight.getFlightFromXML("Flights.xml", String.valueOf( ticket.getFlightNumber()));
		Airplane airplane = logAirplane.getAirplaneFromXML("Airplanes.xml", flight.getAirplane());
		AirplaneModel airplaneModel = logAModel.getAirplaneModelFromXML("Models.xml", airplane.getAirplaneModel());
		if(!logTicket.isSeatsAvailable(fileName, airplane.getAirplaneModel(), airplaneModel)) {
			
			pM.showMessage("Los sentimos, el espacio del asiento solicitado no esta disponible");return;
		}
		crud.addObject(fileName, objectName, ticket.getDataName(), ticket.getData());
		pM.showMessage("Tiquete agregado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void searchTickets() {
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updateTickets() {
		String numberTicket = tF.getTTicketNumber().getText();
		Ticket currentTicket = logTicket.getTicketFromXML(fileName, numberTicket);

		if (numberTicket.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el numero de ticket a modificar");
			return;
		} else if (currentTicket == null) {
			pM.showMessage("El Ticket no existe");
			return;
		}

		String newTicket = numberTicket;
		String newPassport = (String) tF.getCbPassport().getSelectedItem();
		String newFlightNumber = (String) tF.getCbFlightNumber().getSelectedItem();
		String newTicketType = (String) tF.getCBTicketType().getSelectedItem();
		@SuppressWarnings("unused")
		String buydate = currentTicket.getBuyTicketDate();

		if (newPassport.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el pasaporte?", "Modificar")) {
				newPassport = pM.getData("Ingrese el nuevo pasaporte:");
				if(newPassport.equals("Indefinido")) { pM.showMessage("Por favor, seleccione el dato solicitado"); return; }
			}else {
				newPassport = currentTicket.getPassport();
				tF.getCbPassport().setSelectedItem(newPassport);
			}
		}
		if (newTicketType.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el tipo de ticket?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el tipo de ticket");
				newTicketType = (String) tF.getCBTicketType().getSelectedItem();
				if(newTicketType.equals("Indefinido")) { pM.showMessage("Por favor, seleccione el dato solicitado"); return; }
			} else {
				newTicketType = currentTicket.getTickettype();
				tF.getCBTicketType().setSelectedItem(newTicket);
			}
		}
		String[] newData = { newTicket, newPassport, newFlightNumber, newTicketType };
		crud.updateObject(fileName, objectName, "TicketNumber", numberTicket, currentTicket.getDataName(), newData);
		tF.clean();
		pM.showMessage("Ticket modificado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void deleteTickets() {
		String ticket = tF.getTTicketNumber().getText();
		if (ticket.isEmpty()) {
			pM.showMessage("Por favor, ingrese el número de tiquete a eliminar");
			return;
		} else if (!lXML.isAlreadyInFile(fileName , objectName, "TicketNumber", ticket)) {
			pM.showMessage( "No se puede eliminar debido a que no existe");
			return;
		}
		if (pM.showConfirmationDialog("¿Está seguro de eliminar el tiquete?", "Eliminar" )) {
			tF.clean();
			crud.deleteObject(fileName, objectName, "TicketNumber", ticket);
			crud.deleteObject("HistoricTickets.xml", "HistoricTickets", "TicketNumber", ticket);
			pM.showMessage("Tiquete eliminado");
			setTableData();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void downloadTicket() 
	{
		String ticketNumber = tF.getTTicketNumber().getText();
		if (ticketNumber.isEmpty()) {
			pM.showMessage("Por favor, ingrese el número del pasaporte a guardar!");
			return;
		}
		Ticket ticket = logTicket.getTicketFromXML(fileName, ticketNumber);
		if(ticket == null) {pM.showMessage("El ticket no existe en los datos registrados"); return;}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		Passenger passenger = logPassenger.getPassengerFromXML("Passengers.xml", ticket.getPassport());
		if(passenger == null) {pM.showMessage("El pasajero no existe en los datos registrados"); return;}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		Flight flight = logFlight.getFlightFromXML("Flights.xml", String.valueOf( ticket.getFlightNumber()));
		if(flight == null) {pM.showMessage("El vuelo no existe en los datos registrados"); return;}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		Airplane airplane = logAirplane.getAirplaneFromXML("Airplanes.xml", flight.getAirplane());
		if(airplane == null) {pM.showMessage("El avión no existe en los datos registrados"); return;}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		Airline airline = logAirline.getAirlineFromFile("Airlines.xml", airplane.getAirline());
		if(airline == null) {pM.showMessage("La aerolínea no existe en los datos registrados"); return;}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		AirplaneModel airplaneModel = logAModel.getAirplaneModelFromXML("Models.xml", airplane.getAirplaneModel());
		if(airplaneModel == null) {pM.showMessage("El modelo de avión no existe en los datos registrados"); return;}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		Double price = 0.0;
		if(ticket.getTickettype().equalsIgnoreCase("Ejecutivo")) {
			price = flight.getBusinessClassSeatsPrice();
		}else if(ticket.getTickettype().equalsIgnoreCase("Turista")) {
			price = flight.getTouristClassSeatsPrice();
		}else if(ticket.getTickettype().equalsIgnoreCase("Economico")) {
			price = flight.getEconomyClassSeatsPrice();
		}
		//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+
		pdf.createTicket(ticket, passenger, airline, airplane, airplaneModel, flight, price);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.help(userTypeString, "mTickets.html");
	}
}