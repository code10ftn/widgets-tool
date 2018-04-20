package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Position implements Serializable {
	private int gridx;
	private int gridy;
	private int gridwidth;
	private int gridheight;

	public Position() {
	}

	public Position(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	public int getGridx() {
		return gridx;
	}

	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

	public int getGridwidth() {
		return gridwidth;
	}

	public void setGridwidth(int gridwidth) {
		this.gridwidth = gridwidth;
	}

	public int getGridheight() {
		return gridheight;
	}

	public void setGridheight(int gridheight) {
		this.gridheight = gridheight;
	}
}
