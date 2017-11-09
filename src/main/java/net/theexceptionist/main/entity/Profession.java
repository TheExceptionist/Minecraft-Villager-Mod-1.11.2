package net.theexceptionist.main.entity;

public class Profession {
	private String name;
	private int id;
	public static final int SOLDIER_ID = 0;
	
	public Profession(String name, int id){
		this.name = name;
		this.id = id;
	}
		
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public int getID(){
		return id;
	}

}
