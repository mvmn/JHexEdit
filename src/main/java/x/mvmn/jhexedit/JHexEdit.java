package x.mvmn.jhexedit;

import x.mvmn.jhexedit.gui.JHexEditWindow;

public class JHexEdit {
	public static void main(String args[]) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		JHexEditWindow window = new JHexEditWindow();
		window.pack();
		window.setVisible(true);
	}
}
