public class Mobs {
	
	private String name;
	private int damage;
	
	public Mobs(String n , int d){
		name = n;
		damage = d;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return damage;
	}
}
