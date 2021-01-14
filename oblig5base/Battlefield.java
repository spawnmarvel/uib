import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;
/**
 * This is the battlefield of an RPG where the hero will fight the monster
 * 
 * @Author Andreas Bjerknes
 * @Version 23.04.2010 
 */
public class Battlefield implements Serializable{
    private ArrayList<Monster> monsters;
    private Player player;
    private Monster currentMonster;
    private Random random;

    /**
     * Constructor for objects of class Battlefield
     * @param player The player that will fight monsters in the RPG
     */
     public Battlefield(Player player){
         monsters = new ArrayList<Monster>();
         this.random = new Random();
         this.player = player;
         //Makes sure we have some monsters 
         fillMonsters();
         setCurrentMonster();       
     }
    

    
    /**
    * Adding monsters to the battlefield
    * @param monster An instance of class monster that will be added to the collection.
    */ 
    public void addMonster(Monster monster)
    {   
        //You can not add the same monster twice, and you don't add a dead monster.
        if(!monsters.contains(monster) && monster.isAlive()){
            monsters.add(monster);
        }
    }
    
   /**
    * Selects a random monster from the available monsters
    * @return monster A monster from the collection or null if the collection is empty.
    */ 
    public void setCurrentMonster(){
        if(monsters.size() != 0){
            int randomMonster = random.nextInt(monsters.size());
            Monster monster = monsters.get(randomMonster);
            this.currentMonster = monster;
        }
        else{
            currentMonster = null;
        }
    }
   
    /**
     * Returns the current active Monster from the Battlefield
     * @return the current Monster
     */
    public Monster getCurrentMonster(){
        return currentMonster;
    }

   /**
    * Removes a monster from the collection.
    * @param monster the monster to be removed.
    */
    public void removeMonster(Monster monster){
        monsters.remove(monster); 
    }
   
   /**
    * Returns the Player who's currently fighting in the arena
    * @return Player
    */
    public Player getPlayer(){
        return player; 
    }
   
   /**
    * Creates a couple of (cookie) monsters so that the hero has someone to fight when he enters the arena
    */
    public void fillMonsters(){
        addMonster(new Monster("Cookiemonster", 40, 30, 150, 40, "cookiemonster.png"));
        addMonster(new Monster("Hard Cookiemonster", 40, 30, 250, 40, "cookiemonster.png"));
    }
}
