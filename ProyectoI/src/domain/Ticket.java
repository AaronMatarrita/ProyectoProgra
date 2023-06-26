package domain;

public class Ticket {
	private int ticketNumber;
	private String passport;
	private int flightNumber;
	private String tickettype;
	
	public Ticket(int ticketNumber, String passport, int flightNumber,String tickettype) {
		super();
		this.ticketNumber = ticketNumber;
		this.passport = passport;
		this.flightNumber = flightNumber;
		this.tickettype = tickettype;
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
	
	public String getTicketType() {
		return tickettype;
	}

	public void setTicketType(String tickettype) {
		this.tickettype = tickettype;
	}
	
	
	public String[] getDataName() {
		String[] dataName = {"TicketNumber", "Passport", "FlightNumber","TicketType"};
		return dataName;
	}

	public String[] getData() {
		String[] data = {String.valueOf(ticketNumber), passport, String.valueOf(flightNumber), tickettype};
			
		return data;
	}

	
}
