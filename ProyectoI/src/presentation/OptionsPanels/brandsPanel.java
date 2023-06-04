package presentation.OptionsPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class brandsPanel extends JPanel {
	private JLabel lTitle;
	private JButton btnNewButton;

	public brandsPanel() {
		setBackground(new Color(63, 37, 170));
		setBounds(207, 0, 777, 544);
		setLayout(null);
		add(getLTitle());
		add(getBtnNewButton());
	}
	
	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de marcas");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(168, 11, 388, 47);
		}
		return lTitle;
	}
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
			btnNewButton.setBounds(188, 202, 89, 23);
		}
		return btnNewButton;
	}
}
