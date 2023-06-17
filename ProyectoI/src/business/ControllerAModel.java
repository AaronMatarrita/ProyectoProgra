package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLBrand;
import data.LogicXMLModel;
import data.XMLFiles;
import domain.AirplaneModel;
import presentation.ModelFrame;

public class ControllerAModel implements ActionListener{

	private ModelFrame mF;
	private AirplaneModel Am;
	private CRUD crud;
	private LogicXML lXML;
	private XMLFiles xmlF;
	private LogicXMLModel lXMLM;
	private LogicXMLBrand lXMLB;
	private String fileName = "Models.xml";
	private String objectName = "models";
	
	public ControllerAModel(String userType)
	{
		mF = new ModelFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		lXMLM = new LogicXMLModel();
		lXMLB = new LogicXMLBrand();
		lXMLB.readBrandsFromXML("Brands.xml");
		xmlF.createXML(fileName, objectName);
		initializerAction();
		setTableData();
		mF.fillBrandComboBox(lXMLB.readBrandsFromXML("Brands.xml"));
	}

	private void setTableData() {
		ArrayList<AirplaneModel> models = lXMLM.readXMLFile(fileName);
		mF.setJTableData(models);
	}

	public void initializerAction() {
		mF.getBAddModel().addActionListener(this);
		mF.getBUpdate().addActionListener(this);
		mF.getBClear().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------

		if (mF.getBAddModel() == e.getSource()) {
			String model = mF.getTModel().getText();

			if (model.isEmpty() ||mF.getTCEjecutive().getText().isEmpty()||mF.getTCTurist().getText().isEmpty()||mF.getTCEco().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
				return;
			} else if (lXML.isAlreadyInFile(fileName, objectName, "modelName", model)) {
				JOptionPane.showMessageDialog(null, "El modelo ya existe");
				return;
			}

			int SeatsEje = Integer.parseInt(mF.getTCEjecutive().getText());
			int SeatsTur = Integer.parseInt(mF.getTCTurist().getText());
			int SeatsEco = Integer.parseInt(mF.getTCEco().getText());
			String StringBrandType = (String) mF.getCBrands().getSelectedItem();


			if (StringBrandType.equals("Indefinido") ) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione una marca");
				return;
			}

			mF.clean();
			Am = new AirplaneModel(model, StringBrandType, SeatsEje ,SeatsTur, SeatsEco );
			crud.addObject(fileName, objectName, Am.getDataName(), Am.getData());

			JOptionPane.showMessageDialog(null, "Modelo agregado");

			setTableData();
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		} else if (mF.getBUpdate() == e.getSource()) {
			//En proceso...
			setTableData();
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if (mF.getBClear() == e.getSource()) {
			String model = mF.getTModel().getText();
			if (model.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete el nombre del modelo a eliminar");
				return;
			}else if(lXML.isAlreadyInFile("Airplanes.xml", "airplanes" ,"model", model)) 
			{
				JOptionPane.showMessageDialog(null, "El modelo no se puede eliminar debido a que esta asociado con un avión");
				return;
			
				
			}else if(!lXML.isAlreadyInFile("Models.xml", "models" ,"modelName", model)) 
			{
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");
				return;
			
			}
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el modelo?", "Eliminar", dialogButton);

			if (dialogResult == JOptionPane.YES_OPTION) {
				crud.deleteObject(fileName, objectName, "modelName", model);
				JOptionPane.showMessageDialog(null, "Modelo eliminado");
				setTableData();
			}
		}
	}
}
