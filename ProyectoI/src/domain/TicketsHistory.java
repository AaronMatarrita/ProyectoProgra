package domain;

public class TicketsHistory {
	private String ticketNumber;
	private String pasPassport;
	private String pasName;
	private String passurNames;
	private String email;
	private String dateofBirth;
	private String phoneNumber;
	private String buyDate;
	private String airlineName;
	private String operationCenter;
	private String airplaneId;
	private String airlineAirplane;
	private String airplaneModel;
	private String yearAirplane;
	private String exitCity;
	private String exitDate;
	private String enterCity;
	private String enterDate;
	private String ticketClass;
	private double priceTicket;
	
	
	
	public TicketsHistory(
			String ticketNumber
			,String pasPassport,String pasName,String passurNames
			,String email,String dateofBirth
			,String phoneNumber,String buyDate
			,String airlineName,String operationCenter,String airplaneId
			,String airlineAirplane,String airplaneModel,String yearAirplane
			,String exitCity,String exitDate,String enterCity,String enterDate,String ticketClass,double priceTicket) {
		super();
		this.ticketNumber = ticketNumber;
		this.pasPassport = pasPassport;
		this.pasName = pasName;
		this.passurNames = passurNames;
		this.email = email;
		this.dateofBirth = dateofBirth;
		this.phoneNumber = phoneNumber;
		this.buyDate = buyDate;
		this.airlineName = airlineName;
		this.operationCenter = operationCenter;
		this.airplaneId = airplaneId;
		this.airlineAirplane = airlineAirplane;
		this.airplaneModel = airplaneModel;
		this.yearAirplane = yearAirplane;
		this.exitCity = exitCity;
		this.exitDate = exitDate;
		this.enterCity = enterCity;
		this.enterDate = enterDate;
		this.ticketClass = ticketClass;
		this.priceTicket = priceTicket;
		
		
	}
	
	public String getEnterCity() {
		return enterCity;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterCity(String enterCity) {
		this.enterCity = enterCity;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
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


	public double getPriceTicket() {
		return priceTicket;
	}


	public void setPriceTicket(double priceTicket) {
		this.priceTicket = priceTicket;
	}
	
	public String[] getDataName() {
		String[] dataName = {
				"TicketNumber"
				,"PassengerPassport"
				,"Name"
				,"LastName"
				,"Email"
				,"DateOfBirth"
				,"PhoneNumber"
				,"BuyTicketDate"
				,"AirlineName"
				,"AirplaneOperationCenter"
				,"AirplaneId"
				,"AirplaneAirline"
				,"AirplaneModel"
				,"AirplaneYear"
				,"ExitCity"
				,"ExitDate"
				,"EnterCity"
				,"EnterDate"
				,"TicketClass"
				,"TicketPrice"};
		return dataName;
	}

	public String[] getData() {
		String[] data = {ticketNumber, pasPassport, pasName, passurNames,
						email,dateofBirth,phoneNumber,buyDate,airlineName,
						operationCenter,airplaneId,airlineAirplane,airplaneModel,
						yearAirplane,exitCity,exitDate,enterCity,enterDate,ticketClass,String.valueOf(priceTicket)};
			
		return data;
	}

	

}