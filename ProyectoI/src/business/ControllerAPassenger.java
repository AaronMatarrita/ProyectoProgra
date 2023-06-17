package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLPassenger;
import data.XMLFiles;
import domain.Passenger;
import presentation.PassengerFrame;

public class ControllerAPassenger implements ActionListener{
	private Passenger passenger;
	private PassengerFrame pF;
	private CRUD crud;
	private LogicXMLPassenger lXMLP;
	private LogicXML lXML;
	private XMLFiles xmlF;
	private String fileName = "Passengers.xml";
	private String objectName = "person";

	public ControllerAPassenger(String userType) {
		pF = new PassengerFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		lXMLP = new LogicXMLPassenger();
		xmlF = new XMLFiles();
		xmlF.createXML(fileName, objectName);
		setTableData();
		initializerAction();

	}

	private void setTableData() {
		ArrayList<Passenger> passengers = lXMLP.readXMLFile(fileName);
		pF.setJTableData(passengers);
	}

	public void initializerAction() {
		pF.getBAddPassenger().addActionListener(this);
		pF.getBUpdate().addActionListener(this);
		pF.getBClear().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (pF.getBAddPassenger() == e.getSource()) {
			String passport = pF.getTPassport().getText();

			if (passport.isEmpty() || pF.getTName().getText().isEmpty() || pF.getTLastname().getText().isEmpty() 
					|| pF.getTDateOfBirth().getText().isEmpty() || pF.getTEmail().getText().isEmpty() 
					|| pF.getTPhoneNumber().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
				return;
			} else if (lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
				JOptionPane.showMessageDialog(null, "El pasaporte ya existe");
				return;
			}

			String name = pF.getTName().getText();
			String lastname = pF.getTLastname().getText();
			String dateOB = pF.getTDateOfBirth().getText();
			String email = pF.getTEmail().getText();
			String phoneNumber = pF.getTPhoneNumber().getText();

			pF.clean();
			passenger = new Passenger(passport, name, lastname, dateOB, email, phoneNumber);
			crud.addObject(fileName, objectName, passenger.getDataName(), passenger.getData());

			JOptionPane.showMessageDialog(null, "Usuario agregado");

			setTableData();
		} else if (pF.getBUpdate() == e.getSource()) {
			// En proceso...
			setTableData();
		} else if (pF.getBClear() == e.getSource()) {
			String passport = pF.getTPassport().getText();
			if (passport.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete el número del pasajero a eliminar");
				return;
			} else if (!lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");
				return;
			}

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario?", "Eliminar", dialogButton);

			if (dialogResult == JOptionPane.YES_OPTION) {
				pF.clean();
				crud.deleteObject(fileName, objectName, "Passport", passport);
				JOptionPane.showMessageDialog(null, "Pasajero eliminado");
				setTableData();
			}
		}
	}
}
