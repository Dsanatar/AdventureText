import java.util.Scanner;

public class Menu {

	public static RandomDeath[] levels;
	public static String[] namesE;	//list of names for easy mobs
	public static String[] namesM;  //medium
	public static String[] namesH;  //hard
	public static Char c;
	
	public static void main(String[] args){
		setup();
		/*
		if(!isDead()){
			random();
		}
		*/
		fight(getEnemy());
	}
	
	//Creates and resets the game
	public static void setup(){
		String m = "What is your name?";
		slow(m);
		stop();
		Scanner n = new Scanner(System.in);
		String name = n.nextLine();
		c = new Char(name);
		
		namesE = new String[3];
		namesE[0] = "Giant Rat";
		namesE[1] = "Goblin";
		namesE[2] = "Sentient Slime";
		
		namesM = new String[3];
		namesM[0] = "Troll";
		namesM[1] = "Swarm of Bats";
		namesM[2] = "Skeleton";
		
		namesH = new String[3];
		namesH[0] = "Minotaur";
		namesH[1] = "Warlock";
		namesH[2] = "Dark Knight";
		
	}
	
	//Determines each scenario
	public static void random(){
		int ran = getRandom(9);
		//Possible death
		if(ran >= 2){
			int ranDeath = getRandom(levels.length);
			RandomDeath d = levels[ranDeath];
		}
		//Possible loot
		else if(ran <= 3 && ran > 6){
			
		}
		//Fight
		else{
			
		}
	}
	
    //returns random Mob based on progression
    public static Mobs getEnemy(){
    	if(c.getRooms() <= 3){
    		//generate ez mob
    		int ran = getRandom(namesE.length);
    		return generateMob(ran, "E");
    	}
    	else if(c.getRooms() > 3 && c.getRooms() <= 6){
    		//generate med mob
    		int ran = getRandom(namesM.length);
    		return generateMob(ran, "M");
    	}
    	else{
    		//generate hard mob
    		int ran = getRandom(namesH.length);
    		return generateMob(ran, "H");
    	}
    }
    
    public static Mobs generateMob(int ran, String lvlSet){
    	String name;
    	int attk;
    	
    	switch(lvlSet){
    		//generates mob based on determined lvlset
    		case("E"):
    			//creates dmg bonus
    			attk = getRandom(2)-1;
    			name = namesE[ran];
    			break;
    		case("M"):
    			attk = getRandom(3);
    			name = namesM[ran];
    			break;
    		default:
    			attk = getRandom(4) + 1;
    			name = namesH[ran];
    			break;
    	}
    	return new Mobs(name, attk);
    }
	
	
	public static boolean isDead(){
		return c.getLives() <= 0;
	}
	
	
    public static int getRandom(int max){
        return (int) (Math.random()*max);
    }
    
    
    public static void fight(Mobs m){
    	slow("You encounter a " + m.getName());
    	stop();
    	slow("Fight or Flee?");
    	stop();
    	Scanner s = new Scanner(System.in);
    	String choice = s.nextLine();
    	choice.toLowerCase();
    	if(choice.equals("fight")){
    		//Simulate fight
    		int player_roll = 0;
    		int other_roll = 0;
    		int roll1 = getRandom(6) + 1;
    		int roll2 = getRandom(6) + 1;
    		slow("You roll a ... ... ");
    		System.out.println(roll1);
    		slow("Would you like to: Keep or Pass?");
    		stop();
    		String choice2 = s.nextLine();
    		choice2.toLowerCase();
    		
    		if(choice2.equals("keep")){
    			player_roll = roll1;
    			slow("The ");
    			slow(m.getName());
    			slow(" rolls a ... ... ");
    			System.out.println(roll2);
    			other_roll = roll2;
    		}
    		else if(choice2.equals("pass")){
    			other_roll = roll1;
    			slow("You roll a ... ... ");
    			System.out.println(roll2);
    			player_roll = roll2;
    		}
    		else{
    			System.out.print("Error! Command not found");
    			stop();
    			slow("Keep or Pass?");
    			stop();
    		}
    		
    		player_roll += c.getDamage();
    		other_roll += m.getDamage();
    		slow("After attack bonuses ...");
    		stop();
    		System.out.print(player_roll);
    		slow(" v ");
    		System.out.print(other_roll);
    		stop();
    		
    		if(player_roll > other_roll){
    			slow("You slay the ");
    			System.out.print(m.getName() + "!");
    			stop();
    			c.addKills();
    		}
    		else if(other_roll > player_roll){
    			slow("The ");
    			System.out.print(m.getName());
    			slow(" beats yo ass into the dirt. You loose a life");
    			c.damage(1);
    		}
    		else{
    			slow("It appears to be a draw...");
    			fight(m);
    		}
   
    		
    	}
    	else if(choice.equals("flee")){
    		//Attempt flee
    	}
    	else{
    		System.out.print("Error, command not found");
    		slow("Fight or Flee?");
    		stop();
    	}
    }
    
	
	//Imitates type writer effect
	public static void slow(String message){
		for(int i = 0; i < message.length(); i++){
			System.out.print(message.charAt(i));
			
			try{
				Thread.sleep(70);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		//System.out.println();
	}
	
	public static void stop(){
		try{
			Thread.sleep(20);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println();
	}
}
