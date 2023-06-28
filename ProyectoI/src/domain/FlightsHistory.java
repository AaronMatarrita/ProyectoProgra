package domain;

public class FlightsHistory {
	private String flightNumber;
	private String airline;
	private String airplane;
	private String exitCity;
	private String exitDate;
	private String enterCity;
	private String enterDate;
	private int soldEJEseats;
	private int totalEJEseats;
	private int soldTOUseats;
	private int totalTOUseats;
	private int soldECOseats;
	private int totalECOseats;
	private double EJEprice;
	private double TOUprice;
	private double ECOprice;
	private double totalflight;
	

	public FlightsHistory(String flightNumber, String airline, String airplane, String exitCity, String exitDate,
            String enterCity, String enterDate, int soldEJEseats, int totalEJEseats, int soldTOUseats,
            int totalTOUseats, int soldECOseats, int totalECOseats, double EJEprice, double TOUprice,
            double ECOprice, double totalflight) {
		  
		super();
		  this.flightNumber = flightNumber;
		  this.airline = airline;
		  this.airplane = airplane;
		  this.exitCity = exitCity;
		  this.exitDate = exitDate;
		  this.enterCity = enterCity;
		  this.enterDate = enterDate;
		  this.soldEJEseats = soldEJEseats;
		  this.totalEJEseats = totalEJEseats;
		  this.soldTOUseats = soldTOUseats;
		  this.totalTOUseats = totalTOUseats;
		  this.soldECOseats = soldECOseats;
		  this.totalECOseats = totalECOseats;
		  this.EJEprice = EJEprice;
		  this.TOUprice = TOUprice;
		  this.ECOprice = ECOprice;
		  this.totalflight = totalflight;
		}
			
	
	public String[] getDataName() {
		String[] dataName = {
				"FlightNumber"
				,"Airline"
				,"Airplane"
				,"ExitCity"
				,"ExitDate"
				,"EnterCity"
				,"EnterDate"
				,"SoldBusinessClassSeats"
				,"TotalBusinessClassSeats"
				,"SoldTouristClassSeats"
				,"TotalTouristClassSeats"
				,"SoldEconomicClassSeats"
				,"TotalEconomicClassSeats"
				,"BusinessPrice"
				,"TouristPrice"
				,"EconomicPrice"
				,"TotalForFlight"};
		return dataName;
	}

	public String[] getData() {
	    String[] data = {flightNumber, airline, airplane, exitCity, exitDate, enterCity, enterDate,
	                    String.valueOf(soldEJEseats), String.valueOf(totalEJEseats),
	                    String.valueOf(soldTOUseats), String.valueOf(totalTOUseats),
	                    String.valueOf(soldECOseats), String.valueOf(totalECOseats),
	                    String.valueOf(EJEprice), String.valueOf(TOUprice), String.valueOf(ECOprice),
	                    String.valueOf(totalflight)};
	    return data;
	}



	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public String getAirplane() {
		return airplane;
	}


	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}


	public String getExitCity() {
		return exitCity;
	}


	public void setExitCity(String exitCity) {
		this.exitCity = exitCity;
	}


	public String getExitDate() {
		return exitDate;
	}


	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}


	public String getEnterCity() {
		return enterCity;
	}


	public void setEnterCity(String enterCity) {
		this.enterCity = enterCity;
	}


	public String getEnterDate() {
		return enterDate;
	}


	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}


	public int getSoldEJEseats() {
		return soldEJEseats;
	}


	public void setSoldEJEseats(int soldEJEseats) {
		this.soldEJEseats = soldEJEseats;
	}


	public int getTotalEJEseats() {
		return totalEJEseats;
	}


	public void setTotalEJEseats(int totalEJEseats) {
		this.totalEJEseats = totalEJEseats;
	}


	public int getSoldTOUseats() {
		return soldTOUseats;
	}


	public void setSoldTOUseats(int soldTOUseats) {
		this.soldTOUseats = soldTOUseats;
	}


	public int getTotalTOUseats() {
		return totalTOUseats;
	}


	public void setTotalTOUseats(int totalTOUseats) {
		this.totalTOUseats = totalTOUseats;
	}


	public int getSoldECOseats() {
		return soldECOseats;
	}


	public void setSoldECOseats(int soldECOseats) {
		this.soldECOseats = soldECOseats;
	}


	public int getTotalECOseats() {
		return totalECOseats;
	}


	public void setTotalECOseats(int totalECOseats) {
		this.totalECOseats = totalECOseats;
	}


	public double getEJEprice() {
		return EJEprice;
	}


	public void setEJEprice(double eJEprice) {
		EJEprice = eJEprice;
	}


	public double getTOUprice() {
		return TOUprice;
	}


	public void setTOUprice(double tOUprice) {
		TOUprice = tOUprice;
	}


	public double getECOprice() {
		return ECOprice;
	}


	public void setECOprice(double eCOprice) {
		ECOprice = eCOprice;
	}


	public double getTotalflight() {
		return totalflight;
	}


	public void setTotalflight(double totalflight) {
		this.totalflight = totalflight;
	}
	

	

}