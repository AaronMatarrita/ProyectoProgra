package presentation;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import domain.Brand;
import domain.TicketsHistory;

import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class ShowTicketsFrame extends JFrame {
	//Paneles
	private JPanel panel;
	private JPanel JPInfo;
	//Etiquetas
	private JLabel lTitle;
	// Tabla
	private DefaultTableModel dtmT;
	private JTable jTable;
	// Scroll
	private JScrollPane spT;
	private Object dataTable[][];
	private JButton bSearch;

    public ShowTicketsFrame() {
    	setType(Type.UTILITY);
        setForeground(new Color(0, 0, 0));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1349, 601);
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
            panel.setBounds(0, 0, 1333, 570);
            panel.setLayout(null);
            panel.add(getJPInfo());
        }
        return panel;
    }

    public JPanel getJPInfo() {
        if (JPInfo == null) {
            JPInfo = new JPanel();
            JPInfo.setBackground(new Color(63, 37, 170));
            JPInfo.setBounds(0, 0, 1333, 570);
            JPInfo.setLayout(null);
            //JLabels
            JPInfo.add(getLTitle());

            // JTable
            setDTM(dataTable, getColumnsName());
            setJTable(getDTM());
            setSPTable(getJTable());
            JPInfo.add(getSPTable());
            JPInfo.add(getBSearch());
        }
        return JPInfo;
    }

    public JLabel getLTitle() {
        if (lTitle == null) {
            lTitle = new JLabel("Historial de Tickets");
            lTitle.setHorizontalAlignment(SwingConstants.CENTER);
            lTitle.setForeground(Color.WHITE);
            lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
            lTitle.setBounds(491, 11, 350, 50);
        }
        return lTitle;
    }

    public void setDTM(Object data[][], String[] columnsName) {
		dtmT = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTM() {
		return dtmT;
	}

	public void setJTable(DefaultTableModel dtmT) {
		jTable = new JTable(dtmT);
		jTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTable.getTableHeader().setOpaque(false);
		jTable.getTableHeader().setBackground(new Color(32, 136, 203));
		jTable.getTableHeader().setForeground(new Color(255, 255, 255));
		jTable.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTable.setEnabled(false);
		// No poder mover las columnas
		jTable.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTable.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTable() {
		return jTable;
	}

	public void setSPTable(JTable jTable) {
		spT = new JScrollPane(jTable);
		spT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spT.setBounds(10, 72, 1313, 404);
	}

	public JScrollPane getSPTable() {
		return spT;
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
				,"Fecha y hora de la compra del tiquete"
				,"Nombre de la Aerolinea"
				,"Centro de operaciones de la Aerolinea"
				,"Matricula avion"
				,"Aerolinea del avion"
				,"Modelo del avion"
				,"Año del Avion"
				,"Lugar de salida"
				,"Fecha de salida"
				,"Lugar de llegada"
				,"Fecha de llegada"
				,"Clase de Tiquete"
				,"Monto del tiquete"};                                                                                                                      
		return columnsName;
	}
	
	public void setJTableData(List<TicketsHistory> tickets) {
	    Object[][] data = new Object[tickets.size()][20];
	    for (int i = 0; i < tickets.size(); i++) {
	    	TicketsHistory hticks = tickets.get(i);
	        data[i][0] = hticks.getTicketNumber();
	        data[i][1] = hticks.getPasPassport();
	        data[i][2] = hticks.getPasName();
	        data[i][3] = hticks.getPassurNames();
	        data[i][4] = hticks.getEmail();
	        data[i][5] = hticks.getDateofBirth();
	        data[i][6] = hticks.getPhoneNumber();
	        data[i][7] = hticks.getBuyDate();
	        data[i][9] = hticks.getAirlineName();
	        data[i][10] = hticks.getOperationCenter();
	        data[i][11] = hticks.getAirplaneId();
	        data[i][12] = hticks.getAirlineAirplane();
	        data[i][13] = hticks.getAirplaneModel();
	        data[i][14] = hticks.getYearAirplane();
	        data[i][15] = hticks.getExitCity();
	        data[i][16] = hticks.getExitDate();
	        data[i][17] = hticks.getEnterCity();
	        data[i][18] = hticks.getEnterDate();
	        data[i][19] = hticks.getTicketClass();
	        data[i][20] = hticks.getPriceTicket();
	        
	    }
	    dtmT.setDataVector(data, getColumnsName());
	}
    
    public void clean() {
    	
    }
    public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/search-button.png")));
			bSearch.setForeground(Color.WHITE);
			bSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
			bSearch.setFocusable(false);
			bSearch.setBackground(new Color(28, 28, 28));
			bSearch.setBounds(581, 509, 140, 40);
		}
		return bSearch;
	}
}
