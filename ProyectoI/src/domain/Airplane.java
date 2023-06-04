package domain;

public class Airplane {

	private String Id;
	private Airline Airline;
	private AirplaneModel AirplaneModel;
	
	public Airplane() {}

	public Airplane(String Id, domain.Airline Airline, domain.AirplaneModel AirplaneModel) {
		super();
		this.Id = Id;
		this.Airline = Airline;
		this.AirplaneModel = AirplaneModel;
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public Airline getAirline() {
		return Airline;
	}

	public void setAirline(Airline Airline) {
		this.Airline = Airline;
	}

	public AirplaneModel getAirplaneModel() {
		return AirplaneModel;
	}

	public void setAirplaneModel(AirplaneModel AirplaneModel) {
		this.AirplaneModel = AirplaneModel;
	}

	@Override
	public String toString() {
		return Id + "," + Airline + "," + AirplaneModel;
	}
}
