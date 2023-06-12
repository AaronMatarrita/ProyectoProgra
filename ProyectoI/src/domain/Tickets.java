package domain;

public class Tickets {
	private int ticketNumber;
	private String passport;
	private int flightNumber;
	
	public Tickets(int ticketNumber, String passport, int flightNumber) {
		super();
		this.ticketNumber = ticketNumber;
		this.passport = passport;
		this.flightNumber = flightNumber;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public String getPassport() {
		return passport;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	
	public String[] getDataName() {
		String[] dataName = {"TicketNumber", "Passport", "FlightNumber"};
		return dataName;
	}

	public String[] getData() {
		String[] data = {String.valueOf(ticketNumber), passport, String.valueOf(flightNumber)};
			
		return data;
	}
}
