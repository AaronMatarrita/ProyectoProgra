package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLPassenger;
import data.XMLFiles;
import domain.Passenger;
import presentation.PassengerFrame;
import presentation.PopUpMessages;

public class ControllerAPassenger implements ActionListener{
	private Passenger passenger;
	private PassengerFrame pF;
	private CRUD crud;
	private LogicXMLPassenger lXMLP;
	private LogicXML lXML;
	private XMLFiles xmlF;
	private String fileName = "Passengers.xml";
	private String objectName = "person";
	private PopUpMessages pM;
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();

	public ControllerAPassenger(String userType) {
		pF = new PassengerFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		lXMLP = new LogicXMLPassenger();
		xmlF = new XMLFiles();
		pM = new PopUpMessages();
		xmlF.createXML(fileName, objectName);
		initializerAction();
	}

	private void setTableData() {
		String passport = pF.getTPassport().getText().trim();
		if(passport.isEmpty()) {
			passengers = lXMLP.readXMLFile(fileName);
		}else {
			passengers.clear();
			Passenger searchedPassenger = lXMLP.getPassengerFromXML(fileName, passport);
			if(searchedPassenger != null) {
				passengers.add(searchedPassenger);
			}
		}
		pF.setJTableData(passengers);
	}

	public void initializerAction() {
		pF.getBAddPassenger().addActionListener(this);
		pF.getBUpdate().addActionListener(this);
		pF.getBClear().addActionListener(this);
		pF.getBSearch().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (pF.getBAddPassenger() == e.getSource())
		{
			addPassenger();	
		} else if (pF.getBUpdate() == e.getSource())
		{
			updatePassenger();
		} else if (pF.getBClear() == e.getSource())
		{
			deletePassenger();
		}else if(pF.getBSearch() == e.getSource()) {
			searchPassenger();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addPassenger() {
		String passport = pF.getTPassport().getText();

		if (passport.isEmpty() || pF.getTName().getText().isEmpty() || pF.getTLastname().getText().isEmpty() 
				|| pF.getTDateOfBirth().getText().isEmpty() || pF.getTEmail().getText().isEmpty() 
				|| pF.getTPhoneNumber().getText().isEmpty()) {
			pM.showMessage("Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
			pM.showMessage("El pasaporte ya existe");
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
		setTableData();
		pM.showMessage("Usuario agregado");
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void searchPassenger() {
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updatePassenger() {
		String passport = pF.getTPassport().getText();
		Passenger currentPassenger = lXMLP.getPassengerFromXML(fileName, passport);

		String newPassport = passport; 

		String newName = pF.getTName().getText();
		String newLastname = pF.getTLastname().getText();
		String newDateOB = pF.getTDateOfBirth().getText();
		String newEmail = pF.getTEmail().getText();
		String newPhoneNumber = pF.getTPhoneNumber().getText();

		if (passport.isEmpty()) {
			pM.showMessage("Por favor, ingrese el nombre del pasajero a modificar");
			return;
		} else if (!lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
			pM.showMessage("El pasaporte no existe");
			return;
		}

		if(pM.showConfirmationDialog("Desea modificar el pasaporte del pasajero?", "Modificar")){
			newPassport = pM.getData("Ingrese el nuevo pasaporte del pasajero:");
			pF.getTPassport().setText(newPassport);
		}else {
			newPassport = currentPassenger.getPassport();
		}

		if(newName.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar el nombre del pasajero?", "Modificar")) {
				newName = pM.getData("Ingrese el nuevo nombre del pasajero:");
				pF.getTName().setText(newName);
			}else {
				newName = currentPassenger.getName();
			}
		}

		if(newLastname.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar los apellidos del pasajero?", "Modificar")){
				newLastname = pM.getData("Ingrese los apellidos pasaporte del pasajero:");
				pF.getTLastname().setText(newLastname);
			}else {
				newLastname = currentPassenger.getLastname();
			}
		}

		if(newDateOB.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar la fecha de nacimiento del pasajero?", "Modificar")) {

				newDateOB = pM.getData("Ingrese la fecha de nacimiento del pasajero:");
				pF.getTDateOfBirth().setText(newDateOB);
			}else {
				newDateOB = currentPassenger.getDateofbirth();
			}
		}

		if(newEmail.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar el email del pasajero?", "Modificar")){

				newEmail = pM.getData("Ingrese el email del pasajero:");
				pF.getTEmail().setText(newEmail);
			}else {
				newEmail = currentPassenger.getEmail();
			}
		}

		if(newPhoneNumber.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar el número de telefono del pasajero?", "Modificar")) {
				newPhoneNumber = pM.getData("Ingrese el número de telefono del pasajero:");
				pF.getTPhoneNumber().setText(newPhoneNumber);
			}else {
				newPhoneNumber = currentPassenger.getPhoneNumber();
			}
		}
		String[] newData = {newPassport, newName, newLastname, newDateOB, newEmail, newPhoneNumber};
		crud.updateObject(fileName, objectName, "Passport", passport, currentPassenger.getDataName(), newData);
		pF.clean();
		setTableData();
		pM.showMessage("Usuario agregado");
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void deletePassenger() {
		String passport = pF.getTPassport().getText();
		if (passport.isEmpty()) {
			pM.showMessage("Por favor, complete el número del pasajero a eliminar");
			return;
		} else if (!lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
			pM.showMessage("No se puede eliminar debido a que no existe");
			return;
		}
		if(pM.showConfirmationDialog("¿Está seguro de eliminar el usuario?", "Eliminar")){
			pF.clean();
			crud.deleteObject(fileName, objectName, "Passport", passport);
			setTableData();
			pM.showMessage("Pasajero eliminado");
		}
	}
}
