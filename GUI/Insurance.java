package gui;

public class Insurance {

	String ClaimNumber,PolicyNumber;
	int deductible;
	public Insurance(String ClaimNumber,String PolicyNumber, int deductible) {
		this.ClaimNumber = ClaimNumber;
		this.PolicyNumber = PolicyNumber;
		this.deductible = deductible;
	}
	
	public String getClaimNumber() {
		return this.ClaimNumber;
	}
	public String getPolicyNumber() {
		return this.PolicyNumber;
	}
	public int getDeductible() {
		return this.deductible;

	}
}
