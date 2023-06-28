package domain;

public class User {

	private String User;
	private String Password;
	private int UserType;
	private boolean Status;
	
	public User() {}

	public User(String User, String Password, int UserType, boolean Status) {
		super();
		this.User = User;
		this.Password = Password;
		this.UserType = UserType;
		this.Status = Status;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String User) {
		this.User = User;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public int getUserType() {
		return UserType;
	}

	public void setUserType(int UserType) {
		this.UserType = UserType;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean Status) {
		this.Status = Status;
	}
	
	public String[] getDataName() {
		String[] dataName = {"user", "password", "userType", "status"};
		return dataName;
	}
	
	public String[] getData() {
		String[] data = {User, Password, String.valueOf(UserType), String.valueOf(Status)};
		return data;
	}
	
	@Override
	public String toString() {
		return User + "," + Password + "," + UserType + "," + Status;
	}
}
