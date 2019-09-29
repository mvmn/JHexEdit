package x.mvmn.jhexedit.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import x.mvmn.jhexedit.util.gui.ExceptionDisplayUtil;
import x.mvmn.jhexedit.util.gui.JMenuBarBuilder;
import x.mvmn.jhexedit.util.gui.KeyStroker;

public class JHexEditWindow extends JFrame {

	private static final long serialVersionUID = -1234355343618105116L;

	private JTabbedPane mainPane;

	public JHexEditWindow() {
		JMenuBar menubar = JMenuBarBuilder.jMenuBar().addMenu("File").key('F').addItem("Open").key(KeyStroker.of('O').defMod().build())
				.act(actEvt -> this.showOpenFileDialog()).build().build().build();
		this.setMinimumSize(new Dimension(400, 300));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setJMenuBar(menubar);
		this.getContentPane().setLayout(new BorderLayout());
		mainPane = new JTabbedPane();
		mainPane.addTab("No files open", new JLabel("Drop files here to open", JLabel.CENTER));
		mainPane.setTransferHandler(new FileDropTransferHandler(this::openFile, ExceptionDisplayUtil::displayExceptionMessage));
		this.getContentPane().add(mainPane);
	}

	private void showOpenFileDialog() {
		JFileChooser jfc = new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(this)) {
			for (File file : jfc.getSelectedFiles()) {
				openFile(file);
			}
		}
	}

	public void openFile(final File file) {
		new Thread() {
			public void run() {
				try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
					byte buffer[] = new byte[(int) Math.min(file.length(), 16384)];
					raf.read(buffer);
					JHexEditorTab tab = new JHexEditorTab(new HexEditTableModel(buffer, 10, StandardCharsets.US_ASCII));
					SwingUtilities.invokeLater(() -> mainPane.addTab(file.getName(), tab));
				} catch (Throwable t) {
					SwingUtilities.invokeLater(() -> ExceptionDisplayUtil.displayExceptionMessage(t));
				}
			}
		}.start();
	}
}
