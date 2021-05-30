package gui;

public class CustomerView {
String Username, FirstName, LastName;
Integer NumberOfVisits;
	public CustomerView(String Username, Integer NumberOfVisits, String FirstName, String LastName) {
		this.Username = Username;
		this.NumberOfVisits = NumberOfVisits;
		this.FirstName = FirstName;
		this.LastName = LastName;
	}
}
