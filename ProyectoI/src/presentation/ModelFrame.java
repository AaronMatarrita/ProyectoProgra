package presentation;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
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

import domain.AirplaneModel;

@SuppressWarnings("serial")
public class ModelFrame extends JFrame {
	//Paneles
	private JPanel panel;
	private JPanel JPInfo;
	//Etiquetas
	private JLabel lTitle;
	private JLabel lModel;
	private JLabel lCEjecutive;
	private JLabel lBrand;
	private JLabel lCTurist;
	private JLabel lCEco;
	//Butones
	private JButton bAddModel;
	private JButton bUpdate;
	private JButton bClear;
	//Campos de texto
	private JTextField tModel;
	private JTextField tCEjecutive;
	private JTextField tCTurist;
	private JTextField tCEco;
	//ComboBoxes
	private JComboBox<String> cBrands;
	// Tabla
	private DefaultTableModel dtmTAirplaneModels;
	private JTable jTableAirplaneModels;
	// Scroll
	private JScrollPane spTAirplaneModels;
	private Object dataTable[][];
	
	
	public ModelFrame() {
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

			//JLabels
			JPInfo.add(getLTitle());
			JPInfo.add(getLModel());
			JPInfo.add(getLCEjecutive());
			JPInfo.add(getLBrand());

			//JTextFields
			JPInfo.add(getTModel());
			JPInfo.add(getTCEjecutive());
			JPInfo.add(getLCTurist());
			JPInfo.add(getTCTurist());
			JPInfo.add(getLCEco());
			JPInfo.add(getTCEco());

			//JComboBoxes
			JPInfo.add(getCBrands());

			//JButtons
			JPInfo.add(getBAddModel());
			JPInfo.add(getBUpdate());
			JPInfo.add(getBClear());

			// JTable
			setDTMAirplaneModels(dataTable, getColumnsName());
			setJTableAirplaneModels(getDTMAirplaneModels());
			setSPTableAirplaneModels(getJTableAirplaneModels());
			JPInfo.add(getSPTableAirplaneModels());
		}
		return JPInfo;
	}

	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de modelos");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(320, 10, 350, 50);
		}
		return lTitle;
	}

	public JLabel getLModel() {
		if (lModel == null) {
			lModel = new JLabel("Modelo:");
			lModel.setForeground(new Color(255, 255, 255));
			lModel.setFont(new Font("Roboto", Font.PLAIN, 16));
			lModel.setBounds(10, 100, 150, 20);
		}
		return lModel;
	}

	public JLabel getLCEjecutive() {
		if (lCEjecutive == null) {
			lCEjecutive = new JLabel("Asientos Ejecutivos:");
			lCEjecutive.setForeground(Color.WHITE);
			lCEjecutive.setFont(new Font("Roboto", Font.PLAIN, 16));
			lCEjecutive.setBounds(10, 150, 150, 20);
		}
		return lCEjecutive;
	}

	public JLabel getLBrand() {
		if (lBrand == null) {
			lBrand = new JLabel("Marca del Avion:");
			lBrand.setForeground(Color.WHITE);
			lBrand.setFont(new Font("Roboto", Font.PLAIN, 16));
			lBrand.setBounds(654, 100, 130, 20);
		}
		return lBrand;
	}

	public JLabel getLCTurist() {
		if (lCTurist == null) {
			lCTurist = new JLabel("Asientos Turistas:");
			lCTurist.setForeground(Color.WHITE);
			lCTurist.setFont(new Font("Roboto", Font.PLAIN, 16));
			lCTurist.setBounds(10, 200, 150, 20);
		}
		return lCTurist;
	}

	public JLabel getLCEco() {
		if (lCEco == null) {
			lCEco = new JLabel("Asientos Economicos:");
			lCEco.setForeground(Color.WHITE);
			lCEco.setFont(new Font("Roboto", Font.PLAIN, 16));
			lCEco.setBounds(654, 150, 160, 20);
		}
		return lCEco;
	}

	public JButton getBAddModel() {
		if (bAddModel == null) {
			bAddModel = new JButton("Agregar");
			bAddModel.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAddModel.setIcon(new ImageIcon(ModelFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
			bAddModel.setBackground(new Color(28, 28, 28));
			bAddModel.setForeground(new Color(255, 255, 255));
			bAddModel.setFocusable(false);
			bAddModel.setBounds(240, 330, 130, 40);
		}
		return bAddModel;
	}

	public JButton getBUpdate() {
		if (bUpdate == null) {
			bUpdate = new JButton("Modificar");
			bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
			bUpdate.setIcon(new ImageIcon(ModelFrame.class.getResource("/imagesMain/imagesButtons/update-button.png")));
			bUpdate.setBackground(new Color(28, 28, 28));
			bUpdate.setForeground(new Color(255, 255, 255));
			bUpdate.setFocusable(false);
			bUpdate.setBounds(440, 330, 130, 40);
		}
		return bUpdate;
	}

	public JButton getBClear() {
		if (bClear == null) {
			bClear = new JButton("Eliminar");
			bClear.setFont(new Font("Roboto", Font.PLAIN, 16));
			bClear.setIcon(new ImageIcon(ModelFrame.class.getResource("/imagesMain/imagesButtons/delete-button.png")));
			bClear.setBackground(new Color(28, 28, 28));
			bClear.setForeground(new Color(255, 255, 255));
			bClear.setFocusable(false);
			bClear.setBounds(640, 330, 130, 40);
		}
		return bClear;
	}

	public JTextField getTModel() {
		if (tModel == null) {
			tModel = new JTextField();
			tModel.setBackground(new Color(28, 28, 28));
			tModel.setForeground(new Color(255, 255, 255));
			tModel.setFont(new Font("Roboto", Font.PLAIN, 16));
			tModel.setBorder(BorderFactory.createEmptyBorder());
			tModel.setBounds(170, 100, 150, 20);
			tModel.setColumns(10);
		}
		return tModel;
	}

	public JTextField getTCEjecutive() {
		if (tCEjecutive == null) {
			tCEjecutive = new JTextField();
			tCEjecutive.setBackground(new Color(28, 28, 28));
			tCEjecutive.setForeground(new Color(255, 255, 255));
			tCEjecutive.setFont(new Font("Roboto", Font.PLAIN, 16));
			tCEjecutive.setBorder(BorderFactory.createEmptyBorder());
			tCEjecutive.setColumns(10);
			tCEjecutive.setBounds(170, 150, 150, 20);
		}
		return tCEjecutive;
	}


	public JTextField getTCTurist() {
		if (tCTurist == null) {
			tCTurist = new JTextField();
			tCTurist.setForeground(Color.WHITE);
			tCTurist.setFont(new Font("Roboto", Font.PLAIN, 16));
			tCTurist.setColumns(10);
			tCTurist.setBorder(BorderFactory.createEmptyBorder());
			tCTurist.setBackground(new Color(28, 28, 28));
			tCTurist.setBounds(170, 200, 150, 20);
		}
		return tCTurist;
	}

	public JTextField getTCEco() {
		if (tCEco == null) {
			tCEco = new JTextField();
			tCEco.setForeground(Color.WHITE);
			tCEco.setFont(new Font("Roboto", Font.PLAIN, 16));
			tCEco.setColumns(10);
			tCEco.setBorder(BorderFactory.createEmptyBorder());
			tCEco.setBackground(new Color(28, 28, 28));
			tCEco.setBounds(824, 150, 150, 20);
		}
		return tCEco;
	}
	
	
	public JComboBox<String> getCBrands() {
		if (cBrands == null) {
			cBrands = new JComboBox<String>();
			cBrands.setBackground(new Color(28, 28, 28));
			cBrands.setForeground(new Color(255, 255, 255));
			cBrands.setFont(new Font("Roboto", Font.PLAIN, 16));
			cBrands.setBorder(BorderFactory.createEmptyBorder());
			cBrands.setBounds(824, 100, 150, 20);
		}
		return cBrands;
	}

	public void fillBrandComboBox(ArrayList<String> brands) {
		cBrands.addItem("Indefinido");
	    for (String brand : brands) {
	        cBrands.addItem(brand);
	    }
	}
	
	public void setDTMAirplaneModels(Object data[][], String[] columnsName) {
		dtmTAirplaneModels = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMAirplaneModels() {
		return dtmTAirplaneModels;
	}

	public void setJTableAirplaneModels(DefaultTableModel dtmTAirplaneModels) {
		jTableAirplaneModels = new JTable(dtmTAirplaneModels);
		jTableAirplaneModels.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableAirplaneModels.getTableHeader().setOpaque(false);
		jTableAirplaneModels.getTableHeader().setBackground(new Color(32, 136, 203));
		jTableAirplaneModels.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableAirplaneModels.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableAirplaneModels.setEnabled(false);
		// No poder mover las columnas
		jTableAirplaneModels.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableAirplaneModels.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTableAirplaneModels() {
		return jTableAirplaneModels;
	}

	public void setSPTableAirplaneModels(JTable jTableAirplaneModels) {
		spTAirplaneModels = new JScrollPane(jTableAirplaneModels);
		spTAirplaneModels.setBounds(10, 400, 964, 133);
	}

	public JScrollPane getSPTableAirplaneModels() {
		return spTAirplaneModels;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Modelo", "Marca", "Asientos Ejecutivos", "Asientos Turista", "Asientos Económicos"};
		return columnsName;
	}

	public void setJTableData(List<AirplaneModel> airplaneModels) {
	    Object[][] data = new Object[airplaneModels.size()][5];
	    for (int i = 0; i < airplaneModels.size(); i++) {
	        AirplaneModel airplaneModel = airplaneModels.get(i);

	        data[i][0] = airplaneModel.getName();
	        data[i][1] = airplaneModel.getBrand();
	        data[i][2] = airplaneModel.getBusinessClassSeats();
	        data[i][3] = airplaneModel.getTouristClassSeats();
	        data[i][4] = airplaneModel.getEconomyClassSeats();
	    }
	    dtmTAirplaneModels.setDataVector(data, getColumnsName());
	}

	public void clean() {
		getTModel().setText("");
		getTCEjecutive().setText("");
		getTCEco().setText("");
		getTCTurist().setText("");
		getCBrands().setSelectedItem("Indefinido");
	}
}