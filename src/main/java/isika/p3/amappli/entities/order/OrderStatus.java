package isika.p3.amappli.entities.order;

public enum OrderStatus {
	
	PENDING("En attente"),
	IN_PROGRESS("En cours"),
	DONE("Terminée");
	
	private String displayName;
	
	OrderStatus(String displayName){
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

}
