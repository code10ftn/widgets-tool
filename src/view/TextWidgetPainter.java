package view;

import javax.swing.JTextArea;

import model.Widget;

@SuppressWarnings("serial")
public class TextWidgetPainter extends JTextArea implements WidgetPainter {

	private Widget widget;

	public TextWidgetPainter(Widget widget) {
		super();
		this.widget = widget;

		setText("Sample text...");
		setEditable(false);
	}

	@Override
	public void paint() {
		validate();
		repaint();
		revalidate();
	}

	@Override
	public Widget getWidget() {
		return widget;
	}
}
