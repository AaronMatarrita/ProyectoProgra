package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLAirline;
import data.LogicXMLAirplane;
import data.LogicXMLModel;
import data.XMLFiles;
import domain.Airplane;
import presentation.AirplaneFrame;
import presentation.PopUpMessages;

public class ControllerAAirplane implements ActionListener {
	private Airplane Ai;
	private AirplaneFrame aF;

	private CRUD crud;
	private LogicXML lXML;

	private XMLFiles xmlF;
	private LogicXMLAirplane logicXMLAirplane;
	private LogicXMLModel lXMLM;
	private LogicXMLAirline lXMLA;

	private String fileName = "Airplanes.xml";
	private String objectName = "airplanes";

	private PopUpMessages pM;

	public ControllerAAirplane(String userType) {
		aF = new AirplaneFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		lXMLM = new LogicXMLModel();
		lXMLA = new LogicXMLAirline();
		logicXMLAirplane = new LogicXMLAirplane();
		xmlF.createXML(fileName, objectName);
		pM = new PopUpMessages();
		setTableData();
		aF.fillModelComboBox(lXMLM.getAirplaneModels("Models.xml"));
		aF.fillAirlineComboBox(lXMLA.getAirlineList("Airlines.xml"));
		initializerAction();
	}

	private void setTableData() {
		List<Airplane> airplanes = logicXMLAirplane.readXMLFile(fileName);
		aF.setJTableData(airplanes);
	}

	public void initializerAction() {
		aF.getBAddAirplane().addActionListener(this);
		aF.getBUpdate().addActionListener(this);
		aF.getBClear().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aF.getBAddAirplane() == e.getSource())
		{
			addAirplane();
		} else if (aF.getBUpdate() == e.getSource())
		{
			updateAirplane(); 
		} else if (aF.getBClear() == e.getSource()) 
		{
			deleteAirplane();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void addAirplane() {
		String id = aF.getTId().getText();

		if (id.isEmpty() || aF.getTYear().getText().isEmpty()) {
			pM.showMessage("Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "id", id)) {
			pM.showMessage("El avion ya existe");
			return;
		} //no pueden eliminarse aviones que estén registrados en vuelos.


		String year = aF.getTYear().getText();
		String airline = (String) aF.getCBAirline().getSelectedItem();
		String model = (String) aF.getCBModel().getSelectedItem();

		if (airline.equals("Indefinido")) {
			pM.showMessage("Por favor, seleccione un tipo de aerolinea");
			return;
		} else if (model.equals("Indefinido")) {
			pM.showMessage("Por favor, seleccione el modelo");
			return;
		}

		aF.clean();
		Ai = new Airplane(id, year, airline, model);
		crud.addObject(fileName, objectName, Ai.getDataName(), Ai.getData());
		pM.showMessage( "Avion agregado");

		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updateAirplane() {
		String id = aF.getTId().getText();
		Airplane currentAirplane = logicXMLAirplane.getAirplaneFromXML(fileName, objectName, "id", id);

		if (id.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el nombre del avión a modificar");
			return;
		} else if (currentAirplane == null) {
			pM.showMessage("El avión no existe");
			return;
		}

		String newAirplane = id;
		if (pM.showConfirmationDialog("Desea modificar el nombre (id) del avión?", "Modificar")) {
			newAirplane = pM.getData("Ingrese el nuevo nombre (id) del avión:");
		}

		String newYear = aF.getTYear().getText();
		String newAirline = (String) aF.getCBAirline().getSelectedItem();
		String newModel = (String) aF.getCBModel().getSelectedItem();

		if (newYear.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el año del avión?", "Modificar")) {
				newYear = pM.getData("Ingrese el nuevo año del avión:");
			}
		}
		if (newAirline.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar la aerolínea del avión?", "Modificar")) {
				pM.showMessage("Por favor, seleccione la aerolínea del avión");
				newAirline = (String) aF.getCBAirline().getSelectedItem();
				if (newAirline.equals("Indefinido")) {
					return;
				}
			} else {
				newAirline = currentAirplane.getAirline();
			}
		}

		if (newModel.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar el modelo del avión?", "Modificar")) {
				pM.showMessage("Por favor, seleccione el modelo del avión");
				newModel = (String) aF.getCBModel().getSelectedItem();
				if (newModel.equals("Indefinido")) {
					return;
				}
			} else {
				newModel = currentAirplane.getAirplaneModel();
			}
		}

		String[] newData = { newAirplane, newYear, newAirline, newModel };
		crud.updateObject(fileName, objectName, "id", id, currentAirplane.getDataName(), newData);
		aF.clean();
		pM.showMessage("Avión modificado");
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void deleteAirplane() {
		String id = aF.getTId().getText();
		if (id.isEmpty()) {
			pM.showMessage("Por favor, complete la matricula a eliminar");
			return;
		} else if (lXML.isAlreadyInFile("Flights.xml" , "flights", "Airplane", id)) {
			pM.showMessage("El avión no se puede eliminar debido a que esta asociado con un vuelo");
			return;
		} else if (!lXML.isAlreadyInFile(fileName , objectName, "id", id)) {
			pM.showMessage("No se puede eliminar debido a que no existe");
			return;
		}//no pueden eliminarse aviones que 
		//estén registrados en vuelos.

		if(pM.showConfirmationDialog("¿Está seguro de eliminar el avion?", "Eliminar")){	
			aF.clean();
			crud.deleteObject(fileName, objectName, "id", id);
			pM.showMessage("Avion eliminado");
			setTableData();
		}
	}
}
