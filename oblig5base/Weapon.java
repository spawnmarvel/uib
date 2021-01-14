
/**
 * This is a class representing a weapon in an RPG
 * 
 * @author Andreas Bjerknes
 * @version 22.04.2010
 */
public class Weapon extends Item
{   
private int damage;
    
    /**
     * Constructor for class weapon
     * @param name The name of a weapon
     * @param description The description of a weapon
     * @param action The action a weapon performs
     * @param value The value of a weapon
     * @param damage The damage a weapon can cause
     */
    public Weapon(String name, String description, String action, int value, int damage)
    {
        super(name, description, action, value);
        setDamage(damage);
    }
    
    /**
     * Sets the damage of a weapon
     * @param damage The damage a weapon can cause
     */
    public void setDamage(int damage){
    this.damage = Util.checkNotNegativeInt(damage);
    }
    
    /**
     * Gets the damage of a weapon
     * @return damage The damage a weapon can cause
     */
    public int getDamage()
    {
        return damage; 
    }
        
}