package jardin;

import javax.swing.SwingUtilities;

import jardin.ui.MainFrame;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame m = MainFrame.getInstance();
			m.setVisible(true);
		});
	}

}
