
/**
 * This is a class for the main method of an RPG
 * 
 * @author Andreas Bjerknes
 * @version 23.04.2010
 */
public class Game
{
    

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
       
    }

    /**
     * Main method for this RPG
     * Creates a Player, a Battlefield (which in turn creates some Monsters, a GUI and a BattleController to start the game
     * 
     * @param String[] args
     */
    public static void main(String[] args)
    {
      
        Player player1 = new Player("Arnulf", "Mage", 60, 50, 200, "player.png");
        Battlefield battlefi1 = new Battlefield(player1);
        GUI gui = new GUI(battlefi1);
        BattleController bc = new BattleController(gui, battlefi1);

      
    }
}
