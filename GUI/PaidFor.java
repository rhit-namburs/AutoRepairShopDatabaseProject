package gui;

public class PaidFor {
	String VehicleVIN, InsuranceClaimNumber, Receipt;
	int RepairID;
	public PaidFor(String VehicleVIN, int RepairID, String InsuranceClaimNumber, String Receipt) {
		this.VehicleVIN = VehicleVIN;
		this.InsuranceClaimNumber = InsuranceClaimNumber;
		this.Receipt = Receipt;
		this.RepairID = RepairID;
	}
	public String getVehicleVIN() {
		return this.VehicleVIN;
	}
	public String getInsuranceClaimNumber() {
		return this.InsuranceClaimNumber;
	}
	public String getReceipt() {
		return this.Receipt;
	}
	public int getRepairID() {
		return this.RepairID;
	}
}
