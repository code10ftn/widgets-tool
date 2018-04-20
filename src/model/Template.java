package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Template implements Serializable {
	private List<Position> positions;

	public Template() {
		positions = new ArrayList<Position>();
	}

	public Template(List<Position> positions) {
		this.positions = positions;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
}
