package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentation.GUIAdmin;

public class ControllerAdmin implements ActionListener{

	// Declaración de instancias de clases y variables
	private GUIAdmin guiA;

	public ControllerAdmin() {
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
	}

	public void actionPerformed(ActionEvent e) {
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (guiA.getBUsers() == e.getSource()) {
			new ControllerAUser();	
		} 
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBBrands() == e.getSource()) {
			new ControllerABrand();
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBModels() == e.getSource()) {
			new ControllerAModel();
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBAirlines() == e.getSource()) {
			new ControllerAAirline();
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBAirplanes() == e.getSource()) {
			new ControllerAAirplane();
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBFlights() == e.getSource()) {
			new ControllerAFlights();
		}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if(guiA.getBPassengers() == e.getSource()) {
			new ControllerAPassenger();
		}
		
	}
}