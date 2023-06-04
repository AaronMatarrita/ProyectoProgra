package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.CRUD;
import data.XMLFiles;
import domain.User;
import presentation.GUIAdmin;
import presentation.UserFrame;

public class ControllerAdmin implements ActionListener{

	//Declaración de instancias de clases y variables
	GUIAdmin guiA;
	UserFrame uF;
	CRUD crud;
	XMLFiles xmlFiles;
	User Us;

	public ControllerAdmin() {
		//Inicializo Instancias
		guiA = new GUIAdmin();
		crud = new CRUD();
		xmlFiles = new XMLFiles();
		
		initializerAction();
	}

	public void initializerAction() {
		guiA.getBUsers().addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (guiA.getBUsers() == e.getSource()) {

			uF = new UserFrame();
			uF.getBAddUser().addActionListener(this);
			uF.getBUpdate().addActionListener(this);
			uF.getBClear().addActionListener(this);
			

		} else if (uF.getBAddUser() == e.getSource()) {
			String user = uF.getTUser().getText();
			String password = uF.getTPassword().getText();
			int userType = (int) uF.getCBUserType().getSelectedItem();
			Object selectedItem = uF.getCBUserStatus().getSelectedItem();
			boolean userStatus = false;
			
			if (selectedItem instanceof Boolean) {
				userStatus = (boolean) selectedItem;
			} else if (selectedItem instanceof String) {
				String selectedString = (String) selectedItem;
				if (selectedString.equals("true")) {
					userStatus = true;
				}

				Us = new User(user, password, userType, userStatus);
				crud.addObject("Users.xml", "person", Us.getDataName(), Us.getData());

				JOptionPane.showMessageDialog(null, "Usuario agregado");
			}
		}else if(uF.getBClear() == e.getSource()) {
			String user = uF.getTUser().getText();
			crud.deleteObject("Users.xml", "person", "user", user);
			JOptionPane.showMessageDialog(null, "Usuario eliminado");
		}
	}
}