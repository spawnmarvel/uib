package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.listeners.ListenerHoved;

import control.FeedbackHandler;

/**
 *A class witch represents the main interfaces screen and contains all its functions
 * @author Dro068, jkl070 (David , Espen).
 * 5.February 2011
 */

public class Hoved {

	private JFrame window;
	private JMenuBar mb;
	private JMenu Fil, Rediger, datoVis, IdS�k, Rediger2, Rediger3,seMelding;
	private JMenuItem  lukk, nyttSkift,endreSkift, slettSkift, 
	nyAnsatt, slettAnsatt, lagMelding,dagensVakt, oppDaterKnapp, seAnsatt;
	private JLabel idHer,dagsOversikt,tekst, luft1, luft2, dateMess;
	private JSpinner id, datoMessSpinner, dato;
	private JButton idKnapp, datoKnapp, meldingKnapp, seNesteMelding, redigerDenneMelding;
	private JPanel utFraFil, knappPane;
	private JScrollPane scrollPane;
	private JTextArea skjerm;
	private SpinnerDateModel dSpinner, dSpinner2;
	//hei 27 espen


	/**
	 * The constructor of the class
	 *
	 */
	public Hoved() {

		createWindow();
		init();
		addComp();
		window.setVisible(true);
		appendWelcome();
	}
	/**
	 * This method creates the window
	 *
	 */
	public void createWindow() {

		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 600);
		window.setTitle("VaktPlanlegger");
		window.setLocation(100,50);
		window.setLayout(new GridLayout(1,2));
		window.setResizable(true);

	}

	/**
	 * The method appends the welcome message on the screen.
	 */

	public void appendWelcome(){
		Font font1 = new Font("Verdana", Font.BOLD, 40);
		skjerm.setFont(font1);
		skjerm.setForeground(Color.BLUE);
		skjerm.append("\n\n\n         Velkommen!" + "\n\n" +
				"  " + getDateTime());
	}
	/**
	 * This method initializes all the components
	 *
	 */
	public void init() {


		//The main menu bar
		mb = new JMenuBar();
		Fil = new JMenu("Fil");
		Fil.setToolTipText("Her kan du lagre eller lukke");
		Rediger = new JMenu("Vakt");
		Rediger.setToolTipText("Her kan du skifte, legge til, endre m.m");
		Rediger2 = new JMenu("Ansatt");
		Rediger3 = new JMenu("Melding");
		datoVis = new JMenu("Vakter pr dato");
		datoVis.setToolTipText("Her kan du se alle vakter for en bestemt dato");
		IdS�k = new JMenu("Vakter pr id");
		IdS�k.setToolTipText("Her kan du f� frem vakter for din ID");
		//The first under menu
		lukk = new JMenuItem("Lukk");

		//The second under menu
		nyttSkift = new JMenuItem("Nytt skift");

		endreSkift = new JMenuItem("Endre skift");

		slettSkift = new JMenuItem("Slett skift");

		nyAnsatt = new JMenuItem("Legg til en ansatt");
		
		seAnsatt = new JMenuItem("Se alle ansatte");

		slettAnsatt = new JMenuItem("Slett ansatt");

		lagMelding = new JMenuItem("Skriv melding");
		
		seMelding = new JMenu("Se melding");



		//The third under menu
		dagsOversikt = new JLabel("Se vakter for dato");
		dagsOversikt.setToolTipText("Eksempel : 12.12.2011");
		dSpinner = new SpinnerDateModel();
		dato = new JSpinner(dSpinner);

		JSpinner.DateEditor setDate = new JSpinner.DateEditor(dato, "dd/MM/yy");
		dato.setEditor(setDate);
		JFormattedTextField tf =
			((JSpinner.DefaultEditor)dato.getEditor()).getTextField();
		tf.setEditable(false);
		datoKnapp = new JButton("Bekreft dato");

		//The fourth under menu
		idHer = new JLabel("Se vakter for Id  ");
		idHer.setToolTipText("Eksempel : 123");

		dateMess = new JLabel("Se meldinger for dato");
		dSpinner2 = new SpinnerDateModel();
		datoMessSpinner = new JSpinner(dSpinner2);
		JSpinner.DateEditor setD = new JSpinner.DateEditor(datoMessSpinner, "dd/MM/yy");
		datoMessSpinner.setEditor(setD);
		JFormattedTextField de =
			((JSpinner.DefaultEditor)datoMessSpinner.getEditor()).getTextField();
		de.setEditable(false);
		meldingKnapp = new JButton("Ok");


		SpinnerModel sm = new SpinnerNumberModel(1, 1, 100, 1);
		id = new JSpinner(sm);
		JFormattedTextField df =
			((JSpinner.DefaultEditor)id.getEditor()).getTextField();
		df.setEditable(false);
		idKnapp = new JButton("Bekreft id");

		//The text area in the center
		utFraFil = new JPanel();
		utFraFil.setLayout(new BorderLayout());
		utFraFil.setBackground(Color.GRAY);
		//the scroll pane
		skjerm = new JTextArea();
		scrollPane = new JScrollPane(skjerm);
		skjerm.setEditable(false);
		skjerm.setToolTipText("Her ligger vaktlisten og data som er lagret til n�");
		//The frame outside the text area
		tekst = new JLabel("      ");
		luft1 = new JLabel("      ");
		luft2 = new JLabel("      ");
		oppDaterKnapp = new JMenuItem("Se alle vakter");

		dagensVakt = new JMenuItem("Se vaktliste for i dag");
		knappPane = new JPanel();
		knappPane.setLayout(new FlowLayout());
		knappPane.setBackground(Color.GRAY);
		//The messages buttons
		seNesteMelding = new JButton("Se neste melding");
		seNesteMelding.setEnabled(false);
		seNesteMelding.setVisible(false);
		redigerDenneMelding = new JButton("Rediger denne melding");
		redigerDenneMelding.setEnabled(false);
		redigerDenneMelding.setVisible(false);

		ListenerHoved hov = new ListenerHoved(this);
		lukk.addActionListener(hov);
		nyttSkift.addActionListener(hov);
		endreSkift.addActionListener(hov);
		slettSkift.addActionListener(hov);
		nyAnsatt.addActionListener(hov);
		slettAnsatt.addActionListener(hov);
		datoKnapp.addActionListener(hov);
		idKnapp.addActionListener(hov);
		oppDaterKnapp.addActionListener(hov);
		dagensVakt.addActionListener(hov);
		lagMelding.addActionListener(hov);
		seAnsatt.addActionListener(hov);
		seMelding.addActionListener(hov);
		meldingKnapp.addActionListener(hov);
		
		redigerDenneMelding.addActionListener(hov);
		seNesteMelding.addActionListener(hov);
	}
	/**
	 * This method adds the different components in the right order
	 */
	public void addComp() {

		//The main menu
		window.setJMenuBar(mb);
		mb.add(Fil);
		mb.add(Rediger);
		mb.add(Rediger2);
		mb.add(Rediger3);
		mb.add(seMelding);
		seMelding.add(dateMess);
		seMelding.add(datoMessSpinner);
		seMelding.add(meldingKnapp);
		mb.add(datoVis);
		mb.add(IdS�k);
		//The first inner
		Fil.add(lukk);
		//The second inner
		Rediger.add(nyttSkift);
		Rediger.add(endreSkift);
		Rediger.add(slettSkift);
		Rediger.add(oppDaterKnapp);
		Rediger.add(dagensVakt);
		Rediger2.add(slettAnsatt);
		Rediger2.add(nyAnsatt);
		Rediger2.add(seAnsatt);
		Rediger3.add(lagMelding);
		


		//The third inner
		datoVis.add(dagsOversikt);
		datoVis.add(dato);
		datoVis.add(datoKnapp);
		//the fourth inner
		IdS�k.add(idHer);
		IdS�k.add(id);
		IdS�k.add(idKnapp);
		//The inner frame, the text area
		window.add(utFraFil);
		utFraFil.add(BorderLayout.NORTH,tekst);
		utFraFil.add(BorderLayout.CENTER,scrollPane);
		utFraFil.add(BorderLayout.EAST,luft1);
		utFraFil.add(BorderLayout.SOUTH,knappPane);
		knappPane.add(seNesteMelding);
		knappPane.add(redigerDenneMelding);
		utFraFil.add(BorderLayout.WEST,luft2);
	}

	/**
	 * The method sets the font of the text that will be appended on screen.
	 */
	public void setFont(){
		Font font2 = new Font("Verdana", Font.BOLD, 11);
		skjerm.setFont(font2);
		skjerm.setForeground(Color.BLACK);
	}


	/**
	 * The method returns the time and date at the time it has been invoked.
	 * @return - time and date at invoking.
	 */
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	/**
	 * returns the JTextArea
	 * @return JTextArea
	 */

	public JTextArea getSkjerm(){
		return skjerm;
	}
	/**
	 * returns the Date JSpinner
	 * @return JSpinner
	 */

	public JSpinner getDato(){
		return dato;
	}
	/**
	 * returns the id JSpinner
	 * @return JSpinner
	 */

	public JSpinner getID(){
		return id;
	}
	/**
	 * returns the  JMenuItem
	 * @return JMenuItem
	 */
	public JMenuItem getSeMelding() {
		return seMelding;
	}
	/**
	 * returns the Date JSpinner
	 * @return JSpinner
	 */
	public JSpinner getMessDate() {
		return datoMessSpinner;
	}


	/**
	 * This method gives an warning that an operation failed.
	 * @param feedBackText - the message given.
	 */
	public void failedFeedBack(String feedBackText){
		FeedbackHandler.failedFeedback(feedBackText);
	}
	/**
	 * This method enables the messages components and sets them to
	 * true so they appear on the screen when needed
	 */
	public void enableMessComp(){
		seNesteMelding.setVisible(true);
		seNesteMelding.setEnabled(true);
		redigerDenneMelding.setVisible(true);
		redigerDenneMelding.setEnabled(true);
	}
	/**
	 * This method returns the  the JButton item
	 * @return JButton 
	 */
	public JButton getSeNesteMelding(){
		return seNesteMelding;
	}
	/**
	 * This method returns the  the JButton item
	 * @return JButton 
	 */
	public JButton getRedigerDenneMelding(){
		return redigerDenneMelding;
	}


	/**
	 * This method gets the main window
	 * @return this(current hoved gui)
	 */

	public Hoved getHoved() {
		return this;
	}



	/**
	 * This method gets the JFrame window
	 * @return JFrame
	 */

	public JFrame getWindow() {
		return window;
	}
}
