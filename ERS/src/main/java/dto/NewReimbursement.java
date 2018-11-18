package dto;

public class NewReimbursement {
	private int id;
	private double amount;
	private String description;
	private String type;
	
	public NewReimbursement(int id, double amount, String description, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public double getAmount() {
		return amount;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
	
	
}
