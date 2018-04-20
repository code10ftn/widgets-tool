package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	private JLabel usernameLabel;
	private JLabel passwordLabel;

	private JTextField usernameField;
	private JPasswordField passwordField;

	private JButton loginButton;

	public LoginPanel() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);

		setupComponents(gbc);
		setupListeners();
	}

	private void setupComponents(GridBagConstraints gbc) {
		usernameLabel = new JLabel("Username ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(usernameLabel, gbc);

		passwordLabel = new JLabel("Password ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(passwordLabel, gbc);

		usernameField = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(usernameField, gbc);

		passwordField = new JPasswordField(10);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(passwordField, gbc);

		loginButton = new JButton("Login");
		loginButton.setEnabled(false);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(loginButton, gbc);
	}

	private void setupListeners() {
		usernameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				inputChanged();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				inputChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				inputChanged();
			}
		});

		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				inputChanged();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				inputChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				inputChanged();
			}
		});
	}

	private void inputChanged() {
		if (usernameField.getText().equals("") || passwordField.getPassword().length == 0) {
			loginButton.setEnabled(false);
		} else {
			loginButton.setEnabled(true);
		}
	}

	public void resetFields() {
		getUsernameField().setText("");
		getPasswordField().setText("");
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
}
