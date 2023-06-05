package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXMLUser;
import domain.User;
import presentation.GUIAdmin;
import presentation.UserFrame;

public class ControllerAdmin implements ActionListener, WindowListener {

	// Declaración de instancias de clases y variables
	private GUIAdmin guiA;
	private UserFrame uF;
	private CRUD crud;
	private User Us;
	private LogicXMLUser lXMLU;

	public ControllerAdmin() {
		// Inicializo Instancias
		guiA = new GUIAdmin();
		crud = new CRUD();
		lXMLU = new LogicXMLUser();
		initializerAction();
	}

	public void initializerAction() {
		guiA.getBUsers().addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (guiA.getBUsers() == e.getSource()) {
			uF = new UserFrame();
			uF.addWindowListener(this);
			uF.getBAddUser().addActionListener(this);
			uF.getBUpdate().addActionListener(this);
			uF.getBClear().addActionListener(this);
			guiA.dispose();
		}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (uF.getBAddUser() == e.getSource()) {
			String user = uF.getTUser().getText();

			if (user.isEmpty() || uF.getTPassword().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
				return;
			} else if (lXMLU.isAlreadyUser(user, "Users.xml")) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe");
				return;
			} else if (user.equals("admin")) {
				JOptionPane.showMessageDialog(null, "No se puede agregar un usuario con el nombre de administrador por defecto");
				return;
			}

			String password = uF.getTPassword().getText();
			String StringUserType = (String) uF.getCBUserType().getSelectedItem();
			String StringUserStatus = (String) uF.getCBUserStatus().getSelectedItem();

			if (StringUserType.equals("Indefinido") ) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de usuario");
				return;
			}else if(StringUserStatus.equals("Indefinido")) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione el estado del usuario");
				return;
			}

			//condición ? valorSiVerdadero : valorSiFalso
			int UserType = StringUserType.equals("Administrador") ? 1 : 2;
			boolean UserStatus = StringUserStatus.equals("Activo");

			uF.clean();
			Us = new User(user, password, UserType, UserStatus);
			crud.addObject("Users.xml", "person", Us.getDataName(), Us.getData());

			JOptionPane.showMessageDialog(null, "Usuario agregado");
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		} else if (uF.getBUpdate() == e.getSource()) {
		 //En proceso...
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if (uF.getBClear() == e.getSource()) {
			String user = uF.getTUser().getText();
			if (user.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete el nombre del usuario a eliminar");return;
			}else if(!lXMLU.isAlreadyUser(user, "Users.xml")) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
			else if(user.equals("admin")) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar el usuario por defecto");return;}
			else {
				crud.deleteObject("Users.xml", "person", "user", user);
			}
			JOptionPane.showMessageDialog(null, "Usuario eliminado");
		}
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
	    guiA.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}