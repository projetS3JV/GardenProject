package jardin.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OpenGardenFrame extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private String selected = null;
	
	private OpenGardenFrame(JFrame frame) {
		super(frame, "Ouvrir jardin", true);
		this.setSize(230, 200);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		// TODO ajouter le panel contenant le nom des Jardins
		
		JPanel resultPanel = new JPanel();
		JButton annuler = new JButton("Annuler");
		JButton ouvrir = new JButton("Ouvrir");
		
		
		annuler.addActionListener(e -> {
			OpenGardenFrame.this.setVisible(false);
			OpenGardenFrame.this.dispose();
			});
		
		ouvrir.addActionListener(e -> {
			// a completer
			});
		
		resultPanel.add(annuler);
		resultPanel.add(ouvrir);
		
		this.add(resultPanel);
		this.setVisible(true);
	}
	
	public String getSelected() {
		return this.selected;
	}
	
	public static String showOpenGardenFrame(JFrame owner) {
		OpenGardenFrame openGarden = new OpenGardenFrame(owner);
		return openGarden.selected;
	}

	
}
