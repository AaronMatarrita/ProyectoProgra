package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLFlights;
import data.XMLFiles;
import domain.Flights;
import presentation.FlightsFrame;

public class ControllerAFlights implements ActionListener{

	private FlightsFrame fF;
	private CRUD crud;
	
	private Flights Fl;
	private LogicXML lXML;
	
	private XMLFiles xmlF;
	private LogicXMLFlights logicXMLFlights;

	private String fileName = "Flights.xml";
	private String objectName = "flights";

	public ControllerAFlights() {
		fF = new FlightsFrame();
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		logicXMLFlights = new LogicXMLFlights();
		xmlF.createXML(fileName, objectName);
		setTableData();
		initializerAction();
	}
	
	private void setTableData() {
        List<Flights> flights = logicXMLFlights.readXMLFile(fileName);
        fF.setJTableData(flights);
    }

	public void initializerAction() {
		//bF.addWindowListener(this);
		fF.getBAddFlights().addActionListener(this);
		fF.getBUpdate().addActionListener(this);
		fF.getBClear().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (fF.getBAddFlights() == e.getSource()) {
			String flightNumber = fF.getTFlightNumber();
			String exitCity = fF.getTExitCity().getText();
			String exitDate = fF.getTExitDate().getText();
			String exitTime = fF.getTExitTime().getText();
			String enterCity = fF.getTExitCity().getText();
			String enterDate = fF.getTExitDate().getText();
			String enterTime = fF.getTExitTime().getText();
			String priceEJE = fF.getTPriceEJE().getText();
			String priceTUR = fF.getTPriceTUR().getText();
			String priceECO = fF.getTPriceECO().getText();
			String airplane = fF.getTAirplane().getText();
			JOptionPane.showMessageDialog(null, "El numero de vuelo es :"+flightNumber);

			if (exitCity.isEmpty() || exitDate.isEmpty() || exitTime.isEmpty() ||
				enterCity.isEmpty()|| enterDate.isEmpty()|| enterTime.isEmpty()||
				priceEJE.isEmpty() || priceECO.isEmpty() || airplane.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
				return;
			} else if (lXML.isAlreadyInFile(fileName, objectName, "FlightNumber" , flightNumber)) {
				JOptionPane.showMessageDialog(null, "El vuelo ya existe");
				return;
			} 

			//No se pueden repetir vuelos con los mismos aviones en un rango de 20 horas.
		
			fF.clean();
			
			Fl = new Flights((Integer.parseInt(flightNumber)) ,
					exitCity, exitDate,exitTime, enterCity, enterDate,
					enterTime, airplane,Double.parseDouble(priceEJE),
					Double.parseDouble(priceTUR), Double.parseDouble(priceECO));
			
			crud.addObject(fileName, objectName, Fl.getDataName(), Fl.getData());

			JOptionPane.showMessageDialog(null, "Marca agregada");
			setTableData();
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if(fF.getBUpdate() == e.getSource()) {
			//En proceso..
			setTableData();
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if(fF.getBClear() == e.getSource()){
			
			String flightNumber = fF.getTFlightNumber();
			
			if (flightNumber.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese el codigo de vuelo");
				return;
			}else if(!lXML.isAlreadyInFile(fileName, objectName, "FlightNumber" ,flightNumber)) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
			else {
				xmlF.createXML(fileName, objectName);
				crud.deleteObject(fileName, objectName, "FlightNumber", flightNumber);
			}
			JOptionPane.showMessageDialog(null, "Vuelo eliminado");
			setTableData();
		}

	}
}