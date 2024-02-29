package client.card.business.enumeration;

public enum FieldObjectEnum {
	NAME("name"),
	LASTNAME("lastName"),
	ADDRESS("address"),
	EMAIL("email"),
	MOBILE("mobile");
	
	private final String name;
	
	FieldObjectEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
