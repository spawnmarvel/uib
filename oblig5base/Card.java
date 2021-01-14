import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The "card" for a Character in the game, either the hero or the monster he fights against
 * 
 * @author Sindre Benonisen
 * @version Oblig 5
 */
public class Card
{
	private Character ch;
	private String name;
	private JPanel cardFace, healthbar;
	private JLabel healthLbl;

	/**
	 * Constructor for objects of class Card
	 * @param ch A Character object
	 */
	public Card(Character ch)
	{
		this.ch = ch;
		makeCard(ch);
	}

	/**
	 * The nuts and bolts of setting up a Card. Called directly from the constructor
	 */
	private void makeCard(Character ch){
		String name = ch.getName();
		String img = ch.getImg();

		cardFace = new ImagePanel(new ImageIcon("img/card.png").getImage());
		JPanel icon = new ImagePanel(new ImageIcon("img/"+img).getImage());
		healthbar = new ImagePanel(new ImageIcon("img/health.png").getImage());   

		cardFace.setLayout(new BorderLayout());

		JPanel gc = new JPanel();
		gc.setOpaque(false);

		gc.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15,1,0,0); 
		gc.add(icon, c);

		JLabel nameLbl = new JLabel(name);
		nameLbl.setOpaque(false);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,0);
		gc.add(nameLbl, c);

		healthLbl = new JLabel();
		updateHealthLabel();
		healthLbl.setOpaque(false);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10,0,0,0);
		gc.add(healthLbl, c);

		c.gridx = 0;
		c.gridy = 3;
		gc.add(healthbar, c);

		cardFace.add(gc, BorderLayout.NORTH);

	}

	/**
	 * Return the cardFace of a Card
	 * @return the Card's cardFace
	 */
	public JPanel getCardFace(){
		return cardFace;
	}

	/**
	 * Updates the health label and health bar for a character in the game when hit.
	 */
	public void updateHealth(){
		updateHealthLabel();
		updateHealthBar();
	}

	/**
	 * Sets the healthLabel to the current correct value
	 */
	private  void updateHealthLabel(){
		healthLbl.setText(ch.getHealth() + " / " + ch.getMaxHealth());
	}

	/**
	 * Sets the healthBar to the current correct value
	 */
	private void updateHealthBar(){
		float percentage = (ch.getHealth()  * 100) / ch.getMaxHealth();
		float hbPercentage = (207 / 100) * percentage;
		int intPercentage = Math.round(hbPercentage);

		healthbar.setPreferredSize(new Dimension(intPercentage, 12));
	}

	/**
	 * Returns the Character the Card is about
	 * @return the Character
	 */
	public Character getCharacter(){
		return ch;
	}
}
