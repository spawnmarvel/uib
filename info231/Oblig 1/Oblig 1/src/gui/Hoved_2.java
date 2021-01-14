package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Controller;
import control.IOHandler;

/**
 *A class witch represents the main interfaces screen and contains all its functions
 * @author Dro068, jkl070 (David , Espen).
 * 5.February 2011
 */

public class Hoved_2 implements ActionListener {

    private JFrame window;
	private JMenuBar mb;
	private JMenu Fil, Rediger, datoVis, IdSøk;
	private JMenuItem  lukk, nyttSkift,endreSkift, slettSkift, nyAnsatt, slettAnsatt;
	private JLabel idHer,dagsOversikt,tekst, luft1, luft2;
	private JTextField id, dato;
	private JButton idKnapp, datoKnapp, oppDaterKnapp, dagensVakt;
	private JPanel utFraFil, knappPane;
	private JScrollPane scrollPane;
	private JTextArea skjerm;



	/**
	 * The constructor of the class
	 *
	 */
	public Hoved_2() {

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
		Rediger = new JMenu("Rediger");
		Rediger.setToolTipText("Her kan du skifte, legge til, endre m.m");
		datoVis = new JMenu("Vakter pr dato");
		datoVis.setToolTipText("Her kan du se alle vakter for en bestemt dato");
		IdSøk = new JMenu("Vakter pr id");
		IdSøk.setToolTipText("Her kan du få frem vakter for din ID");
		//The first under menu
		lukk = new JMenuItem("Lukk");
		lukk.addActionListener(this);
		//The second under menu
		nyttSkift = new JMenuItem("Nytt skift");
		nyttSkift.addActionListener(this);
		endreSkift = new JMenuItem("Endre skift");
		endreSkift.addActionListener(this);
		slettSkift = new JMenuItem("Slett skift");
		slettSkift.addActionListener(this);
		nyAnsatt = new JMenuItem("Legg til en ansatt");
		nyAnsatt.addActionListener(this);
		slettAnsatt = new JMenuItem("Slett ansatt");
		slettAnsatt.addActionListener(this);
		//The third under menu
		dagsOversikt = new JLabel("Se vakter for dato");
		dagsOversikt.setToolTipText("Eksempel : 12.12.2011");
		dato = new JTextField(6);
		datoKnapp = new JButton("Bekreft dato");
		datoKnapp.addActionListener(this);
		//The fourth under menu
		idHer = new JLabel("Se vakter for ditt ansattId nummer");
		idHer.setToolTipText("Eksempel : 123");
		id = new JTextField(5);
		idKnapp = new JButton("Bekreft id");
		idKnapp.addActionListener(this);
		//The text area in the center
		utFraFil = new JPanel();
		utFraFil.setLayout(new BorderLayout());
		utFraFil.setBackground(Color.GRAY);
		//the scroll pane
		skjerm = new JTextArea();
		scrollPane = new JScrollPane(skjerm);
		skjerm.setEditable(false);
		skjerm.setToolTipText("Her ligger vaktlisten og data som er lagret til nå");
		//The frame outside the text area
		tekst = new JLabel("      ");
		luft1 = new JLabel("      ");
		luft2 = new JLabel("      ");
		oppDaterKnapp = new JButton("Se alle vakter");
		oppDaterKnapp.addActionListener(this);
		//oppDaterKnapp.setSize(20, 20);
		dagensVakt = new JButton("Se vaktliste for i dag");
		Font font6 = new Font("Verdana", Font.BOLD, 14);
		dagensVakt.setFont(font6);
		oppDaterKnapp.setFont(font6);
		dagensVakt.setForeground(Color.BLACK);
		oppDaterKnapp.setForeground(Color.BLACK);
		dagensVakt.addActionListener(this);
		knappPane = new JPanel();
		knappPane.setLayout(new FlowLayout());


	}
	/**
	 * This method adds the different components in the right order
	 */
    public void addComp() {
		//The main menu
		window.setJMenuBar(mb);
		mb.add(Fil);
		mb.add(Rediger);
		mb.add(datoVis);
		mb.add(IdSøk);
		//The first inner
		Fil.add(lukk);
		//The second inner
		Rediger.add(nyttSkift);
		Rediger.add(endreSkift);
		Rediger.add(slettSkift);
		Rediger.add(slettAnsatt);
		Rediger.add(nyAnsatt);
		Rediger.add(slettAnsatt);
		//The third inner
		datoVis.add(dagsOversikt);
		datoVis.add(dato);
		datoVis.add(datoKnapp);
		//the fourth inner
		IdSøk.add(idHer);
	    IdSøk.add(id);
		IdSøk.add(idKnapp);
		//The inner frame, the text area
		window.add(utFraFil);
		utFraFil.add(BorderLayout.NORTH,tekst);
		utFraFil.add(BorderLayout.CENTER,scrollPane);
		utFraFil.add(BorderLayout.EAST,luft1);
		knappPane.add(dagensVakt);
		knappPane.add(oppDaterKnapp);
		utFraFil.add(BorderLayout.SOUTH,knappPane);
		utFraFil.add(BorderLayout.WEST,luft2);
    }

/**
 * The method controls the events according to user commands.
 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Endre skift")){
			System.out.println("Skift");
			new ChangeShiftGui();

		}

		if (e.getActionCommand().equalsIgnoreCase("Bekreft dato")){
			skjerm.setText("");
			try{
				if(dato.getText().equalsIgnoreCase("")){
					failedFeedBack("Alle ikke-valgrie felt må fylles ut!");
			}
				else{
			String inString = dato.getText();
			Controller c = new Controller();
			setFont();
			skjerm.append(c.showShiftPrIdOrDate(inString));

				}
		}
			catch (Exception NumberFormatException) {
	 	    	failedFeedBack("ID-nummeret finnes ikke!");
		}
		}
		if (e.getActionCommand().equalsIgnoreCase("Bekreft id")){
			skjerm.setText("");
			try{
				if(id.getText().equalsIgnoreCase("")){
					failedFeedBack("Alle ikke-valgfrie felt må fylles ut!");
					}
				else{

			int inString = Integer.parseInt(id.getText());
			Controller c = new Controller();
			setFont();
			skjerm.append(c.showShiftPrIdOrDate(inString));
			}
				}
			catch (Exception NumberFormatException) {
	 	    	failedFeedBack("ID-nummeret finnes ikke!");
	 	    	}
			}
		if (e.getActionCommand().equalsIgnoreCase("Nytt skift")){
			new CreateShiftGui();
			}
		if (e.getActionCommand().equalsIgnoreCase("Legg til en ansatt")){
			new AddEmployee();
			}
		if (e.getActionCommand().equalsIgnoreCase("Se alle vakter")){
			setFont();
			skjerm.setText("");
			IOHandler ioh = new IOHandler();
			ArrayList<String>list = ioh.readDataFromFile("Shift_List.txt");
			for (String entry:list){
				String s = entry;
				skjerm.append(s);
				}
		}
		if (e.getActionCommand().equalsIgnoreCase("Lukk")){
			System.exit(0);
			}
		if (e.getActionCommand().equalsIgnoreCase("Slett ansatt")){
			new DeleteEmployee();
			}
		if (e.getActionCommand().equalsIgnoreCase("Slett skift")){
			new DeleteShiftGUI();
			}
		if (e.getActionCommand().equalsIgnoreCase("Se vaktliste for i dag")){
			setFont();
			skjerm.setText("");
			skjerm.append("Vaktliste for i dag den " + getDate() + " :" + "\n");
			Controller c = new Controller();
			skjerm.append(c.showShiftPrIdOrDate(getDate()));
			}
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
	 * The method returns the current date.
	 * @return - the current date at the time of invoking the method.
	 */

    private static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        Date date = new Date();
        return dateFormat.format(date);

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
	     * This method gives an warning that an operation failed.
	     * @param feedBackText - the message given.
	     */
	    public void failedFeedBack(String feedBackText){
	    	String title = "Feil oppstod!";
			String message = feedBackText;
			JOptionPane.showMessageDialog(null,message,title,
					JOptionPane.OK_CANCEL_OPTION);
			}
	}
