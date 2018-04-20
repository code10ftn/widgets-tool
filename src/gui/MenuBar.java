package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem newProject;
	private JMenuItem newWidget;
	private JMenuItem logOut;
	private JMenuItem about;

	public MenuBar() {
		fileMenu = new JMenu("File");
		add(fileMenu);

		newProject = new JMenuItem("Add project");
		fileMenu.add(newProject);

		newWidget = new JMenuItem("Add widget");
		fileMenu.add(newWidget);

		logOut = new JMenuItem("Log out");
		fileMenu.add(logOut);

		helpMenu = new JMenu("Help");
		add(helpMenu);

		about = new JMenuItem("About");
		helpMenu.add(about);
	}

	public JMenuItem getLogOut() {
		return logOut;
	}

	public void setLogOut(JMenuItem logOut) {
		this.logOut = logOut;
	}

	public JMenuItem getNewProject() {
		return newProject;
	}

	public void setNewProject(JMenuItem newProject) {
		this.newProject = newProject;
	}

	public JMenuItem getNewWidget() {
		return newWidget;
	}

	public void setNewWidget(JMenuItem newWidget) {
		this.newWidget = newWidget;
	}

	public JMenuItem getAbout() {
		return about;
	}

	public void setAbout(JMenuItem about) {
		this.about = about;
	}
}
