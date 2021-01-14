/**
 * The Item class as it looks after the third assignment.
 * 
 * @author Andreas Bjerknes
 * @version 23.04.2010
 */
public class Item
{
    private String name;
    private String description;
    private String action;
    private int value;
    
    /**
     * Constructor for objects of class Item
     * @param name The Item's name
     * @param description A description of the item
     * @param action The verb or expression to be printed when a player uses the item
     * @param value The item's value in gold
     */
    public Item(String name, String description, String action, int value)
    {
        setName(name);
        setDescription(description);
        setAction(action);
        setValue(value);
    }
    
    /**
     * Prints information about the item to console
     */
    public void print(){
        System.out.println(this); 
    }
    
    /**
     * Sets an item's name. If empty, sets it to "Unspecified"
     * @param name The Item's name
     */
    public void setName(String name){
        this.name = Util.checkString(name);
    }
    
    /**
     * Gets an item's name
     * @return The name of the item
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets an item's description. If empty, sets it to "Unspecified"
     * @param description A short description of the item
     */
    public void setDescription(String description){
      this.description = Util.checkString(description); 
    }
    
    /**
     * Gets an item's description
     * @return The description of the item
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * Sets an item's action. If empty, sets it to "Unspecified"
     * @param action the item's action.
     */
    public void setAction(String action){
        this.action = Util.checkString(action);
    }
    
    /**
     * Gets an item's description of the action that occurs when you use it
     * @return The word/expression to be printed when you use an item
     */
    public String getAction(){
        return action;
    }
    
    /**
     * Sets an item's name. If empty, sets it to "Unspecified"
     * @param value The value of the Item in gold.
     */
    public void setValue(int value){
        if(value > 0){
            this.value = value;
        } 
        else{
            value = 0;
        }
    }
    
    /**
     * Gets an item's value in gold
     * @return The item's value in gold
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Returns the item information as a string.
     * @return a representation of the object as a String.
     */
    public String toString(){
        return name+ " ("+description+") Value: " + value + " gold \n"; 
    }
}
