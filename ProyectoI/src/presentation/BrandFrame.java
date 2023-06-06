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

public class BrandFrame extends JFrame {
	//GitHub
    private JPanel panel;
    private JPanel JPInfo;
    private JLabel lTitle;
    private JLabel lBrand;
    private JButton bAddBrand;
    private JButton bUpdate;
    private JButton bClear;
    private JTextField tBrand;

    public BrandFrame() {
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
            JPInfo.setBounds(0, 11, 984, 544);
            JPInfo.setLayout(null);

            // Labels
            JPInfo.add(getLTitle());
            JPInfo.add(getLBrand());

            // Text fields
            JPInfo.add(getTBrand());

            // Buttons
            JPInfo.add(getBAddBrand());
            JPInfo.add(getBUpdate());
            JPInfo.add(getBClear());

            // Table
            JPInfo.add(getJSPTableUsers());
        }
        return JPInfo;
    }

    public JLabel getLTitle() {
        if (lTitle == null) {
            lTitle = new JLabel("Gestión de marcas");
            lTitle.setHorizontalAlignment(SwingConstants.CENTER);
            lTitle.setForeground(Color.WHITE);
            lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
            lTitle.setBounds(320, 10, 350, 50);
        }
        return lTitle;
    }

    public JLabel getLBrand() {
        if (lBrand == null) {
            lBrand = new JLabel("Nombre:");
            lBrand.setForeground(new Color(255, 255, 255));
            lBrand.setFont(new Font("Roboto", Font.PLAIN, 16));
            lBrand.setBounds(366, 142, 100, 20);
        }
        return lBrand;
    }

    public JButton getBAddBrand() {
        if (bAddBrand == null) {
            bAddBrand = new JButton("Agregar");
            bAddBrand.setFont(new Font("Roboto", Font.PLAIN, 16));
            bAddBrand.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesAdminMain/imagesUser/add-user.png")));
            bAddBrand.setBackground(new Color(28, 28, 28));
            bAddBrand.setForeground(new Color(255, 255, 255));
            bAddBrand.setFocusable(false);
            bAddBrand.setBounds(190, 230, 130, 40);
        }
        return bAddBrand;
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

    public JTextField getTBrand() {
        if (tBrand == null) {
            tBrand = new JTextField();
            tBrand.setBackground(new Color(28, 28, 28));
            tBrand.setForeground(new Color(255, 255, 255));
            tBrand.setFont(new Font("Roboto", Font.PLAIN, 16));
			tBrand.setBorder(BorderFactory.createEmptyBorder());
            tBrand.setBounds(436, 142, 150, 20);
            tBrand.setColumns(10);
        }
        return tBrand;
    }

    public JScrollPane getJSPTableUsers() {
        JTable jTableBrands = new JTable();
        jTableBrands.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
        jTableBrands.getTableHeader().setOpaque(false);
        jTableBrands.getTableHeader().setBackground(new Color(32, 136, 203));
        jTableBrands.getTableHeader().setForeground(new Color(255,255,255));
        jTableBrands.setRowHeight(25);
        jTableBrands.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null},
        		{null},
        		{null},
        		{null},
        	},
        	new String[] {
        		"Marcas:"
        	}
        ));

        JScrollPane jScrollPaneUsers = new JScrollPane(jTableBrands);
        jScrollPaneUsers.setBounds(101, 350, 767, 152);
      // for (int i = 0; i < columnModel.getColumnCount(); i++) {
        //    TableColumn column = columnModel.getColumn(i);
         //   column.setHeaderValue(column.getHeaderValue());
      //  }

        return jScrollPaneUsers;
    }
    
    public void clean() {
    	getTBrand().setText("");

    }
}
