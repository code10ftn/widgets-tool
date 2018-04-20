package test;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.Model;
import model.Project;
import model.TableWidget;
import model.User;
import model.Widget;
import view.View;
import view.WidgetPainter;

public class OpenProjectTest {
	private Model model;
	private View view;
	private Controller controller;
	private Project project;

	@Before
	public void setUp() throws Exception {
		model = new Model();
		model.setUser(new User());
		view = new View();
		controller = new Controller(model);

		project = new Project("proj1", model.getTemplates().get(0));
		model.addProject(project);

		Widget widget1 = new TableWidget("widget1", "", "");
		model.addProjectWidget(project, widget1, 0);
		Widget widget2 = new TableWidget("widget2", "", "");
		model.addProjectWidget(project, widget2, 2);
	}

	@Test
	public void testProjectTreeClick() {
		controller.setView(view);
		controller.populateView(project.getProjectName());

		Iterator<JPanel> it = view.getPanels().iterator();
		for (Widget widget : project.getWidgets()) {
			JPanel panel = it.next();
			Widget panelWidget = null;
			if (panel.getComponentCount() != 0) {
				panelWidget = ((WidgetPainter) panel.getComponent(0)).getWidget();
			}
			assertEquals(panelWidget, widget);
		}
	}
}
