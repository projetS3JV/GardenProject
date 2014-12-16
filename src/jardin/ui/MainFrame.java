package jardin.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//Les différents composants graphiques
	private JardinPanel jardinPanel;
	private OutilPanel outilPanel;
	private Plantotheque plantotheque;
	private CalendarPanel calendarPanel;
	private MenuBar menuPanel;
	
	private MainFrame(){
		this.setSize(1270, 850);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());		
		this.setJMenuBar(new MenuBar());	   
	    this.add(BorderLayout.SOUTH, this.calendarPanel = new CalendarPanel());	    
	 //   this.add(BorderLayout.EAST, this.outilPanel);	    
	 //   this.add(BorderLayout.CENTER, this.jardinPanel);
	}

	public JardinPanel getJardinPanel() {
		return jardinPanel;
	}

	public OutilPanel getOutilPanel() {
		return outilPanel;
	}

	public Plantotheque getPlantotheque() {
		return plantotheque;
	}

	public CalendarPanel getCalendarPanel() {
		return calendarPanel;
	}

	public MenuBar getMenuPanel() {
		return menuPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame m = new MainFrame();
			m.setVisible(true);
		});
		
	}
	
	

}
