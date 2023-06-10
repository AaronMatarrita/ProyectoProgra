package domain;

public class Airplane {

	private String Id;
	private String Year;
	private String Airline;
	private String AirplaneModel;
	
	public Airplane() {}

	public Airplane(String Id,String Year, String Airline, String AirplaneModel) {
		super();
		this.Id = Id;
		this.Year = Year;
		this.Airline = Airline;
		this.AirplaneModel = AirplaneModel;
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}
	

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getAirline() {
		return Airline;
	}

	public void setAirline(String Airline) {
		this.Airline = Airline;
	}

	public String getAirplaneModel() {
		return AirplaneModel;
	}

	public void setAirplaneModel(String AirplaneModel) {
		this.AirplaneModel = AirplaneModel;
	}
	
	public String[] getDataName() {
		String[] dataName = {"id", "year", "airline", "model"};
		return dataName;
	}
	
	public String[] getData() {
		String[] data = {Id, Year, Airline, AirplaneModel};
		return data;
	}

	@Override
	public String toString() {
		return Id +","+ Year +"," + Airline + "," + AirplaneModel;
	}

}
