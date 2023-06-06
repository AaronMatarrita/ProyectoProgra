package domain;

public class AirplaneModel {

	private String Name;
	private String Brand;
	private int  BusinessClassSeats;
	private int  TouristClassSeats;
	private int  EconomyClassSeats;
	
	public AirplaneModel() {}

	public AirplaneModel(String Name,String Brand, int BusinessClassSeats, int TouristClassSeats,
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

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String Brand) {
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
	
	public String[] getDataName() {
		String[] dataName = {"model", "brand", "BusinessClassSeats", "TouristClassSeats", "EconomyClassSeats"};
		return dataName;
	}
	
	public String[] getData() {
		String[] data = {Name, String.valueOf(Brand) ,String.valueOf(BusinessClassSeats) ,String.valueOf(TouristClassSeats), String.valueOf(EconomyClassSeats)};
		return data;
	}

	@Override
	public String toString() {
		return Name + "," + Brand + "," + BusinessClassSeats
				+ "," + TouristClassSeats + "," + EconomyClassSeats;
	}
}