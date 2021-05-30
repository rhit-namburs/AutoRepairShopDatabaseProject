package gui;

public class Repair {
	int ID;
	String startDate;
	String endDate;
	String Description;
	int Discount;
	int TotalCost;
	int Completion;
	Object priceCompleted ;
	public Repair(int ID,String startDate,String endDate,String Description, int Discount, int TotalCost, int Completion) {
		this.ID = ID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.Description = Description;
		this.Discount = Discount;
		this.TotalCost = TotalCost;
		this.Completion = Completion;
	}
	public int getID() {
		return this.ID;
	}
	
	public String getstartDate() {
		return this.startDate;
	}
	
	public String getendDate() {
		return this.endDate;
	}
	
	public String getDescription() {
		return this.Description;
	}
	
	public int getDiscount() {
		return this.Discount;
	}
	
	public int getTotalCost() {
		return this.TotalCost;
	}
	
	public int getCompletion() {
		return this.Completion;
	}
	
}
