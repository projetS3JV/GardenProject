package jardin.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JardinPanel jardinPanel;
	private OutilPanel outilPanel;
	private Plantotheque plantotheque;
	private CalendarPanel calendarPanel;
	private MenuPanel menuPanel;
	
	private MainFrame(){
		this.setSize(900, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		/*
		this.add(northButton, BorderLayout.NORTH);

	   
	    this.add(southButton, BorderLayout.SOUTH);

	    
	    this.add(eastButton, BorderLayout.EAST);

	    
	    this.add(westButton, BorderLayout.WEST);

	    
	    this.add(centerButton, BorderLayout.CENTER);*/

	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame m = new MainFrame();
			m.setVisible(true);
		});
		
	}

}
