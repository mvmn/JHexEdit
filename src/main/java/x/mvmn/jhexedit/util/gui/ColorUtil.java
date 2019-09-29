package x.mvmn.jhexedit.util.gui;

import java.awt.Color;

public class ColorUtil {

	public static Color invertHue(Color color) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
		hsb[0] = (hsb[0] + 0.5f) % 1;
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
}
