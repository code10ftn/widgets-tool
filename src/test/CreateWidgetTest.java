package test;

import static org.junit.Assert.assertEquals;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Before;
import org.junit.Test;

import gui.ProjectTree;
import model.Model;
import model.TextWidget;
import model.Widget;

public class CreateWidgetTest {
	private Widget widget;
	private Model model;
	private String name;
	private ProjectTree projectTree;
	private DefaultMutableTreeNode root;

	@Before
	public void setUp() throws Exception {
		model = new Model();
		name = "testWidget";
		widget = new TextWidget(name, "test query", "test data");
		root = new DefaultMutableTreeNode("testRoot");
		projectTree = new ProjectTree(root);
	}

	@Test
	public void testCreateNewWidget() {
		model.addLibraryWidget(widget);

		Widget foundWidget = model.findLibraryWidget(name);
		assertEquals(widget, foundWidget);

		projectTree.addNode(name);

		DefaultMutableTreeNode foundNode = (DefaultMutableTreeNode) projectTree.getModel().getChild(root, 0);

		assertEquals(name, foundNode.toString());
	}
}
