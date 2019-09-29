package x.mvmn.jhexedit.util;

public class HexUtil {

	private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	private static final String[] HEX_VALS;
	static {
		HEX_VALS = new String[256];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				HEX_VALS[i * 16 + j] = (HEX_DIGITS[i] + HEX_DIGITS[j]).intern();
			}
		}
	}

	public static String toHex(byte byteVal) {
		return HEX_VALS[byteVal & 0xFF];
	}

	public static void main(String args[]) {
		for (int i = 0; i < 256; i++) {
			System.out.println(toHex((byte) i));
		}
	}
}
