package beans;

import java.sql.Timestamp;

public class Reimbursement {
	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int author;
	private int resolver;
	private int statusID;
	private int typeID;
	
	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, 
			int author, int resolver, int statusID, int typeID) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.statusID = statusID;
		this.typeID = typeID;
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public String getDescription() {
		return description;
	}

	public int getAuthor() {
		return author;
	}

	public int getResolver() {
		return resolver;
	}

	public int getStatusID() {
		return statusID;
	}

	public int getTypeID() {
		return typeID;
	}
	
}
