package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabs;
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(640, 340));
		
		setLayout(new BorderLayout());
		
		tabs = new JTabbedPane();
		
		tabs.add("Reset", new ResetPanel());
		tabs.add("Last inn", new ProcessPanel());
		tabs.add("Finn DF", new DFPanel());
		tabs.add("FInn TF-IDF", new TFIDFPanel());
		tabs.add("Søk", new SearchPanel());
		
		add(tabs);
		
		pack();
		
		setVisible(true);
	}
}
