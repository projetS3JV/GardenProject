package jardin.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OpenGardenFrame extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private int selected = -1;
	
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
			// TODO a completer
			});
		
		resultPanel.add(annuler);
		resultPanel.add(ouvrir);
		
		this.add(resultPanel);
		this.setVisible(true);
	}
	
	/**
	 * Retourne l'indice du jardin a charger. -1 si il a choisi d'annuler
	 * @param owner
	 * @return
	 */
	public static int showOpenGardenFrame(JFrame owner) {
		OpenGardenFrame openGarden = new OpenGardenFrame(owner);
		return openGarden.selected;
	}

	
}
