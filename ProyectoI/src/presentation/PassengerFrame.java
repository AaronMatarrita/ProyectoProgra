package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import domain.Passenger;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class PassengerFrame extends JFrame {
	private String userType;
	// Etiquetas
	private JPanel panel;
	private JPanel JPInfo;
	private JLabel lTitle;
	private JLabel lPassport;
	private JLabel lName;
	private JLabel lLastname;
	private JLabel lDateOfBirth;
	// Botones
	private JButton bAddPassenger;
	private JButton bUpdate;
	private JButton bClear;
	// Campos de texto
	private JTextField tPassport;
	private JTextField tName;
	// Tabla
	private DefaultTableModel dtmTPassengers;
	private JTable jTablePassengers;
	// Scroll
	private JScrollPane spTPassengers;
	private Object dataTable[][];
	private JTextField tLastname;
	private JLabel lEmain;
	private JTextField tEmail;
	private JLabel lPhoneNumber;
	private JTextField tPhoneNumber;
	private JButton bSearch;
	private JButton bHelp;
	private JButton bReturn;
	private JDateChooser tDateofBirth;

	public PassengerFrame(String userType) {
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

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
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

			// JLabels
			JPInfo.add(getLTitle());
			JPInfo.add(getLPassport());
			JPInfo.add(getLName());
			JPInfo.add(getLLastname());
			JPInfo.add(getLDateOfBirth());

			// JTextFields
			JPInfo.add(getTPassport());
			JPInfo.add(getTName());

			// JButtons
			JPInfo.add(getBAddPassenger());
			JPInfo.add(getBUpdate());
			JPInfo.add(getBClear());
			if(userType.equals("2"))//Usuario de tipo *Colaborador* 
			{
				bClear.setVisible(false);
				bUpdate.setVisible(false);
			}
			// JTable
			setDTMPassengers(dataTable, getColumnsName());
			setJTablePassengers(getDTMPassengers());
			setSPTablePassengers(getJTablePassengers());
			JPInfo.add(getSPTablePassengers());
			JPInfo.add(getTLastname());
			JPInfo.add(getLEmain());
			JPInfo.add(getTEmail());
			JPInfo.add(getLPhoneNumber());
			JPInfo.add(getTPhoneNumber());
			JPInfo.add(getBSearch());
			JPInfo.add(getBHelp());
			JPInfo.add(getBReturn());
			JPInfo.add(getTDateOfBirth());

		}
		return JPInfo;
	}

	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de pasajeros");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(320, 10, 350, 50);
			lTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
			lTitle.setAlignmentY(Component.CENTER_ALIGNMENT);

		}
		return lTitle;
	}

	public JLabel getLPassport() {
		if (lPassport == null) {
			lPassport = new JLabel("Número de pasaporte:");
			lPassport.setForeground(new Color(255, 255, 255));
			lPassport.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPassport.setBounds(10, 150, 170, 20);
		}
		return lPassport;
	}

	public JLabel getLName() {
		if (lName == null) {
			lName = new JLabel("Nombre:");
			lName.setForeground(Color.WHITE);
			lName.setFont(new Font("Roboto", Font.PLAIN, 16));
			lName.setBounds(10, 200, 170, 20);
		}
		return lName;
	}

	public JLabel getLLastname() {
		if (lLastname == null) {
			lLastname = new JLabel("Apellidos:");
			lLastname.setForeground(Color.WHITE);
			lLastname.setFont(new Font("Roboto", Font.PLAIN, 16));
			lLastname.setBounds(10, 250, 160, 20);
		}
		return lLastname;
	}

	public JLabel getLDateOfBirth() {
		if (lDateOfBirth == null) {
			lDateOfBirth = new JLabel("Fecha de nacimiento:");
			lDateOfBirth.setForeground(Color.WHITE);
			lDateOfBirth.setFont(new Font("Roboto", Font.PLAIN, 16));
			lDateOfBirth.setBounds(624, 150, 170, 20);
		}
		return lDateOfBirth;
	}

	public JButton getBAddPassenger() {
		if (bAddPassenger == null) {
			bAddPassenger = new JButton("Agregar");
			bAddPassenger.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAddPassenger.setIcon(new ImageIcon(PassengerFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
			bAddPassenger.setBackground(new Color(28, 28, 28));
			bAddPassenger.setForeground(new Color(255, 255, 255));
			bAddPassenger.setFocusable(false);
			bAddPassenger.setBounds(350, 330, 140, 40);
		}
		return bAddPassenger;
	}

	public JButton getBUpdate() {
		if (bUpdate == null) {
			bUpdate = new JButton("Modificar");
			bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
			bUpdate.setIcon(new ImageIcon(PassengerFrame.class.getResource("/imagesMain/imagesButtons/update-button.png")));
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
			bClear.setIcon(new ImageIcon(PassengerFrame.class.getResource("/imagesMain/imagesButtons/update-button.png")));
			bClear.setBackground(new Color(28, 28, 28));
			bClear.setForeground(new Color(255, 255, 255));
			bClear.setFocusable(false);
			bClear.setBounds(750, 330, 140, 40);
		}
		return bClear;
	}

	public JTextField getTPassport() {
		if (tPassport == null) {
			tPassport = new JTextField();
			tPassport.setBackground(new Color(28, 28, 28));
			tPassport.setForeground(new Color(255, 255, 255));
			tPassport.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPassport.setBorder(BorderFactory.createEmptyBorder());
			tPassport.setBounds(190, 150, 170, 20);
			tPassport.setColumns(10);
		}
		return tPassport;
	}

	public JTextField getTName() {
		if (tName == null) {
			tName = new JTextField();
			tName.setBackground(new Color(28, 28, 28));
			tName.setForeground(new Color(255, 255, 255));
			tName.setFont(new Font("Roboto", Font.PLAIN, 16));
			tName.setBorder(BorderFactory.createEmptyBorder());
			tName.setColumns(10);
			tName.setBounds(191, 200, 170, 20);
		}
		return tName;
	}

	public void setDTMPassengers(Object data[][], String[] columnsName) {
		dtmTPassengers = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMPassengers() {
		return dtmTPassengers;
	}

	public void setJTablePassengers(DefaultTableModel dtmTPassengers) {
		jTablePassengers = new JTable(dtmTPassengers);
		jTablePassengers.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTablePassengers.getTableHeader().setOpaque(false);
		jTablePassengers.getTableHeader().setBackground(new Color(28, 28, 28));
		jTablePassengers.getTableHeader().setForeground(new Color(255, 255, 255));
		jTablePassengers.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTablePassengers.setEnabled(false);
		// No poder mover las columnas
		jTablePassengers.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTablePassengers.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTablePassengers() {
		return jTablePassengers;
	}

	public void setSPTablePassengers(JTable jTablePassengers) {
		spTPassengers = new JScrollPane(jTablePassengers);
		spTPassengers.setBounds(10, 400, 964, 133);
	}

	public JScrollPane getSPTablePassengers() {
		return spTPassengers;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Número de pasaporte", "Nombre", "Apellidos", "Fecha de nacimiento", "Coreo electrónico", "Teléfono"};
		return columnsName;
	}

	public void setJTableData(List<Passenger> passengers) {
		Object[][] data = new Object[passengers.size()][6];
		for (int i = 0; i < passengers.size(); i++) {
			Passenger passenger = passengers.get(i);
			data[i][0] = passenger.getPassport();
			data[i][1] = passenger.getName();
			data[i][2] = passenger.getLastname();
			data[i][3] = passenger.getDateofbirth();
			data[i][4] = passenger.getEmail();
			data[i][5] = passenger.getPhoneNumber();
		}
		dtmTPassengers.setDataVector(data, getColumnsName());
	}

	public JTextField getTLastname() {
		if (tLastname == null) {
			tLastname = new JTextField();
			tLastname.setForeground(Color.WHITE);
			tLastname.setFont(new Font("Roboto", Font.PLAIN, 16));
			tLastname.setColumns(10);
			tLastname.setBorder(BorderFactory.createEmptyBorder());
			tLastname.setBackground(new Color(28, 28, 28));
			tLastname.setBounds(191, 250, 170, 20);
		}
		return tLastname;
	}
	public JLabel getLEmain() {
		if (lEmain == null) {
			lEmain = new JLabel("Correo electrónico:");
			lEmain.setForeground(Color.WHITE);
			lEmain.setFont(new Font("Roboto", Font.PLAIN, 16));
			lEmain.setBounds(624, 200, 170, 20);
		}
		return lEmain;
	}
	public JTextField getTEmail() {
		if (tEmail == null) {
			tEmail = new JTextField();
			tEmail.setForeground(Color.WHITE);
			tEmail.setFont(new Font("Roboto", Font.PLAIN, 16));
			tEmail.setColumns(10);
			tEmail.setBorder(BorderFactory.createEmptyBorder());
			tEmail.setBackground(new Color(28, 28, 28));
			tEmail.setBounds(804, 200, 170, 20);
		}
		return tEmail;
	}
	public JLabel getLPhoneNumber() {
		if (lPhoneNumber == null) {
			lPhoneNumber = new JLabel("Teléfono:");
			lPhoneNumber.setForeground(Color.WHITE);
			lPhoneNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPhoneNumber.setBounds(624, 250, 170, 20);
		}
		return lPhoneNumber;
	}
	public JTextField getTPhoneNumber() {
		if (tPhoneNumber == null) {
			tPhoneNumber = new JTextField();
			tPhoneNumber.setForeground(Color.WHITE);
			tPhoneNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPhoneNumber.setColumns(10);
			tPhoneNumber.setBorder(BorderFactory.createEmptyBorder());
			tPhoneNumber.setBackground(new Color(28, 28, 28));
			tPhoneNumber.setBounds(804, 250, 170, 20);
		}
		return tPhoneNumber;
	}

	public JDateChooser getTDateOfBirth() {
		if (tDateofBirth == null) {
			tDateofBirth = new JDateChooser();
			tDateofBirth.setForeground(Color.WHITE);
			tDateofBirth.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPhoneNumber.setBorder(BorderFactory.createEmptyBorder());
			tPhoneNumber.setBackground(new Color(28, 28, 28));
			tDateofBirth.setDateFormatString("dd/MM/yyyy");
			tDateofBirth.setBounds(804, 150, 170, 20);
		}
		return tDateofBirth;
	}
	
	public void clean() {
		getTPassport().setText("");
		getTName().setText("");
		getTLastname().setText("");
		getTDateOfBirth().setDate(null);
		getTEmail().setText("");
		getTPhoneNumber().setText("");
	}
	public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(PassengerFrame.class.getResource("/imagesMain/imagesButtons/search-button.png")));
			bSearch.setForeground(Color.WHITE);
			bSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
			bSearch.setFocusable(false);
			bSearch.setBackground(new Color(28, 28, 28));
			bSearch.setBounds(550, 330, 140, 40);
		}
		return bSearch;
	}
	public JButton getBHelp() {
		if (bHelp == null) {
			bHelp = new JButton("");
			bHelp.setIcon(new ImageIcon(PassengerFrame.class.getResource("/imagesMain/imagesButtons/help-button.png")));
			bHelp.setForeground(Color.WHITE);
			bHelp.setFont(new Font("Roboto", Font.PLAIN, 16));
			bHelp.setFocusable(false);
			bHelp.setBackground(new Color(28, 28, 28));
			bHelp.setBounds(934, 10, 40, 40);
		}
		return bHelp;
	}
	public JButton getBReturn() {
		if (bReturn == null) {
			bReturn = new JButton("");
			bReturn.setIcon(new ImageIcon(PassengerFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 10, 40, 40);
		}
		return bReturn;
	}
}