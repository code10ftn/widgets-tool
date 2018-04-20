package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String username;
	private String passwordHash;
	private Role role;
	private List<Project> projects;

	public User() {
		projects = new ArrayList<Project>();
	}

	public User(String username, String passwordHash, Role role) {
		this();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public User(String username, String passwordHash, Role role, List<Project> projects) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.projects = projects;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void addProject(Project project) {
		projects.add(project);
	}

	public boolean removeProject(Project project) {
		if (project == null) {
			return false;
		}
		if (projects != null) {
			return projects.remove(project);
		}
		return false;
	}

	public Project findProject(String projectName) {
		for (Project project : projects) {
			if (project.getProjectName().equals(projectName)) {
				return project;
			}
		}
		return null;
	}
}
