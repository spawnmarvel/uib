package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Main;

import static db.DBHandler.getDB;

public class ResetPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextArea setupText;
	private JButton reset;

	// Endre SQL spørringen til å passe ditt/deres oppsett.
	private String initialSetup =
		"DROP TABLE IF EXISTS "+Main.tableNameForDocuments+","+Main.tableNameForDist_terms+","+Main.tableNameForInvertIndexWithFreq+","+Main.tableNameForDoc_Frequency+";\n" +
		"CREATE TABLE IF NOT EXISTS `"+Main.tableNameForDocuments+"` (`ID` int(11) NOT NULL,`Doc_Name` varchar(100) NOT NULL, `Document` text NOT NULL, PRIMARY KEY (`ID`));\n" +
		"CREATE TABLE IF NOT EXISTS `"+Main.tableNameForDist_terms+"` (`termID` int(11) NOT NULL, `term` varchar(100) NOT NULL, PRIMARY KEY (`termID`));\n" +
		"CREATE TABLE IF NOT EXISTS `"+Main.tableNameForInvertIndexWithFreq+"` (`term` varchar(100) NOT NULL, `Doc_ID` int(8),`Frequency` int(8));\n"+
		"CREATE TABLE IF NOT EXISTS `"+Main.tableNameForDoc_Frequency+"` (`term` varchar(100) NOT NULL, `Frequency` int(8), PRIMARY KEY (`term`));\n";


	public ResetPanel() {
		setLayout(new BorderLayout());
		setupText = new JTextArea(initialSetup);
		add(new JScrollPane(setupText), BorderLayout.CENTER);

		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPressed();

			}
		});
		add(reset, BorderLayout.SOUTH);
	}

	private void resetPressed() {
		String[] lines = setupText.getText().split("\\n");
		try {
			getDB().runQueries(lines);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					this,
					e.getMessage(),
					"Feil oppstod!",
					JOptionPane.ERROR_MESSAGE);
		}
		JOptionPane.showMessageDialog(
				this,
				"Ferdig. Ingen feil. ",
				"Reset",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
