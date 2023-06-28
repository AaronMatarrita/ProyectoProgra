package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.XMLFiles;
import presentation.GUILogin;

public class ControllerLogin implements ActionListener{

	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	GUILogin guiL;
	LogicLogin logL;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	XMLFiles fXML;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	
	public ControllerLogin() {	
		guiL = new GUILogin();
		fXML = new XMLFiles();
		logL = new LogicLogin();
		initializerAction();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializerAction() {
		guiL.getBLogin().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e) {
	    if (guiL.getBLogin() == e.getSource()) {
	        String username = guiL.getTUser().getText();
	        String password = String.valueOf(guiL.getJPasswordUser().getPassword());
	        String userType = logL.getUserType("Users.xml", username);
	        boolean verify = logL.verify("Users.xml", username, password);

	        if (username.equals("admin") && password.equals("admin")) {
	            guiL.dispose();
	            new ControllerMain(logL.getUserType("Users.xml", "1"));
	            return;
	        } else if (verify) {
	            guiL.dispose();
	            new ControllerMain(logL.getUserType("Users.xml", username));
	            return;
	        } else if(!verify){
	            guiL.showMessage("Credenciales incorrectas");
	            return;
	        }else if(userType.equals("Inactivo")) {
	        	guiL.showMessage("Usuario Inactivo");
	        	return;
	        }
	    }
	}
}