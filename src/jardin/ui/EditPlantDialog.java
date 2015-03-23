package jardin.ui;

import jardin.plante.Plante;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class EditPlantDialog extends JDialog{
	
	private PlantothequePanel pp;

	public EditPlantDialog(JDialog owner){
		super(owner, true);
		this.setSize(400, 400);
		this.setResizable(false);
		
		this.pp = new PlantothequePanel();
		this.add(pp);
		
		this.setVisible(true);
	}


    public static Plante showPlantDialog(JDialog owner) {
    	EditPlantDialog e = new EditPlantDialog(owner);
		return e.pp.getSelected();
    }
	
}
