
public class Char extends Menu {
	
	private static int lives;
	private int killCount;
	private int goldCount;
	private int roomCount;
	private String name;
	private Item w;
	
	public Char(String n){
		lives = 3;
		killCount = 0;
		goldCount = 0;
		roomCount = 0;
		name = n;
		w = new Item("Fist" , 0);
	}
	
	public String getName(){
		return name;
	}
	
	public String getWeapon(){
		return w.getName();
	}
	
	public int getDamage(){
		return w.getDamage();
	}

	public int getLives(){
		return lives;
	}
	
	public static boolean isDead(){
		return lives <= 0;
	}
	
	public void damage(int a){
		lives -= a;
	}
	
	public void heal(int a){
		lives += a;
	}
	
	public void addKills(){
		killCount++;
	}
	
	public int getKills(){
		return killCount;
	}
	
	public int getGold(){
		return goldCount;
	}
	
	public int getRooms(){
		return roomCount;
	}
}
