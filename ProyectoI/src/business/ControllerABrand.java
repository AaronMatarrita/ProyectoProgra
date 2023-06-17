package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLBrand;
import data.XMLFiles;
import domain.Brand;
import presentation.BrandFrame;

public class ControllerABrand implements ActionListener{

	private BrandFrame bF;
	private CRUD crud;
	private Brand Br;
	private LogicXML lXML;
	private LogicXMLBrand lXMLB;
	private XMLFiles xmlF;


	private String fileName = "Brands.xml";
	private String objectName = "brands";

	public ControllerABrand(String userType) {
		bF = new BrandFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		lXMLB = new LogicXMLBrand();
		xmlF.createXML(fileName, objectName);
		setTableData();
		initializerAction();
	}

	private void setTableData() {
		List<Brand> brands = lXMLB.readXMLFile(fileName);
		bF.setJTableData(brands);
	}

	public void initializerAction() {
		//bF.addWindowListener(this);
		bF.getBAddBrand().addActionListener(this);
		bF.getBUpdate().addActionListener(this);
		bF.getBClear().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (bF.getBAddBrand() == e.getSource()) {
			String brand = bF.getTBrand().getText();

			if (brand.isEmpty() || bF.getTBrand().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la marca");
				return;
			} else if (lXML.isAlreadyInFile(fileName, objectName, "brand" , brand)) {
				JOptionPane.showMessageDialog(null, "La Marca ya existe");
				return;
			} 
			//No pueden existir marcas con el mismo nombre y no pueden eliminarse marcas que hayan 
			//sido asociados con algún modelo de avión

			bF.clean();
			Br = new Brand(brand);
			crud.addObject(fileName, objectName, Br.getDataName(), Br.getData());

			JOptionPane.showMessageDialog(null, "Marca agregada");

			setTableData();
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if(bF.getBUpdate() == e.getSource()) {
			//En proceso..
			setTableData();
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if(bF.getBClear() == e.getSource()){
			String brand = bF.getTBrand().getText();
			if (brand.isEmpty() || bF.getTBrand().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la marca");
				return;
			}else if(lXML.isAlreadyInFile("Models.xml", "models", "brand" , brand)) {
				JOptionPane.showMessageDialog(null, "La Marca no se puede eliminar debido a que esta asociada a un modelo de avión");
				return;
			}else if(!lXML.isAlreadyInFile(fileName, objectName, "brand" , brand)) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la marca?", "Eliminar", dialogButton);

			if (dialogResult == JOptionPane.YES_OPTION) {
				xmlF.createXML(fileName, objectName);
				crud.deleteObject(fileName, objectName, "brand", brand);
				bF.clean();
				JOptionPane.showMessageDialog(null, "Marca eliminada");
				setTableData();
			}
		}
	}
}