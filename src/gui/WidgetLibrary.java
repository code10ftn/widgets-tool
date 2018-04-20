package gui;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.Widget;

@SuppressWarnings("serial")
public class WidgetLibrary extends JTree {
	private DefaultMutableTreeNode root;

	public WidgetLibrary(DefaultMutableTreeNode root) {
		super(root);
		this.root = root;
	}

	public void initTree(List<Widget> widgets) {
		for (Widget widget : widgets) {
			if (widget != null) {
				root.add(new DefaultMutableTreeNode(widget.getWidgetName()));
			}
		}
		expandRow(0);
	}

	public void addNode(String widgetName) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(widgetName);
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
