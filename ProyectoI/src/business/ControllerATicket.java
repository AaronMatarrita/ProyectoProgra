package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLTicket;
import data.XMLFiles;
import domain.Tickets;
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

	public ControllerATicket(String userType) {
		tF = new TicketFrame(userType);
		lXML = new LogicXML();
		lXMLT = new LogicXMLTicket();
		crud = new CRUD();
		xmlF = new XMLFiles();
		xmlF.createXML(fileName, objectName);
		initializer();
		setTableData();
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

		if(tF.getBAddTickets() == e.getSource()) {
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
		}else if(tF.getBUpdate() == e.getSource()) {
			//En proceso...
			setTableData();
		}else if(tF.getBClear() == e.getSource()) {
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

}
