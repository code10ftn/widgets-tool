package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import controller.Controller;
import gui.dialogs.AboutDialog;
import gui.dialogs.NewProjectDialog;
import gui.dialogs.NewWidgetDialog;
import model.LineChartWidget;
import model.Model;
import model.Project;
import model.Role;
import model.TableWidget;
import model.Template;
import model.TextWidget;
import model.Widget;
import view.View;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private MenuBar menuBar;
	private ToolBar toolBar;
	private JPanel treePanel;
	private ProjectTree projectTree;
	private WidgetLibrary widgetLibrary;
	private JPanel canvas;
	private JPanel filterPanel;

	private Model model;
	private View view;
	private Controller controller;

	public MainPanel() {
		model = new Model();
		controller = new Controller(model);

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);

		setupComponents(gbc);
		setupListeners();
		initFilters();
	}

	private void setupComponents(GridBagConstraints gbc) {
		menuBar = new MenuBar();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(menuBar, gbc);

		toolBar = new ToolBar();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(toolBar, gbc);

		filterPanel = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(filterPanel, gbc);

		treePanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(treePanel, gbc);

		canvas = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(canvas, gbc);
	}

	private void setupListeners() {
		menuBar.getNewProject().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewProject();
			}
		});

		menuBar.getNewWidget().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewWidget();
			}
		});

		toolBar.getProjectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewProject();
			}
		});

		toolBar.getWidgetButon().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewWidget();
			}
		});

		menuBar.getAbout().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickAbout();
			}
		});
	}

	private void initFilters() {
		JButton button = new JButton("TestFilter");
		filterPanel.add(button);
	}

	public void initTreePanel() {
		treePanel.removeAll();
		treePanel.setLayout(new GridLayout(1, 1));

		projectTree = new ProjectTree(new DefaultMutableTreeNode("Projects"));
		projectTree.initTree(model.getUser().getProjects());

		treePanel.add(projectTree);

		projectTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				projectTreeClick(me);
			}
		});

		if (model.getUser().getRole() == Role.ADMIN) {
			treePanel.setLayout(new GridLayout(2, 1));
			widgetLibrary = new WidgetLibrary(new DefaultMutableTreeNode("Widgets"));
			widgetLibrary.initTree(model.getWidgets());
			treePanel.add(widgetLibrary);

			widgetLibrary.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me) {
					widgetLibraryClick(me);
				}
			});
		}

	}

	private void createNewProject() {
		final NewProjectDialog prompt = new NewProjectDialog();

		int input = JOptionPane.showOptionDialog(null, prompt, "New project...", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (input == JOptionPane.OK_OPTION) {
			Template temp = null;
			if (prompt.getButton1().isSelected()) {
				temp = model.getTemplates().get(0);
			} else if (prompt.getButton2().isSelected()) {
				temp = model.getTemplates().get(1);
			}

			Project proj = new Project(prompt.getNamePrompt().getText(), temp);
			model.addProject(proj);
			projectTree.addNode(proj.getProjectName());
		}
	}

	private void createNewWidget() {
		final NewWidgetDialog prompt = new NewWidgetDialog();

		int input = JOptionPane.showOptionDialog(null, prompt, "New widget...", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (input == JOptionPane.OK_OPTION) {
			Widget widget = null;
			if (prompt.getButton1().isSelected()) {
				widget = new TableWidget(prompt.getNamePrompt().getText(), "", "");
			} else if (prompt.getButton2().isSelected()) {
				widget = new TextWidget(prompt.getNamePrompt().getText(), prompt.getQuery().getText(), "");
			} else if (prompt.getButton3().isSelected()) {
				widget = new LineChartWidget(prompt.getNamePrompt().getText(), "", "");
			}

			model.addLibraryWidget(widget);
			widgetLibrary.addNode(widget.getWidgetName());
		}
	}

	public void projectTreeClick(MouseEvent me) {
		TreePath tp = projectTree.getPathForLocation(me.getX(), me.getY());
		if (tp == null)
			return;

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
		String projectName = node.toString();
		if (projectName.equals("Projects")) {
			return;
		}

		view = new View();
		controller.setView(view);
		controller.populateView(projectName);
		view.setupListeners(controller);

		canvas.removeAll();
		canvas.setLayout(new GridLayout(1, 1));
		canvas.add(view);
		canvas.repaint();
		canvas.revalidate();
	}

	public void widgetLibraryClick(MouseEvent me) {
		TreePath tp = projectTree.getPathForLocation(me.getX(), me.getY());
		if (tp == null)
			return;

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
		String widgetName = node.toString();

		if (widgetName.equals("Widgets")) {
			return;
		}

		// TODO: Open widget editting dialog
	}

	private void clickAbout() {
		final AboutDialog info = new AboutDialog();
		JOptionPane.showOptionDialog(null, info, "About", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
				null, null);
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
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

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}
}
