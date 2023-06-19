package domain;

public class TicketsHistory {
	private String ticketNumber;
	private String pasPassport;
	private String pasName;
	private String passurNames;
	private String pasbornDate;
	private String email;
	private String dateofBirth;
	private String phoneNumber;
	private String buyDate;
	private String buyTime;
	private String airlineName;
	private String operationCenter;
	private String airplaneId;
	private String airlineAirplane;
	private String airplaneModel;
	private String yearAirplane;
	private String exitCity;
	private String exitDate;
	private String ticketClass;
	private int priceTicket;
	
	
	
	public TicketsHistory(
			String ticketNumber
			,String pasPassport,String pasName,String passurNames
			,String pasbornDate,String email,String dateofBirth
			,String phoneNumber,String buyDate,String buyTime
			,String airlineName,String operationCenter,String airplaneId
			,String airlineAirplane,String airplaneModel,String yearAirplane
			,String exitCity,String exitDate,String ticketClass,int priceTicket) {
		super();
		this.ticketNumber = ticketNumber;
		this.pasPassport = pasPassport;
		this.pasName = pasName;
		this.passurNames = passurNames;
		this.pasbornDate = pasbornDate;
		this.email = email;
		this.dateofBirth = dateofBirth;
		this.phoneNumber = phoneNumber;
		this.buyDate = buyDate;
		this.buyTime = buyTime;
		this.airlineName = airlineName;
		this.operationCenter = operationCenter;
		this.airplaneId = airplaneId;
		this.airlineAirplane = airlineAirplane;
		this.airplaneModel = airplaneModel;
		this.yearAirplane = yearAirplane;
		this.exitCity = exitCity;
		this.exitDate = exitDate;
		this.ticketClass = ticketClass;
		this.priceTicket = priceTicket;
		
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}


	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}


	public String getPasPassport() {
		return pasPassport;
	}


	public void setPasPassport(String pasPassport) {
		this.pasPassport = pasPassport;
	}


	public String getPasName() {
		return pasName;
	}


	public void setPasName(String pasName) {
		this.pasName = pasName;
	}


	public String getPassurNames() {
		return passurNames;
	}


	public void setPassurNames(String passurNames) {
		this.passurNames = passurNames;
	}


	public String getPasbornDate() {
		return pasbornDate;
	}


	public void setPasbornDate(String pasbornDate) {
		this.pasbornDate = pasbornDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDateofBirth() {
		return dateofBirth;
	}


	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getBuyDate() {
		return buyDate;
	}


	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}


	public String getBuyTime() {
		return buyTime;
	}


	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}


	public String getAirlineName() {
		return airlineName;
	}


	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}


	public String getOperationCenter() {
		return operationCenter;
	}


	public void setOperationCenter(String operationCenter) {
		this.operationCenter = operationCenter;
	}


	public String getAirplaneId() {
		return airplaneId;
	}


	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}


	public String getAirlineAirplane() {
		return airlineAirplane;
	}


	public void setAirlineAirplane(String airlineAirplane) {
		this.airlineAirplane = airlineAirplane;
	}


	public String getAirplaneModel() {
		return airplaneModel;
	}


	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}


	public String getYearAirplane() {
		return yearAirplane;
	}


	public void setYearAirplane(String yearAirplane) {
		this.yearAirplane = yearAirplane;
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


	public String getTicketClass() {
		return ticketClass;
	}


	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}


	public int getPriceTicket() {
		return priceTicket;
	}


	public void setPriceTicket(int priceTicket) {
		this.priceTicket = priceTicket;
	}
	
	public String[] getDataName() {
		String[] dataName = {
				"TicketNumber"
				,"PassengerPassport"
				,"Name"
				,"LastName"
				,"BornDate"
				,"Email"
				,"PhoneNumber"
				,"BuyTicketDate"
				,"BuyTicketTime"
				,"AirlineName"
				,"AirplaneOperationCenter"
				,"AirplaneId"
				,"AirplaneAirline"
				,"AirplaneModel"
				,"AirplaneYear"
				,"ExitCity"
				,"ExitDate"
				,"TicketClass"
				,"TicketPice"};
		return dataName;
	}

	public String[] getData() {
		String[] data = {ticketNumber, pasPassport, pasName, passurNames,pasbornDate,
						email,dateofBirth,phoneNumber,buyDate,buyTime,airlineName,
						operationCenter,airplaneId,airlineAirplane,airplaneModel,
						yearAirplane,exitCity,exitDate,ticketClass,String.valueOf(priceTicket)};
			
		return data;
	}

	

}
