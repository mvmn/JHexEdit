package x.mvmn.jhexedit.util.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

public class KeyStroker {

	protected final char c;
	protected int modifiers = 0;

	public KeyStroker(char c) {
		this.c = c;
	}

	public static KeyStroker of(char c) {
		return new KeyStroker(c);
	}

	public KeyStroke build() {
		if (modifiers == 0) {
			return KeyStroke.getKeyStroke(Character.valueOf(c));
		} else {
			return KeyStroke.getKeyStroke(Character.valueOf(c), modifiers);
		}
	}

	public KeyStroker alt() {
		modifiers |= ActionEvent.ALT_MASK;
		return this;
	}

	public KeyStroker ctrl() {
		modifiers |= ActionEvent.CTRL_MASK;
		return this;
	}

	public KeyStroker shift() {
		modifiers |= ActionEvent.SHIFT_MASK;
		return this;
	}

	public KeyStroker meta() {
		modifiers |= ActionEvent.META_MASK;
		return this;
	}

	public KeyStroker defMod() {
		modifiers |= Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		return this;
	}
}
