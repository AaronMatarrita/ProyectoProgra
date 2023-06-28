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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import domain.FlightsHistory;

@SuppressWarnings("serial")
public class ShowFlightsFrame extends JFrame {
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
	//private boolean ColumnSorted = false;
	private JComboBox<String> comboBox;
	private JLabel lFlight;
	private JButton bReturn;

	public ShowFlightsFrame() {
		closeProgram();
		setForeground(new Color(0, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1010, 582);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		setVisible(true);
	}
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
			panel.setBounds(0, 0, 1001, 551);
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
			setColumnWidth(getJTable());
			JPInfo.add(getSPTable());
			JPInfo.add(getBSearch());
			JPInfo.add(getComboBox());
			JPInfo.add(getLFlight());
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
			lTitle.setBounds(323, 11, 350, 50);
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
		//jTable.getTableHeader().setResizingAllowed(false);
		//jTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		//Agregar MouseAdapter a los encabezados de columna
		TableColumnModel columnModel = jTable.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(500);

		jTable.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int columnIndex = jTable.getTableHeader().columnAtPoint(e.getPoint());
				sortTable(columnIndex);
			}
		});
		setColumnWidth(jTable);
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

	public JTable getJTable() {
		return jTable;
	}

	public void setSPTable(JTable jTable) {
		spT = new JScrollPane(jTable);
		spT.setBounds(10, 186, 971, 347);
	}

	public JScrollPane getSPTable() {
		return spT;
	}

	public String[] getColumnsName() {
		String columnsName[] = {
				"Número de vuelo"
				,"Aerolínea"
				,"Avion"
				,"Lugar de salida"
				,"Fecha de salida"
				,"Lugar de llegada"
				,"Fecha de llegada"
				,"Asientos EJE vendidos"
				,"Asientos EJE disponibles"
				,"Asientos TOUR vendidos"
				,"Asientos TOUR disponibles"
				,"Asientos ECO vendidos"
				,"Asientos ECO disponibles"
				,"Costo por tiquete EJE"
				,"Costo por tiquete TOUR"
				,"Costo por tiquete ECO"
				,"Monto total"
		};   



		return columnsName;
	}

	public void setJTableData(ArrayList<FlightsHistory> flights) {
		Object[][] data = new Object[flights.size()][17];
		for (int i = 0; i < flights.size(); i++) {
			FlightsHistory hflights = flights.get(i);
			data[i][0] = hflights.getFlightNumber();
			data[i][1] = hflights.getAirline();
			data[i][2] = hflights.getAirplane();
			data[i][3] = hflights.getExitCity();
			data[i][4] = hflights.getExitDate();
			data[i][5] = hflights.getEnterCity();
			data[i][6] = hflights.getEnterDate();
			data[i][7] = hflights.getSoldEJEseats();
			data[i][8] = hflights.getTotalEJEseats();
			data[i][9] = hflights.getSoldTOUseats();
			data[i][10] = hflights.getTotalTOUseats();
			data[i][11] = hflights.getSoldECOseats();
			data[i][12] = hflights.getTotalECOseats();
			data[i][13] = hflights.getEJEprice();
			data[i][14] = hflights.getTOUprice();
			data[i][15] = hflights.getECOprice();
			data[i][16] = hflights.getTotalflight();

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
			bSearch.setBounds(584, 93, 140, 40);
		}
		return bSearch;
	}
	public JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setBackground(new Color(28, 28, 28));
			comboBox.setForeground(new Color(255, 255, 255));
			comboBox.setFont(new Font("Roboto", Font.PLAIN, 16));
			comboBox.setBorder(BorderFactory.createEmptyBorder());
			comboBox.setBounds(262, 103, 150, 20);
		}
		return comboBox;

	}

	public void fillFlightsComboBox(ArrayList<String> fligths) {
		comboBox.addItem("Indefinido");
		for(String flight : fligths) {
			comboBox.addItem(flight);
		}
	}

	public JLabel getLFlight() {
		if (lFlight == null) {
			lFlight = new JLabel("Vuelo:");
			lFlight.setBounds(200, 103, 52, 20);
			lFlight.setForeground(Color.WHITE);
			lFlight.setFont(new Font("Roboto", Font.PLAIN, 16));	
		}
		return lFlight;
	}
	public JButton getBReturn() {
		if (bReturn == null) {
			bReturn = new JButton("");
			bReturn.setIcon(new ImageIcon(ShowFlightsFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 11, 40, 40);
		}
		return bReturn;
	}
}

