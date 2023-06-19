package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLTicket;
import data.XMLFiles;
import domain.Passenger;
//import objetos
import domain.Tickets;
import domain.TicketsHistory;
//import frames
import presentation.TicketFrame;
import presentation.ShowTicketsFrame;
//import logicsxml
import data.LogicXMLPassenger;



public class ControllerTicketsHistory implements ActionListener{
	private String fileName = "HistoricTickets.xml";
	private String objectName = "HistoricTickets";
	//logics
	private XMLFiles xmlF;
	private LogicXML lXML;
	private CRUD crud;
	private LogicXMLTicket lXMLT;
	private LogicXMLPassenger lXMLP;
	//objetos
	private Tickets ticket;
	private TicketsHistory Hticks;
	//frames
	private TicketFrame tF;
	private ShowTicketsFrame sF;
	
	
	
	
	

	public ControllerTicketsHistory() {
		sF = new ShowTicketsFrame();
		//tF = new TicketFrame();
		lXML = new LogicXML();
		lXMLP = new LogicXMLPassenger();
		lXMLT = new LogicXMLTicket();
		crud = new CRUD();
		xmlF = new XMLFiles();
		xmlF.createXML(fileName, objectName);
		//setTableData();
		initializerAction();
	}
/*
	private void setTableData() {
		List<Tickets> users = lXMLT.readXMLFile(fileName);
		//tF.setJTableData(users);
	}*/

	public void initializerAction() {

		ArrayList<String> numberTickets = new ArrayList<>();
		ArrayList<Tickets> tickets = lXMLT.readXMLFile("Tickets.xml");
		ArrayList<String> passport = new ArrayList<>();
		ArrayList<Passenger> passenger = new ArrayList<>();
		int i=0;
		for(Tickets ticket : tickets) {
			
			numberTickets.add(String.valueOf(ticket.getTicketNumber()));
			passport.add(ticket.getPassport());
			
			String Spassport = passport.get(i);
			passenger = lXMLP.searchPassenger(Spassport);
			
			
			for (Passenger pas : passenger) {
	            String pasPassport = pas.getPassport();
	            String pasName = pas.getName();
	            String pasLastName = pas.getLastname();
	            String pasDateofbirth = pas.getDateofbirth();
	            String pasEmail = pas.getEmail();
	            String phoneNumber = pas.getPhoneNumber();
	        }
			for (Passenger dato : passenger) {
	            System.out.println(dato);
	        }
			
			for(String Numbertickets : numberTickets) {
				if(!lXML.isAlreadyInFile(fileName, objectName, "TicketNumber", Numbertickets)) {
					//System.out.println(Numbertickets);
					//System.out.println(passport);
					
					sF.clean();
					//Hticks  = new TicketsHistory(Numbertickets,pasPassport,pasNamepasLastName
					//		pasDateofbirth,pasEmail,phoneNumber);
					//crud.addObject(fileName, objectName, Hticks.getDataName(), Hticks.getData());
					JOptionPane.showMessageDialog(null, "Tiquete agregado");
					//setTableData();
				}
			}	
			i++;
		}
	
		//System.out.println("hola");
		
	}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//if(tF.getBShowTickets() == e.getSource()) {
		
			
		}


}