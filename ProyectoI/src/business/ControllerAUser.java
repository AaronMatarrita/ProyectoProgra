package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLUser;
import data.XMLFiles;
import domain.User;
import presentation.UserFrame;

public class ControllerAUser implements ActionListener {
    private User Us;
    private UserFrame uF;
    
    private CRUD crud;
    private LogicXML lXML;
    
    private XMLFiles xmlF;
    private LogicXMLUser logicXMLUser;
    
    private String fileName = "Users.xml";
    private String objectName = "person";

    public ControllerAUser() {
        uF = new UserFrame();
        crud = new CRUD();
        lXML = new LogicXML();
        xmlF = new XMLFiles();
        logicXMLUser = new LogicXMLUser();
        xmlF.createXML(fileName, objectName);
        setTableData();
        initializerAction();

    }
    
    private void setTableData() {
        List<User> users = logicXMLUser.readXMLFile(fileName);
        uF.setJTableData(users);
    }

    public void initializerAction() {
        uF.getBAddUser().addActionListener(this);
        uF.getBUpdate().addActionListener(this);
        uF.getBClear().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (uF.getBAddUser() == e.getSource()) {
            String user = uF.getTUser().getText();

            if (user.isEmpty() || uF.getTPassword().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                return;
            } else if (lXML.isAlreadyInFile("Users.xml", "person", "user", user)) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe");
                return;
            } else if (user.equals("admin")) {
                JOptionPane.showMessageDialog(null, "No se puede agregar un usuario con el nombre de administrador por defecto");
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

            JOptionPane.showMessageDialog(null, "Usuario agregado");
            
            setTableData();
        } else if (uF.getBUpdate() == e.getSource()) {
            // En proceso...
        	 setTableData();
        } else if (uF.getBClear() == e.getSource()) {
            String user = uF.getTUser().getText();
            if (user.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete el nombre del usuario a eliminar");
                return;
            } else if (!lXML.isAlreadyInFile("Users.xml", "person", "user", user)) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");
                return;
            } else if (user.equals("admin")) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el usuario por defecto administrador");
                return;
            }

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario?", "Eliminar", dialogButton);

            if (dialogResult == JOptionPane.YES_OPTION) {
                uF.clean();
                crud.deleteObject(fileName, objectName, "user", user);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");

                setTableData();
            }
        }
    }
}
