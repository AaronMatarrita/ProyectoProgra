package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class GUILogin extends JFrame {

	private JPanel contentPane;
	private JPanel pInfomartion;
	private JLabel lUser;
	private JTextField tUser;
	private JSeparator sUser;
	private JLabel lPassword;
	private JSeparator sPassword;
	private JPasswordField jPasswordUser;
	private JPanel pBackground;
	private JButton bLogin;
	private JLabel lPlaneIcon;
	
	public GUILogin() {
		setForeground(new Color(0, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 382);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPInfomartion());
		contentPane.add(getPBackground());
		setVisible(true);
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	public JPanel getPInfomartion() {
		if (pInfomartion == null) {
			pInfomartion = new JPanel();
			pInfomartion.setBackground(new Color(63, 37, 170));
			pInfomartion.setBounds(319, 0, 265, 343);
			pInfomartion.setLayout(null);
			pInfomartion.add(getLUser());
			pInfomartion.add(getTUser());
			pInfomartion.add(getSUser());
			pInfomartion.add(getLPassword());
			pInfomartion.add(getSPassword());
			pInfomartion.add(getJPasswordUser());
			pInfomartion.add(getBLogin());
		}
		return pInfomartion;
	}
	public JLabel getLUser() {
		if (lUser == null) {
			lUser = new JLabel("Usuario:");
			lUser.setFont(new Font("Roboto", Font.PLAIN, 16));
			lUser.setForeground(new Color(255, 255, 255));
			lUser.setBounds(10, 49, 100, 20);
		}
		return lUser;
	}
	public JTextField getTUser() {
		if (tUser == null) {
			tUser = new JTextField();
			tUser.setFont(new Font("Roboto", Font.PLAIN, 16));
			tUser.setBorder(BorderFactory.createEmptyBorder());
			tUser.setForeground(new Color(255, 255, 255));
			tUser.setBackground(new Color(63, 37, 170));
			tUser.setBounds(10, 80, 245, 20);
			tUser.setColumns(10);
		}
		return tUser;
	}
	public JSeparator getSUser() {
		if (sUser == null) {
			sUser = new JSeparator();
			sUser.setBounds(10, 111, 245, 2);
		}
		return sUser;
	}
	public JLabel getLPassword() {
		if (lPassword == null) {
			lPassword = new JLabel("Contraseña:");
			lPassword.setForeground(Color.WHITE);
			lPassword.setFont(new Font("Roboto", Font.PLAIN, 16));
			lPassword.setBounds(10, 164, 100, 20);
		}
		return lPassword;
	}
	public JSeparator getSPassword() {
		if (sPassword == null) {
			sPassword = new JSeparator();
			sPassword.setBounds(10, 226, 245, 2);
		}
		return sPassword;
	}
	public JPasswordField getJPasswordUser() {
		if (jPasswordUser == null) {
			jPasswordUser = new JPasswordField();
			jPasswordUser.setEchoChar('*');
			jPasswordUser.setBorder(BorderFactory.createEmptyBorder());
			jPasswordUser.setFont(new Font("Roboto", Font.PLAIN, 16));
			jPasswordUser.setForeground(new Color(255, 255, 255));
			jPasswordUser.setBackground(new Color(63, 37, 170));
			jPasswordUser.setBounds(10, 195, 245, 20);
		}
		return jPasswordUser;
	}
	public JPanel getPBackground() {
		if (pBackground == null) {
			pBackground = new JPanel();
			pBackground.setBackground(new Color(28, 28, 28));
			pBackground.setForeground(Color.WHITE);
			pBackground.setBounds(0, 0, 323, 343);
			pBackground.setLayout(null);
			pBackground.add(getLPlaneIcon());
		}
		return pBackground;
	}
	public JButton getBLogin() {
		if (bLogin == null) {
			bLogin = new JButton("Login");
			bLogin.setFont(new Font("Roboto", Font.PLAIN, 16));
			bLogin.setForeground(new Color(255, 255, 255));
			bLogin.setBackground(new Color(28, 28, 28));
			bLogin.setBounds(10, 267, 245, 43);
			bLogin.setFocusable(false);
			bLogin.setBorder(null);
		}
		return bLogin;
	}
	
	public JLabel getLPlaneIcon() {
		if (lPlaneIcon == null) {
			lPlaneIcon = new JLabel("");
			ImageIcon imageIcon = new ImageIcon(GUILogin.class.getResource("/imagesLogin/AeroJetLogo.png"));
			Image image = imageIcon.getImage().getScaledInstance(263, 265, Image.SCALE_SMOOTH);
			lPlaneIcon.setIcon(new ImageIcon(image));
			lPlaneIcon.setBounds(29, 31, 263, 265);
		}
		return lPlaneIcon;
	}
}
