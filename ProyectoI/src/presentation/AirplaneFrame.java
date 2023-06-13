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
import domain.Airplane;

@SuppressWarnings("serial")
public class AirplaneFrame extends JFrame {
	// Etiquetas
	private JPanel panel;
	private JPanel JPInfo;
	private JLabel lTitle;
	private JLabel lId;
	private JLabel lYear;
	private JLabel lAirline;
	private JLabel lModel;
	// Botones
	private JButton bAddAirplane;
	private JButton bUpdate;
	private JButton bClear;
	// Campos de texto
	private JTextField tId;
	private JTextField tYear;
	// ComboBoxes
	private JComboBox<String> cBAirline;
	private JComboBox<String> cBModel;
	// Tabla
	private DefaultTableModel dtmTAirplanes;
	private JTable jTableAirplanes;
	// Scroll
	private JScrollPane spTAirplanes;
	private Object dataTable[][];

	public AirplaneFrame() {
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
			JPInfo.add(getLId());
			JPInfo.add(getLYear());
			JPInfo.add(getLAirline());
			JPInfo.add(getLModel());

			// JTextFields
			JPInfo.add(getTId());
			JPInfo.add(getTYear());

			// JComboBoxes
			JPInfo.add(getCBAirline());
			JPInfo.add(getCBModel());

			// JButtons
			JPInfo.add(getBAddAirplane());
			JPInfo.add(getBUpdate());
			JPInfo.add(getBClear());

			// JTable
			setDTMAirplanes(dataTable, getColumnsName());
	        setJTableAirplanes(getDTMAirplanes());
	        setSPTableAirplanes(getJTableAirplanes());
	        JPInfo.add(getSPTableAirplanes());
			
		}
		return JPInfo;
	}

	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de Aviones");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(320, 10, 293, 50);
		}
		return lTitle;
	}

	public JLabel getLId() {
		if (lId == null) {
			lId = new JLabel("Matricula:");
			lId.setForeground(new Color(255, 255, 255));
			lId.setFont(new Font("Roboto", Font.PLAIN, 16));
			lId.setBounds(10, 129, 150, 20);
		}
		return lId;
	}

	public JLabel getLYear() {
		if (lYear == null) {
			lYear = new JLabel("Año:");
			lYear.setForeground(Color.WHITE);
			lYear.setFont(new Font("Roboto", Font.PLAIN, 16));
			lYear.setBounds(10, 229, 150, 20);
		}
		return lYear;
	}

	public JLabel getLAirline() {
		if (lAirline == null) {
			lAirline = new JLabel("Aerolinea:");
			lAirline.setForeground(Color.WHITE);
			lAirline.setFont(new Font("Roboto", Font.PLAIN, 16));
			lAirline.setBounds(664, 129, 150, 20);
		}
		return lAirline;
	}

	public JLabel getLModel() {
		if (lModel == null) {
			lModel = new JLabel("Modelo:");
			lModel.setForeground(Color.WHITE);
			lModel.setFont(new Font("Roboto", Font.PLAIN, 16));
			lModel.setBounds(664, 229, 150, 20);
		}
		return lModel;
	}

	public JButton getBAddAirplane() {
		if (bAddAirplane == null) {
			bAddAirplane = new JButton("Agregar");
			bAddAirplane.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAddAirplane.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/add-user.png")));
			bAddAirplane.setBackground(new Color(28, 28, 28));
			bAddAirplane.setForeground(new Color(255, 255, 255));
			bAddAirplane.setFocusable(false);
			bAddAirplane.setBounds(240, 330, 130, 40);
		}
		return bAddAirplane;
	}

	public JButton getBUpdate() {
		if (bUpdate == null) {
			bUpdate = new JButton("Modificar");
			bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
			bUpdate.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/updateUser.png")));
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
			bClear.setIcon(new ImageIcon(UserFrame.class.getResource("/imagesMain/imagesUser/removeUser.png")));
			bClear.setBackground(new Color(28, 28, 28));
			bClear.setForeground(new Color(255, 255, 255));
			bClear.setFocusable(false);
			bClear.setBounds(640, 330, 130, 40);
		}
		return bClear;
	}

	public JTextField getTId() {
		if (tId == null) {
			tId = new JTextField();
			tId.setBackground(new Color(28, 28, 28));
			tId.setForeground(new Color(255, 255, 255));
			tId.setFont(new Font("Roboto", Font.PLAIN, 16));
			tId.setBorder(BorderFactory.createEmptyBorder());
			tId.setBounds(170, 129, 150, 20);
			tId.setColumns(10);
		}
		return tId;
	}

	public JTextField getTYear() {
		if (tYear == null) {
			tYear = new JTextField();
			tYear.setBackground(new Color(28, 28, 28));
			tYear.setForeground(new Color(255, 255, 255));
			tYear.setFont(new Font("Roboto", Font.PLAIN, 16));
			tYear.setBorder(BorderFactory.createEmptyBorder());
			tYear.setColumns(10);
			tYear.setBounds(170, 229, 150, 20);
		}
		return tYear;
	}

	public JComboBox<String> getCBAirline() {
		if (cBAirline == null) {
			cBAirline = new JComboBox<String>();
			cBAirline.setBackground(new Color(28, 28, 28));
			cBAirline.setForeground(new Color(255, 255, 255));
			cBAirline.setFont(new Font("Roboto", Font.PLAIN, 16));
			cBAirline.setBorder(BorderFactory.createEmptyBorder());
			cBAirline.setBounds(824, 129, 150, 20);
		}
		return cBAirline;
	}

	
	public void fillAirlineComboBox(ArrayList<String> airlines) {
		cBAirline.addItem("Indefinido");
		for(String airline : airlines) {
			cBAirline.addItem(airline);
		}
	}
	
	public JComboBox<String> getCBModel() {
		if (cBModel == null) {
			cBModel = new JComboBox<String>();
			cBModel.setBackground(new Color(28, 28, 28));
			cBModel.setForeground(new Color(255, 255, 255));
			cBModel.setFont(new Font("Roboto", Font.PLAIN, 16));
			cBModel.setBorder(BorderFactory.createEmptyBorder());
			cBModel.setBounds(824, 229, 150, 20);
		}
		return cBModel;
	}

	public void fillModelComboBox(ArrayList<String> models) {
		cBModel.addItem("Indefinido");
		for(String model : models) {
			cBModel.addItem(model);
		}
	}
	
	public void setDTMAirplanes(Object data[][], String[] columnsName) {
		dtmTAirplanes = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMAirplanes() {
		return dtmTAirplanes;
	}

	public void setJTableAirplanes(DefaultTableModel dtmTAirplanes) {
		jTableAirplanes = new JTable(dtmTAirplanes);
		jTableAirplanes.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableAirplanes.getTableHeader().setOpaque(false);
		jTableAirplanes.getTableHeader().setBackground(new Color(32, 136, 203));
		jTableAirplanes.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableAirplanes.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableAirplanes.setEnabled(false);
		// No poder mover las columnas
		jTableAirplanes.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableAirplanes.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTableAirplanes() {
		return jTableAirplanes;
	}

	public void setSPTableAirplanes(JTable jTableAirplanes) {
		spTAirplanes = new JScrollPane(jTableAirplanes);
		spTAirplanes.setBounds(10, 400, 964, 133);
	}

	public JScrollPane getSPTableAirplanes() {
		return spTAirplanes;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Matricula", "Año", "Aerolinea", "Modelo"};
		return columnsName;
	}
	
	public void setJTableData(List<Airplane> Airplanes) {
	    Object[][] data = new Object[Airplanes.size()][4];
	    for (int i = 0; i < Airplanes.size(); i++) {
	    	Airplane airplane = Airplanes.get(i);
	        String airline = airplane.getAirline();
			String model = airplane.getAirplaneModel();
	        data[i][0] = airplane.getId();
	        data[i][1] = airplane.getYear();
	        data[i][2] = airline;
	        data[i][3] = model;
	    }
	    dtmTAirplanes.setDataVector(data, getColumnsName());
	}

	public void clean() {
		getTId().setText("");
		getTYear().setText("");
		getCBAirline().setSelectedItem("Indefinido");
		getCBModel().setSelectedItem("Indefinido");
	}
}
