package presentation;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import domain.Brand;
import javax.swing.ScrollPaneConstants;
@SuppressWarnings("serial")
public class ShowTicketsFrame extends JFrame {
	//Paneles
	private JPanel panel;
	private JPanel JPInfo;
	//Etiquetas
	private JLabel lTitle;
	// Tabla
	private DefaultTableModel dtmTBrands;
	private JTable jTableBrands;
	// Scroll
	private JScrollPane spTTickets;
	private Object dataTable[][];

    public ShowTicketsFrame() {
    	setType(Type.UTILITY);
        setForeground(new Color(0, 0, 0));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 583);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().add(getPanel());
        setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBounds(0, 0, 984, 586);
            panel.setLayout(null);
            panel.add(getJPInfo());
        }
        return panel;
    }

    public JPanel getJPInfo() {
        if (JPInfo == null) {
            JPInfo = new JPanel();
            JPInfo.setBackground(new Color(63, 37, 170));
            JPInfo.setBounds(0, 0, 984, 544);
            JPInfo.setLayout(null);
            //JLabels
            JPInfo.add(getLTitle());

            // JTable
            setDTMBrands(dataTable, getColumnsName());
            setJTableBrands(getDTMBrands());
            setSPTableBrands(getJTableBrands());
            JPInfo.add(getSPTableBrands());
        }
        return JPInfo;
    }

    public JLabel getLTitle() {
        if (lTitle == null) {
            lTitle = new JLabel("Historial de Tickets");
            lTitle.setHorizontalAlignment(SwingConstants.CENTER);
            lTitle.setForeground(Color.WHITE);
            lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
            lTitle.setBounds(320, 10, 350, 50);
        }
        return lTitle;
    }

    public void setDTMBrands(Object data[][], String[] columnsName) {
		dtmTBrands = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMBrands() {
		return dtmTBrands;
	}

	public void setJTableBrands(DefaultTableModel dtmTBrands) {
		jTableBrands = new JTable(dtmTBrands);
		jTableBrands.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableBrands.getTableHeader().setOpaque(false);
		jTableBrands.getTableHeader().setBackground(new Color(32, 136, 203));
		jTableBrands.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableBrands.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableBrands.setEnabled(false);
		// No poder mover las columnas
		jTableBrands.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableBrands.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTableBrands() {
		return jTableBrands;
	}

	public void setSPTableBrands(JTable jTableBrands) {
		spTTickets = new JScrollPane(jTableBrands);
		spTTickets.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spTTickets.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spTTickets.setBounds(10, 71, 964, 404);
	}
	
	 //número de tiquete
	 //todos los datos del pasajero
	 //fecha y hora de la compra del tiquete
	 //todos los datos de la aerolínea
	 //todos los datos del avión
	 //lugar y fecha salida
	 //lugar y fecha arribo, clase de tiquete (ejecutiva, turista o económica)
	 //tiquete (ejecutiva, turista o económica) y monto del tiquete.
	
	public JScrollPane getSPTableBrands() {
		return spTTickets;
	}

	public String[] getColumnsName() {
		String columnsName[] = {
				"Número de tiquete"
				,"Pasaporte del pasajero"
				,"Nombre"
				,"Apellidos"
				,"Fecha nacimiento"
				,"CorreoElectronico"
				,"Telefono"
				,"Fecha de la compra del tiquete"
				,"Hora de la compra del tiquete"
				,"Nombre de la Aerolinea"
				,"Centro de operaciones de la Aerolinea"
				,"Matricula avion"
				,"Aerolinea del avion"
				,"Modelo del avion"
				,"Año del Avion"
				,"Lugar de salida"
				,"Fecha de salida"
				,"Clase de Tiquete"
				,"Monto del tiquete"};                                                                                                                      
		return columnsName;
	}
	
	public void setJTableData(List<Brand> brands) {
	    Object[][] data = new Object[brands.size()][19];
	    for (int i = 0; i < brands.size(); i++) {
	    	Brand brand = brands.get(i);
	        data[i][0] = brand.getBrand();
	    }
	    dtmTBrands.setDataVector(data, getColumnsName());
	}
    
    public void clean() {
    	
    }
}