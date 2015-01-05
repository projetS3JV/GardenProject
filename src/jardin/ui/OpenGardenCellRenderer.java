package jardin.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class OpenGardenCellRenderer extends JLabel implements ListCellRenderer<Object> {

	private static final long serialVersionUID = 1L;

	public OpenGardenCellRenderer() {
		super();
		this.setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Entry e = (Entry) value;
		this.setText((String) e.getValue());
		if(isSelected) {
			this.setBackground(list.getSelectionBackground());
		} else this.setBackground(Color.WHITE);
		return this;
	}

}
