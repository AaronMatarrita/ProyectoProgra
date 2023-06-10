package domain;

public class Flights {

	private int FlightNumber;
    private String DepartureCity;
    private String DepartureTime;
    private String DepartureDate;
    private String ArrivalCity;
    private String ArrivalTime;
    private String ArrivalDate;
    private String Airplane;
    private double BusinessClassSeatsPrice;
    private double TouristClassSeatsPrice;
    private double EconomyClassSeatsPrice;
    
    public Flights() {}
    
    public Flights(int FlightNumber, String DepartureCity, String DepartureDate,String DepartureTime, 
            String ArrivalCity, String ArrivalDate,String ArrivalTime, String Airplane, 
            double BusinessClassSeatsPrice, double TouristClassSeatsPrice, double EconomyClassSeatsPrice) {
    	super();
        this.FlightNumber = FlightNumber;
        this.DepartureCity = DepartureCity;
        this.DepartureDate = DepartureDate;
        this.DepartureTime = DepartureTime;
        this.ArrivalCity = ArrivalCity;
        this.ArrivalDate = ArrivalDate;
        this.ArrivalTime = ArrivalTime;
        this.Airplane = Airplane;
        this.BusinessClassSeatsPrice = BusinessClassSeatsPrice;
        this.TouristClassSeatsPrice = TouristClassSeatsPrice;
        this.EconomyClassSeatsPrice = EconomyClassSeatsPrice;
	}

	public int getFlightNumber() {
		return FlightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}

	public String getDepartureCity() {
		return DepartureCity;
	}

	public void setDepartureCity(String departureCity) {
		DepartureCity = departureCity;
	}

	public String getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(String departureTime) {
		DepartureTime = departureTime;
	}
	public String getDepartureDate() {
		return DepartureDate;
	}

	public void setDepartureDate(String departureDate) {
		DepartureDate = departureDate;
	}

	public String getArrivalCity() {
		return ArrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		ArrivalCity = arrivalCity;
	}

	public String getArrivalTime() {
		return ArrivalTime;
	}
	
	public String getArrivalDate() {
		return ArrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		ArrivalDate = arrivalDate;
	}
	
	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public String getAirplane() {
		return Airplane;
	}

	public void setAirplane(String airplane) {
		Airplane = airplane;
	}

	public double getBusinessClassSeatsPrice() {
		return BusinessClassSeatsPrice;
	}

	public void setBusinessClassSeatsPrice(double businessClassSeatsPrice) {
		BusinessClassSeatsPrice = businessClassSeatsPrice;
	}

	public double getTouristClassSeatsPrice() {
		return TouristClassSeatsPrice;
	}

	public void setTouristClassSeatsPrice(double touristClassSeatsPrice) {
		TouristClassSeatsPrice = touristClassSeatsPrice;
	}

	public double getEconomyClassSeatsPrice() {
		return EconomyClassSeatsPrice;
	}
	

	public String[] getDataName() {
		String[] dataName = {"FlightNumber", "DepartureCity", "DepartureDate","DepartureTime", "ArrivalCity"
				,"ArrivalDate","ArrivalTime","Airplane","BusinessClassSeatsPrice","TouristClassSeatsPrice","EconomyClassSeatsPrice"};
		return dataName;
	}

	public String[] getData() {
		String[] data = {String.valueOf(FlightNumber), DepartureCity, DepartureDate,DepartureTime, ArrivalCity
			,ArrivalDate,ArrivalTime,Airplane,String.valueOf(BusinessClassSeatsPrice),
			String.valueOf(TouristClassSeatsPrice),String.valueOf(EconomyClassSeatsPrice)};
			
		return data;
	}

	public void setEconomyClassSeatsPrice(double economyClassSeatsPrice) {
		EconomyClassSeatsPrice = economyClassSeatsPrice;
	}

	
	
}
