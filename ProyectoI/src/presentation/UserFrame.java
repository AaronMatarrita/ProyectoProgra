package presentation;

import java.awt.Color;
import java.awt.Font;

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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UserFrame extends JFrame {
	//GitHub
    private JPanel panel;
    private JPanel JPInfo;
    private JLabel lTitle;
    private JLabel lUser;
    private JLabel lPassword;
    private JLabel lUserType;
    private JLabel lUserStatus;
    private JButton bAddUser;
    private JButton bUpdate;
    private JButton bClear;
    private JTextField tUser;
    private JTextField tPassword;
    private JComboBox<String> cBUserType;
    private JComboBox<String> cBUserStatus;

    public UserFrame() {
    	setType(Type.UTILITY);
        setForeground(new Color(0, 0, 0));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 583);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().add(getPanel());
        setVisible(true); // Mover esta línea al final del constructor
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

            // Labels
            JPInfo.add(getLTitle());
            JPInfo.add(getLUser());
            JPInfo.add(getLPassword());
            JPInfo.add(getLUserType());
            JPInfo.add(getLUserStatus());

            // Text fields
            JPInfo.add(getTUser());
            JPInfo.add(getTPassword());

            // Combo boxes
            JPInfo.add(getCBUserType());
            JPInfo.add(getCBUserStatus());

            // Buttons
            JPInfo.add(getBAddUser());
            JPInfo.add(getBUpdate());
            JPInfo.add(getBClear());

            // Table
            JPInfo.add(getJSPTableUsers());
        }
        return JPInfo;
    }

    public JLabel getLTitle() {
        if (lTitle == null) {
            lTitle = new JLabel("Gestión de usuarios");
            lTitle.setHorizontalAlignment(SwingConstants.CENTER);
            lTitle.setForeground(Color.WHITE);
            lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
            lTitle.setBounds(320, 10, 293, 50);
        }
        return lTitle;
    }

    public JLabel getLUser() {
        if (lUser == null) {
            lUser = new JLabel("Usuario:");
            lUser.setForeground(new Color(255, 255, 255));
            lUser.setFont(new Font("Roboto", Font.PLAIN, 16));
            lUser.setBounds(81, 100, 100, 20);
        }
        return lUser;
    }

    public JLabel getLPassword() {
        if (lPassword == null) {
            lPassword = new JLabel("Contraseña:");
            lPassword.setForeground(Color.WHITE);
            lPassword.setFont(new Font("Roboto", Font.PLAIN, 16));
            lPassword.setBounds(81, 175, 100, 20);
        }
        return lPassword;
    }

    public JLabel getLUserType() {
        if (lUserType == null) {
            lUserType = new JLabel("Tipo de usuario:");
            lUserType.setForeground(Color.WHITE);
            lUserType.setFont(new Font("Roboto", Font.PLAIN, 16));
            lUserType.setBounds(595, 100, 130, 20);
        }
        return lUserType;
    }

    public JLabel getLUserStatus() {
        if (lUserStatus == null) {
            lUserStatus = new JLabel("Estado del usuario:");
            lUserStatus.setForeground(Color.WHITE);
            lUserStatus.setFont(new Font("Roboto", Font.PLAIN, 16));
            lUserStatus.setBounds(595, 175, 135, 20);
        }
        return lUserStatus;
    }

    public JButton getBAddUser() {
        if (bAddUser == null) {
            bAddUser = new JButton("Agregar");
            bAddUser.setFont(new Font("Roboto", Font.PLAIN, 16));
            bAddUser.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesAdminMain/imagesUser/add-user.png")));
            bAddUser.setBackground(new Color(28, 28, 28));
            bAddUser.setForeground(new Color(255, 255, 255));
            bAddUser.setFocusable(false);
            bAddUser.setBounds(190, 230, 130, 40);
        }
        return bAddUser;
    }

    public JButton getBUpdate() {
        if (bUpdate == null) {
            bUpdate = new JButton("Modificar");
            bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
            bUpdate.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesAdminMain/imagesUser/updateUser.png")));
            bUpdate.setBackground(new Color(28, 28, 28));
            bUpdate.setForeground(new Color(255, 255, 255));
            bUpdate.setFocusable(false);
            bUpdate.setBounds(390, 230, 130, 40);
        }
        return bUpdate;
    }

    public JButton getBClear() {
        if (bClear == null) {
            bClear = new JButton("Eliminar");
            bClear.setFont(new Font("Roboto", Font.PLAIN, 16));
            bClear.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesAdminMain/imagesUser/removeUser.png")));
            bClear.setBackground(new Color(28, 28, 28));
            bClear.setForeground(new Color(255, 255, 255));
            bClear.setFocusable(false);
            bClear.setBounds(590, 230, 130, 40);
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
            tUser.setBounds(171, 100, 150, 20);
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
            tPassword.setBounds(171, 175, 150, 20);
        }
        return tPassword;
    }

    public JComboBox<String> getCBUserType() {
        if (cBUserType == null) {
        	String arrayCBUserType[] = {"Indefinido", "Administrador", "Colaborador"};
        	cBUserType = new JComboBox(arrayCBUserType);
            cBUserType.setBackground(new Color(28, 28, 28));
            cBUserType.setForeground(new Color(255, 255, 255));
            cBUserType.setFont(new Font("Roboto", Font.PLAIN, 16));
            cBUserType.setBorder(BorderFactory.createEmptyBorder());
            cBUserType.setBounds(735, 101, 150, 22);
        }
        return cBUserType;
    }

    public JComboBox<String> getCBUserStatus() {
        if (cBUserStatus == null) {
        	String arrayCBUserStatus[] = {"Indefinido", "Activo", "Inactivo"};
            cBUserStatus = new JComboBox(arrayCBUserStatus);
            cBUserStatus.setBackground(new Color(28, 28, 28));
            cBUserStatus.setForeground(new Color(255, 255, 255));
            cBUserStatus.setFont(new Font("Roboto", Font.PLAIN, 16));
            cBUserStatus.setBorder(BorderFactory.createEmptyBorder());
            cBUserStatus.setBounds(735, 176, 150, 22);
        }
        return cBUserStatus;
    }

    public JScrollPane getJSPTableUsers() {
        JTable jTableUsers = new JTable();
        jTableUsers.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
        jTableUsers.getTableHeader().setOpaque(false);
        jTableUsers.getTableHeader().setBackground(new Color(32, 136, 203));
        jTableUsers.getTableHeader().setForeground(new Color(255,255,255));
        jTableUsers.setRowHeight(25);
        jTableUsers.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
            },
            new String[] {
                "Usuario", "Contraseña", "Tipo de usuario", "Estado del usuario"
            }
        ));

        JScrollPane jScrollPaneUsers = new JScrollPane(jTableUsers);
        jScrollPaneUsers.setBounds(10, 350, 964, 125);

        TableColumnModel columnModel = jTableUsers.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setHeaderValue(column.getHeaderValue());
        }

        return jScrollPaneUsers;
    }
    
    public void clean() {
    	getTUser().setText("");
    	getTPassword().setText("");
    }
}