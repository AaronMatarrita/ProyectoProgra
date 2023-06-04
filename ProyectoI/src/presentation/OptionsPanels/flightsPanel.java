package presentation.OptionsPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class flightsPanel extends JPanel {
	private JLabel lTitle;

	public flightsPanel() {
		setBackground(new Color(63, 37, 170));
		setBounds(207, 0, 777, 544);
		setLayout(null);
		add(getLTitle());
	}
	
	public JLabel getLTitle() {
		if (lTitle == null) {
			lTitle = new JLabel("Gestión de vuelos");
			lTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lTitle.setForeground(Color.WHITE);
			lTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
			lTitle.setBounds(168, 11, 468, 47);
		}
		return lTitle;
	}
}
