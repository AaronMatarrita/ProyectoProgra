package business;

import java.io.IOException;

public class LogicExecuteHTML {

	public void executeHTML(String url) {
		String osName = System.getProperty("os.name");

		try {
			if (osName.startsWith("Windows")) {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else if (osName.startsWith("Mac OS X")) {
				Runtime.getRuntime().exec("open -a safari " + url);
				Runtime.getRuntime().exec("open " + url + "/index.html");
				Runtime.getRuntime().exec("open " + url);
			}
		} catch (IOException ioe) {
			System.out.println("Failed to start a browser to open the URL " + url);
			ioe.printStackTrace();
		}
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void help(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\index.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpUser(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\manualUsuarios.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpBrand(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mMarcas.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpModel(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mModelos.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpAirline(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mAerolíneas.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpAirplane(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mAviones.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpFlight(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mVuelos.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpPassenger(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mPasajeros.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void helpTickets(String userType) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\mTickets.html"; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
}