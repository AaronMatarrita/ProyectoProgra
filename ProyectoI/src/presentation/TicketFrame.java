package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import domain.Ticket;

@SuppressWarnings("serial")
public class TicketFrame extends JFrame {
	//Etiquetas
	private JPanel panel;
	private JPanel JPInfo;
	private JLabel lTitle;
	private JLabel lTicketNumber;
	private JLabel lPassport;
	//Botones
	private JButton bAddFlights;
	private JButton bUpdate;
	private JButton bClear;
	//Campos de Texto
	private JTextField tTicketNumber;
	//Tabla
	private DefaultTableModel dtmTFlights;
	private JTable jTableFlights;
	//Scroll
	private JScrollPane spTFlights;
	private Object dataTable[][];
	private JLabel lFlightNumber;
	private JMenuBar JMenu;
	private JMenu mnFile;
	private JMenu MNHelp;
	private JMenuItem bHelp;
	private JLabel lTicketType;
	private JComboBox<String> cBTicketType;
	private JMenuItem bShowTickets;
	private String userType;
	private JButton bSearch;
	private JMenuItem bSaveTicket;
	private JComboBox<String> cbFlightNumber;
	private JComboBox<String> cbPassport;
	private JButton bReturn;

	public TicketFrame(String userType) {
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
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 984, 544);
			panel.setLayout(null);
			panel.add(getJPInfo());
			panel.add(getMenuBar_1());
		}
		return panel;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JPanel getJPInfo() {
		if (JPInfo == null) {
			JPInfo = new JPanel();
			JPInfo.setBackground(new Color(63, 37, 170));
			JPInfo.setBounds(0, 38, 984, 509);
			JPInfo.setLayout(null);
			//JLabels
			JPInfo.add(getLTitle());
			JPInfo.add(getLTicketNumber());
			JPInfo.add(getLPassport());
			//JTextFields
			JPInfo.add(getTTicketNumber());
			//JButtons
			JPInfo.add(getBAddTickets());
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
			JPInfo.add(getLFlightNumber());
			JPInfo.add(getLTicketType());
			JPInfo.add(getCBTicketType());
			JPInfo.add(getBSearch());
			JPInfo.add(getCbFlightNumber());
			JPInfo.add(getCbPassport());
			JPInfo.add(getBReturn());

		}
		return JPInfo;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de tiquetes");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(320, 30, 350, 50);
		}
		return lTitle;
	}
	public String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return currentDateTime.format(formatter);
	}

	public JLabel getLTicketNumber() {
		if (lTicketNumber == null) {
			lTicketNumber = new JLabel("Número de tiquete:");
			lTicketNumber.setForeground(new Color(255, 255, 255));
			lTicketNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			lTicketNumber.setBounds(120, 130, 150, 20);
		}
		return lTicketNumber;
	}
	public JLabel getLPassport() {
		if (lPassport == null) {
			lPassport = new JLabel("Pasaporte:");
			lPassport.setForeground(Color.WHITE);
			lPassport.setFont(new Font("Dialog", Font.PLAIN, 16));
			lPassport.setBounds(120, 230, 150, 20);
		}
		return lPassport;
	}
	public JLabel getLFlightNumber() {
		if (lFlightNumber == null) {
			lFlightNumber = new JLabel("Número de vuelo:");
			lFlightNumber.setForeground(Color.WHITE);
			lFlightNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
			lFlightNumber.setBounds(580, 230, 150, 20);
		}
		return lFlightNumber;
	}
	public JLabel getLTicketType() {
		if (lTicketType == null) {
			lTicketType = new JLabel("Tipo de Ticket:");
			lTicketType.setForeground(Color.WHITE);
			lTicketType.setFont(new Font("Dialog", Font.PLAIN, 16));
			lTicketType.setBounds(580, 130, 130, 20);
		}
		return lTicketType;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JTextField getTTicketNumber() {
		if (tTicketNumber == null) {
			tTicketNumber = new JTextField();
			tTicketNumber.setBackground(new Color(28, 28, 28));
			tTicketNumber.setForeground(new Color(255, 255, 255));
			tTicketNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			tTicketNumber.setBorder(BorderFactory.createEmptyBorder());
			tTicketNumber.setBounds(290, 130, 150, 20);
			tTicketNumber.setColumns(10);
		}
		return tTicketNumber;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JComboBox<String> getCBTicketType() {
		if (cBTicketType == null) {
			String arrayCBTicketType[] = {"Indefinido", "Ejecutivo", "Turista","Economico"};
			cBTicketType = new JComboBox<String>();
			for(int i = 0; i < arrayCBTicketType.length; i++) {
				cBTicketType.addItem(arrayCBTicketType[i]);
			}
			cBTicketType.setForeground(Color.WHITE);
			cBTicketType.setFont(new Font("Dialog", Font.PLAIN, 16));
			cBTicketType.setBorder(BorderFactory.createEmptyBorder());
			cBTicketType.setBackground(new Color(28, 28, 28));
			cBTicketType.setBounds(740, 130, 150, 22);
		}
		return cBTicketType;
	}
	public JComboBox<String> getCbFlightNumber() {
		if (cbFlightNumber == null) {
			cbFlightNumber = new JComboBox<String>();
			cbFlightNumber.setForeground(Color.WHITE);
			cbFlightNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
			cbFlightNumber.setBorder(BorderFactory.createEmptyBorder());
			cbFlightNumber.setBackground(new Color(28, 28, 28));
			cbFlightNumber.setBounds(740, 230, 150, 22);
		}
		return cbFlightNumber;
	}
	public void fillFlightNumberComboBox(ArrayList<String> flights) {
		cbFlightNumber.addItem("Indefinido");
		for (String flight : flights) {
			cbFlightNumber.addItem(flight);
		}
	}
	public JComboBox<String> getCbPassport() {
		if (cbPassport == null) {
			cbPassport = new JComboBox<String>();
			cbPassport.setForeground(Color.WHITE);
			cbPassport.setFont(new Font("Dialog", Font.PLAIN, 16));
			cbPassport.setBorder(BorderFactory.createEmptyBorder());
			cbPassport.setBackground(new Color(28, 28, 28));
			cbPassport.setBounds(290, 230, 150, 22);
		}
		return cbPassport;
	}
	public void fillPassportComboBox(ArrayList<String> passengerPassports) {
		cbPassport.addItem("Indefinido");
		for (String passengerPassport : passengerPassports) {
			cbPassport.addItem(passengerPassport);
		}
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JMenuBar getMenuBar_1() {
		if (JMenu == null) {
			JMenu = new JMenuBar();
			JMenu.setBounds(0, 0, 984, 40);
			JMenu.setForeground(new Color(255, 255, 255));
			JMenu.setBackground(new Color(28, 28, 28));
			JMenu.add(getMnFile());
			JMenu.add(getMNHelp());
		}
		return JMenu;
	}
	public JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setFont(new Font("Roboto", Font.PLAIN, 12));
			mnFile.setBorder(null);
			mnFile.setFocusable(false);
			mnFile.setForeground(new Color(255, 255, 255));
			mnFile.add(getBShowTickets());
			mnFile.add(getBSaveTicket());
		}
		return mnFile;
	}
	public JMenu getMNHelp() {
		if (MNHelp == null) {
			MNHelp = new JMenu("Help");
			MNHelp.setForeground(new Color(255, 255, 255));
			MNHelp.setFont(new Font("Roboto", Font.PLAIN, 12));
			MNHelp.add(getBHelp());
		}
		return MNHelp;
	}
	public JMenuItem getBHelp() {
		if (bHelp == null) {
			bHelp = new JMenuItem("Help");
			bHelp.setIcon(new ImageIcon(TicketFrame.class.getResource("/imagesMain/imagesButtons/help-button.png")));
		}
		return bHelp;
	}
	public JMenuItem getBShowTickets() {
		if (bShowTickets == null) {
			bShowTickets = new JMenuItem("Show Tickets");
			bShowTickets.setIcon(new ImageIcon(TicketFrame.class.getResource("/imagesMain/tickets.png")));
			bShowTickets.setForeground(Color.WHITE);
			bShowTickets.setFont(new Font("Dialog", Font.PLAIN, 12));
			bShowTickets.setFocusable(false);
			bShowTickets.setBorder(null);
			bShowTickets.setBackground(new Color(28, 28, 28));
		}
		return bShowTickets;
	}
	public JMenuItem getBSaveTicket() {
		if (bSaveTicket == null) {
			bSaveTicket = new JMenuItem("Save Ticket");
			bSaveTicket.setIcon(new ImageIcon(TicketFrame.class.getResource("/imagestTickets/downloadPDF.png")));
			bSaveTicket.setForeground(Color.WHITE);
			bSaveTicket.setFont(new Font("Dialog", Font.PLAIN, 12));
			bSaveTicket.setFocusable(false);
			bSaveTicket.setBorder(null);
			bSaveTicket.setBackground(new Color(28, 28, 28));
		}
		return bSaveTicket;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public JButton getBAddTickets() {
		if (bAddFlights == null) {
			bAddFlights = new JButton("Agregar");
			bAddFlights.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAddFlights.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
			bAddFlights.setBackground(new Color(28, 28, 28));
			bAddFlights.setForeground(new Color(255, 255, 255));
			bAddFlights.setFocusable(false);
			bAddFlights.setBounds(150, 320, 140, 40);
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
			bUpdate.setBounds(350, 320, 140, 40);
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
			bClear.setBounds(750, 320, 140, 40);
		}
		return bClear;
	}

	public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(TicketFrame.class.getResource("/imagesMain/imagesButtons/search-button.png")));
			bSearch.setForeground(Color.WHITE);
			bSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
			bSearch.setFocusable(false);
			bSearch.setBackground(new Color(28, 28, 28));
			bSearch.setBounds(550, 320, 140, 40);
		}
		return bSearch;
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
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
		spTFlights.setBounds(10, 380, 964, 118);
	}

	public JScrollPane getSPTableFlights() {
		return spTFlights;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Numero de tiquete", "Pasaporte", "Número de vuelo", "Tipo de tiquete","Fecha y hora de compra"};
		return columnsName;
	}

	public void setJTableData(ArrayList<Ticket> tickets) {
		Object[][] data = new Object[tickets.size()][5];
		for (int i = 0; i < tickets.size(); i++) {
			Ticket ticket = tickets.get(i);

			data[i][0] = ticket.getTicketNumber();
			data[i][1] = ticket.getPassport();
			data[i][2] = ticket.getFlightNumber();
			data[i][3] = ticket.getTickettype();
			data[i][4] = ticket.getBuyTicketDate();
		}
		dtmTFlights.setDataVector(data, getColumnsName());
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	public void clean() {
		getCbFlightNumber().setSelectedItem("Indefinido");
		getTTicketNumber().setText("");
	}
	public JButton getBReturn() {
		if (bReturn == null) {
			bReturn = new JButton("");
			bReturn.setIcon(new ImageIcon(TicketFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 11, 40, 40);
		}
		return bReturn;
	}
}
