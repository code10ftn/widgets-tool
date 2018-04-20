package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.dialogs.AddWidgetDialog;
import model.Model;
import model.Position;
import model.Project;
import model.Template;
import model.Widget;
import view.View;
import view.WidgetPainter;

public class Controller implements MouseListener {
	private Model model;
	private View view;
	private Project openProject;

	public Controller() {
	}

	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			leftMouseClick(e);
		}
	}

	private void leftMouseClick(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();
		if (panel.getComponentCount() != 0)
			return;

		AddWidgetDialog dialog = new AddWidgetDialog(model.getWidgets());
		int input = JOptionPane.showOptionDialog(null, dialog, "Select widget...", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (input == JOptionPane.OK_OPTION) {
			Widget widget = model.findLibraryWidget(dialog.getSelected()).copy();
			int pos = view.getPanelIndex(panel);
			model.addProjectWidget(openProject, widget, pos);
			WidgetPainter painter = createPainter(widget);
			panel.setLayout(new GridLayout(1, 1));
			panel.add((Component) painter);
			view.paintWidgets();
		}
	}

	private void rightMouseClick(MouseEvent e) {
		WidgetPainter painter = (WidgetPainter) e.getSource();
		JPanel panel = (JPanel) ((JComponent) painter).getParent();
		int pos = view.getPanelIndex(panel);
		model.addProjectWidget(openProject, null, pos);
		panel.removeAll();
		view.paintWidgets();
	}

	public void populateView(String projectName) {
		openProject = model.findProject(projectName);
		Template template = openProject.getTemplate();
		setViewLayOut(template);

		Iterator<JPanel> it = view.getPanels().iterator();
		for (Widget widget : openProject.getWidgets()) {
			JPanel panel = it.next();
			if (widget != null) {
				WidgetPainter painter = createPainter(widget);
				panel.removeAll();
				panel.add((Component) painter);
				panel.validate();
			}
		}
	}

	private void setViewLayOut(Template template) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		view.setLayout(gbl);

		List<JPanel> panels = new ArrayList<JPanel>();

		for (Position pos : template.getPositions()) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 1));
			gbc.gridx = pos.getGridx();
			gbc.gridy = pos.getGridy();
			gbc.gridwidth = pos.getGridwidth();
			gbc.gridheight = pos.getGridheight();
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.BOTH;
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			view.add(panel, gbc);

			panels.add(panel);
		}

		view.setPanels(panels);
	}

	private WidgetPainter createPainter(Widget widget) {
		try {
			Class<?> painterClass = Class.forName("view." + widget.getClass().getName().split("\\.")[1] + "Painter");
			Constructor<?> constructor = painterClass.getConstructor(Widget.class);
			WidgetPainter painter = (WidgetPainter) constructor.newInstance(widget);

			((JComponent) painter).addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						rightMouseClick(e);
					}
				}
			});

			return painter;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Project getOpenProject() {
		return openProject;
	}

	public void setOpenProject(Project openProject) {
		this.openProject = openProject;
	}
}
