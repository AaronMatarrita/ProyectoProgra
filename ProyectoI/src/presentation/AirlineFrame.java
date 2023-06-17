package presentation;

import java.awt.Color;
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

import domain.Airline;

@SuppressWarnings("serial")
public class AirlineFrame extends JFrame {
	private String userType;
	//Paneles
    private JPanel panel;
    private JPanel JPInfo;
    //Etiquetas
    private JLabel lTitle;
    private JLabel lAirline;
    private JLabel lCountry;
    //Botones
    private JButton bAddAirline;
    private JButton bUpdate;
    private JButton bClear;
    //Campos de Texto
    private JTextField tAirline;
    private JTextField tCountry;
    //Tabla
    private DefaultTableModel dtmTAirline;
    private JTable jTableAirline;
    //Scroll
    private JScrollPane spTAirline;
	private Object dataTable[][];
    
    

    public AirlineFrame(String userType) {
    	this.userType = userType;
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
            JPInfo.add(getLAirline());
            JPInfo.add(getLCountry());
            //JTextFields
            JPInfo.add(getTAirline());
            JPInfo.add(getTCountry());
            //JButtons
            JPInfo.add(getBAddAirline());
            JPInfo.add(getBUpdate());
            JPInfo.add(getBClear());
            
            if(userType.equals("2"))//Usuario de tipo *Colaborador* 
			{
				bClear.setVisible(false);
				bUpdate.setVisible(false);
			}
            //JTable
            setDTMAirline(dataTable, getColumnsName());
			setJTableAirline(getDTMAirline());
			setSPTableAirline(getJTableAirline());
			JPInfo.add(getSPTableAirline());
		
            
			
			
        }
        return JPInfo;
    }

    public JLabel getLTitle() {
        if (lTitle == null) {
            lTitle = new JLabel("Gestión de Aerolineas");
            lTitle.setHorizontalAlignment(SwingConstants.CENTER);
            lTitle.setForeground(Color.WHITE);
            lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
            lTitle.setBounds(320, 10, 350, 50);
        }
        return lTitle;
    }

    public JLabel getLAirline() {
        if (lAirline == null) {
            lAirline = new JLabel("Nombre de la Aerolinea:");
            lAirline.setForeground(new Color(255, 255, 255));
            lAirline.setFont(new Font("Roboto", Font.PLAIN, 16));
            lAirline.setBounds(130, 189, 180, 20);
        }
        return lAirline;
    }

    public JButton getBAddAirline() {
        if (bAddAirline == null) {
            bAddAirline = new JButton("Agregar");
            bAddAirline.setFont(new Font("Roboto", Font.PLAIN, 16));
            bAddAirline.setIcon(new ImageIcon(AirlineFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
            bAddAirline.setBackground(new Color(28, 28, 28));
            bAddAirline.setForeground(new Color(255, 255, 255));
            bAddAirline.setFocusable(false);
            bAddAirline.setBounds(440, 330, 130, 40);
        }
        return bAddAirline;
    }

    public JButton getBUpdate() {
        if (bUpdate == null) {
            bUpdate = new JButton("Modificar");
            bUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
            bUpdate.setIcon(new ImageIcon(AirlineFrame.class.getResource("/imagesMain/imagesButtons/update-button.png")));
            bUpdate.setBackground(new Color(28, 28, 28));
            bUpdate.setForeground(new Color(255, 255, 255));
            bUpdate.setFocusable(false);
            bUpdate.setBounds(240, 330, 130, 40);
        }
        return bUpdate;
    }

    public JButton getBClear() {
        if (bClear == null) {
            bClear =  new JButton("Eliminar");
            bClear.setFont(new Font("Roboto", Font.PLAIN, 16));
            bClear.setIcon(new ImageIcon(AirlineFrame.class.getResource("/imagesMain/imagesButtons/delete-button.png")));
            bClear.setBackground(new Color(28, 28, 28));
            bClear.setForeground(new Color(255, 255, 255));
            bClear.setFocusable(false);
            bClear.setBounds(640, 330, 130, 40);
        }
        return bClear;
    }

    public JTextField getTAirline() {
        if (tAirline == null) {
            tAirline = new JTextField();
            tAirline.setBackground(new Color(28, 28, 28));
            tAirline.setForeground(new Color(255, 255, 255));
            tAirline.setFont(new Font("Roboto", Font.PLAIN, 16));
			tAirline.setBorder(BorderFactory.createEmptyBorder());
            tAirline.setBounds(320, 190, 150, 20);
            tAirline.setColumns(10);
        }
        return tAirline;
    }

    
    
	public JLabel getLCountry() {
		if (lCountry == null) {
			lCountry = new JLabel("Centro de Operaciones:");
			lCountry.setForeground(Color.WHITE);
			lCountry.setFont(new Font("Roboto", Font.PLAIN, 16));
			lCountry.setBounds(550, 189, 180, 20);
		}
		return lCountry;
	}
	public JTextField getTCountry() {
		if (tCountry == null) {
			tCountry = new JTextField();
			tCountry.setForeground(Color.WHITE);
			tCountry.setFont(new Font("Roboto", Font.PLAIN, 16));
			tCountry.setColumns(10);
			tCountry.setBorder(BorderFactory.createEmptyBorder());
			tCountry.setBackground(new Color(28, 28, 28));
			tCountry.setBounds(740, 189, 150, 20);
		}
		return tCountry;
	}
	public void setDTMAirline(Object data[][], String[] columnsName) {
		dtmTAirline = new DefaultTableModel(data, columnsName);
	}

	public DefaultTableModel getDTMAirline() {
		return dtmTAirline;
	}

	public void setJTableAirline(DefaultTableModel dtmTAirline) {
		jTableAirline = new JTable(dtmTAirline);
		jTableAirline.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
		jTableAirline.getTableHeader().setOpaque(false);
		jTableAirline.getTableHeader().setBackground(new Color(32, 136, 203));
		jTableAirline.getTableHeader().setForeground(new Color(255, 255, 255));
		jTableAirline.setRowHeight(25);
		// No poder editar los valores de la tabla
		jTableAirline.setEnabled(false);
		// No poder mover las columnas
		jTableAirline.getTableHeader().setReorderingAllowed(false);
		// No poder reducir el tamaño de las columnas
		jTableAirline.getTableHeader().setResizingAllowed(false);
	}

	public JTable getJTableAirline() {
		return jTableAirline;
	}

	public void setSPTableAirline(JTable jTableAirline) {
		spTAirline = new JScrollPane(jTableAirline);
		spTAirline.setBounds(10, 400, 964, 133);
	}

	public JScrollPane getSPTableAirline() {
		return spTAirline;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Nombre", "Centro OP"};
		return columnsName;
	}

	public void setJTableData(List<Airline> airlines) {
	    Object[][] data = new Object[airlines.size()][2];
	    for (int i = 0; i < airlines.size(); i++) {
	        Airline airline = airlines.get(i);
	        data[i][0] = airline.getName();
	        data[i][1] = airline.getCountry();
	    }
	    dtmTAirline.setDataVector(data, getColumnsName());
	}

	public void clean() {
    	getTAirline().setText("");
    	getTCountry().setText("");
    }
}
