
package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIAdmin extends JFrame
{
	private JPanel panel;
	private JPanel sideRightPanel;
	private JPanel JPInfo;
	private JButton bUsers;
	private JButton bBrands;
	private JButton bModels;
	private JButton bAirlines;
	private JButton bAirplanes;
	private JButton bFlights;
	private JButton bPassengers;
	private JButton bTickets;
	private JLabel lPlaneIcon;
	private JLabel lHour;
	private JLabel lDate;


	public GUIAdmin() {
		setForeground(new Color(0, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			panel.add(getSideRightPanel());
			panel.add(getJPInfo());
		}
		return panel;
	}
	public JPanel getSideRightPanel() {
		if (sideRightPanel == null) {
			sideRightPanel = new JPanel();
			sideRightPanel.setBackground(new Color(28, 28, 28));
			sideRightPanel.setBounds(0, 0, 210, 544);
			sideRightPanel.setLayout(null);
			sideRightPanel.add(getBUsers());
			sideRightPanel.add(getBBrands());
			sideRightPanel.add(getBModels());
			sideRightPanel.add(getBAirlines());
			sideRightPanel.add(getBAirplanes());
			sideRightPanel.add(getBFlights());
			sideRightPanel.add(getBPassengers());
			sideRightPanel.add(getBTickets());
		}
		return sideRightPanel;
	}
	public JPanel getJPInfo() {
		if (JPInfo == null) {
			JPInfo = new JPanel();
			JPInfo.setBackground(new Color(63, 37, 170));
			JPInfo.setBounds(207, 0, 777, 544);
			JPInfo.setLayout(null);
			JPInfo.add(getLPlaneIcon());
			JPInfo.add(getLHour());
			JPInfo.add(getLDate());
		}
		return JPInfo;
	}
	public JButton getBUsers() {
		if (bUsers == null) {
			bUsers = new JButton("Usuarios");
			bUsers.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/users.png")));
			bUsers.setBorder(null);
			bUsers.setBackground(new Color(28, 28, 28));
			bUsers.setFocusable(false);
			bUsers.setForeground(new Color(255, 255, 255));
			bUsers.setFont(new Font("Roboto", Font.PLAIN, 16));
			bUsers.setBounds(10, 81, 190, 39);
		}
		return bUsers;
	}
	public JButton getBBrands() {
		if (bBrands == null) {
			bBrands = new JButton("Marcas");
			bBrands.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/brands.png")));
			bBrands.setForeground(Color.WHITE);
			bBrands.setFont(new Font("Roboto", Font.PLAIN, 16));
			bBrands.setFocusable(false);
			bBrands.setBorder(null);
			bBrands.setBackground(new Color(28, 28, 28));
			bBrands.setBounds(10, 131, 190, 39);
		}
		return bBrands;
	}
	public JButton getBModels() {
		if (bModels == null) {
			bModels = new JButton("Modelos de aviones");
			bModels.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/airplanes models.png")));
			bModels.setForeground(Color.WHITE);
			bModels.setFont(new Font("Roboto", Font.PLAIN, 16));
			bModels.setFocusable(false);
			bModels.setBorder(null);
			bModels.setBackground(new Color(28, 28, 28));
			bModels.setBounds(10, 181, 190, 39);
		}
		return bModels;
	}
	public JButton getBAirlines() {
		if (bAirlines == null) {
			bAirlines = new JButton("Aerolíneas");
			bAirlines.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			bAirlines.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/airlines.png")));
			bAirlines.setToolTipText("");
			bAirlines.setForeground(Color.WHITE);
			bAirlines.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAirlines.setFocusable(false);
			bAirlines.setBorder(null);
			bAirlines.setBackground(new Color(28, 28, 28));
			bAirlines.setBounds(10, 231, 190, 39);
		}
		return bAirlines;
	}
	public JButton getBAirplanes() {
		if (bAirplanes == null) {
			bAirplanes = new JButton("Aviones");
			bAirplanes.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/airplanes.png")));
			bAirplanes.setForeground(Color.WHITE);
			bAirplanes.setFont(new Font("Roboto", Font.PLAIN, 16));
			bAirplanes.setFocusable(false);
			bAirplanes.setBorder(null);
			bAirplanes.setBackground(new Color(28, 28, 28));
			bAirplanes.setBounds(10, 281, 190, 39);
		}
		return bAirplanes;
	}
	public JButton getBFlights() {
		if (bFlights == null) {
			bFlights = new JButton("Vuelos");
			bFlights.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/flights.png")));
			bFlights.setForeground(Color.WHITE);
			bFlights.setFont(new Font("Roboto", Font.PLAIN, 16));
			bFlights.setFocusable(false);
			bFlights.setBorder(null);
			bFlights.setBackground(new Color(28, 28, 28));
			bFlights.setBounds(10, 331, 190, 39);
		}
		return bFlights;
	}
	public JButton getBPassengers() {
		if (bPassengers == null) {
			bPassengers = new JButton("Pasajeros");
			bPassengers.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/passenger.png")));
			bPassengers.setForeground(Color.WHITE);
			bPassengers.setFont(new Font("Roboto", Font.PLAIN, 16));
			bPassengers.setFocusable(false);
			bPassengers.setBorder(null);
			bPassengers.setBackground(new Color(28, 28, 28));
			bPassengers.setBounds(10, 381, 190, 39);
		}
		return bPassengers;
	}
	public JButton getBTickets() {
		if (bTickets == null) {
			bTickets = new JButton("Tiquetes");
			bTickets.setIcon(new ImageIcon(GUIAdmin.class.getResource("/imagesMain/tickets.png")));
			bTickets.setForeground(Color.WHITE);
			bTickets.setFont(new Font("Roboto", Font.PLAIN, 16));
			bTickets.setFocusable(false);
			bTickets.setBorder(null);
			bTickets.setBackground(new Color(28, 28, 28));
			bTickets.setBounds(10, 431, 190, 39);
		}
		return bTickets;
	}

	public JLabel getLPlaneIcon() {
		if (lPlaneIcon == null) {
			lPlaneIcon = new JLabel("");
			ImageIcon imageIcon = new ImageIcon(GUILogin.class.getResource("/imagesLogin/AeroJetLogo.png"));
			Image image = imageIcon.getImage().getScaledInstance(263, 265, Image.SCALE_SMOOTH);
			lPlaneIcon.setIcon(new ImageIcon(image));
			lPlaneIcon.setBounds(272, 110, 263, 265);
		}
		return lPlaneIcon;
	}
	public JLabel getLHour() {
        if (lHour == null) {
            lHour = new JLabel("Hora actual:");
            lHour.setForeground(new Color(255, 255, 255));
            lHour.setFont(new Font("Roboto", Font.PLAIN, 16));
            lHour.setBounds(10, 514, 191, 19);

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm: a");
            String horaActual = sdf.format(new Date());

            lHour.setText("Hora actual: " + horaActual);
        }
        return lHour;
    }
	
	public JLabel getLDate() {
        if (lDate == null) {
            lDate = new JLabel("Fecha:");
            lDate.setForeground(new Color(255, 255, 255));
            lDate.setFont(new Font("Roboto", Font.PLAIN, 16));
            lDate.setBounds(607, 514, 160, 16);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = sdf.format(new Date());

            lDate.setText("Fecha: " + fechaActual);
        }
        return lDate;
    }
}