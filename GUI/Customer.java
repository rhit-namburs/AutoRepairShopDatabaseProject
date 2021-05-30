package gui;

public class Customer {

	String userName;
	int numberOfVisits;

	public Customer(String username, int numberOfVisits) {
		this.userName = username;
		this.numberOfVisits = numberOfVisits;
	}

	public String getusername() {
		return this.userName;
	}

	public int getnumberOfVisits() {
		return this.numberOfVisits;
	}
}
