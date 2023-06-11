package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import data.CRUD;
import data.LogicXML;
import data.XMLFiles;
import data.LogicXMLAirline;
import domain.Airline;
import presentation.AirlineFrame;

public class ControllerAAirline implements ActionListener {
    private Airline Ar;
    private AirlineFrame aF;
    
    private CRUD crud;
    private LogicXML lXML;
    
    private XMLFiles xmlF;
    private LogicXMLAirline logicXMLAirline;
    
    private String fileName = "Airlines.xml";
    private String objectName = "airlines";

    public ControllerAAirline() {
        aF = new AirlineFrame();
        crud = new CRUD();
        lXML = new LogicXML();
        xmlF = new XMLFiles();
        logicXMLAirline = new LogicXMLAirline();
        xmlF.createXML(fileName, objectName);
        setTableData();
        initializerAction();
    }
    
    private void setTableData() {
        List<Airline> airlines = logicXMLAirline.readXMLFile(fileName);
        aF.setJTableData(airlines);
    }
    
    public void initializerAction() {
        aF.getBAddAirline().addActionListener(this);
        aF.getBClear().addActionListener(this);
        aF.getBUpdate().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (aF.getBAddAirline() == e.getSource()) {
            String airline = aF.getTAirline().getText();

            if (airline.isEmpty() || aF.getTCountry().getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                return;
            } else if (lXML.isAlreadyInFile(fileName,objectName, "Name", airline)) {
                JOptionPane.showMessageDialog(null, "La Aerolinea ya existe");
                return;
            } 
            //No pueden existir aerolíneas con el mismo nombre y no pueden eliminarse aerolíneas que 
            //estén asociadas a un avión

            String country = aF.getTCountry().getText();

            aF.clean();
            Ar = new Airline(airline, country);
            crud.addObject(fileName, objectName, Ar.getDataName(), Ar.getData());

            JOptionPane.showMessageDialog(null, "Aerolinea agregada");
            setTableData();

        } else if (aF.getBUpdate() == e.getSource()) {
            // En proceso...

        } else if (aF.getBClear() == e.getSource()) {
        	
            String airline = aF.getTAirline().getText();
            
            if (airline.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete el nombre de la Aerolinea a eliminar");
                return;
            } else if (!lXML.isAlreadyInFile(fileName, objectName	, "Name", airline)) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");
                return;
            }//No pueden existir aerolíneas con el mismo nombre y no pueden eliminarse aerolíneas que 
            //estén asociadas a un avión
                aF.clean();
                crud.deleteObject(fileName, objectName, "Name", airline);
                JOptionPane.showMessageDialog(null, "Aerolinea eliminada");
                setTableData();

           
        }
    }
}