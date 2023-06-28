package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.GUIMain;

public class ControllerMain implements ActionListener{

	private GUIMain guiM;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private String userType;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	
	public ControllerMain(String userType) {
		this.userType = userType;
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		guiM = new GUIMain();
		initializerAction();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializerAction() {
		guiM.getBUsers().addActionListener(this);
		guiM.getBBrands().addActionListener(this);
		guiM.getBModels().addActionListener(this);
		guiM.getBAirlines().addActionListener(this);
		guiM.getBAirplanes().addActionListener(this);
		guiM.getBFlights().addActionListener(this);
		guiM.getBPassengers().addActionListener(this);
		guiM.getBTickets().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e) {
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if (guiM.getBUsers() == e.getSource()) {
			new ControllerUser(userType);	
			guiM.dispose();
		} 
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if(guiM.getBBrands() == e.getSource()) {
			new ControllerBrand(userType);
			guiM.dispose();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if(guiM.getBModels() == e.getSource()) {
			new ControllerModel(userType);
			guiM.dispose();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if(guiM.getBAirlines() == e.getSource()) {
			new ControllerAirline(userType);
			guiM.dispose();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-		
		if(guiM.getBAirplanes() == e.getSource()) {
			new ControllerAirplane(userType);
			guiM.dispose();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if(guiM.getBFlights() == e.getSource()) {
			new ControllerFlight(userType);
			guiM.dispose();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if(guiM.getBPassengers() == e.getSource()) {
			new ControllerPassenger(userType);
			guiM.dispose();
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		if(guiM.getBTickets() == e.getSource()) {
			new ControllerTicket(userType);
			guiM.dispose();
		}
	}
}