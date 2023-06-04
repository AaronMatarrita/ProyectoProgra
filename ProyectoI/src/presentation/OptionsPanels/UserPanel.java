package presentation.OptionsPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import business.ControllerAdmin;
import javax.swing.JTextField;

public class UserPanel extends JPanel {
	private JLabel lTitle;
	private JButton bHi;
	private JTextField tUser;

	public UserPanel() {
		setBackground(new Color(63, 37, 170));
		setBounds(207, 0, 777, 544);
		setLayout(null);
		add(getLTitle());
		add(getBHi());
		add(getTUser());
	}
	
	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de usuarios");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(168, 11, 388, 47);
		}
		return lTitle;
	}
	public JButton getBHi() {
		if (bHi == null) {
			bHi = new JButton("Login");
			bHi.setFont(new Font("Roboto", Font.PLAIN, 16));
			bHi.setForeground(new Color(255, 255, 255));
			bHi.setBackground(new Color(28, 28, 28));
			bHi.setBounds(10, 267, 245, 43);
			bHi.setFocusable(false);
			bHi.setBorder(null);
		}
		return bHi;
	}
	public JTextField getTUser() {
		if (tUser == null) {
			tUser = new JTextField();
			tUser.setBounds(10, 93, 245, 32);
			tUser.setColumns(10);
		}
		return tUser;
	}
}