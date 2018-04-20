package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Widget implements Serializable {
	protected String widgetName;
	protected String query;
	protected String data;

	public Widget() {
	}

	public Widget(String widgetName, String query, String data) {
		this.widgetName = widgetName;
		this.query = query;
		this.data = data;
	}

	public Widget(Widget other) {
		this.widgetName = new String(other.widgetName);
		this.query = new String(other.query);
		this.data = new String(other.data);
	}

	public abstract Widget copy();

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
