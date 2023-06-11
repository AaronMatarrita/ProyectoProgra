package presentation;

import java.awt.Color;
import java.awt.Component;
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

import domain.Passenger;

@SuppressWarnings("serial")
public class PassengerFrame extends JFrame {
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
	private JTextField tDateOfBirth;
	private JLabel lEmain;
	private JTextField tEmail;
	private JLabel lPhoneNumber;
	private JTextField tPhoneNumber;

	public PassengerFrame() {
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

			// JTable
			setDTMPassengers(dataTable, getColumnsName());
			setJTablePassengers(getDTMPassengers());
			setSPTablePassengers(getJTablePassengers());
			JPInfo.add(getSPTablePassengers());
			JPInfo.add(getTLastname());
			JPInfo.add(getTDateOfBirth());
			JPInfo.add(getLEmain());
			JPInfo.add(getTEmail());
			JPInfo.add(getLPhoneNumber());
			JPInfo.add(getTPhoneNumber());

		}
		return JPInfo;
	}

	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de pasajeros");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(371, 11, 293, 50);
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
			lPassport.setBounds(10, 120, 170, 20);
		}
		return lPassport;
	}

	public JLabel getLName() {
		if (lName == null) {
			lName = new JLabel("Nombre:");
			lName.setForeground(Color.WHITE);
			lName.setFont(new Font("Roboto", Font.PLAIN, 16));
			lName.setBounds(10, 151, 100, 20);
		}
		return lName;
	}

	public JLabel getLLastname() {
		if (lLastname == null) {
			lLastname = new JLabel("Apellidos:");
			lLastname.setForeground(Color.WHITE);
			lLastname.setFont(new Font("Roboto", Font.PLAIN, 16));
			lLastname.setBounds(10, 182, 130, 20);
		}
		return lLastname;
	}

	public JLabel getLDateOfBirth() {
		if (lDateOfBirth == null) {
			lDateOfBirth = new JLabel("Fecha de nacimiento:");
			lDateOfBirth.setForeground(Color.WHITE);
			lDateOfBirth.setFont(new Font("Roboto", Font.PLAIN, 16));
			lDateOfBirth.setBounds(624, 120, 170, 20);
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
			bAddPassenger.setBounds(190, 260, 130, 40);
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
			bUpdate.setBounds(410, 260, 130, 40);
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
			bClear.setBounds(624, 260, 130, 40);
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
			tPassport.setBounds(190, 120, 170, 20);
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
			tName.setBounds(191, 150, 170, 20);
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
		jTablePassengers.getTableHeader().setBackground(new Color(32, 136, 203));
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
		spTPassengers.setBounds(10, 350, 964, 183);
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
			tLastname.setBounds(191, 183, 170, 20);
		}
		return tLastname;
	}
	public JTextField getTDateOfBirth() {
		if (tDateOfBirth == null) {
			tDateOfBirth = new JTextField();
			tDateOfBirth.setForeground(Color.WHITE);
			tDateOfBirth.setFont(new Font("Roboto", Font.PLAIN, 16));
			tDateOfBirth.setColumns(10);
			tDateOfBirth.setBorder(BorderFactory.createEmptyBorder());
			tDateOfBirth.setBackground(new Color(28, 28, 28));
			tDateOfBirth.setBounds(804, 121, 170, 20);
		}
		return tDateOfBirth;
	}
	public JLabel getLEmain() {
		if (lEmain == null) {
			lEmain = new JLabel("Correo electrónico:");
			lEmain.setForeground(Color.WHITE);
			lEmain.setFont(new Font("Roboto", Font.PLAIN, 16));
			lEmain.setBounds(624, 151, 170, 20);
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
			tEmail.setBounds(804, 152, 170, 20);
		}
		return tEmail;
	}
	public JLabel getLPhoneNumber() {
		if (lPhoneNumber == null) {
			lPhoneNumber = new JLabel("Teléfono:");
			lPhoneNumber.setForeground(Color.WHITE);
			lPhoneNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPhoneNumber.setBounds(624, 181, 170, 20);
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
			tPhoneNumber.setBounds(804, 182, 170, 20);
		}
		return tPhoneNumber;
	}

	public void clean() {
		getTPassport().setText("");
		getTName().setText("");
		getTLastname().setText("");
		getTDateOfBirth().setText("");
		getTEmail().setText("");
		getTPhoneNumber().setText("");
	}
}