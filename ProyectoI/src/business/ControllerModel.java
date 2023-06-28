package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import domain.AirplaneModel;
import presentation.ModelFrame;
import presentation.PopUpMessages;

public class ControllerModel implements ActionListener{

	private String fileName = "Models.xml";
	private String objectName = "models";
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private ModelFrame mF;
	private PopUpMessages pM;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private AirplaneModel Am;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private CRUD crud;
	private LogicXML lXML;
	private XMLFiles xmlF;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private LogicModel logModel;
	private LogicBrand logBrand;
	private LogicExecuteHTML logHTML;
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	private String userType;
	private ArrayList<AirplaneModel> models = new ArrayList<AirplaneModel>();
	//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	
	public ControllerModel(String userType)
	{
		this.userType = userType;
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		mF = new ModelFrame(userType);
		pM = new PopUpMessages();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		logModel = new LogicModel();
		logBrand = new LogicBrand();
		logHTML = new LogicExecuteHTML();
		logBrand.readBrandsFromXML("Brands.xml");
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		xmlF.createXML(fileName, objectName);
		initializerAction();
		mF.fillBrandComboBox(logBrand.readBrandsFromXML("Brands.xml"));
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void setTableData() {
		String model = mF.getTModel().getText().trim();
		if(model.isEmpty()) {
			models = logModel.readXMLFile(fileName);
		}else {
			models.clear();
			AirplaneModel searchedModel = logModel.getAirplaneModelFromXML(fileName, model);
			if(searchedModel != null) {
				models.add(searchedModel);
			}
		}
		mF.setJTableData(models);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	public void initializerAction() {
		mF.getBAddModel().addActionListener(this);
		mF.getBUpdate().addActionListener(this);
		mF.getBClear().addActionListener(this);
		mF.getBSearch().addActionListener(this);
		mF.getBHelp().addActionListener(this);
		mF.getBReturn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (mF.getBAddModel() == e.getSource()){
			addModel();
		} else if (mF.getBUpdate() == e.getSource()){
			updateModel();
		}else if (mF.getBClear() == e.getSource()){
			deleteModel();
		}else if(mF.getBSearch() == e.getSource()) {
			searchModel();
		}else if(mF.getBHelp() == e.getSource()) {
			help();
		}else if(mF.getBReturn() == e.getSource()){
			mF.dispose();
			new ControllerMain(userType);
		}
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void addModel() {
		String model = mF.getTModel().getText();

		if (model.isEmpty() ||mF.getTCEjecutive().getText().isEmpty()||mF.getTCTurist().getText().isEmpty()||mF.getTCEco().getText().isEmpty()) {
			pM.showMessage("Por favor, complete todos los campos");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "modelName", model)) {
			pM.showMessage("El modelo ya existe");
			return;
		}

		int SeatsEje = Integer.parseInt(mF.getTCEjecutive().getText());
		int SeatsTur = Integer.parseInt(mF.getTCTurist().getText());
		int SeatsEco = Integer.parseInt(mF.getTCEco().getText());
		String StringBrandType = (String) mF.getCBrands().getSelectedItem();


		if (StringBrandType.equals("Indefinido") ) {
			pM.showMessage("Por favor, seleccione una marca");
			return;
		}

		mF.clean();
		Am = new AirplaneModel(model, StringBrandType, SeatsEje ,SeatsTur, SeatsEco );
		crud.addObject(fileName, objectName, Am.getDataName(), Am.getData());
		setTableData();
		pM.showMessage("Modelo agregado");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void searchModel() {
		setTableData();
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void updateModel() {
		String model = mF.getTModel().getText();
		AirplaneModel currentModel = logModel.getAirplaneModelFromXML(fileName, model);

		String newModel = model;
		if (model.isEmpty()) {
			pM.showMessage("Por favor, ingrese el modelo");
			return;
		} else if (!lXML.isAlreadyInFile(fileName, objectName, "modelName", model)) {
			pM.showMessage( "El modelo no existe");
			return;
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		String newSeatsEje = mF.getTCEjecutive().getText();
		String newSeatsTur = mF.getTCTurist().getText();
		String newSeatsEco = mF.getTCEco().getText();
		String newBrandType = (String) mF.getCBrands().getSelectedItem();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if (newBrandType.equals("Indefinido")) {
			if (pM.showConfirmationDialog("¿Desea modificar la marca del modelo?", "Modificar")) {
				pM.showMessage("Por favor, seleccione la nueva marca del modelo");
				newBrandType = (String) mF.getCBrands().getSelectedItem();
				if (newBrandType.equals("Indefinido")) {
					return; 
				}
			} else {
				newBrandType = String.valueOf(currentModel.getBrand());
			} 
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if(newSeatsEje.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la cantidad de asientos de clase ejecutiva?", "Modificar")) {
				newSeatsEje = pM.getData("Ingrese la nueva cantidad de asientos:");
				if(newSeatsEje.equals("null")) { newSeatsEje = String.valueOf(currentModel.getBusinessClassSeats()); }
				mF.getTCEjecutive().setText(newSeatsEje);
			}else {
				newSeatsEje = String.valueOf(currentModel.getBusinessClassSeats());
			}
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if(newSeatsTur.isEmpty()) { 
			if(pM.showConfirmationDialog("Desea modificar la cantidad de asientos de clase turista?", "Modificar")) {
				newSeatsTur = pM.getData("Ingrese la nueva cantidad de asientos:");
				if(newSeatsTur.equals("null")) { newSeatsTur = String.valueOf(currentModel.getTouristClassSeats()); }
				mF.getTCTurist().setText(newSeatsTur);
			}else {
				newSeatsTur = String.valueOf(currentModel.getTouristClassSeats());
			}
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if(newSeatsEco.isEmpty()) {
			if(pM.showConfirmationDialog("Desea modificar la cantidad de asientos de clase económicos?", "Modificar")) {
				newSeatsEco = pM.getData("Ingrese la nueva cantidad de asientos:");
				if(newSeatsEco.equals("null")) { newSeatsEco = String.valueOf(currentModel.getEconomyClassSeats());}
				mF.getTCEco().setText(newSeatsEco);
			}else {
				newSeatsEco = String.valueOf(currentModel.getEconomyClassSeats());
			}
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		String[] newData = {newModel, newBrandType, newSeatsEje, newSeatsTur, newSeatsEco};
		crud.updateObject(fileName, objectName, "modelName", model, currentModel.getDataName(), newData);
		setTableData();
		pM.showMessage("Modelo modificado");
		mF.clean();
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void deleteModel() {
		String model = mF.getTModel().getText();
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if (model.isEmpty()) {
			pM.showMessage("Por favor, complete el nombre del modelo a eliminar");
			return;
		}else if(lXML.isAlreadyInFile("Airplanes.xml", "airplanes" ,"model", model)) {
			pM.showMessage("El modelo no se puede eliminar debido a que esta asociado con un avión");
			return;
		}else if(!lXML.isAlreadyInFile("Models.xml", "models" ,"modelName", model)) {
			pM.showMessage( "No se puede eliminar debido a que no existe");
			return;
		}
		//+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+
		if (pM.showConfirmationDialog("¿Está seguro de eliminar el modelo?", "Eliminar")) {
			crud.deleteObject(fileName, objectName, "modelName", model);
			setTableData();
			pM.showMessage("Modelo eliminado");
			mF.clean();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpModel(userTypeString);
	}
}
