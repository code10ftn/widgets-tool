package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class Project implements Serializable {
	private String projectName;
	private Template template;
	private List<Widget> widgets;

	public Project() {
		widgets = new ArrayList<Widget>();
	}

	public Project(String projectName, Template template) {
		widgets = new ArrayList<Widget>(Collections.nCopies(template.getPositions().size(), null));
		this.projectName = projectName;
		this.template = template;
	}

	public Project(String projectName, Template template, List<Widget> widgets) {
		this.projectName = projectName;
		this.template = template;
		this.widgets = widgets;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<Widget> getWidgets() {
		return widgets;
	}

	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}

	public void addWidget(Widget widget, int pos) {
		widgets.set(pos, widget);
	}

	public boolean removeWidget(Widget widget) {
		if (widget == null) {
			return false;
		}
		if (widgets != null) {
			return widgets.remove(widget);
		}
		return false;
	}

	public Widget findWidget(String widgetName) {
		for (Widget widget : widgets) {
			if (widget.getWidgetName().equals(widgetName)) {
				return widget;
			}
		}
		return null;
	}
}
