package domain;

public class AirplaneModel {

	private String Name;
	private String Brand;
	private int  BusinessClassSeats;
	private int  TouristClassSeats;
	private int  EconomyClassSeats;
	private int  totalBusinessClassSeats;
	private int  totalTouristClassSeats;
	private int  totalEconomyClassSeats;
	
	public AirplaneModel() {}

	public AirplaneModel(String Name,String Brand, int BusinessClassSeats, int TouristClassSeats,
			int EconomyClassSeats, int totalBusinessClassSeats, int totalTouristClassSeats,
			int totalEconomyClassSeats) {
		super();
		this.Name = Name;
		this.Brand = Brand;
		this.BusinessClassSeats = BusinessClassSeats;
		this.TouristClassSeats = TouristClassSeats;
		this.EconomyClassSeats = EconomyClassSeats;
		this.totalBusinessClassSeats = totalBusinessClassSeats;
		this.totalTouristClassSeats = totalTouristClassSeats;
		this.totalEconomyClassSeats = totalEconomyClassSeats;
	}
	
	
	public int getTotalBusinessClassSeats() {
		return totalBusinessClassSeats;
	}

	public void setTotalBusinessClassSeats(int totalBusinessClassSeats) {
		this.totalBusinessClassSeats = totalBusinessClassSeats;
	}

	public int getTotalTouristClassSeats() {
		return totalTouristClassSeats;
	}

	public void setTotalTouristClassSeats(int totalTouristClassSeats) {
		this.totalTouristClassSeats = totalTouristClassSeats;
	}

	public int getTotalEconomyClassSeats() {
		return totalEconomyClassSeats;
	}

	public void setTotalEconomyClassSeats(int totalEconomyClassSeats) {
		this.totalEconomyClassSeats = totalEconomyClassSeats;
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
		String[] dataName = {"modelName", "brand", "BusinessClassSeats", "TouristClassSeats", "EconomyClassSeats", "TotalBusinessClassSeats", "TotalTouristClassSeats", "TotalEconomyClassSeats"};
		return dataName;
	}
	
	public String[] getData() {
		String[] data = {Name, String.valueOf(Brand) ,String.valueOf(BusinessClassSeats) ,String.valueOf(TouristClassSeats), String.valueOf(EconomyClassSeats),String.valueOf(totalBusinessClassSeats) ,String.valueOf(totalTouristClassSeats), String.valueOf(totalEconomyClassSeats)};                                          
		return data;   
	}

	@Override
	public String toString() {
		return Name + "," + Brand + "," + BusinessClassSeats
				+ "," + TouristClassSeats + "," + EconomyClassSeats + totalBusinessClassSeats
				+ "," + totalTouristClassSeats + "," + totalEconomyClassSeats;
	}
}