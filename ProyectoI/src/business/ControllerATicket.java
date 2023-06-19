package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLTicket;
import data.XMLFiles;
import domain.Airplane;
import domain.Tickets;
import presentation.PopUpMessages;
import presentation.TicketFrame;

public class ControllerATicket implements ActionListener{

	private String fileName = "Tickets.xml";
	private String objectName = "Tickets";

	private XMLFiles xmlF;
	private TicketFrame tF;
	private LogicXML lXML;
	private LogicXMLTicket lXMLT;
	private Tickets ticket;
	private CRUD crud;
	
	private PopUpMessages pM;

	public ControllerATicket(String userType) {
		tF = new TicketFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		lXMLT = new LogicXMLTicket();
		xmlF.createXML(fileName, objectName);
		
		pM = new PopUpMessages();
		setTableData();
		//aF.fillModelComboBox(lXMLM.getAirplaneModels("Models.xml"));
		//aF.fillAirlineComboBox(lXMLA.getAirlineList("Airlines.xml"));
		initializer();
	}

	private void setTableData() {
		List<Tickets> users = lXMLT.readXMLFile(fileName);
		tF.setJTableData(users);
	}

	public void initializer() {
		tF.getBAddTickets().addActionListener(this);
		tF.getBUpdate().addActionListener(this);
		tF.getBClear().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(tF.getBAddTickets() == e.getSource()) 
		{
			addTickets();
			
		}else if(tF.getBUpdate() == e.getSource()) 
		{
			updateTickets(); 
			setTableData();
		}else if(tF.getBClear() == e.getSource()) 
		{
			deleteTickets();
			
		}

	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addTickets() {
		String passport = tF.getTPassport().getText();
		String ticketNumber = tF.getTTicketNumber().getText();
		String flightNumber = tF.getTFlightNumber().getText();
		String ticketType = (String) tF.getCBTicketType().getSelectedItem();

		if (passport.isEmpty() || ticketNumber.isEmpty() || flightNumber.isEmpty()||ticketType.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "id", passport)) {
			JOptionPane.showMessageDialog(null, "El Tiquete ya existe");
			return;
		}	else if (ticketType.equals("Indefinido")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el estado del usuario");
            return;
        }
		tF.clean();
		ticket  = new Tickets(Integer.parseInt(ticketNumber), passport, Integer.parseInt(flightNumber),ticketType);
		crud.addObject(fileName, objectName, ticket.getDataName(), ticket.getData());
		JOptionPane.showMessageDialog(null, "Tiquete agregado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updateTickets() {
		String numberTicket = tF.getTTicketNumber().getText();
		Tickets currentTicket = lXMLT.getTicketFromXML(fileName, objectName, "TicketNumber", numberTicket);

		if (numberTicket.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el numero de ticket a modificar");
			return;
		} else if (currentTicket == null) {
			pM.showMessage("El Ticket no existe");
			return;
		}

		String newTicket = numberTicket;
		if (pM.showConfirmationDialog("Desea modificar el numero de ticket?", "Modificar")) {
			newTicket = pM.getData("Ingrese el nuevo numero de ticket:");
		}

		String newPassport = tF.getTPassport().getText();
		String newFlightNumber = tF.getTFlightNumber().getText();
		String newTicketType = (String) tF.getCBTicketType().getSelectedItem();

		if (newPassport.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el pasaporte?", "Modificar")) {
				newPassport = pM.getData("Ingrese el nuevo pasaporte:");
			}
		}
		if (newFlightNumber.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el numero de vuelo?", "Modificar")) {
				newFlightNumber = pM.getData("Ingrese el nuevo numero de vuelo:");
			}
		}

		if (newTicketType.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el tipo de ticket?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el tipo de ticket");
				newTicketType = (String) tF.getCBTicketType().getSelectedItem();
				if (newTicketType.equals("Indefinido")) {
					return;
				}
			} else {
				newTicketType = currentTicket.getTickettype();
			}
		}

		String[] newData = { newTicket, newPassport, newFlightNumber, newTicketType };
		crud.updateObject(fileName, objectName, "TicketNumber", numberTicket, currentTicket.getDataName(), newData);
		tF.clean();
		pM.showMessage("Ticket modificado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void deleteTickets() {
		String ticket = tF.getTTicketNumber().getText();

		if (ticket.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese el número de tiquete a eliminar");
			return;
		} else if (!lXML.isAlreadyInFile(fileName , objectName, "TicketNumber", ticket)) {
			JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");
			return;
		}
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el tiquete?", "Eliminar", dialogButton);

		if (dialogResult == JOptionPane.YES_OPTION) {
			tF.clean();
			crud.deleteObject(fileName, objectName, "TicketNumber", ticket);
			JOptionPane.showMessageDialog(null, "Tiquete eliminado");
		}
		setTableData();
	}
	
}
