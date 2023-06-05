package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.LogicXMLUser;
import data.XMLFiles;
import presentation.GUILogin;

public class ControllerLogin implements ActionListener{

	//Declaración de instancias de clases y variables
	GUILogin guiL;
	LogicXMLUser lXMLU;
	XMLFiles fXML;
	public ControllerLogin() {
		//Inicializo Instancias
		lXMLU = new LogicXMLUser();
		guiL = new GUILogin();
		fXML = new XMLFiles();
		initializerAction();
	}

	public void initializerAction() {

		guiL.getBLogin().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
	    if (guiL.getBLogin() == e.getSource()) {
	        String username = guiL.getTUser().getText();
	        String password = String.valueOf(guiL.getJPasswordUser().getPassword());
	        boolean verify = lXMLU.verify("Users.xml", username, password);

	        if (username.equals("admin") && password.equals("admin")) {
	            guiL.dispose();
	            new ControllerAdmin();
	            return;
	        } else if (verify) {
	            guiL.dispose();
	            new ControllerAdmin();
	            return;
	        } else {
	            guiL.showMessage("Credenciales incorrectas");
	            return;
	        }
	    }
	}
}