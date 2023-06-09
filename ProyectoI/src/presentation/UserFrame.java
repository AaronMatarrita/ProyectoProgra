package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import domain.User;

@SuppressWarnings("serial")
public class UserFrame extends JFrame {
	private String userType;
	// Etiquetas
	private JPanel panel;
	private JPanel JPInfo;
	private JLabel lTitle;
	private JLabel lUser;
	private JLabel lPassword;
	private JLabel lUserType;
	private JLabel lUserStatus;
	// Botones
	private JButton bAddUser;
	private JButton bUpdate;
	private JButton bClear;
	// Campos de texto
	private JTextField tUser;
	private JTextField tPassword;
	// ComboBoxes
	private JComboBox<String> cBUserType;
	private JComboBox<String> cBUserStatus;
	// Tabla
	private DefaultTableModel dtmTUsers;
	private JTable jTableUsers;
	// Scroll
	private JScrollPane spTUsers;
	private Object dataTable[][];
	private JButton bSearch;
	private JButton bHelp;
	private JButton bReturn;

	public UserFrame(String userType) {

		closeProgram();
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserFrame.class.getResource("/imagesLogin/AeroJetLogo.png")));
		setTitle("Gestión de Usuarios");
		this.userType = userType;
		setForeground(new Color(0, 0, 0));
		setResizable(false);
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

			// JLabels
			JPInfo.add(getLTitle());
			JPInfo.add(getLUser());
			JPInfo.add(getLPassword());
			JPInfo.add(getLUserType());
			JPInfo.add(getLUserStatus());

			// JTextFields
			JPInfo.add(getTUser());
			JPInfo.add(getTPassword());

			// JComboBoxes
			JPInfo.add(getCBUserType());
			JPInfo.add(getCBUserStatus());

			// JButtons
			JPInfo.add(getBAddUser());
			JPInfo.add(getBUpdate());
			JPInfo.add(getBClear());

			if(userType.equals("2"))//Usuario de tipo *Colaborador* 
			{
				bClear.setVisible(false);
				bUpdate.setVisible(false);
			}
			
			// JTable
			setDTMUsers(dataTable, getColumnsName());
	        setJTableUsers(getDTMUsers());
	        setSPTableUsers(getJTableUsers());
	        JPInfo.add(getSPTableUsers());
	        JPInfo.add(getBSearch());
	        JPInfo.add(getBHelp());
	        JPInfo.add(getBReturn());
			
		}
		return JPInfo;
	}

	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de usuarios");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(320, 10, 350, 50);
		}
		return lTitle;
	}

	public JLabel getLUser() {
		if (lUser == null) {
			lUser = new JLabel("Usuario:");
			lUser.setForeground(new Color(255, 255, 255));
			lUser.setFont(new Font("Roboto", Font.PLAIN, 16));
			lUser.setBounds(10, 130, 150, 20);
		}
		return lUser;
	}

	public JLabel getLPassword() {
		if (lPassword == null) {
			lPassword = new JLabel("Contraseña:");
			lPassword.setForeground(Color.WHITE);
			lPassword.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPassword.setBounds(10, 230, 150, 20);
		}
		return lPassword;
	}

	public JLabel getLUserType() {
		if (lUserType == null) {
			lUserType = new JLabel("Tipo de usuario:");
			lUserType.setForeground(Color.WHITE);
			lUserType.setFont(new Font("Roboto", Font.PLAIN, 16));
			lUserType.setBounds(664, 130, 150, 20);
		}
		return lUserType;
	}

	public JLabel getLUserStatus() {
		if (lUserStatus == null) {
			lUserStatus = new JLabel("Estado del usuario:");
			lUserStatus.setForeground(Color.WHITE);
			lUserStatus.setFont(new Font("Roboto", Font.PLAIN, 16));
			lUserStatus.setBounds(664, 230, 150, 20);
		}
		return lUserStatus;
	}

	public JButton getBAddUser() {
		if (bAddUser == null) {
			bAddUser = new JButton("Agregar");
			bAddUser.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAddUser.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/add-user.png")));
			bAddUser.setBackground(new Color(28, 28, 28));
			bAddUser.setForeground(new Color(255, 255, 255));
			bAddUser.setFocusable(false);
			bAddUser.setBounds(350, 330, 140, 40);
		}
		return bAddUser;
	}

	public JButton getBUpdate() {
		if (bUpdate == null) {
			bUpdate = new JButton("Modificar");
			bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
			bUpdate.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/updateUser.png")));
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
			bClear.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/removeUser.png")));
			bClear.setBackground(new Color(28, 28, 28));
			bClear.setForeground(new Color(255, 255, 255));
			bClear.setFocusable(false);
			bClear.setBounds(750, 330, 140, 40);
		}
		return bClear;
	}

	public JTextField getTUser() {
		if (tUser == null) {
			tUser = new JTextField();
			tUser.setBackground(new Color(28, 28, 28));
			tUser.setForeground(new Color(255, 255, 255));
			tUser.setFont(new Font("Roboto", Font.PLAIN, 16));
			tUser.setBorder(BorderFactory.createEmptyBorder());
			tUser.setBounds(170, 130, 150, 20);
			tUser.setColumns(10);
		}
		return tUser;
	}

	public JTextField getTPassword() {
		if (tPassword == null) {
			tPassword = new JTextField();
			tPassword.setBackground(new Color(28, 28, 28));
			tPassword.setForeground(new Color(255, 255, 255));
			tPassword.setFont(new Font("Roboto", Font.PLAIN, 16));
			tPassword.setBorder(BorderFactory.createEmptyBorder());
			tPassword.setColumns(10);
			tPassword.setBounds(170, 230, 150, 20);
		}
		return tPassword;
	}

	public JComboBox<String> getCBUserType() {
		if (cBUserType == null) {
			String arrayCBUserType[] = {"Indefinido", "Administrador", "Colaborador"};
			cBUserType = new JComboBox<String>();
			for(int i = 0; i < arrayCBUserType.length; i++) {
				cBUserType.addItem(arrayCBUserType[i]);
			}
			cBUserType.setBackground(new Color(28, 28, 28));
			cBUserType.setForeground(new Color(255, 255, 255));
			cBUserType.setFont(new Font("Roboto", Font.PLAIN, 16));
			cBUserType.setBorder(BorderFactory.createEmptyBorder());
			cBUserType.setBounds(824, 130, 150, 22);
		}
		return cBUserType;
	}

	public JComboBox<String> getCBUserStatus() {
		if (cBUserStatus == null) {
			String arrayCBUserStatus[] = {"Indefinido", "Activo", "Inactivo"};
			cBUserStatus = new JComboBox<String>();
			for(int i = 0; i < arrayCBUserStatus.length; i++) {
				cBUserStatus.addItem(arrayCBUserStatus[i]);
			}
			cBUserStatus.setBackground(new Color(28, 28, 28));
			cBUserStatus.setForeground(new Color(255, 255, 255));
			cBUserStatus.setFont(new Font("Roboto", Font.PLAIN, 16));
			cBUserStatus.setBorder(BorderFactory.createEmptyBorder());
			cBUserStatus.setBounds(824, 230, 150, 22);
		}
		return cBUserStatus;
	}

	public void setDTMUsers(Object data[][], String[] columnsName) {
		dtmTUsers = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMUsers() {
		return dtmTUsers;
	}

	public void setJTableUsers(DefaultTableModel dtmTUsers) {
		jTableUsers = new JTable(dtmTUsers);
		jTableUsers.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableUsers.getTableHeader().setOpaque(false);
		jTableUsers.getTableHeader().setBackground(new Color(28, 28, 28));
		jTableUsers.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableUsers.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableUsers.setEnabled(false);
		// No poder mover las columnas
		jTableUsers.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableUsers.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTableUsers() {
		return jTableUsers;
	}

	public void setSPTableUsers(JTable jTableUsers) {
		spTUsers = new JScrollPane(jTableUsers);
		spTUsers.setBounds(10, 400, 964, 125);
	}

	public JScrollPane getSPTableUsers() {
		return spTUsers;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Usuario", "Contraseña", "Tipo de usuario", "Estado del usuario"};
		return columnsName;
	}
	
	public void setJTableData(List<User> users) {
	    Object[][] data = new Object[users.size()][4];
	    for (int i = 0; i < users.size(); i++) {
	        User user = users.get(i);
	        String userType = (user.getUserType() == 1) ? "Administrador" : "Colaborador";
			String userStatus = (user.isStatus()) ? "Activo" : "Inactivo";
	        data[i][0] = user.getUser();
	        data[i][1] = user.getPassword();
	        data[i][2] = userType;
	        data[i][3] = userStatus;
	    }
	    dtmTUsers.setDataVector(data, getColumnsName());
	}

	public void clean() {
		getTUser().setText("");
		getTPassword().setText("");
		getCBUserType().setSelectedItem("Indefinido");
		getCBUserStatus().setSelectedItem("Indefinido");
	}
	public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/SearchUser.png")));
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
			bHelp.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesButtons/help-button.png")));
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
			bReturn.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 10, 40, 40);
		}
		return bReturn;
	}
}