package gui;

public class Order {
	String ManagerUserName;
	int PartNumber;
	public Order(String managerUserName, int PartNumber) {
		this.ManagerUserName = managerUserName;
		this.PartNumber = PartNumber;
	}
	
	public String getManagerUserName() {
		return this.ManagerUserName;
	}
	
	public int getPartNumber() {
		return this.PartNumber;
	}
	
}
