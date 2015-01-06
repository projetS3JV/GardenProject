package jardin.ui;

import jardin.AccesBD;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OpenGardenFrame extends JDialog{

	private static final long serialVersionUID = 1L;
	
	/** L'indice du jardin selectionné */
	private int selected = -1;
	
	private OpenGardenFrame(JFrame frame) {
		super(frame, "Ouvrir jardin", true);
		this.setSize(230, 200);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
				
		/* Les jardins*/
		HashMap<Integer, String> jardins = AccesBD.getInstance().getJardins();		
		Set<Entry<Integer, String>> jardinSet = jardins.entrySet();
		
		JPanel jardinsPanel = new JPanel();
		JList<Object> jardinsList = new JList<Object>(jardinSet.toArray());
		jardinsList.setFixedCellWidth(200);
		jardinsList.setCellRenderer(new OpenGardenCellRenderer());
		jardinsPanel.add(new JScrollPane(jardinsList));
		
		
		JPanel resultPanel = new JPanel();
		JButton annuler = new JButton("Annuler");
		JButton ouvrir = new JButton("Ouvrir");
		
		
		annuler.addActionListener(e -> {
			OpenGardenFrame.this.setVisible(false);
			OpenGardenFrame.this.dispose();
			});
		jardinsList.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
					ouvrir.doClick();
				}
			}
		});
		ouvrir.addActionListener(e -> {
			if (jardinsList.getSelectedIndex() >= 0) {
				OpenGardenFrame.this.selected = ((Entry<Integer, String>)jardinsList.getSelectedValue()).getKey();
				OpenGardenFrame.this.setVisible(false);
				OpenGardenFrame.this.dispose();
			}
			});
		
		resultPanel.add(annuler);
		resultPanel.add(ouvrir);
		
		this.add(jardinsPanel);
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
