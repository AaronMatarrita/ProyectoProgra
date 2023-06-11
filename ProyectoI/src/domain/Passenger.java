package domain;

public class Passenger {

	private String Passport;
	private String Name;
	private String Lastname;
	private String Dateofbirth;
	private String Email;
	private String PhoneNumber;
	
	
	
	public Passenger(String passport, String name, String lastname, String dateofbirth, String email,
			String phoneNumber) {
		super();
		Passport = passport;
		Name = name;
		Lastname = lastname;
		Dateofbirth = dateofbirth;
		Email = email;
		PhoneNumber = phoneNumber;
	}

	public String getPassport() {return Passport;}

	public String getName() {return Name;}

	public String getLastname() {return Lastname;}

	public String getDateofbirth() {return Dateofbirth;}

	public String getEmail() {return Email;}

	public String getPhoneNumber() {return PhoneNumber;}

	public void setPassport(String passport) {Passport = passport;}

	public void setName(String name) {Name = name;}

	public void setLastname(String lastname) {Lastname = lastname;}

	public void setDateofbith(String dateofbirth) {Dateofbirth = dateofbirth;}

	public void setEmail(String email) {Email = email;}

	public void setPhoneNumber(String phoneNumber) {PhoneNumber = phoneNumber;}

	public String[] getDataName() {
		String[] dataName = {"Passport", "Name", "Lastname", "Dateofbirth", "Email", "PhoneNumber"};
		return dataName;
	}
	
	public String[] getData() {
		String[] data = {Passport, Name, Lastname, Dateofbirth, Email, PhoneNumber};
		return data;
	}
	
	@Override
	public String toString() {
		return Passport + "," + Name + "," + Lastname + "," + Dateofbirth + "," + Email + "," + PhoneNumber;
	}
}
