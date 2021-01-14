
/**
 * This is a class representing a potion in an RPG
 * 
 * @author Andreas Bjerknes
 * @version 22.04.2010
 */
public class Potion extends Item
{   
private int replenish;

    /**
     * The constructor of class Potion
     * @param name The name of a potion
     * @param description The description of a potion
     * @param action The action of a potion
     * @param value The value of a potion
     * @param replenish The amount of health a potion replenishes
     */
    public Potion(String name, String description, String action, int value, int replenish)
    {
        super(name, description, action, value);
        setReplenish(replenish);
    }
    
    /**
     * Sets the amount of health a potion replenishes
     * @param replenish The amount of health replenished
     */
    public void setReplenish(int replenish){
    this.replenish = Util.checkNotNegativeInt(replenish);
    }
    
    /**
     * Gets the amount of health a potion replenishes
     * @return replenish The amount of health replenished
     */
    public int getReplenish()
    {
        return replenish; 
    }
        
}