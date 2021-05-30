package gui;

public class Vehicle {
	String VIN, model, bodyType;
	int year, mileage;
	public Vehicle(String VIN, int year,String model, int mileage, String bodyType) {
		this.VIN = VIN;
		this.model = model;
		this.bodyType = bodyType;
		this.year = year;
		this.mileage = mileage;
	}
	public int getYear() {
		return this.year;
	}
	public int getMileage() {
		return this.mileage;
	}
	public String getVin() {
		return this.VIN;
	}
	public String getModel() {
		return this.model;
	}
	public String getBodyType() {
		return this.bodyType;
	}
}
