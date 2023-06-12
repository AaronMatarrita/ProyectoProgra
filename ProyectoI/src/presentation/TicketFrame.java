package presentation;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import domain.Tickets;

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
    private JTextField tPassport;
    //Tabla
    private DefaultTableModel dtmTFlights;
    private JTable jTableFlights;
    //Scroll
    private JScrollPane spTFlights;
	private Object dataTable[][];
	private JTextField tFlightNumber;
	private JLabel lFlightNumber;
	private JMenuBar JMenu;
	private JMenu mnFile;
	private JMenu MNHelp;
	private JMenuItem menuItem;
	private JMenuItem bDownloadPDF;

    public TicketFrame() {
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
            panel.add(getMenuBar_1());
        }
        return panel;
    }

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
			JPInfo.add(getTPassport());
            //JButtons
            JPInfo.add(getBAddTickets());
            JPInfo.add(getBUpdate());
            JPInfo.add(getBClear());

            //JTable
            setDTMFlights(dataTable, getColumnsName());
	        setJTableFlights(getDTMFlights());
	        setSPTableFlights(getJTableFlights());
	        JPInfo.add(getSPTableFlights());
	        JPInfo.add(getTFlightNumber());
	        JPInfo.add(getLFlightNumber());
			
			
        }
        return JPInfo;
    }

    public JLabel getLTitle() {
        if (lTitle == null) {
            lTitle = new JLabel("Gestión de tiquetes");
            lTitle.setHorizontalAlignment(SwingConstants.CENTER);
            lTitle.setForeground(Color.WHITE);
            lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
            lTitle.setBounds(320, 33, 350, 50);
        }
        return lTitle;
    }

    public JLabel getLTicketNumber() {
        if (lTicketNumber == null) {
            lTicketNumber = new JLabel("Número de tiquete:");
            lTicketNumber.setForeground(new Color(255, 255, 255));
            lTicketNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
            lTicketNumber.setBounds(320, 122, 150, 20);
        }
        return lTicketNumber;
    }

    public JButton getBAddTickets() {
        if (bAddFlights == null) {
            bAddFlights = new JButton("Agregar");
            bAddFlights.setFont(new Font("Roboto", Font.PLAIN, 16));
            bAddFlights.setIcon(new ImageIcon(FlightsFrame.class.getResource("/imagesMain/imagesButtons/add-button.png")));
            bAddFlights.setBackground(new Color(28, 28, 28));
            bAddFlights.setForeground(new Color(255, 255, 255));
            bAddFlights.setFocusable(false);
            bAddFlights.setBounds(220, 303, 130, 40);
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
            bUpdate.setBounds(420, 303, 130, 40);
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
            bClear.setBounds(620, 303, 130, 40);
        }
        return bClear;
    }

    public JTextField getTTicketNumber() {
        if (tTicketNumber == null) {
            tTicketNumber = new JTextField();
            tTicketNumber.setBackground(new Color(28, 28, 28));
            tTicketNumber.setForeground(new Color(255, 255, 255));
            tTicketNumber.setFont(new Font("Roboto", Font.PLAIN, 16));
			tTicketNumber.setBorder(BorderFactory.createEmptyBorder());
            tTicketNumber.setBounds(520, 123, 150, 20);
            tTicketNumber.setColumns(10);
        }
        return tTicketNumber;
    }
	public JLabel getLPassport() {
		if (lPassport == null) {
			lPassport = new JLabel("Pasaporte:");
			lPassport.setForeground(Color.WHITE);
			lPassport.setFont(new Font("Dialog", Font.PLAIN, 16));
			lPassport.setBounds(320, 173, 150, 20);
		}
		return lPassport;
	}
	public JTextField getTPassport() {
		if (tPassport == null) {
			tPassport = new JTextField();
			tPassport.setForeground(Color.WHITE);
			tPassport.setFont(new Font("Dialog", Font.PLAIN, 16));
			tPassport.setColumns(10);
			tPassport.setBorder(BorderFactory.createEmptyBorder());
			tPassport.setBackground(new Color(28, 28, 28));
			tPassport.setBounds(520, 173, 150, 20);
		}
		return tPassport;
	}
	
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
		jTableFlights.getTableHeader().setBackground(new Color(32, 136, 203));
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
		spTFlights.setBounds(10, 373, 964, 125);
	}

	public JScrollPane getSPTableFlights() {
		return spTFlights;
	}

	public String[] getColumnsName() {
		String columnsName[] = {"Numero de tiquete", "Pasaporte", "Número de vuelo"};
		return columnsName;
	}
	
	public void setJTableData(List<Tickets> tickets) {
	    Object[][] data = new Object[tickets.size()][3];
	    for (int i = 0; i < tickets.size(); i++) {
	        Tickets ticket = tickets.get(i);

	        data[i][0] = ticket.getTicketNumber();
	        data[i][1] = ticket.getPassport();
	        data[i][2] = ticket.getFlightNumber();
	    }
	    dtmTFlights.setDataVector(data, getColumnsName());
	}

	
	public void clean() {
		getTPassport().setText("");
		getTFlightNumber().setText("");
    	getTTicketNumber().setText("");
    }
	public JTextField getTFlightNumber() {
		if (tFlightNumber == null) {
			tFlightNumber = new JTextField();
			tFlightNumber.setForeground(Color.WHITE);
			tFlightNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
			tFlightNumber.setColumns(10);
			tFlightNumber.setBorder(BorderFactory.createEmptyBorder());
			tFlightNumber.setBackground(new Color(28, 28, 28));
			tFlightNumber.setBounds(520, 223, 150, 20);
		}
		return tFlightNumber;
	}
	public JLabel getLFlightNumber() {
		if (lFlightNumber == null) {
			lFlightNumber = new JLabel("Número de vuelo:");
			lFlightNumber.setForeground(Color.WHITE);
			lFlightNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
			lFlightNumber.setBounds(320, 223, 150, 20);
		}
		return lFlightNumber;
	}
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
			mnFile.add(getBDownloadPDF());
		}
		return mnFile;
	}
	public JMenu getMNHelp() {
		if (MNHelp == null) {
			MNHelp = new JMenu("Help");
			MNHelp.setForeground(new Color(255, 255, 255));
			MNHelp.setFont(new Font("Roboto", Font.PLAIN, 12));
			MNHelp.add(getMenuItem());
		}
		return MNHelp;
	}
	public JMenuItem getMenuItem() {
		if (menuItem == null) {
			menuItem = new JMenuItem("New menu item");
		}
		return menuItem;
	}
	public JMenuItem getBDownloadPDF() {
		bDownloadPDF = new JMenuItem("Save ticket");
		bDownloadPDF.setForeground(new Color(255, 255, 255));
		bDownloadPDF.setIcon(new ImageIcon(TicketFrame.class.getResource("/imagestTickets/downloadPDF.png")));
		bDownloadPDF.setFont(new Font("Roboto", Font.PLAIN, 12));
		bDownloadPDF.setBackground(new Color(28, 28, 28));
		bDownloadPDF.setBorder(null);
		bDownloadPDF.setFocusable(false);
	    return bDownloadPDF;
	}
}
