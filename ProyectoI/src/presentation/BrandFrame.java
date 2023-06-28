package presentation;

import java.awt.Color;
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

import domain.Brand;
@SuppressWarnings("serial")
public class BrandFrame extends JFrame {
	private String userType;
	//Paneles
	private JPanel panel;
	private JPanel JPInfo;
	//Etiquetas
	private JLabel lTitle;
	private JLabel lBrand;
	//Botones
	private JButton bAddBrand;
	private JButton bUpdate;
	private JButton bClear;
	//Campos de texto
	private JTextField tBrand;
	// Tabla
	private DefaultTableModel dtmTBrands;
	private JTable jTableBrands;
	// Scroll
	private JScrollPane spTBrands;
	private Object dataTable[][];
	private JButton bSearch;
	private JButton bHelp;
	private JButton bReturn;

    public BrandFrame(String userType) {
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
            JPInfo.add(getLBrand());

            //JTextFields
            JPInfo.add(getTBrand());

            //JButtons
            JPInfo.add(getBAddBrand());
            JPInfo.add(getBUpdate());
            JPInfo.add(getBClear());

            if(userType.equals("2"))//Usuario de tipo *Colaborador* 
			{
				bClear.setVisible(false);
				bUpdate.setVisible(false);
			}
            
            // JTable
            setDTMBrands(dataTable, getColumnsName());
            setJTableBrands(getDTMBrands());
            setSPTableBrands(getJTableBrands());
            JPInfo.add(getSPTableBrands());
            JPInfo.add(getBSearch());
            JPInfo.add(getBHelp());
            JPInfo.add(getBReturn());
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
            lBrand.setBounds(320, 200, 150, 20);
        }
        return lBrand;
    }

    public JButton getBAddBrand() {
        if (bAddBrand == null) {
            bAddBrand = new JButton("Agregar");
            bAddBrand.setFont(new Font("Roboto", Font.PLAIN, 16));
            bAddBrand.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
            bAddBrand.setBackground(new Color(28, 28, 28));
            bAddBrand.setForeground(new Color(255, 255, 255));
            bAddBrand.setFocusable(false);
            bAddBrand.setBounds(350, 330, 140, 40);
        }
        return bAddBrand;
    }

    public JButton getBUpdate() {
        if (bUpdate == null) {
            bUpdate = new JButton("Modificar");
            bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
            bUpdate.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/update-button.png")));
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
            bClear.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/delete-button.png")));
            bClear.setBackground(new Color(28, 28, 28));
            bClear.setForeground(new Color(255, 255, 255));
            bClear.setFocusable(false);
            bClear.setBounds(750, 330, 140, 40);
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
            tBrand.setBounds(480, 200, 150, 20);
            tBrand.setColumns(10);
        }
        return tBrand;
    }

    public void setDTMBrands(Object data[][], String[] columnsName) {
		dtmTBrands = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMBrands() {
		return dtmTBrands;
	}

	public void setJTableBrands(DefaultTableModel dtmTBrands) {
		jTableBrands = new JTable(dtmTBrands);
		jTableBrands.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableBrands.getTableHeader().setOpaque(false);
		jTableBrands.getTableHeader().setBackground(new Color(28, 28, 28));
		jTableBrands.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableBrands.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableBrands.setEnabled(false);
		// No poder mover las columnas
		jTableBrands.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableBrands.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTableBrands() {
		return jTableBrands;
	}

	public void setSPTableBrands(JTable jTableBrands) {
		spTBrands = new JScrollPane(jTableBrands);
		spTBrands.setBounds(10, 400, 964, 133);
	}

	public JScrollPane getSPTableBrands() {
		return spTBrands;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Marcas"};
		return columnsName;
	}
	
	public void setJTableData(List<Brand> brands) {
	    Object[][] data = new Object[brands.size()][1];
	    for (int i = 0; i < brands.size(); i++) {
	    	Brand brand = brands.get(i);
	        data[i][0] = brand.getBrand();
	    }
	    dtmTBrands.setDataVector(data, getColumnsName());
	}
    
    public void clean() {
    	getTBrand().setText("");
    }
	public JButton getBSearch() {
		if (bSearch == null) {
			bSearch = new JButton("Consultar");
			bSearch.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/search-button.png")));
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
			bHelp.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/help-button.png")));
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
			bReturn.setIcon(new ImageIcon(BrandFrame.class.getResource("/imagesMain/imagesButtons/return.png")));
			bReturn.setForeground(Color.WHITE);
			bReturn.setFont(new Font("Roboto", Font.PLAIN, 16));
			bReturn.setFocusable(false);
			bReturn.setBackground(new Color(28, 28, 28));
			bReturn.setBounds(10, 10, 40, 40);
		}
		return bReturn;
	}
}
