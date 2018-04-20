package view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Widget;

@SuppressWarnings("serial")
public class TableWidgetPainter extends JTable implements WidgetPainter {

	private Widget widget;

	public TableWidgetPainter(Widget widget) {
		super();
		this.widget = widget;

		String columnNames[] = { "Column 1", "Column 2", "Column 3" };
		String dataValues[][] = { { "12", "234", "67" }, { "-123", "43", "853" }, { "93", "89.2", "109" },
				{ "279", "9033", "3092" } };
		setModel(new DefaultTableModel(dataValues, columnNames));
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
