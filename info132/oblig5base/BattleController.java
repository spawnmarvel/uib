import java.awt.event.*;
import java.io.*;
/**
 * This class is responsible for handling events from the GUI, and the logic of a game round
 * 
 * @author C-style
 * @version oblig 5
 */
public class BattleController implements ActionListener
{
    private GUI gui;
    private Battlefield battlefield;

    /**
     * constructor for objects of class BattleController
     * @param gui The GUI to display the information in the game
     * @param bf The Battlefield with the player and the monsters
     */
    public BattleController(GUI gui, Battlefield bf) {
        this.gui = gui;
        this.battlefield = bf;
        gui.addListeners(this);
    }
    
    /**
     * The method for handling ActionEvents, such as clicked buttons. Defined in the ActionListener interface
     * @param e The ActionEvent. The element the action is invoked on must have a listener to register the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
//         if(e.getSource() == this.gui.getSaveButton()) {
//             saveToFile();
//         }
    }
    
    /**
     * Saves the Battlefield object to disk.
     */
    private void saveToFile() {
        //Code goes here
    }
    
    /**
     * Reads a Battlefield object from disk.
     */
    private void readFromFile(){
        //Code goes here
    }
    
    /**
     * Handles one rund of attack. Logic that was previously in the Battlefield class
     */
    public void attackRound() {
        Monster currentMonster = battlefield.getCurrentMonster();
        Player player = battlefield.getPlayer();

        if(currentMonster != null && currentMonster.isAlive()){
            if(player.isAlive()){
                hit(player,currentMonster);

                if(currentMonster.isAlive()){
                    hit(currentMonster, player);
                }

                else{
                    gui.updateActionField("Monster is dead");
                    battlefield.removeMonster(currentMonster);
                    battlefield.setCurrentMonster();
                    gui.changeMonster(battlefield.getCurrentMonster());
                }
            }

            else{
                gui.updateActionField("You are dead");
            }
        }
    }

    /**
     * A method for one Character to hit another Character. Deals damage, updates the health
     * graphics and writes the event to the bottom of the screen
     * @param c1 The Character that attacks
     * @param c2 The Character that is attacked
     */
    private void hit(Character c1, Character c2){ 
        int damage = c1.attack();
        c2.changeHealth(-(damage));
        gui.updateHealth(c2);
        gui.updateActionField(c1.getName()+  " hits " + c2.getName() + " for " + damage + " damage ");           
    }

}
