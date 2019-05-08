package model;

public enum Destinies {
	
	JAPON("Japon"),
	CALI("Cali York"),
	TITAN("Titan"),
	VENECIA("Venecia"),
	PANEM("Panem"), 
	KONOHA("Konoha"),
	OLIMPO("Olimpo"),
	MCDONALDS("MCdonalds"),
	PANCE("Pance"),
	HOGWARTS("Hogwarts");
	
private String name;
	
	private Destinies(String n) {
		this.name = n;
	}
	
	public int getValue() {
		return Destinies.values().length;
	}
	
	public String getName() {
		return name;
	}
}
	


