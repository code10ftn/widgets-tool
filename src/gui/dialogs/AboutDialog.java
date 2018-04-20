package gui.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AboutDialog extends JPanel {
	private ImageIcon image;
	private JLabel imageLabel;
	private JLabel info;

	public AboutDialog() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);

		image = new ImageIcon("data\\img\\team.jpg");
		imageLabel = new JLabel("", image, JLabel.CENTER);
		info = new JLabel(
				"<html>Aleksandar Nikolic SW25/2014<br>Luka Maletin SW7/2014<br>Helena Zecevic SW6/2014</html>");
		setupComponents(gbc);
	}

	private void setupComponents(GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(info, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 0, 0, 0);
		add(imageLabel, gbc);
	}
}
