package gui;



import java.awt.*;
import com.listeners.MessageListener;

import javax.swing.*;

/**
 * The GUI class for making a new message and to update a message
 * @author Hedvig, Aslak
 * modified by dro068, jkl070
 */

public class MessageGui {

	private JFrame frame;
	private JCheckBox ferdig;
	private JTextArea ta;
	private JTextField tf, hm;
	private JPanel panel, idPanel, idPanel2, meldingPanel, mainLowerPanel, westLowerPanel, centerLowerPanel;
	private JLabel meldingLabel, viktighetLabel, varighetLabel, donelabel, id, rmsg, tomLabel, vest, øst, nord, sør, tom1, tom2, tom3, meldingID;
	private JButton lagre, avbryt, hent, lagreEndring;;
	private JRadioButton lav, middels, høy, enDag, treDag, syvDag, fjortenDag;
	private ButtonGroup viktighetGroup, varighetGroup, doneGroup;
	private JSpinner dato;


	/**
	 * The constructor.
	 */
	public MessageGui(){

		initTopPanel();
		createTopPanel();
		initLowerPanel();
		createLowerPanel();
		createWindow();
		frame.setVisible(true);
	}

	/**
	 * Creates the main window in the GUI.
	 */
	public void createWindow(){
		frame = new JFrame();
		frame.setTitle("Ny melding");
		frame.setSize(500,300);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(500,350);

		vest = new JLabel("      ");
		øst = new JLabel("      ");
		nord = new JLabel("      ");
		sør = new JLabel("      ");

		panel = new JPanel(new GridLayout(2,0));
		panel.add(meldingPanel);
		panel.add(mainLowerPanel);

		frame.add(vest, BorderLayout.WEST);
		frame.add(øst, BorderLayout.EAST);
		frame.add(nord, BorderLayout.NORTH);
		frame.add(sør, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.CENTER);






	}

	/**
	 * Initiates the components for the top panel.
	 */
	public void initTopPanel(){
		meldingPanel = new JPanel();
		idPanel = new JPanel();
		idPanel2 = new JPanel();
		id = new JLabel("Ansatt Id: ");
		tf = new JTextField(3);
		
		SpinnerModel dSpinner = new SpinnerDateModel();
		dato = new JSpinner(dSpinner);

		JSpinner.DateEditor setDate = new JSpinner.DateEditor(dato, "dd/MM/yy");
		dato.setEditor(setDate);
		JFormattedTextField tf =
			((JSpinner.DefaultEditor)dato.getEditor()).getTextField();
		tf.setEditable(false);
		meldingID = new JLabel("       MeldingID");
		meldingID.setVisible(false);
		
		rmsg = new JLabel("  Når skal meldingen vises?");
		hm = new JTextField(3);
		//hent = new JButton("Hent");
		meldingLabel = new JLabel("Melding: ");
		ta = new JTextArea(20,20);
		ta.setBorder(BorderFactory.createLineBorder(Color.black));
		ta.setLineWrap(true);
	}

	/**
	 * Creates the top panel.
	 */
	public void createTopPanel(){
		meldingPanel.setLayout(new BorderLayout());
		idPanel.setLayout(new BorderLayout());
		idPanel2.setLayout(new FlowLayout());
		idPanel2.add(id);
		idPanel2.add(tf);
		idPanel2.add(rmsg);
		idPanel2.add(dato);
		idPanel2.add(meldingID);
		idPanel2.add(hm);
		//idPanel2.add(hent);
		idPanel.add(idPanel2, BorderLayout.WEST);
		meldingPanel.add(idPanel, BorderLayout.NORTH);
		meldingPanel.add(meldingLabel, BorderLayout.WEST);
		meldingPanel.add(ta, BorderLayout.CENTER);
	}

	/**
	 * Initiates the components for the lower panel.
	 */
	public void initLowerPanel(){

		viktighetLabel = new JLabel("Viktighet: ");
		varighetLabel = new JLabel("Varighet: ");
		donelabel = new JLabel("Ferdig?");
		tomLabel = new JLabel();
		lagre = new JButton("Lagre");
		avbryt = new JButton("Avbryt");
		lagreEndring = new JButton("Oppdater");


		lav = new JRadioButton("Lav", false);
		middels = new JRadioButton("Middels", false);
		høy = new JRadioButton("Høy", false);
		enDag = new JRadioButton("1 dag", false);
		treDag = new JRadioButton("3 dager", false);
		syvDag = new JRadioButton("7 dager", false);
		fjortenDag = new JRadioButton("14 dager", false);
		ferdig = new JCheckBox();

		viktighetGroup = new ButtonGroup();
		varighetGroup = new ButtonGroup();
		doneGroup = new ButtonGroup();

		MessageListener mlis = new MessageListener(this);
		//hent.addActionListener(mlis);
		lagre.addActionListener(mlis);
		lagreEndring.addActionListener(mlis);
		avbryt.addActionListener(mlis);

		lav.addActionListener(mlis);
		middels.addActionListener(mlis);
		høy.addActionListener(mlis);

		enDag.addActionListener(mlis);
		treDag.addActionListener(mlis);
		syvDag.addActionListener(mlis);
		fjortenDag.addActionListener(mlis);
		ferdig.addActionListener(mlis);


	}

	/**
	 * Creates the lower panel.
	 */
	public void createLowerPanel(){
		mainLowerPanel = new JPanel(new BorderLayout());
		westLowerPanel = new JPanel(new GridLayout(4,1,5,5));
		centerLowerPanel = new JPanel(new GridLayout(4,4,5,5));

		viktighetGroup.add(lav);
		viktighetGroup.add(middels);
		viktighetGroup.add(høy);

		varighetGroup.add(enDag);
		varighetGroup.add(treDag);
		varighetGroup.add(syvDag);
		varighetGroup.add(fjortenDag);

		doneGroup.add(ferdig);

		tom1 = new JLabel("   ");
		tom2 = new JLabel("   ");
		tom3 = new JLabel("   ");

		mainLowerPanel.add(westLowerPanel, BorderLayout.WEST);
		mainLowerPanel.add(centerLowerPanel, BorderLayout.CENTER);

		westLowerPanel.add(viktighetLabel);
		westLowerPanel.add(varighetLabel);
		westLowerPanel.add(donelabel);
		westLowerPanel.add(avbryt);

		centerLowerPanel.add(lav);
		centerLowerPanel.add(middels);
		centerLowerPanel.add(høy);
		centerLowerPanel.add(tomLabel);

		centerLowerPanel.add(enDag);
		centerLowerPanel.add(treDag);
		centerLowerPanel.add(syvDag);
		centerLowerPanel.add(fjortenDag);
		centerLowerPanel.add(ferdig);

		centerLowerPanel.add(tom1);
		centerLowerPanel.add(tom2);
		centerLowerPanel.add(tom3);
		centerLowerPanel.add(lagre);
		centerLowerPanel.add(lagreEndring);
	}


	/**
	 * Method to be used in the MessageListener class.
	 * Is used to abort the message making when the button
	 * Avbryt is used.
	 */
	public JFrame getFrame(){
		return frame;
	}

	/**
	 * @return the message ID input.
	 */
	public JTextField getHent() {
		return hm;
	}

	/**
	 * @return the lagre button.
	 */
	public JButton getLagre(){
		return lagre;
	}
	/**
	 * returns the JButton
	 * @return JButton
	 */
	public JButton getLagreEndring(){
	return lagreEndring;
	}

	/**
	 * @return the ferdig checkbox.
	 */
	public JCheckBox getFerdig() {
		return ferdig;
	}

	/**
	 * @return the text area containing the message.
	 */
	public String getText(){
		return ta.getText();
	}
	/**
	 * returns the textarea
	 * @return textarea
	 */
	public JTextArea getArea() {
		return ta;
	}

	/**
	 * @return the text field containing the employee id.
	 */
	public JTextField getId(){
		return tf;
	}

	/**
	 * @return the radio button Lav.
	 */
	public JRadioButton getLav(){
		return lav;
	}

	/**
	 * @return the radio button Middels.
	 */
	public JRadioButton getMiddels(){
		return middels;
	}

	/**
	 * @return the radio button høy.
	 */
	public JRadioButton getHøy(){
		return høy;
	}

	/**
	 * @return the radio button 1 dag.
	 */
	public JRadioButton getEnDag(){
		return enDag;
	}

	/**
	 * @return the radio button 3 dager.
	 */
	public JRadioButton getTreDag(){
		return treDag;

	}

	/**
	 * @return the radio button 7 dager.
	 */
	public JRadioButton getSyvDag(){
		return syvDag;
	}

	/**
	 * @return the radio button 14 dager.
	 */
	public JRadioButton getFjortenDag(){
		return fjortenDag;
	}
//	/**
//	 * returns the JButton
//	 * @return JButton
//	 */
//	public JButton getHentButton() {
//		return hent;
//	}

	/**
	 * 
	 */
	public JLabel getMeldingID(){
		return meldingID;
	}
	/**
	 * 
	 */
	public JSpinner getDatoSpinn(){
		return dato;
	}

}
