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
	}
}