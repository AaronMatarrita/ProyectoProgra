package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.CRUD;
import data.FilePDF;
import data.LogicXML;
import data.LogicXMLAirline;
import data.LogicXMLAirplane;
import data.LogicXMLFlights;
import data.LogicXMLModel;
import data.LogicXMLPassenger;
import data.LogicXMLTicket;
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

	private XMLFiles xmlF;
	private TicketFrame tF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicXML lXML;
	private LogicXMLPassenger lXMLP;
	private LogicXMLTicket lXMLT;
	private LogicXMLAirline logAirline;
	private LogicXMLFlights logFlight;
	private LogicXMLAirplane logAirplane;
	private LogicXMLModel logAModel;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private Ticket ticket;
	private CRUD crud;
	private PopUpMessages pM;
	private FilePDF pdf;
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public ControllerTicket(String userType) {
		tF = new TicketFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		lXMLT = new LogicXMLTicket();
		logAirline = new LogicXMLAirline();
		lXMLP = new LogicXMLPassenger();
		logFlight = new LogicXMLFlights();
		logAirplane = new LogicXMLAirplane();
		logAModel = new LogicXMLModel();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		pdf = new FilePDF();
		xmlF.createXML(fileName, objectName);
		pM = new PopUpMessages();
		tF.fillFlightNumberComboBox(logFlight.readFlightsumbersFromXML("Flights.xml"));
		tF.fillPassportComboBox(lXMLP.readPassengerPassportsFromXML("Passengers.xml"));
		initializer();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void setTableData() {
		String ticketNumber = tF.getTTicketNumber().getText();
		if(ticketNumber.isEmpty()) {
			tickets = lXMLT.readXMLFile(fileName);
		}else {
			tickets.clear();
			Ticket searchedTicket = lXMLT.getTicketFromXML(fileName, ticketNumber);
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
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addTickets() {
		String passport = String.valueOf(tF.getCbFlightNumber().getSelectedItem());
		String ticketNumber = tF.getTTicketNumber().getText();
		String flightNumber = String.valueOf(tF.getCbFlightNumber().getSelectedItem());
		String ticketType = (String) tF.getCBTicketType().getSelectedItem();

		if (passport.isEmpty() || ticketNumber.isEmpty() || flightNumber.isEmpty()||ticketType.isEmpty()) {
			pM.showMessage("Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "id", ticketNumber)) {
			pM.showMessage("El Tiquete ya existe");
			return;
		}	else if (ticketType.equals("Indefinido")) {
			pM.showMessage("Por favor, seleccione el estado del usuario");
			return;
		}
		tF.clean();
		ticket  = new Ticket(Integer.parseInt(ticketNumber), passport, Integer.parseInt(flightNumber),ticketType);
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
		Ticket currentTicket = lXMLT.getTicketFromXML(fileName, numberTicket);

		if (numberTicket.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el numero de ticket a modificar");
			return;
		} else if (currentTicket == null) {
			pM.showMessage("El Ticket no existe");
			return;
		}

		String newTicket = numberTicket;
		if (pM.showConfirmationDialog("Desea modificar el numero de ticket?", "Modificar")) {
			newTicket = pM.getData("Ingrese el nuevo numero de ticket:");
		}

		String newPassport = String.valueOf(tF.getCbFlightNumber().getSelectedItem());
		String newFlightNumber = String.valueOf(tF.getCbFlightNumber().getSelectedItem());
		String newTicketType = (String) tF.getCBTicketType().getSelectedItem();
		
		if (newPassport.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el pasaporte?", "Modificar")) {
				newPassport = pM.getData("Ingrese el nuevo pasaporte:");
			}else {
				newPassport = currentTicket.getPassport();
				tF.getCbPassport().setSelectedItem(newPassport);
			}
		}
		if (newTicketType.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el tipo de ticket?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el tipo de ticket");
				newTicketType = (String) tF.getCBTicketType().getSelectedItem();
				if (newTicketType.equals("Indefinido")) {
					return;
				}
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
		Ticket ticket = lXMLT.getTicketFromXML(fileName, ticketNumber);
		Passenger passenger = lXMLP.getPassengerFromXML("Passengers.xml", ticket.getPassport());
		Flight flight = logFlight.getFlightFromXML("Flights.xml", String.valueOf( ticket.getFlightNumber()));
		Airplane airplane = logAirplane.getAirplaneFromXML("Airplanes.xml", flight.getAirplane());
		Airline airline = logAirline.getAirlineFromFile("Airlines.xml", airplane.getAirline());
		AirplaneModel airplaneModel = logAModel.getAirplaneModelFromXML("Models.xml", airplane.getAirplaneModel());
		
		pdf.createTicket(ticket, passenger, airline, airplane, airplaneModel, flight);

	}
}