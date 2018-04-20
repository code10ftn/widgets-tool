package gui;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.Project;

@SuppressWarnings("serial")
public class ProjectTree extends JTree {
	private DefaultMutableTreeNode root;

	public ProjectTree(DefaultMutableTreeNode root) {
		super(root);
		this.root = root;
	}

	public void initTree(List<Project> projects) {
		for (Project project : projects) {
			if (project != null) {
				root.add(new DefaultMutableTreeNode(project.getProjectName()));
			}
		}
		expandRow(0);
	}

	public void addNode(String projectName) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(projectName);
		root.add(newNode);
		// Refresh tree after adding node:
		((DefaultTreeModel) getModel()).reload();
		expandRow(0);
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public void setRoot(DefaultMutableTreeNode root) {
		this.root = root;
	}
}
