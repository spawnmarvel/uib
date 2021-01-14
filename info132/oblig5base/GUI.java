import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * The class responsible for setting up the GUI for the RPG
 * @version Oblig 5
 * @author C-Style & Beno
 */
public class GUI extends JFrame
{
    private BattleController battleController;
    private Battlefield battlefield;
    private JPanel toolbarContainer;
    private Card playerCard, monsterCard;
    private JPanel playerCardPanel, monsterCardPanel, bgPanel;
    private JTextArea actionField;


    /**
     * Constructor for objects of class GUI. Takes a Battlefield and sets up the GUI based on the player and current monster
     * @param bf The Battlefield
     */
    public GUI(Battlefield bf)
    {
        this.battlefield = bf;
        this.monsterCard = new Card(bf.getCurrentMonster());
        this.playerCard = new Card(bf.getPlayer());
        makeMenu();
        makeFrame();
    }

    /**
     * Creates menu
     */
    private void makeMenu() {
        //Code goes here
    }

    /**
     * Sets up the frame
     */
    private void makeFrame()
    {  
        JPanel graphic = makeGraphic();

        actionField = new JTextArea("", 5, 40);
        actionField.setLineWrap(true);
        actionField.setEditable(false);

        JScrollPane scrollerPane = new JScrollPane(actionField );
        scrollerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Container contents = getContentPane();
        contents.add(graphic, BorderLayout.NORTH);
        contents.add(scrollerPane, BorderLayout.CENTER);

        setResizable(false);
        pack();
        setVisible(true);
    }

    /**
     * Sets up the graphics
     */
    private JPanel makeGraphic(){
        bgPanel = new ImagePanel(new ImageIcon("img/bg.png").getImage());

        playerCardPanel = addCard(playerCard, true);
        monsterCardPanel = addCard(monsterCard, false);

        bgPanel.add(playerCardPanel);
        bgPanel.add(monsterCardPanel);

        playerCard.updateHealth();
        monsterCard.updateHealth();

        return bgPanel;
    }

    /**
     * Changes the monster card and repaints the GUI
     */
    public void changeMonster(Monster monster){
        removeMonster();
        if(monster != null){
            bgPanel.add(addCard(new Card(monster), false));
        }
        repaint();
    }
    
    /**
     * Removes the current monster from the GUI, and repaints the GUI
     */
    public void removeMonster(){
        bgPanel.remove(monsterCardPanel);
        repaint();
    }

    /**
     * Adds a Character Card. If it is a monster, it's added to the right, if it's a player, it's added to the left
     * @param card The Card to be added
     * @param isPlayer If the Character is a player or not
     */
    private JPanel addCard(Card card, boolean isPlayer){
        JPanel jpcard = card.getCardFace();
        if(isPlayer){
            jpcard.setLocation(30,30);
        }
        else{
            jpcard.setLocation(440,30);
            monsterCard = card;
        }
        return jpcard;
    }

    /**
     * Updates the health of the Character supplied as a parameter
     * @param The Character whose health is to be updated
     */
    public void updateHealth(Character ch){
        if(ch instanceof Player){
            playerCard.updateHealth();
        }
        else{
            monsterCard.updateHealth();
        }
    }

    /**
     * Adds text to the action field
     * @param s The String to be added
     */
    public void updateActionField(String s){
        String cs = actionField.getText();
        actionField.setText(cs + "\n" + s);
    }

    /**
     * Lets the Player run from battle
     */
    public void run(){
        Monster currentMonster = battlefield.getCurrentMonster();
        Player player = battlefield.getPlayer();
        if(currentMonster != null && player.isAlive() && currentMonster.isAlive()){
            String runString = player.run();
            updateActionField(runString);
            removeMonster();
        }
    }
    
    /**
     * Refreshes the frame when a new Battlefield is needed, for example when the game is loaded
     * @param battlefield The Battlefield that should replace the current one
     */
    public void refreshFrame(Battlefield battlefield){
        this.battlefield = battlefield;
        playerCard = new Card(battlefield.getPlayer());
        monsterCard = new Card(battlefield.getCurrentMonster());
        getContentPane().removeAll();
        makeFrame();
        repaint();
    }
    
    /**
     * Method for adding ActionListeners to buttons and menu items.
     */
    public void addListeners(ActionListener listener) {
        //Code goes here
    }
    

}
