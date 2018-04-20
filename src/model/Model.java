package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private User user;
	private List<Widget> widgets;
	private List<Template> templates;

	public Model() {
		widgets = new ArrayList<Widget>();
		setupTemplates();
	}

	private void setupTemplates() {
		templates = new ArrayList<Template>();

		List<Position> positions = new ArrayList<Position>();
		positions.add(new Position(0, 0, 1, 2));
		positions.add(new Position(1, 0, 1, 1));
		positions.add(new Position(2, 0, 1, 1));
		positions.add(new Position(0, 2, 1, 1));
		positions.add(new Position(1, 1, 2, 1));
		positions.add(new Position(1, 2, 2, 1));
		templates.add(new Template(positions));

		positions = new ArrayList<Position>();
		positions.add(new Position(0, 0, 1, 1));
		positions.add(new Position(1, 0, 2, 2));
		positions.add(new Position(0, 1, 1, 1));
		positions.add(new Position(0, 2, 1, 1));
		positions.add(new Position(1, 2, 1, 1));
		positions.add(new Position(2, 2, 1, 1));
		templates.add(new Template(positions));
	}

	public boolean authenticateUser(String username, String password) {
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new FileReader("data\\users.txt"));
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split("\\|");
				if (username.equals(tokens[0]) && password.equals(tokens[1])) {
					deserializeData(username, password, Role.valueOf(tokens[2]));
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void serializeData() {
		serializeUser();
		serializeWidgets();
	}

	private void serializeUser() {
		try {
			FileOutputStream fileOut = new FileOutputStream("data\\user_" + user.getUsername() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(user);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void serializeWidgets() {
		try {
			FileOutputStream fileOut = new FileOutputStream("data\\widget_library.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(widgets);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deserializeData(String username, String password, Role role) {
		deserializeUser(username, password, role);
		deserializeWidgets();
	}

	private void deserializeUser(String username, String password, Role role) {
		try {
			FileInputStream fileIn = new FileInputStream("data\\user_" + username + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			user = (User) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) { // User logged in for the first time
			user = new User(username, password, role);
		}
	}

	@SuppressWarnings("unchecked")
	private void deserializeWidgets() {
		try {
			FileInputStream fileIn = new FileInputStream("data\\widget_library.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			widgets = (ArrayList<Widget>) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) { // App opened for the first time
			widgets = new ArrayList<Widget>();
		}
	}

	public void addProject(Project project) {
		user.addProject(project);
	}

	public boolean removeProject(Project project) {
		return user.removeProject(project);
	}

	public Project findProject(String projectName) {
		return user.findProject(projectName);
	}

	public void addProjectWidget(Project project, Widget widget, int pos) {
		project.addWidget(widget, pos);
	}

	public boolean removeProjectWidget(Project project, Widget widget) {
		return project.removeWidget(widget);
	}

	public Widget findProjectWidget(Project project, String widgetName) {
		return project.findWidget(widgetName);
	}

	public void addLibraryWidget(Widget widget) {
		widgets.add(widget);
	}

	public boolean removeLibraryWidget(Widget widget) {
		if (widget == null) {
			return false;
		}
		if (widgets != null) {
			return widgets.remove(widget);
		}
		return false;
	}

	public Widget findLibraryWidget(String widgetName) {
		for (Widget widget : widgets) {
			if (widget.getWidgetName().equals(widgetName)) {
				return widget;
			}
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Widget> getWidgets() {
		return widgets;
	}

	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
}
