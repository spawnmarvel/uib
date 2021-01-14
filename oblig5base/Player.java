import java.util.HashMap;
import java.util.Random;

/**
 * This class represents a Player in an RPG.
 * 
 * @author Andreas Bjerknes
 * @version 23.04.2010
 */
public class Player extends Character
{
    private String type;
    private HashMap<String, Item> items;
    private Weapon weapon;
    
    /**
     * Constructor for objects of class Player.,
     * @param name The player's name
     * @param type The player's type
     * @param maxDamage The maximum damage a player can do
     * @param minDamange The minimum damage a player can do
     * @param health The health of the player.
     */
    public Player(String name, String type, int maxDamage, int minDamage, int health, String img)
    {
        super(name, health, maxDamage, minDamage, 50, img);
        setType(type);
        items = new HashMap <String, Item>();
    }
    
    /**
     * Method which increases a player's attack-damage if a weapon is equipped
     * @return damage The damage a player's attack will have
     */
    public int attack(){
        int damage = super.attack();
        if(weapon != null){
            damage += weapon.getDamage();
        }
        return damage;
    }
    
    
    /**
     *  Prints out information about the player 
     */
    public void print(){
        System.out.println(this);
    }
    
    /**
     * Adds an item to the player's items
     * @param item The Item to be added
     */
    public void addItem(Item item){
        items.put(item.getName().toLowerCase(), item);
    }
    
    /**
     * Method which lets a player equip a weapon in combat
     * @param searchString A searchstring that finds an item
     */
    public void equipWeapon(String searchString){
        Item i = findItem(searchString);    
        if(i instanceof Weapon){
            this.weapon = (Weapon) i;
            if(this.weapon != null){
                addItem(this.weapon);
            }
            items.remove(i.getName());
        }
    }
    
    /**
     * Method wich lets a player drink a health-potion during combat
     * @param searchString A searchstring that finds an item
     */
    public void drinkPotion(String searchString){
        Item i = findItem(searchString);
        if(i instanceof Potion){
            Potion potion = (Potion) i;
            changeHealth(potion.getReplenish());         
            items.remove(i.getName());
        }
        
    }
    
    /**
     * Searches for an item based on the item name, and returns the item, if found
     * @param searchString The name of the item to be found.
     * @return The first item in the items Hashmap to match the searchString, null if no items match
     */
    private Item findItem(String searchString){
        Item i = items.get(searchString);
        if(i == null){
            System.out.println(searchString + " was not found");
        }
        return i;
    }
    
    /**
     * Sells an item by adding its value to the owning player's gold, and removing it from his items collection
     * @param searchString The name of the item to be sold.
     */
    public boolean sellItem(String searchString){
        Item i = findItem(searchString);
        
        //Check to see that an Item was actually found
        if(i != null){
            setGold( i.getValue());
            items.remove(i.getName());
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Uses an item by printing the name of the player, the name of the item,
     * and the item's action description to the console
     * @param searchString The name of the item to be used.
     */
    public boolean useItem(String searchString){
        Item i = findItem(searchString);
        
        //Check to see that an Item was actually found
        if(i != null){
            System.out.println(getName() + " " + i.getAction() + " " + i.getName());
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
    * Prints out a message when you run, and how much gold you loose according to how much gold the player has.
    */
   public String run(){
        String s = getName() +" run over the hills and far far away! ";
        
        //If the player has enough gold to loose 50 gold.
        if(getGold() >= 50){
            changeGold(-50);
                return s + getName() + " loose 50 gold in the process.";
        }
   
        //If the player is broke
        else if(getGold() == 0){
                return s + "Fortunately " + getName() +" has not any gold to loose.";
        }    
   
        //If he has some gold but not 50 he looses all
        else{
            int goldLeft = getGold();
            changeGold(-goldLeft);
               return s + getName() +" loose " + goldLeft +  " gold in the process.";
        }
   }
       
    /**
     * Sets the type of the player
     * @param type Type of player
     */
    public void setType(String type)
    {
        type = type.toLowerCase();
        
        if(type.equals("mage") || type.equals("ranger") || type.equals("rogue") || type.equals("warrior")){
            this.type = type;
        }
        else{
            this.type = "Unspecified";
        }
    }
    
    /**
     * Gets the type of the player
     * @return type Type of the player
     */
    public String getType(){
        return type;
    }
    
    
    /**
     * Method returning information about a player
     * @return Information about a player
     */
    public String toString(){
        String s  = "Player Information: ";
        s += super.toString();
        s += "Class: " + getType() + "\n\n";
        
        
        int counter = 1;
        for(Item i : items.values()){
           s += counter + ": ";
           s += i;
           counter++;
        }
        return s;
    }
}
