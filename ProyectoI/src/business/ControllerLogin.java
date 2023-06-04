package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.GUIAdmin;
import presentation.GUILogin;
import data.XMLFiles;

public class ControllerLogin implements ActionListener{

	//Declaración de instancias de clases y variables
	private GUILogin guiL;
	private XMLFiles fXML;
	public ControllerLogin() {
		//Inicializo Instancias
		guiL = new GUILogin();
		fXML = new XMLFiles();
		initializerAction();
	}

	public void initializerAction() {

		guiL.getBLogin().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(guiL.getBLogin() == e.getSource()) {

			String username = String.valueOf(guiL.getTUser().getText());
			String password = String.valueOf(guiL.getJPasswordUser().getPassword());
			boolean verify = fXML.verify("users.xml", username, password);
			if (verify == true) {
				guiL.dispose();
				new ControllerAdmin();
			}else{
				guiL.showMessage("Credenciales incorrectas");
			}
		}
	}
}