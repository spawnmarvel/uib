import java.util.Random;
import java.io.Serializable;
/**
 * A class representing a character in an RPG
 * 
 * @Author Andreas Bjerknes
 * @version 20.04.2010
 */
public abstract class Character implements Serializable
{
    private int health;
    private int maxhealth;
    private String name;
    private int maxDamage;
    private int minDamage;
    private int gold;
    private Random random;
    private String img; 

    /**
     * Constructor for objects of class Character
     * @param name Name of a character
     * @param health Health of a character
     * @param maxDamage Max damage of a character's attack
     * @param minDamage Minimum damage of a character's attack
     * @param gold Gold of a character
     */
    public Character(String name, int health, int maxDamage, int minDamage, int gold, String img)
    {
        setName(name);
        setHealth(health);
        setMaxHealth(health);
        setGold(gold);
        setMinMaxDamage(minDamage, maxDamage);
        this.random = new Random();
        this.img = img;
        
    }
    
    /**
     * Sets the name of a character's image located in the /img folder of the project
     * @param img The name of the image (including file ending)
     */
    public void setImg(String img){
        this.img = img;
    }
    
    /**
     * Returns the name of the image
     * @return The name of the image (including file ending)
     */
    public String getImg(){
        return img;
    }

    /**
     * Method deciding what damage a character's attack will have
     * 
     * @return randomDamage Damage of the character's attack 
     */
    public int attack()
    {    
       int randomDamage = random.nextInt((maxDamage + 1) - minDamage);
       randomDamage += minDamage;
       return randomDamage;
    }
    
    /**
    * A method for changing the health of a character
    *@param value The amount health will change
    */
    public void changeHealth(int value)
    {
        if(health + value >= 0){
            health += value; 
        } else{
            health = 0;
        }
    }
    /**
     * Alters the gold
     * @param value The value that the characters's gold supply should be altered with
     */
    public void changeGold(int value){
        if(gold + value >= 0){
            gold += value; 
        } 
        else{
            gold = 0;
        }
    }
    /**
     * Method that checks if the character is alive
     * return boolean true if the character has over 0 in health and false otherwise.
     */
      public boolean isAlive()
    {
        return (health > 0);
    }
    /**
     * Set the name of the character
     * @param name The name of the character
     */
     public void setName(String name){
      this.name = Util.checkString(name); 
    }
   
    public String getName()
    {
        return name; 
    }
    
    
    
    /**
     * Get the gold of the character
     * @return The gold of the character
     */
    public int getGold(){
        return gold;
    }
    
    /**
     * Get the health of the character
     * @return The health of the character
     */
    public int getHealth(){
        return health;
    }
    
    
     /**
     * Sets the character's health
     * @param health The character's health
     */
    public void setHealth(int health){
        this.health = Util.checkNotNegativeInt(health);
    }
    
    /**
     * Get the max health of the character
     * @return The max health of the character
     */
    public int getMaxHealth(){
        return maxhealth;
    }
    
    
    /**
     * Sets the character's max health
     * @param health The character's health
     */
    public void setMaxHealth(int health){
        this.maxhealth = Util.checkNotNegativeInt(health);
    }
     /**
     * Sets the character's gold
     * @param gold The character's  gold
     */
    public void setGold(int gold){
        this.gold = Util.checkNotNegativeInt(gold);
    }
    
     /**
     * Sets the maximum and minimum damage that the character can inflict to an enemy
     * @param minDamage The minimal damage
     * @param maxDamage The maxium damage.
     */
    public void setMinMaxDamage(int minDamage, int maxDamage){
        minDamage = Util.checkNotNegativeInt(minDamage);           
        maxDamage = Util.checkNotNegativeInt(maxDamage);
        this.minDamage = minDamage;
        
        //We check that the maxDamage is higher than the minDamage.
        if(maxDamage > minDamage){
            this.maxDamage = maxDamage;
        }
        else{
            this.maxDamage = this.minDamage + 1;
        }
    }
    
    /**
     * Gets the minimum damage of a character
     * @return minDamage The minimum damage of a character
     */
    public int getMinDamage(){
    return minDamage;
    }
    
    /**
     * Gets the maximum damage of a character
     * @return maxDamage The maximum damage of a character
     */
    public int getMaxDamage(){
    return maxDamage;
    }
    
    /**
     * Method returning information about a character
     * @return Information about a character
     */
    public String toString(){
        String s = name + "\n " +
        "Health: " + health + "\n" +
        "Gold:" + gold + "\n";
        
        return s;
    }
    
    
    
        
}
