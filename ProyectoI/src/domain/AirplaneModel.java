package domain;

public class AirplaneModel {

	private String Name;
	private Brand Brand;
	private int  BusinessClassSeats;
	private int  TouristClassSeats;
	private int  EconomyClassSeats;
	
	public AirplaneModel() {}

	public AirplaneModel(String Name, domain.Brand Brand, int BusinessClassSeats, int TouristClassSeats,
			int EconomyClassSeats) {
		super();
		this.Name = Name;
		this.Brand = Brand;
		this.BusinessClassSeats = BusinessClassSeats;
		this.TouristClassSeats = TouristClassSeats;
		this.EconomyClassSeats = EconomyClassSeats;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public Brand getBrand() {
		return Brand;
	}

	public void setBrand(Brand Brand) {
		this.Brand = Brand;
	}

	public int getBusinessClassSeats() {
		return BusinessClassSeats;
	}

	public void setBusinessClassSeats(int BusinessClassSeats) {
		this.BusinessClassSeats = BusinessClassSeats;
	}

	public int getTouristClassSeats() {
		return TouristClassSeats;
	}

	public void setTouristClassSeats(int TouristClassSeats) {
		this.TouristClassSeats = TouristClassSeats;
	}

	public int getEconomyClassSeats() {
		return EconomyClassSeats;
	}

	public void setEconomyClassSeats(int EconomyClassSeats) {
		this.EconomyClassSeats = EconomyClassSeats;
	}

	@Override
	public String toString() {
		return Name + "," + Brand + "," + BusinessClassSeats
				+ "," + TouristClassSeats + "," + EconomyClassSeats;
	}
}
