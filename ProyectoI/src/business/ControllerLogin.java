package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.GUIAdmin;
import presentation.GUILogin;

public class ControllerLogin implements ActionListener{

	//Declaración de instancias de clases y variables
	GUILogin guiL;

	public ControllerLogin() {
		//Inicializo Instancias
		guiL = new GUILogin();
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
			//Verificación del usuario administrador por defecto
			if (username.equals("admin") && password.equals("admin")) {
				guiL.dispose();
				new ControllerAdmin();
			}else if(!username.equals("admin")){
				guiL.showMessage("Usuario invalido");
			}else if(!password.equals("admin")){
				guiL.showMessage("Contraseña invalida");
			}
		}
	}
}