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
	public void help(String userType, String HTMLName) {
		String url = System.getProperty("user.dir") + "\\Manual\\" + userType + "\\" + HTMLName; 
		executeHTML(url);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
}