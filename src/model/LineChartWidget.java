package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineChartWidget extends Widget implements Serializable {
	private int targetValue;

	public LineChartWidget() {
		super();
	}

	public LineChartWidget(String widgetName, String query, String data) {
		super(widgetName, query, data);
	}

	public LineChartWidget(String widgetName, String query, String data, int targetValue) {
		super(widgetName, query, data);
		this.targetValue = targetValue;
	}

	public LineChartWidget(Widget other) {
		super(other);
	}

	@Override
	public Widget copy() {
		return new LineChartWidget(this);
	}

	public int getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(int targetValue) {
		this.targetValue = targetValue;
	}
}
