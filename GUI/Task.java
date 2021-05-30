package gui;

public class Task {
	int ID, Price,Completion;
	String Name, Description;
	public Task(int ID, String Name, String Description, int price, int Completion) {
		this.ID = ID;
		this.Price = Price;
		this.Name = Name;
		this.Description = Description;
		this.Completion = Completion;
	}
	
	public int getID() {
		return this.ID;
	}
	public int getPrice() {
		return this.Price;
	}
	public String getName() {
		return this.Name;
	}
	public String getDescription() {
		return this.Description;
	}
	public int getCompletion() {
		return this.Completion;
	}
	
}
