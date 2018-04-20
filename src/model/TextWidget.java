package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TextWidget extends Widget implements Serializable {

	public TextWidget() {
		super();
	}

	public TextWidget(String widgetName, String query, String data) {
		super(widgetName, query, data);
	}

	public TextWidget(Widget other) {
		super(other);
	}

	@Override
	public Widget copy() {
		return new TextWidget(this);
	}
}
