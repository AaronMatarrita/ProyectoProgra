package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private LogicExecuteHTML logHTML;
	private String userType;

	private PopUpMessages pM;

	private ArrayList<Airplane> airplanes = new ArrayList<Airplane>();

	public ControllerAAirplane(String userType) {
		this.userType = userType;
		logHTML = new LogicExecuteHTML();
		aF = new AirplaneFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		lXMLM = new LogicXMLModel();
		lXMLA = new LogicXMLAirline();
		logicXMLAirplane = new LogicXMLAirplane();
		xmlF.createXML(fileName, objectName);

		pM = new PopUpMessages();
		aF.fillModelComboBox(lXMLM.getAirplaneModels("Models.xml"));
		aF.fillAirlineComboBox(lXMLA.getAirlineList("Airlines.xml"));
		initializerAction();
	}

	private void setTableData() {
		String id = aF.getTId().getText().trim();//.trim() elimina espacios inicio o final
		if (id.isEmpty()) {
			airplanes = logicXMLAirplane.readXMLFile(fileName);
		} else {
			airplanes.clear();
			Airplane searchedAirplane = logicXMLAirplane.getAirplaneFromXML(fileName, id);
			if (searchedAirplane != null) {
				airplanes.add(searchedAirplane);
			}
		}
		aF.setJTableData(airplanes);
	}

	public void initializerAction() {
		aF.getBAddAirplane().addActionListener(this);
		aF.getBUpdate().addActionListener(this);
		aF.getBClear().addActionListener(this);
		aF.getBSearch().addActionListener(this);
		aF.getBHelp().addActionListener(this);
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
		}else if(aF.getBSearch() == e.getSource()) {
			searchAirplane();
		}else if(aF.getBHelp() == e.getSource()) {
			help();
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
		setTableData();
		pM.showMessage( "Avion agregado");
	}

	private void searchAirplane() {
		setTableData();
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void updateAirplane() {
		String id = aF.getTId().getText();
		Airplane currentAirplane = logicXMLAirplane.getAirplaneFromXML(fileName, id);

		if (id.isEmpty()) {
			pM.showMessage( "Por favor, ingrese el nombre del avión a modificar");
			return;
		} else if (currentAirplane == null) {
			pM.showMessage("El avión no existe");
			return;
		}

		String newAirplane = id;
		String newYear = aF.getTYear().getText();
		String newAirline = (String) aF.getCBAirline().getSelectedItem();
		String newModel = (String) aF.getCBModel().getSelectedItem();

		if (newYear.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar el año del avión?", "Modificar")) {
				newYear = pM.getData("Ingrese el nuevo año del avión:");
				if(newYear == null) { pM.showMessage("Por favor, ingrese el dato solicitado"); return; }
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
		setTableData();
		pM.showMessage("Avión modificado");
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
			setTableData();
			pM.showMessage("Avion eliminado");
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpAirplane(userTypeString);
	}
}
