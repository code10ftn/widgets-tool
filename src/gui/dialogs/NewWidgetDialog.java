package gui.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewWidgetDialog extends JPanel {
	private GridBagConstraints gbc;
	private JLabel nameLabel;
	private JTextField namePrompt;
	private JLabel queryLabel;
	private JTextField query;
	private ButtonGroup radioGroup;
	private JRadioButton button1;
	private JRadioButton button2;
	private JRadioButton button3;
	private JLabel typeLabel;

	private boolean lineChart;
	private JLabel targetLabel;
	private JTextField target;

	public NewWidgetDialog() {
		GridBagLayout gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(gbl);
		nameLabel = new JLabel("Enter widget name: ");
		radioGroup = new ButtonGroup();
		namePrompt = new JTextField(15);
		queryLabel = new JLabel("Enter query: ");
		query = new JTextField(15);
		button1 = new JRadioButton("Table widget");
		button2 = new JRadioButton("Text widget");
		button3 = new JRadioButton("Line chart widget");
		typeLabel = new JLabel("Choose widget type:");

		button1.setSelected(true);
		radioGroup.add(button1);
		radioGroup.add(button2);
		radioGroup.add(button3);

		targetLabel = new JLabel("Define target value: ");
		target = new JTextField(5);
		lineChart = false;

		setupComponents(gbc);
		setupListeners();
	}

	private void setupComponents(GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(nameLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(namePrompt, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(queryLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		add(query, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(typeLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;
		add(button1, gbc);

		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;
		add(button2, gbc);

		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;
		add(button3, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		add(targetLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		add(target, gbc);

		targetLabel.setVisible(false);
		target.setVisible(false);
	}

	private void setupListeners() {
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lineChart) {
					lineChart = false;
					targetLabel.setVisible(false);
					target.setVisible(false);
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lineChart) {
					lineChart = false;
					targetLabel.setVisible(false);
					target.setVisible(false);
				}
			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lineChart = true;
				targetLabel.setVisible(true);
				target.setVisible(true);
			}
		});
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

	public JRadioButton getButton3() {
		return button3;
	}

	public void setButton3(JRadioButton button3) {
		this.button3 = button3;
	}

	public JTextField getQuery() {
		return query;
	}

	public void setQuery(JTextField query) {
		this.query = query;
	}
}
