package domain;

public class Brand {

	private String Brand;
	
	public Brand() {}

	public Brand(String Brand) {
		super();
		this.Brand = Brand;
		
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String Brand) {
		this.Brand = Brand;
	}

	
	public String[] getDataName() {
		String[] dataName = {"brand"};
		return dataName;
	}
	
	public String[] getData() {
		String[] data = {Brand};
		return data;
	}
	
	@Override
	public String toString() {
		return Brand;
	}
}
