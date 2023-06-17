package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.LogicXMLAirline;
import data.LogicXMLAirplane;
import data.LogicXMLModel;
import data.XMLFiles;
import domain.Airplane;
import presentation.AirplaneFrame;

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

    public ControllerAAirplane(String userType) {
        aF = new AirplaneFrame(userType);
        crud = new CRUD();
        lXML = new LogicXML();
        xmlF = new XMLFiles();
        lXMLM = new LogicXMLModel();
        lXMLA = new LogicXMLAirline();
        logicXMLAirplane = new LogicXMLAirplane();
        xmlF.createXML(fileName, objectName);
        setTableData();
        initializerAction();
        aF.fillModelComboBox(lXMLM.getAirplaneModels("Models.xml"));
        aF.fillAirlineComboBox(lXMLA.getAirlineList("Airlines.xml"));
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
        if (aF.getBAddAirplane() == e.getSource()) {
            String id = aF.getTId().getText();

            if (id.isEmpty() || aF.getTYear().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                return;
            } else if (lXML.isAlreadyInFile(fileName, objectName, "id", id)) {
                JOptionPane.showMessageDialog(null, "El avion ya existe");
                return;
            } //no pueden eliminarse aviones que estén registrados en vuelos.
            

            String year = aF.getTYear().getText();
            String airline = (String) aF.getCBAirline().getSelectedItem();
            String model = (String) aF.getCBModel().getSelectedItem();

            if (airline.equals("Indefinido")) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de aerolinea");
                return;
            } else if (model.equals("Indefinido")) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el modelo");
                return;
            }

            aF.clean();
            Ai = new Airplane(id, year, airline, model);
            crud.addObject(fileName, objectName, Ai.getDataName(), Ai.getData());

            JOptionPane.showMessageDialog(null, "Avion agregado");
            
            setTableData();
        } else if (aF.getBUpdate() == e.getSource()) {
            // En proceso...
        	 setTableData();
        } else if (aF.getBClear() == e.getSource()) {
            String id = aF.getTId().getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete la matricula a eliminar");
                return;
            } else if (lXML.isAlreadyInFile("Flights.xml" , "flights", "Airplane", id)) {
                JOptionPane.showMessageDialog(null, "El avión no se puede eliminar debido a que esta asociado con un vuelo");
                return;
            } else if (!lXML.isAlreadyInFile(fileName , objectName, "id", id)) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");
                return;
            }//no pueden eliminarse aviones que 
            //estén registrados en vuelos.

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el avion?", "Eliminar", dialogButton);

            if (dialogResult == JOptionPane.YES_OPTION) {
                aF.clean();
                crud.deleteObject(fileName, objectName, "id", id);
                JOptionPane.showMessageDialog(null, "Avion eliminado");

                setTableData();
            }
        }
    }
}