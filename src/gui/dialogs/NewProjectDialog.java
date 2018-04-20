package gui.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewProjectDialog extends JPanel {
	private JLabel nameLabel;
	private ButtonGroup radioGroup;
	private JTextField namePrompt;
	private JRadioButton button1;
	private ImageIcon image1;
	private JLabel labImage1;
	private ImageIcon image2;
	private JLabel labImage2;
	private JRadioButton button2;
	private JLabel templateLabel;

	public NewProjectDialog() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);

		nameLabel = new JLabel("Enter project name: ");
		radioGroup = new ButtonGroup();
		namePrompt = new JTextField(15);
		button1 = new JRadioButton();
		image1 = new ImageIcon("data\\img\\template1.jpg");
		labImage1 = new JLabel(image1);
		image2 = new ImageIcon("data\\img\\template2.jpg");
		labImage2 = new JLabel(image2);
		button2 = new JRadioButton();
		templateLabel = new JLabel("Choose template:");
		button1.setSelected(true);

		setupComponents(gbc);
	}

	private void setupComponents(GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(nameLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(namePrompt, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(templateLabel, gbc);

		radioGroup.add(button1);
		radioGroup.add(button2);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(button1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		add(labImage1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(5, 0, 0, 0);
		add(button2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		add(labImage2, gbc);
	}

	public JTextField getNamePrompt() {
		return namePrompt;
	}

	public void setNamePrompt(JTextField namePrompt) {
		this.namePrompt = namePrompt;
	}

	public JRadioButton getButton1() {
		return button1;
	}

	public void setButton1(JRadioButton button1) {
		this.button1 = button1;
	}

	public JRadioButton getButton2() {
		return button2;
	}

	public void setButton2(JRadioButton button2) {
		this.button2 = button2;
	}
}
