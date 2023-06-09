package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import domain.Flight;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class FlightsFrame extends JFrame {
	private String userType;
	//Etiquetas
	private JPanel panel;
	private JPanel JPInfo;
	private JLabel lTitle;
	private JLabel lExitCity;
	private JLabel lExitTime;
	private JLabel lExitDate;
	private JLabel lEnterTime;
	private JLabel lEnterDate;
	private JLabel lEnterCity;
	private JLabel lAirplane;
	//Botones
	private JButton bAddFlights;
	private JButton bUpdate;
	private JButton bClear;
	//Campos de Texto
	private JTextField tExitCity;
	private JTextField tExitTime;
	private JTextField tEnterTime;
	private JTextField tEnterCity;
	//Tabla
	private DefaultTableModel dtmTFlights;
	private JTable jTableFlights;
	//Scroll
	private JScrollPane spTFlights;
	private Object dataTable[][];
	private JTextField tPriceEJE;
	private JLabel lpriceEJE;
	private JLabel lPriceTUR;
	private JTextField tPriceTUR;
	private JTextField tPriceECO;
	private JLabel lPriceECO;
	private JLabel lFlightNumber;
	private JTextField tFlightNum;
	private JComboBox<String> CBAirplane;
	private JButton bSearch;
	private JButton bHelp;
	private JButton bReturn;
	private JButton bShowFlights;
	private JDateChooser tExitDate;
	private JDateChooser tEnterDate;


	public FlightsFrame(String userType) {
		this.userType = userType;
		closeProgram();
		setForeground(new Color(0, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1000, 583);
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
			JPInfo.add(getLExitCity());
			JPInfo.add(getLExitDate());
			JPInfo.add(getLEnterDate());
			JPInfo.add(getLEnterCity());
			JPInfo.add(getLExitTime());
			JPInfo.add(getLAirplane());
			JPInfo.add(getLEnterTime());
			//JTextFields
			JPInfo.add(getTExitCity());
			JPInfo.add(getTExitTime());
			JPInfo.add(getTEnterTime());
			JPInfo.add(getTEnterCity());
			//JButtons
			JPInfo.add(getBAddFlights());
			JPInfo.add(getBUpdate());
			JPInfo.add(getBClear());
			if(userType.equals("2"))//Usuario de tipo *Colaborador* 
			{
				bClear.setVisible(false);
				bUpdate.setVisible(false);
			}
			//JTable
			setDTMFlights(dataTable, getColumnsName());
			setJTableFlights(getDTMFlights());
			setSPTableFlights(getJTableFlights());
			JPInfo.add(getSPTableFlights());
			JPInfo.add(getTPriceEJE());
			JPInfo.add(getLpriceEJE());
			JPInfo.add(getLPriceTUR());
			JPInfo.add(getTPriceTUR());
			JPInfo.add(getTPriceECO());
			JPInfo.add(getLPriceECO());
			JPInfo.add(getLFlightNumber());
			JPInfo.add(getTFlightNum());
			JPInfo.add(getCBAirplane());
			JPInfo.add(getBSearch());
			JPInfo.add(getBHelp());
			JPInfo.add(getBReturn());
			JPInfo.add(getBShowFlights());
			JPInfo.add(getTExitDate());
			JPInfo.add(getTEnterDate());


		}
		return JPInfo;
	}

	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de vuelos");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(320, 10, 350, 50);
		}
		return lTitle;
	}

	public JLabel getLExitCity() {
		if (lExitCity == null) {
			lExitCity = new JLabel("Ciudad de salida:");
			lExitCity.setForeground(new Color(255, 255, 255));
			lExitCity.setFont(new Font("Roboto", Font.PLAIN, 16));
			lExitCity.setBounds(10, 150, 150, 20);
		}
		return lExitCity;
	}

	public JButton getBAddFlights() {
		if (bAddFlights == null) {
			bAddFlights = new JButton("Agregar");
			bAddFlights.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAddFlights.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
			bAddFlights.setBackground(new Color(28, 28, 28));
			bAddFlights.setForeground(new Color(255, 255, 255));
			bAddFlights.setFocusable(false);
			bAddFlights.setBounds(350, 330, 140, 40);
		}
		return bAddFlights;
	}

	public JButton getBUpdate() {
		if (bUpdate == null) {
			bUpdate = new JButton("Modificar");
			bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
			bUpdate.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/update-button.png")));
			bUpdate.setBackground(new Color(28, 28, 28));
			bUpdate.setForeground(new Color(255, 255, 255));
			bUpdate.setFocusable(false);
			bUpdate.setBounds(150, 330, 140, 40);
		}
		return bUpdate;
	}

	public JButton getBClear() {
		if (bClear == null) {
			bClear = new JButton("Eliminar");
			bClear.setFont(new Font("Roboto", Font.PLAIN, 16));
			bClear.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/delete-button.png")));
			bClear.setBackground(new Color(28, 28, 28));
			bClear.setForeground(new Color(255, 255, 255));
			bClear.setFocusable(false);
			bClear.setBounds(750, 330, 140, 40);
		}
		return bClear;
	}
	
	public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/search-button.png")));
			bSearch.setForeground(Color.WHITE);
			bSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
			bSearch.setFocusable(false);
			bSearch.setBackground(new Color(28, 28, 28));
			bSearch.setBounds(550, 330, 140, 40);
		}
		return bSearch;
	}

	public JTextField getTExitCity() {
		if (tExitCity == null) {
			tExitCity = new JTextField();
			tExitCity.setBackground(new Color(28, 28, 28));
			tExitCity.setForeground(new Color(255, 255, 255));
			tExitCity.setFont(new Font("Roboto", Font.PLAIN, 16));
			tExitCity.setBorder(BorderFactory.createEmptyBorder());
			tExitCity.setBounds(170, 150, 150, 20);
			tExitCity.setColumns(10);
		}
		return tExitCity;
	}

	public JLabel getLExitTime() {
		if (lExitTime == null) {
			lExitTime = new JLabel("Hora de salida:");
			lExitTime.setForeground(Color.WHITE);
			lExitTime.setFont(new Font("Roboto", Font.PLAIN, 16));
			lExitTime.setBounds(10, 200, 150, 20);
		}
		return lExitTime;
	}
	public JTextField getTExitTime() {
		if (tExitTime == null) {
			tExitTime = new JTextField();
			tExitTime.setToolTipText("Formato 24 horas (hour : minutes) (xx:xx)");
			tExitTime.setForeground(Color.WHITE);
			tExitTime.setFont(new Font("Roboto", Font.PLAIN, 16));
			tExitTime.setColumns(10);
			tExitTime.setBorder(BorderFactory.createEmptyBorder());
			tExitTime.setBackground(new Color(28, 28, 28));
			tExitTime.setBounds(170, 200, 150, 20);
		}
		return tExitTime;
	}
	public JLabel getLExitDate() {
		if (lExitDate == null) {
			lExitDate = new JLabel("Fecha de salida:");
			lExitDate.setForeground(Color.WHITE);
			lExitDate.setFont(new Font("Roboto", Font.PLAIN, 16));
			lExitDate.setBounds(10, 250, 150, 20);
		}
		return lExitDate;
	}
	public JLabel getLEnterTime() {
		if (lEnterTime == null) {
			lEnterTime = new JLabel("Hora de llegada:");
			lEnterTime.setForeground(Color.WHITE);
			lEnterTime.setFont(new Font("Roboto", Font.PLAIN, 16));
			lEnterTime.setBounds(340, 200, 150, 20);
		}
		return lEnterTime;
	}
	public JTextField getTEnterTime() {
		if (tEnterTime == null) {
			tEnterTime = new JTextField();
			tEnterTime.setToolTipText("Formato 24 horas (hour : minutes) (xx:xx)");
			tEnterTime.setForeground(Color.WHITE);
			tEnterTime.setFont(new Font("Roboto", Font.PLAIN, 16));
			tEnterTime.setColumns(10);
			tEnterTime.setBorder(BorderFactory.createEmptyBorder());
			tEnterTime.setBackground(new Color(28, 28, 28));
			tEnterTime.setBounds(500, 200, 150, 20);
		}
		return tEnterTime;
	}
	public JLabel getLEnterDate() {
		if (lEnterDate == null) {
			lEnterDate = new JLabel("Fecha de llegada:");
			lEnterDate.setForeground(Color.WHITE);
			lEnterDate.setFont(new Font("Roboto", Font.PLAIN, 16));
			lEnterDate.setBounds(340, 250, 150, 20);
		}
		return lEnterDate;
	}
	public JLabel getLEnterCity() {
		if (lEnterCity == null) {
			lEnterCity = new JLabel("Ciudad de llegada:");
			lEnterCity.setForeground(Color.WHITE);
			lEnterCity.setFont(new Font("Roboto", Font.PLAIN, 16));
			lEnterCity.setBounds(340, 150, 150, 20);
		}
		return lEnterCity;
	}
	public JTextField getTEnterCity() {
		if (tEnterCity == null) {
			tEnterCity = new JTextField();
			tEnterCity.setForeground(Color.WHITE);
			tEnterCity.setFont(new Font("Roboto", Font.PLAIN, 16));
			tEnterCity.setColumns(10);
			tEnterCity.setBorder(BorderFactory.createEmptyBorder());
			tEnterCity.setBackground(new Color(28, 28, 28));
			tEnterCity.setBounds(500, 150, 150, 20);
		}
		return tEnterCity;
	}
	public JLabel getLAirplane() {
		if (lAirplane == null) {
			lAirplane = new JLabel("Avion:");
			lAirplane.setForeground(Color.WHITE);
			lAirplane.setFont(new Font("Roboto", Font.PLAIN, 16));
			lAirplane.setBounds(340, 100, 150, 20);
		}
		return lAirplane;
	}

	public void setDTMFlights(Object data[][], String[] columnsName) {
		dtmTFlights = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMFlights() {
		return dtmTFlights;
	}

	public void setJTableFlights(DefaultTableModel dtmTFlights) {
		jTableFlights = new JTable(dtmTFlights);
		jTableFlights.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableFlights.getTableHeader().setOpaque(false);
		jTableFlights.getTableHeader().setBackground(new Color(28, 28, 28));
		jTableFlights.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableFlights.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableFlights.setEnabled(false);
		// No poder mover las columnas
		jTableFlights.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableFlights.getTableHeader().setResizingAllowed(false);
		
	}

	public JTable getJTableFlights() {
		return jTableFlights;
	}

	public void setSPTableFlights(JTable jTableFlights) {
		spTFlights = new JScrollPane(jTableFlights);
		spTFlights.setBounds(10, 400, 964, 133);
	}

	public JScrollPane getSPTableFlights() {
		return spTFlights;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Numero de Vuelo", "Ciudad de salida", "Fecha de salida","Hora de salida", "Ciudad de llegada", "Fecha de llegada", "Hora de llegada", "Avion","Monto Ejecutivos", "Monto Turistas","Monto Economico"};                                                                            
		return columnsName;
	}

	public void setJTableData(ArrayList<Flight> flights) {
		Object[][] data = new Object[flights.size()][11];
		for (int i = 0; i < flights.size(); i++) {
			Flight flight = flights.get(i);

			data[i][0] = flight.getFlightNumber();
			data[i][1] = flight.getDepartureCity();
			data[i][2] = flight.getDepartureDate();
			data[i][3] = flight.getDepartureTime();
			data[i][4] = flight.getArrivalCity();
			data[i][5] = flight.getArrivalDate();
			data[i][6] = flight.getArrivalTime();
			data[i][7] = flight.getAirplane();
			data[i][8] = flight.getBusinessClassSeatsPrice();
			data[i][9] = flight.getTouristClassSeatsPrice();
			data[i][10] = flight.getEconomyClassSeatsPrice();
		}
		dtmTFlights.setDataVector(data, getColumnsName());
	}
	public JTextField getTPriceEJE() {
		if (tPriceEJE == null) {
			tPriceEJE = new JTextField();
			tPriceEJE.setForeground(Color.WHITE);
			tPriceEJE.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPriceEJE.setColumns(10);
			tPriceEJE.setBorder(BorderFactory.createEmptyBorder());
			tPriceEJE.setBackground(new Color(28, 28, 28));
			tPriceEJE.setBounds(824, 100, 150, 20);
		}
		return tPriceEJE;
	}
	public JLabel getLpriceEJE() {
		if (lpriceEJE == null) {
			lpriceEJE = new JLabel("Precio A. Ejecutivos:");
			lpriceEJE.setForeground(Color.WHITE);
			lpriceEJE.setFont(new Font("Roboto", Font.PLAIN, 16));
			lpriceEJE.setBounds(660, 100, 150, 20);
		}
		return lpriceEJE;
	}
	public JLabel getLPriceTUR() {
		if (lPriceTUR == null) {
			lPriceTUR = new JLabel("Precio A. Turistas:");
			lPriceTUR.setForeground(Color.WHITE);
			lPriceTUR.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPriceTUR.setBounds(660, 150, 150, 20);
		}
		return lPriceTUR;
	}
	public JTextField getTPriceTUR() {
		if (tPriceTUR == null) {
			tPriceTUR = new JTextField();
			tPriceTUR.setForeground(Color.WHITE);
			tPriceTUR.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPriceTUR.setColumns(10);
			tPriceTUR.setBorder(BorderFactory.createEmptyBorder());
			tPriceTUR.setBackground(new Color(28, 28, 28));
			tPriceTUR.setBounds(825, 150, 150, 20);
		}
		return tPriceTUR;
	}
	public JTextField getTPriceECO() {
		if (tPriceECO == null) {
			tPriceECO = new JTextField();
			tPriceECO.setForeground(Color.WHITE);
			tPriceECO.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPriceECO.setColumns(10);
			tPriceECO.setBorder(BorderFactory.createEmptyBorder());
			tPriceECO.setBackground(new Color(28, 28, 28));
			tPriceECO.setBounds(824, 200, 150, 20);
		}
		return tPriceECO;
	}
	public JLabel getLPriceECO() {
		if (lPriceECO == null) {
			lPriceECO = new JLabel("Precio A. Economicos:");
			lPriceECO.setForeground(Color.WHITE);
			lPriceECO.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPriceECO.setBounds(660, 200, 160, 20);
		}
		return lPriceECO;
	}
	public JLabel getLFlightNumber() {
		if (lFlightNumber == null) {
			lFlightNumber = new JLabel("Número de vuelo:");
			lFlightNumber.setForeground(Color.WHITE);
			lFlightNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			lFlightNumber.setBounds(10, 100, 150, 20);
		}
		return lFlightNumber;
	}
	public JTextField getTFlightNum() {
		if (tFlightNum == null) {
			tFlightNum = new JTextField();
			tFlightNum.setForeground(Color.WHITE);
			tFlightNum.setFont(new Font("Roboto", Font.PLAIN, 16));
			tFlightNum.setColumns(10);
			tFlightNum.setBorder(BorderFactory.createEmptyBorder());
			tFlightNum.setBackground(new Color(28, 28, 28));
			tFlightNum.setBounds(170, 100, 150, 20);
		}
		return tFlightNum;
	}
	public JComboBox<String> getCBAirplane() {
		if (CBAirplane == null) {
			CBAirplane = new JComboBox<String>();
			CBAirplane.setBackground(new Color(28, 28, 28));
			CBAirplane.setForeground(new Color(255, 255, 255));
			CBAirplane.setFont(new Font("Roboto", Font.PLAIN, 16));
			CBAirplane.setBorder(BorderFactory.createEmptyBorder());
			CBAirplane.setBounds(500, 100, 150, 20);
		}
		return CBAirplane;
	}
	
	public void fillAirplaneComboBox(ArrayList<String> airplanes) {
		CBAirplane.addItem("Indefinido");
		for(String airplane : airplanes) {
			CBAirplane.addItem(airplane);
		}
	}
	public void clean() {
		getTEnterCity().setText("");
		getTEnterDate().setDate(null);
		getTEnterTime().setText("");
		getTPriceEJE().setText("");
		getTPriceTUR().setText("");
		getTPriceECO().setText("");
		getCBAirplane().setSelectedItem("Indefinido");
		getTExitCity().setText("");
		getTExitDate().setDate(null);
		getTExitTime().setText("");
	}
	public JButton getBHelp() {
		if (bHelp == null) {
			bHelp = new JButton("");
			bHelp.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/help-button.png")));
			bHelp.setForeground(Color.WHITE);
			bHelp.setFont(new Font("Roboto", Font.PLAIN, 16));
			bHelp.setFocusable(false);
			bHelp.setBackground(new Color(28, 28, 28));
			bHelp.setBounds(934, 11, 40, 40);
		}
		return bHelp;
	}
	public JButton getBReturn() {
		if (bReturn == null) {
			bReturn = new JButton("");
			bReturn.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 11, 40, 40);
		}
		return bReturn;
	}
	public JButton getBShowFlights() {
		if (bShowFlights == null) {
			bShowFlights = new JButton("");
			bShowFlights.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/airplanes models.png")));
			bShowFlights.setForeground(Color.WHITE);
			bShowFlights.setFont(new Font("Dialog", Font.PLAIN, 16));
			bShowFlights.setFocusable(false);
			bShowFlights.setBackground(new Color(28, 28, 28));
			bShowFlights.setBounds(884, 10, 40, 40);
		}
		return bShowFlights;
	}
	public JDateChooser getTExitDate() {
		if (tExitDate == null) {
			tExitDate = new JDateChooser();
			tExitDate.setForeground(Color.WHITE);
			tExitDate.setFont(new Font("Roboto", Font.PLAIN, 16));
			tExitDate.setDateFormatString("dd/MM/yyyy");
			tExitDate.setBounds(170, 250, 150, 20);
		}
		return tExitDate;
	}
	public JDateChooser getTEnterDate() {
		if (tEnterDate == null) {
			tEnterDate = new JDateChooser();
			tEnterDate.setForeground(Color.WHITE);
			tEnterDate.setFont(new Font("Roboto", Font.PLAIN, 16));
			tEnterDate.setDateFormatString("dd/MM/yyyy");
			tEnterDate.setBounds(500, 250, 150, 20);
		}
		return tEnterDate;
	}
}
