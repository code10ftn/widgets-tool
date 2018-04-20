package gui.dialogs;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Widget;

@SuppressWarnings("serial")
public class AddWidgetDialog extends JPanel {
	private List<Widget> widgets;
	private JComboBox<String> comboBox;

	public AddWidgetDialog(List<Widget> widgets) {
		super();
		this.widgets = widgets;

		comboBox = new JComboBox<String>();
		initElements();

		add(comboBox);
	}

	public String getSelected() {
		return (String) comboBox.getSelectedItem();
	}

	private void initElements() {

		for (Widget widget : widgets) {
			comboBox.addItem(widget.getWidgetName());
		}
	}
}
