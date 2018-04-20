package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Role;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private MainPanel mainPanel;
	private LoginPanel loginPanel;

	public MainFrame() {
		setTitle("Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new CardLayout());

		setupComponents();
		setupListeners();
	}

	private void setupComponents() {
		mainPanel = new MainPanel();
		add(mainPanel);

		loginPanel = new LoginPanel();
		add(loginPanel);

		loginPanel.setVisible(true);
		mainPanel.setVisible(false);

		pack();
		setMinimumSize(getSize());
		centerPanel();
		getRootPane().setDefaultButton(loginPanel.getLoginButton());
	}

	private void setupListeners() {
		loginPanel.getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickLoginButton();
			}
		});

		mainPanel.getMenuBar().getLogOut().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickLogoutItem();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (mainPanel.isVisible()) {
					mainPanel.getModel().serializeData();
				}
			}
		});
	}

	private void centerPanel() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private void disableButtons() {
		if (mainPanel.getModel().getUser().getRole() == Role.REGULAR) {
			mainPanel.getToolBar().getWidgetButon().setVisible(false);
			mainPanel.getMenuBar().getNewWidget().setVisible(false);
		} else {
			mainPanel.getToolBar().getWidgetButon().setVisible(true);
			mainPanel.getMenuBar().getNewWidget().setVisible(true);
		}
	}

	private void clickLoginButton() {
		String username = loginPanel.getUsernameField().getText();
		String password = new String(loginPanel.getPasswordField().getPassword());
		if (mainPanel.getModel().authenticateUser(username, password)) {
			loginPanel.resetFields();
			loginPanel.setVisible(false);
			mainPanel.setVisible(true);
			mainPanel.initTreePanel();
			setSize(800, 600);
			centerPanel();
			disableButtons();
		} else {
			JOptionPane.showMessageDialog(null, "Error: Wrong username or password!", "Error Massage",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clickLogoutItem() {
		if (mainPanel.getView() != null) {
			mainPanel.getView().removeAll();
		}
		mainPanel.getModel().serializeData();
		loginPanel.setVisible(true);
		mainPanel.setVisible(false);
		setSize(getMinimumSize());
		centerPanel();
	}
}
