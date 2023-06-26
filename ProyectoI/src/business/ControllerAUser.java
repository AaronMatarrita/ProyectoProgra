package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLUser;
import data.XMLFiles;
import domain.User;
import presentation.PopUpMessages;
import presentation.UserFrame;

public class ControllerAUser implements ActionListener {

	private User Us;
	private UserFrame uF;

	private CRUD crud;
	private LogicXML lXML;

	private XMLFiles xmlF;
	private LogicXMLUser logU;
	private LogicExecuteHTML logHTML;

	private PopUpMessages pM;

	private String fileName = "Users.xml";
	private String objectName = "person";
	
	private String userType;

	private ArrayList<User> users = new ArrayList<User>();

	public ControllerAUser(String userType) {
		this.userType = userType;
		uF = new UserFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		logU = new LogicXMLUser();
		logHTML = new LogicExecuteHTML();
		pM = new PopUpMessages();
		xmlF.createXML(fileName, objectName);
		initializerAction();
	}

	private void setTableData() {
		String user = uF.getTUser().getText().trim();//.trim() elimina espacios inicio o final
		if (user.isEmpty()) {
			users = logU.readXMLFile(fileName);
		} else {
			users.clear();
			User searchedUser = logU.getUserFromFile(fileName, user);
			if (searchedUser != null) {
				users.add(searchedUser);
			}
		}
		uF.setJTableData(users);
	}

	public void initializerAction() {
		uF.getBAddUser().addActionListener(this);
		uF.getBUpdate().addActionListener(this);
		uF.getBClear().addActionListener(this);
		uF.getBSearch().addActionListener(this);
		uF.getBHelp().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (uF.getBAddUser() == e.getSource())
		{
			addUser();
		} else if (uF.getBUpdate() == e.getSource())
		{
			updateUser();
		} else if (uF.getBClear() == e.getSource()) {
			deleteUser();
		}else if(uF.getBSearch() == e.getSource()) {
			searchUser();
		}else if(uF.getBHelp() == e.getSource()) {
			help();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addUser() {
		String user = uF.getTUser().getText();

		if (user.isEmpty() || uF.getTPassword().getText().isEmpty()) {
			pM.showMessage("Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile("Users.xml", "person", "user", user)) {
			pM.showMessage("El usuario ya existe");
			return;
		} else if (user.equals("admin")) {
			pM.showMessage("No se puede agregar un usuario con el nombre de administrador por defecto");
			return;
		}

		String password = uF.getTPassword().getText();
		String StringUserType = (String) uF.getCBUserType().getSelectedItem();
		String StringUserStatus = (String) uF.getCBUserStatus().getSelectedItem();

		if (StringUserType.equals("Indefinido")) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de usuario");
			return;
		} else if (StringUserStatus.equals("Indefinido")) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione el estado del usuario");
			return;
		}

		int UserType = StringUserType.equals("Administrador") ? 1 : 2;
		boolean UserStatus = StringUserStatus.equals("Activo");

		uF.clean();
		Us = new User(user, password, UserType, UserStatus);
		crud.addObject(fileName, objectName, Us.getDataName(), Us.getData());
		pM.showMessage("Usuario agregado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void searchUser() {
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updateUser() {
		String user = uF.getTUser().getText();
		User currentUser = logU.getUserFromFile(fileName, user);

		String newUser = user;
		if (user.isEmpty()) {
			pM.showMessage("Por favor, ingrese el nombre del usuario a modificar");
			return;
		} else if (!lXML.isAlreadyInFile("Users.xml", "person", "user", user)) {
			pM.showMessage("El usuario no existe");
			return;
		} else if (user.equals("admin")) {
			pM.showMessage("No se puede modificar el usuario administrador por defecto");
			return;
		}

		String newPassword = uF.getTPassword().getText();
		String newUserType = (String) uF.getCBUserType().getSelectedItem();
		String newUserStatus = (String) uF.getCBUserStatus().getSelectedItem();

		if(newPassword.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar la contraseña del usuario?", "Modificar")) {
				newPassword = pM.getData("Ingrese la nueva contraseña de usuario:");
				if(newPassword == null) { pM.showMessage("Por favor, ingrese el dato solicitado"); return; }
				uF.getTPassword().setText(newPassword);
			}else {
				newPassword = currentUser.getPassword();
			}
		}else {
			newPassword = uF.getTPassword().getText();
		}
		if (newUserType.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el tipo de usuario?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el tipo de usuario");
				newUserType = (String) uF.getCBUserType().getSelectedItem();
				if (newUserType.equals("Indefinido")) {
					return; 
				}
			} else {
				newUserType = String.valueOf( currentUser.getUserType());
			} 
		}else {
			newUserType = newUserType.equals("Administrador") ? "1" : "2";
		}

		if (newUserStatus.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el estado del usuario?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el tipo de usuario");
				newUserStatus = (String) uF.getCBUserStatus().getSelectedItem();
				if (newUserStatus.equals("Indefinido")) {
					return; 
				}
			} else {
				newUserStatus = String.valueOf( currentUser.isStatus());
			}
		}else {
			newUserStatus = newUserStatus.equals("Activo") ? "true" : "false";
		}

		String[] newData = {newUser, newPassword, newUserType, newUserStatus};

		crud.updateObject(fileName, objectName, "user", user, currentUser.getDataName(), newData);
		uF.clean();
		pM.showMessage("Usuario modificado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void deleteUser() {
		String user = uF.getTUser().getText();
		if (user.isEmpty()) {
			pM.showMessage("Por favor, complete el nombre del usuario a eliminar");
			return;
		} else if (!lXML.isAlreadyInFile("Users.xml", "person", "user", user)) {
			pM.showMessage("No se puede eliminar debido a que no existe");
			return;
		} else if (user.equals("admin")) {
			pM.showMessage("No se puede eliminar el usuario por defecto administrador");
			return;
		}

		pM.showConfirmationDialog("¿Está seguro de eliminar el usuario?", "Eliminar");

		if (pM.showConfirmationDialog("¿Está seguro de eliminar el usuario?", "Eliminar")) {
			uF.clean();
			crud.deleteObject(fileName, objectName, "user", user);
			setTableData();
			pM.showMessage("Usuario eliminado");
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpUser(userTypeString);
	}
}

