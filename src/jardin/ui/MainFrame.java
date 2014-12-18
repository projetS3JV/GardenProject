package jardin.ui;

import jardin.AccesBD;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame{
		
	
	private static final long serialVersionUID = 1L;
	
	//Les différents composants graphiques
	private JardinPanel jardinPanel = new JardinPanel();
	private OutilPanel outilPanel;
	private CalendarPanel calendarPanel  = new CalendarPanel();
	private MenuBar menuBar = new MenuBar();
	private static MainFrame instance = null;
	
	private MainFrame(){
		super();
		// acces a la BD pour la creation si besoin
		AccesBD.getInstance();
		this.setSize(1270, 850);
		this.setResizable(false);
		this.setTitle("Jardin Virtuel");
		// Fermeture propre de la fenetre pour pouvoir fermer la base de données
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
						
			@Override
			public void windowClosed(WindowEvent e) {
				AccesBD.getInstance().close();
				System.exit(0);
			}
		});
		
		this.setLayout(new BorderLayout());		
		this.setJMenuBar(menuBar);	   
	    this.add(BorderLayout.SOUTH, this.calendarPanel);	    
	 //   this.add(BorderLayout.EAST, this.outilPanel);	    
	    this.add(BorderLayout.CENTER, this.jardinPanel);
	}

	public JardinPanel getJardinPanel() {
		return this.jardinPanel;
	}

	public OutilPanel getOutilPanel() {
		return this.outilPanel;
	}

	public CalendarPanel getCalendarPanel() {
		return this.calendarPanel;
	}

	public MenuBar getMenuPanel() {
		return this.menuBar;
	}

	public static MainFrame getInstance(){
		if(MainFrame.instance==null) MainFrame.instance = new MainFrame();
		return MainFrame.instance;
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame m = MainFrame.getInstance();
			m.setVisible(true);
		});
		
	}

}
