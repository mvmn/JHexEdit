package x.mvmn.jhexedit.util.gui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class BorderUtil {

	public static JComponent borderize(JComponent cmp, int borderSize) {
		cmp.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
		return cmp;
	}
}
