package view;

import java.util.List;

import javax.swing.JPanel;

import controller.Controller;

@SuppressWarnings("serial")
public class View extends JPanel {
	private List<JPanel> panels;

	public View() {
	}

	public void paintWidgets() {
		for (JPanel panel : panels) {
			if (panel.getComponentCount() == 1) {
				WidgetPainter painter = (WidgetPainter) panel.getComponent(0);
				painter.paint();
			}
			panel.validate();
			panel.repaint();
		}
	}

	public void setupListeners(Controller controller) {
		for (JPanel panel : panels) {
			panel.addMouseListener(controller);
		}
	}

	public int getPanelIndex(JPanel panel) {
		for (int i = 0; i < panels.size(); i++) {
			if (panel == panels.get(i)) {
				return i;
			}
		}
		return -1;
	}

	public List<JPanel> getPanels() {
		return panels;
	}

	public void setPanels(List<JPanel> panels) {
		this.panels = panels;
	}
}
