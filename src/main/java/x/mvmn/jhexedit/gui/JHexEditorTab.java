package x.mvmn.jhexedit.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import x.mvmn.jhexedit.util.HexUtil;
import x.mvmn.jhexedit.util.gui.ColorUtil;

public class JHexEditorTab extends JPanel {
	private static final long serialVersionUID = -6855908048277650277L;

	private final JTable mainTable;
	private static JLabel[] HEX_VAL_LABELS = new JLabel[256];
	static {
		for (int i = 0; i < 256; i++) {
			HEX_VAL_LABELS[i] = new JLabel(HexUtil.toHex((byte) i));
		}
	}

	public JHexEditorTab(HexEditTableModel model) {
		mainTable = new JTable(model);
		Font mono = Font.decode(Font.MONOSPACED);
		Font monoBold = mono.deriveFont(mono.getStyle() | Font.BOLD);
		mainTable.setFont(mono);
		mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainTable.setDefaultRenderer(Byte.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				Byte val = (Byte) value;
				JLabel label = HEX_VAL_LABELS[val.byteValue() & 0xFF];
				label.setOpaque(true);
				label.setFont(hasFocus ? monoBold : mono);
				if (isSelected) {
					label.setBackground(mainTable.getSelectionBackground());
					label.setForeground(mainTable.getSelectionForeground());
					if (hasFocus) {
						label.setBackground(ColorUtil.invertHue(mainTable.getSelectionBackground()));
						label.setForeground(ColorUtil.invertHue(mainTable.getSelectionForeground()));
					}
				} else {
					label.setBackground(mainTable.getBackground());
					label.setForeground(mainTable.getForeground());
				}
				return label;
			}
		});
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mainTable), BorderLayout.CENTER);
	}

	public void replaceModel(HexEditTableModel model) {
		mainTable.setModel(model);
	}
}
