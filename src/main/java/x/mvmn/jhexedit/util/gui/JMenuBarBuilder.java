package x.mvmn.jhexedit.util.gui;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JMenuBarBuilder {

	protected final JMenuBar jMenuBar = new JMenuBar();

	public static JMenuBarBuilder jMenuBar() {
		return new JMenuBarBuilder();
	}

	public JMenuBar build() {
		return jMenuBar;
	}

	public JMenuBuilder addMenu(String name) {
		return new JMenuBuilder(name);
	}

	public class JMenuBuilder {

		protected final JMenu jMenu;

		public JMenuBuilder(String name) {
			this.jMenu = new JMenu(name);
		}

		public JMenuBuilder key(char key) {
			jMenu.setMnemonic(key);
			return this;
		}

		public JMenuBuilder action(Action action) {
			jMenu.setAction(action);
			return this;
		}

		public JMenuBuilder act(ActionListener actListener) {
			jMenu.addActionListener(actListener);
			return this;
		}

		public JMenuBarBuilder build() {
			jMenuBar.add(jMenu);
			return JMenuBarBuilder.this;
		}

		public JMenuBuilder addSeparator() {
			jMenu.addSeparator();
			return this;
		}

		public JMenuItemBuilder addItem(String name) {
			return new JMenuItemBuilder(name);
		}

		public class JMenuItemBuilder {

			private final JMenuItem jMenuItem;

			public JMenuItemBuilder(String name) {
				this.jMenuItem = new JMenuItem(name);
			}

			public JMenuBuilder build() {
				jMenu.add(jMenuItem);
				return JMenuBuilder.this;
			}

			public JMenuItemBuilder act(ActionListener actListener) {
				jMenuItem.addActionListener(actListener);
				return this;
			}

			public JMenuItemBuilder key(KeyStroke keyStroke) {
				jMenuItem.setAccelerator(keyStroke);
				return this;
			}
		}
	}
}
