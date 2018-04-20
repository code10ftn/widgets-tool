package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TableWidget extends Widget implements Serializable {

	public TableWidget() {
		super();
	}

	public TableWidget(String widgetName, String query, String data) {
		super(widgetName, query, data);
	}

	public TableWidget(Widget other) {
		super(other);
	}

	@Override
	public Widget copy() {
		return new TableWidget(this);
	}
}
