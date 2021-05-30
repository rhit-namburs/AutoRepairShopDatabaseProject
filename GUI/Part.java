package gui;

public class Part {
	int PartNumber, Price;
	String Name;

public Part(int PartNumber,String Name,int Price ) {
	this.PartNumber = PartNumber;
	this.Price = Price;
	this.Name = Name;
}

	public int getPartNumber() {
		return this.PartNumber;
	}

	public int getPrice() {
		return this.Price;
	}

	public String getName() {
		return this.Name;
	}

}
