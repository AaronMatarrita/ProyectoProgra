package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import domain.Passenger;
import presentation.PassengerFrame;
import presentation.PopUpMessages;
import java.util.Date;


public class ControllerPassenger implements ActionListener{

	private String fileName = "Passengers.xml";
	private String objectName = "person";
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private Passenger passenger;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private PassengerFrame pF;
	private PopUpMessages pM;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private CRUD crud;
	private XMLFiles xmlF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicPassenger logPassenger;
	private LogicXML lXML;
	private LogicExecuteHTML logHTML;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private String userType;
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-

	public ControllerPassenger(String userType) {
		this.userType = userType;
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		pF = new PassengerFrame(userType);
		pM = new PopUpMessages();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		crud = new CRUD();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		lXML = new LogicXML();
		logPassenger = new LogicPassenger();
		logHTML = new LogicExecuteHTML();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		initializerAction();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void setTableData() {
		String passport = pF.getTPassport().getText().trim();
		if(passport.isEmpty()) {
			passengers = logPassenger.readXMLFile(fileName);
		}else {
			passengers.clear();
			Passenger searchedPassenger = logPassenger.getPassengerFromXML(fileName, passport);
			if(searchedPassenger != null) {
				passengers.add(searchedPassenger);
			}
		}
		pF.setJTableData(passengers);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializerAction() {
		pF.getBAddPassenger().addActionListener(this);
		pF.getBUpdate().addActionListener(this);
		pF.getBClear().addActionListener(this);
		pF.getBSearch().addActionListener(this);
		pF.getBHelp().addActionListener(this);
		pF.getBReturn().addActionListener(this);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (pF.getBAddPassenger() == e.getSource()){
			addPassenger();	
		} else if (pF.getBUpdate() == e.getSource()){
			updatePassenger();
		} else if (pF.getBClear() == e.getSource()){
			deletePassenger();
		}else if(pF.getBSearch() == e.getSource()) {
			searchPassenger();
		}else if(pF.getBHelp() == e.getSource()) {
			help();
		}else if(pF.getBReturn() == e.getSource()){
			pF.dispose();
			new ControllerMain(userType);
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addPassenger() {
	    String passport = pF.getTPassport().getText();

	    if (passport.isEmpty() || pF.getTName().getText().isEmpty() || pF.getTLastname().getText().isEmpty()
	            || pF.getTDateOfBirth().getDate() == null || pF.getTEmail().getText().isEmpty()
	            || pF.getTPhoneNumber().getText().isEmpty()) {
	        pM.showMessage("Por favor, complete todos los campos");
	        return;
	    } else if (lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
	        pM.showMessage("El pasaporte ya existe");
	        return;
	    }

	    String name = pF.getTName().getText();
	    String lastname = pF.getTLastname().getText();

	    Date dateOfBirth = pF.getTDateOfBirth().getDate();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String dateOB = dateFormat.format(dateOfBirth);

	    String email = pF.getTEmail().getText();
	    String phoneNumber = pF.getTPhoneNumber().getText();

	    pF.clean();
	    passenger = new Passenger(passport, name, lastname, dateOB, email, phoneNumber);
	    crud.addObject(fileName, objectName, passenger.getDataName(), passenger.getData());
	    setTableData();
	    pM.showMessage("Pasajero agregado");
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void searchPassenger() {
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updatePassenger() {
	    String passport = pF.getTPassport().getText();
	    Passenger currentPassenger = logPassenger.getPassengerFromXML(fileName, passport);

	    String newPassport = passport;
	    String newName = pF.getTName().getText();
	    String newLastname = pF.getTLastname().getText();
	    Date dateOfBirth = pF.getTDateOfBirth().getDate();
	    SimpleDateFormat newDateOB = new SimpleDateFormat("dd/MM/yyyy");
	    String newDateOfBirth = dateOfBirth != null ? newDateOB.format(dateOfBirth) : null;
	    String newEmail = pF.getTEmail().getText();
	    String newPhoneNumber = pF.getTPhoneNumber().getText();

	    if (passport.isEmpty()) {
	        pM.showMessage("Por favor, ingrese el pasaporte del pasajero a modificar");
	        return;
	    } else if (!lXML.isAlreadyInFile(fileName, objectName, "Passport", passport)) {
	        pM.showMessage("El pasaporte no existe");
	        return;
	    }

	    if (newName.isEmpty()) {
	        if (pM.showConfirmationDialog("Desea modificar el nombre del pasajero?", "Modificar")) {
	            newName = pM.getData("Ingrese el nuevo nombre del pasajero:");
	            if (newName.equals("null")) {
	                newName = currentPassenger.getName();
	                pF.getTName().setText(newName);
	            }
	        } else {
	            newName = currentPassenger.getName();
	            pF.getTName().setText(newName);
	        }
	    }

	    if (newLastname.isEmpty()) {
	        if (pM.showConfirmationDialog("Desea modificar los apellidos del pasajero?", "Modificar")) {
	            newLastname = pM.getData("Ingrese los apellidos del pasajero:");
	            if (newLastname.equals("null")) {
	                newLastname = currentPassenger.getLastname();
	                pF.getTLastname().setText(newLastname);
	            }
	        } else {
	            newLastname = currentPassenger.getLastname();
	            pF.getTLastname().setText(newLastname);
	        }
	    }

	    if (dateOfBirth == null) {
	        if (pM.showConfirmationDialog("Desea modificar la fecha de nacimiento del pasajero?", "Modificar")) {
	            pM.showMessage("Seleccione la nueva fecha de nacimiento");
	            return;
	        } else {
	        	newDateOfBirth = currentPassenger.getDateofbirth();
	            pF.getTDateOfBirth().setDate(dateOfBirth);
	        }
	    }

	    if (newEmail.isEmpty()) {
	        if (pM.showConfirmationDialog("Desea modificar el email del pasajero?", "Modificar")) {
	            newEmail = pM.getData("Ingrese el email del pasajero:");
	            if (newEmail.equals("null")) {
	                newEmail = currentPassenger.getEmail();
	                pF.getTEmail().setText(newEmail);
	            }
	        } else {
	            newEmail = currentPassenger.getEmail();
	            pF.getTEmail().setText(newEmail);
	        }
	    }

	    if (newPhoneNumber.isEmpty()) {
	        if (pM.showConfirmationDialog("Desea modificar el número de teléfono del pasajero?", "Modificar")) {
	            newPhoneNumber = pM.getData("Ingrese el número de teléfono del pasajero:");
	            if (newPhoneNumber.equals("null")) {
	                newPhoneNumber = currentPassenger.getPhoneNumber();
	                pF.getTPhoneNumber().setText(newPhoneNumber);
	            }
	        } else {
	            newPhoneNumber = currentPassenger.getPhoneNumber();
	            pF.getTPhoneNumber().setText(newPhoneNumber);
	        }
	    }

	    String[] newData = {newPassport, newName, newLastname, newDateOfBirth, newEmail, newPhoneNumber};
	    crud.updateObject(fileName, objectName, "Passport", passport, currentPassenger.getDataName(), newData);
	    pM.showMessage("Pasajero modificado");
	    setTableData();
	    pF.clean();
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
			pM.showMessage("Pasajero eliminado");
			setTableData();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpPassenger(userTypeString);
	}
}
