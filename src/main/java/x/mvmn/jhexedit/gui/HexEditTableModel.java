package x.mvmn.jhexedit.gui;

import java.nio.charset.Charset;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

public class HexEditTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 8324868657637487819L;
	private final byte[] buffer;
	private final int bufferLen;
	private final int columnCount;
	private final int rowCount;
	private final Charset charset;

	public HexEditTableModel(byte[] buffer, int columnCount, Charset charset) {
		this.buffer = buffer;
		this.bufferLen = buffer.length;
		this.columnCount = columnCount;
		this.rowCount = Math.floorDiv(buffer.length, columnCount);
		this.charset = charset;
	}

	public void onBufferUpdate() {
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public int getColumnCount() {
		return columnCount + 1;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column < columnCount;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return column < columnCount ? Byte.class : String.class;
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (row < rowCount) {
			int rowStart = row * columnCount;
			int offset = rowStart + column;
			if (column < columnCount) {
				byte result = offset < bufferLen ? buffer[offset] : (byte) 0;
				return result;
			} else {
				byte rowBytes[] = Arrays.copyOfRange(buffer, rowStart, Math.min(bufferLen, rowStart + columnCount));
				return new String(rowBytes, charset);
			}
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		fireTableCellUpdated(row, column);
	}
}
