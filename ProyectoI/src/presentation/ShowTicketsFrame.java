package presentation;

import java.awt.Color;
import java.awt.Font;
//importaciones para el ordenamiento
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import domain.TicketsHistory;
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
	private JButton bReturn;
	//private boolean ColumnSorted = false;

    public ShowTicketsFrame() {
    	closeProgram();
        setForeground(new Color(0, 0, 0));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1009, 577);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().add(getPanel());
        setVisible(true);
    }
  //-------------------------------------------------------------------------------------------------------------------------
  	//Método para confirmar la salida del usuario de la aplicación
  	private void closeProgram() {
  		try {
  			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  			addWindowListener(new WindowAdapter() {
  				@Override
  				public void windowClosing(WindowEvent e) {
  					confirmExit();
  				}
  			});
  			this.setVisible(true);
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  	}
  	
  	private void confirmExit() {
  		int value = JOptionPane.showConfirmDialog(this, "¿Desea cerrar la aplicación?", "Waring", JOptionPane.YES_NO_OPTION);
  		if(value == JOptionPane.YES_OPTION) {
  			System.exit(0);
  		}
  	}
  	//-------------------------------------------------------------------------------------------------------------------------
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBounds(0, 0, 1000, 570);
            panel.setLayout(null);
            panel.add(getJPInfo());
        }
        return panel;
    }

    public JPanel getJPInfo() {
        if (JPInfo == null) {
            JPInfo = new JPanel();
            JPInfo.setBackground(new Color(63, 37, 170));
            JPInfo.setBounds(0, 0, 1000, 544);
            JPInfo.setLayout(null);
            //JLabels
            JPInfo.add(getLTitle());

            // JTable
            setDTM(dataTable, getColumnsName());
            setJTable(getDTM());
            setSPTable(getJTable());
            JPInfo.add(getSPTable());
            JPInfo.add(getBSearch());
            JPInfo.add(getBReturn());
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

    public void setDTM(Object data[][], String[] columnsName) {
		dtmT = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTM() {
		return dtmT;
	}

	public void setJTable(DefaultTableModel dtmT) {
		jTable = new JTable(dtmT);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTable.getTableHeader().setOpaque(false);
		jTable.getTableHeader().setBackground(new Color(28, 28, 28));
		jTable.getTableHeader().setForeground(new Color(255, 255, 255));
		jTable.setRowHeight(40);
		jTable.setEnabled(false);
		jTable.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		//jTable.getTableHeader().setResizingAllowed(false);
		
		// Agregar MouseAdapter a los encabezados de columna
	    jTable.getTableHeader().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int columnIndex = jTable.getTableHeader().columnAtPoint(e.getPoint());
	            sortTable(columnIndex);
	        }
	    });
	    setColumnWidth(jTable);
	}
	//Ordenamiento de la tabla según la columna seleccionada:
	public void sortTable(int columnIndex) {
	    TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable.getModel());
	    jTable.setRowSorter(sorter);
	    sorter.toggleSortOrder(columnIndex);
	    // Obtener el tipo de datos de la columna seleccionada
	    Class<?> columnClass = jTable.getColumnClass(columnIndex);

	    // Comparador personalizado para fechas
	    Comparator<Object> dateComparator = (date1, date2) -> {
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	            Date d1 = format.parse(date1.toString());
	            Date d2 = format.parse(date2.toString());
	            return d1.compareTo(d2);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return 0;
	        }
	    };
	    // Configurar el comparador para las columnas de fechas
	    if (columnClass == String.class) {
	        List<Integer> dateColumns = Arrays.asList(6, 16, 18); // Índices de las columnas de fechas
	        if (dateColumns.contains(columnIndex)) {
	            sorter.setComparator(columnIndex, dateComparator);
	        }
	    }
	}

	
	private void setColumnWidth(JTable jTable) {
		TableColumnModel columnModel = jTable.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(150);
		columnModel.getColumn(7).setPreferredWidth(150);
		columnModel.getColumn(8).setPreferredWidth(150);
		columnModel.getColumn(9).setPreferredWidth(150);
		columnModel.getColumn(10).setPreferredWidth(150);
		columnModel.getColumn(11).setPreferredWidth(150);
		columnModel.getColumn(12).setPreferredWidth(150);
		columnModel.getColumn(13).setPreferredWidth(150);
		columnModel.getColumn(14).setPreferredWidth(150);
		columnModel.getColumn(15).setPreferredWidth(150);
		columnModel.getColumn(16).setPreferredWidth(150);
		columnModel.getColumn(17).setPreferredWidth(150);
		columnModel.getColumn(18).setPreferredWidth(150);
		columnModel.getColumn(19).setPreferredWidth(150);
	}
	
	public JTable getJTable() {
		return jTable;
	}

	public void setSPTable(JTable jTable) {
		spT = new JScrollPane(jTable);
		spT.setBounds(10, 104, 971, 378);
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
				,"Email"
				,"Fecha nacimiento"
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
	        data[i][8] = hticks.getAirlineName();
	        data[i][9] = hticks.getOperationCenter();
	        data[i][10] = hticks.getAirplaneId();
	        data[i][11] = hticks.getAirlineAirplane();
	        data[i][12] = hticks.getAirplaneModel();
	        data[i][13] = hticks.getYearAirplane();
	        data[i][14] = hticks.getExitCity();
	        data[i][15] = hticks.getExitDate();
	        data[i][16] = hticks.getEnterCity();
	        data[i][17] = hticks.getEnterDate();
	        data[i][18] = hticks.getTicketClass();
	        data[i][19] = hticks.getPriceTicket();
	        
	    }
	    dtmT.setDataVector(data, getColumnsName());
	}

    public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/search-button.png")));
			bSearch.setForeground(Color.WHITE);
			bSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
			bSearch.setFocusable(false);
			bSearch.setBackground(new Color(28, 28, 28));
			bSearch.setBounds(10, 493, 140, 40);
		}
		return bSearch;
	}
	public JButton getBReturn() {
		if (bReturn == null) {
			bReturn = new JButton("");
			bReturn.setIcon(new ImageIcon(ShowTicketsFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 11, 40, 40);
		}
		return bReturn;
	}
}

