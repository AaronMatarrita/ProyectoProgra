package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXMLUser;
import data.XMLFiles;
import domain.Brand;
import presentation.BrandFrame;

public class ControllerABrand implements ActionListener{

	private BrandFrame bF;
	private CRUD crud;
	private Brand Br;
	private LogicXMLUser lXMLU;
	private XMLFiles xmlF;

	private String fileName = "Brands.xml";
	private String objectName = "brands";

	public ControllerABrand() {
		bF = new BrandFrame();
		crud = new CRUD();
		lXMLU = new LogicXMLUser();
		xmlF = new XMLFiles();
		xmlF.createXML(fileName, objectName);
		initializerAction();
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
			} else if (lXMLU.isAlreadyInFile(fileName, objectName, "brand" , brand)) {
				JOptionPane.showMessageDialog(null, "La Marca ya existe");
				return;
			} 

			//No pueden existir marcas con el mismo nombre y no pueden eliminarse marcas que hayan 
			//sido asociados con algún modelo de avión

			bF.clean();
			Br = new Brand(brand);
			crud.addObject(fileName, objectName, Br.getDataName(), Br.getData());

			JOptionPane.showMessageDialog(null, "Marca agregada");
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if(bF.getBUpdate() == e.getSource()) {
			//En proceso..
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if(bF.getBClear() == e.getSource()){
			String brand = bF.getTBrand().getText();
			if (brand.isEmpty() || bF.getTBrand().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la marca");
				return;
			}else if(!lXMLU.isAlreadyInFile(fileName, objectName, "brand" , brand)) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
			else {
				xmlF.createXML(fileName, objectName);
				crud.deleteObject(fileName, objectName, "brand", brand);
			}
			JOptionPane.showMessageDialog(null, "Marca eliminada");
		}

	}
}
