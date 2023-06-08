package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import domain.AirplaneModel;
import presentation.ModelFrame;

public class ControllerAModel implements ActionListener{

	private ModelFrame mF;
	private AirplaneModel Am;
	private CRUD crud;
	private LogicXML lXML;
	private XMLFiles xmlF;
	
	private String fileName = "Models.xml";
	private String objectName = "models";
	
	public ControllerAModel() {
		mF = new ModelFrame();
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		xmlF.createXML(fileName, objectName);
		initializerAction();
	}
	
	public void initializerAction() {
		mF = new ModelFrame();
		//mF.addWindowListener(this);
		mF.getBAddModel().addActionListener(this);
		mF.getBUpdate().addActionListener(this);
		mF.getBClear().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (mF.getBAddModel() == e.getSource()) {
			String model = mF.getTName().getText();

			if (model.isEmpty() ||mF.getTCEjecutive().getText().isEmpty()||mF.getTCTurist().getText().isEmpty()||mF.getTCEco().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
				return;
			} else if (lXML.isAlreadyInFile(fileName, objectName, "model", model)) {
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
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		} else if (mF.getBUpdate() == e.getSource()) {
			//En proceso...
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if (mF.getBClear() == e.getSource()) {
			String model = mF.getTName().getText();
			if (model.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete el nombre del modelo a eliminar");return;
			}else if(!lXML.isAlreadyInFile("Models.xml", "models" ,"model", model)) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
			else {
				crud.deleteObject(fileName, objectName, "model", model);
			}
			JOptionPane.showMessageDialog(null, "Modelo eliminado");
		}
	}
}
