package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLBrand;
import data.XMLFiles;
import domain.Brand;
import presentation.BrandFrame;
import presentation.PopUpMessages;

public class ControllerABrand implements ActionListener{

	private BrandFrame bF;
	private CRUD crud;
	private Brand Br;
	private LogicXML lXML;
	private LogicXMLBrand lXMLB;
	private XMLFiles xmlF;

	private String fileName = "Brands.xml";
	private String objectName = "brands";
	private LogicExecuteHTML logHTML;
	private String userType;

	private ArrayList<Brand> brands = new ArrayList<Brand>();

	private PopUpMessages pM;

	public ControllerABrand(String userType) {
		this.userType = userType;
		bF = new BrandFrame(userType);
		crud = new CRUD();
		lXML = new LogicXML();
		xmlF = new XMLFiles();
		logHTML = new LogicExecuteHTML();
		lXMLB = new LogicXMLBrand();
		xmlF.createXML(fileName, objectName);
		pM = new PopUpMessages();
		initializerAction();
	}

	private void setTableData() {
		String brand = bF.getTBrand().getText().trim();
		if(brand.isEmpty()) {
			brands = lXMLB.readXMLFile(fileName);
		}else {
			brands.clear();
			Brand searchedBrand = lXMLB.getBrandFromXML(fileName, brand);
			if(searchedBrand != null) {
				brands.add(searchedBrand);
			}
		}
		bF.setJTableData(brands);
	}

	public void initializerAction() {
		//bF.addWindowListener(this);
		bF.getBAddBrand().addActionListener(this);
		bF.getBUpdate().addActionListener(this);
		bF.getBClear().addActionListener(this);
		bF.getBSearch().addActionListener(this);
		bF.getBHelp().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (bF.getBAddBrand() == e.getSource())
		{
			addBrand();
		}else if(bF.getBUpdate() == e.getSource())
		{
			updateBrand();
		}else if(bF.getBClear() == e.getSource())
		{
			deleteBrand();
		}else if(bF.getBSearch() == e.getSource()) {
			searchBrand();
		}else if(bF.getBHelp() == e.getSource()) {
			help();
		}
	}

	private void addBrand() {
		String brand = bF.getTBrand().getText();

		if (brand.isEmpty() || bF.getTBrand().getText().isEmpty()) {
			pM.showMessage("Por favor, ingrese el nombre de la marca");
			return;
		} else if (lXML.isAlreadyInFile(fileName, objectName, "brand" , brand)) {
			pM.showMessage("La Marca ya existe");
			return;
		} 
		//No pueden existir marcas con el mismo nombre y no pueden eliminarse marcas que hayan 
		//sido asociados con algún modelo de avión

		bF.clean();
		Br = new Brand(brand);
		crud.addObject(fileName, objectName, Br.getDataName(), Br.getData());
		setTableData();
		pM.showMessage("Marca agregada");
	}

	private void searchBrand() {
		setTableData();
	}

	private void updateBrand() {
		String brandName = bF.getTBrand().getText();
		Brand currentBrand = lXMLB.getBrandFromXML(fileName, brandName);
		String newBrand = brandName;
		if (brandName.isEmpty() || bF.getTBrand().getText().isEmpty()) {
			pM.showMessage("Por favor, ingrese el nombre de la marca");
			return;
		} else if (!lXML.isAlreadyInFile(fileName, objectName, "brand" , brandName)) {
			pM.showMessage( "La Marca no existe");
			return;
		} 

		if(pM.showConfirmationDialog("Desea modificar el nombre de la marca?", "Modificar")) {
			newBrand = pM.getData("Ingrese el nuevo nombre de la marca:");
			if(newBrand == null) { pM.showMessage("Por favor, ingrese el dato solicitado"); return; }
		}else {
			newBrand = currentBrand.getBrand();
		}

		String[] newData = {newBrand};
		crud.updateObject(fileName, objectName, "brand", brandName, currentBrand.getDataName(), newData);

		bF.clean();
		setTableData();
		pM.showMessage("Marca agregada");
	}

	private void deleteBrand() {
		String brand = bF.getTBrand().getText();
		if (brand.isEmpty() || bF.getTBrand().getText().isEmpty()) {
			pM.showMessage( "Por favor, ingrese el nombre de la marca");
			return;
		}else if(lXML.isAlreadyInFile("Models.xml", "models", "brand" , brand)) {
			pM.showMessage( "La Marca no se puede eliminar debido a que esta asociada a un modelo de avión");
			return;
		}else if(!lXML.isAlreadyInFile(fileName, objectName, "brand" , brand)) {
			pM.showMessage("No se puede eliminar debido a que no existe");return;}

		if(pM.showConfirmationDialog("¿Está seguro de eliminar la marca?", "Eliminar")) {
			xmlF.createXML(fileName, objectName);
			crud.deleteObject(fileName, objectName, "brand", brand);
			bF.clean();
			setTableData();
			pM.showMessage("Marca eliminada");
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------
	private void help() {
		String userTypeString = userType.equals("1") ? "Administrador" : "Colaborador";
		logHTML.helpBrand(userTypeString);
	}
}