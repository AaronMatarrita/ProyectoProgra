package presentation;

import javax.swing.JOptionPane;

public class PopUpMessages {

	public PopUpMessages(){}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public boolean  showConfirmationDialog(String message, String action) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, message, action, dialogButton);
		if (dialogResult == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getData(String message) {
		String data = String.valueOf(JOptionPane.showInputDialog(message));
		if(data.isEmpty()) {
			showMessage("Ingrse la información solicitada");
			return null;
		}
		return data;
	}	
}
