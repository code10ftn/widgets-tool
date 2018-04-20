package gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	private JButton projectButton;
	private JButton widgetButon;

	ToolBar() {
		setFloatable(false);

		projectButton = new JButton("Add project");
		add(projectButton);

		widgetButon = new JButton("Add widget");
		add(widgetButon);
	}

	public JButton getProjectButton() {
		return projectButton;
	}

	public void setProjectButton(JButton projectButton) {
		this.projectButton = projectButton;
	}

	public JButton getWidgetButon() {
		return widgetButon;
	}

	public void setWidgetButon(JButton widgetButon) {
		this.widgetButon = widgetButon;
	}
}
