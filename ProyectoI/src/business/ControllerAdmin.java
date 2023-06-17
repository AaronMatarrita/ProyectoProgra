package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentation.GUIAdmin;

public class ControllerAdmin implements ActionListener{

	// Declaración de instancias de clases y variables
	private GUIAdmin guiA;
	private String userType;

	public ControllerAdmin(String userType) {
		this.userType = userType;
		// Inicializo Instancias
		guiA = new GUIAdmin();
		initializerAction();
	}

	public void initializerAction() {
		guiA.getBUsers().addActionListener(this);
		guiA.getBBrands().addActionListener(this);
		guiA.getBModels().addActionListener(this);
		guiA.getBAirlines().addActionListener(this);
		guiA.getBAirplanes().addActionListener(this);
		guiA.getBFlights().addActionListener(this);
		guiA.getBPassengers().addActionListener(this);
		guiA.getBTickets().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (guiA.getBUsers() == e.getSource()) {
			new ControllerAUser(userType);	
		} 
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBBrands() == e.getSource()) {
			new ControllerABrand(userType);
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBModels() == e.getSource()) {
			new ControllerAModel(userType);
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBAirlines() == e.getSource()) {
			new ControllerAAirline(userType);
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBAirplanes() == e.getSource()) {
			new ControllerAAirplane(userType);
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBFlights() == e.getSource()) {
			new ControllerAFlights(userType);
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBPassengers() == e.getSource()) {
			new ControllerAPassenger(userType);
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBTickets() == e.getSource()) {
			new ControllerATicket(userType);
		}
	}
}