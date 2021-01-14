import java.util.Random;
/**
 * A class representing a monster in an RPG
 * 
 * @author Andreas Bjerknes
 * @version 23.04.2010
 */
public class Monster extends Character
{
 
   
    private String difficulty;
    

    /**
     * Constructor of objects of class Monster
     * @param name The monster's name
     * @param maxDamage The maximum damage the monster can do
     * @param minDamage The minimum damage the monster can do
     * @param health The health of the monster
     * @param gold The gold of the monster that any player who kills it receives
     * @param img The name of an image in the /img folder to be used as a monster's "profile picture"
     */
    public Monster(String name, int maxDamage, int minDamage, int health, int gold, String img)
    {
        super(name, health, maxDamage, minDamage, gold, img);
        findDifficulty();
    }
   
    /**
     * Sets the difficulty of a monster based on the monster's max damage and health
     */
    public void findDifficulty()
    {
       
        if( (getMaxDamage() + getHealth()) <= 39){
            difficulty = "Easy";
        }
        if((getMaxDamage() + getHealth()) > 39){
            difficulty = "Average";
        }
        if( (getMaxDamage() + getHealth()) > 59){
            difficulty = "Hardened";
        }
        if( (getMaxDamage() + getHealth()) > 79){
            difficulty = "Veteran";
        }
        if( (getMaxDamage() + getHealth()) > 99){
            difficulty = "Impossible";
        }
    }
    
    /**
     * Returns the monster's difficulty
     * @return The monster's difficulty
     */
    public String getDifficulty()
    {
        return difficulty;
    }
    
    /**
     * Represents the Monster object as a string
     */
    public String toString(){
        String s  = "Monster Information: ";
        s += super.toString();
        s += "Difficulty: " + getDifficulty() + "\n\n";
         return s;
    }      
}
