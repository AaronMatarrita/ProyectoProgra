package domain;

import java.time.LocalDateTime;

public class Flights {

	private int FlightNumber;
    private String DepartureCity;
    private LocalDateTime DepartureDateTime;
    private String ArrivalCity;
    private LocalDateTime ArrivalDateTime;
    private Airplane Airplane;
    private double BusinessClassSeatsPrice;
    private double TouristClassSeatsPrice;
    private double EconomyClassSeatsPrice;
    
    public Flights() {}
    
    public Flights(int FlightNumber, String DepartureCity, LocalDateTime DepartureDateTime, 
            String ArrivalCity, LocalDateTime ArrivalDateTime, Airplane Airplane, 
            double BusinessClassSeatsPrice, double TouristClassSeatsPrice, double EconomyClassSeatsPrice) {
    	super();
        this.FlightNumber = FlightNumber;
        this.DepartureCity = DepartureCity;
        this.DepartureDateTime = DepartureDateTime;
        this.ArrivalCity = ArrivalCity;
        this.ArrivalDateTime = ArrivalDateTime;
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

	public LocalDateTime getDepartureDateTime() {
		return DepartureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		DepartureDateTime = departureDateTime;
	}

	public String getArrivalCity() {
		return ArrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		ArrivalCity = arrivalCity;
	}

	public LocalDateTime getArrivalDateTime() {
		return ArrivalDateTime;
	}

	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		ArrivalDateTime = arrivalDateTime;
	}

	public Airplane getAirplane() {
		return Airplane;
	}

	public void setAirplane(Airplane airplane) {
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

	public void setEconomyClassSeatsPrice(double economyClassSeatsPrice) {
		EconomyClassSeatsPrice = economyClassSeatsPrice;
	}

	
	
}
