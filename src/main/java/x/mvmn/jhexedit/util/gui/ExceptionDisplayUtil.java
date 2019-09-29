package x.mvmn.jhexedit.util.gui;

import java.awt.BorderLayout;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ExceptionDisplayUtil {

	public static void displayExceptionMessage(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));

		JDialog jDialog = new JDialog();
		jDialog.setTitle("Error occurred");
		jDialog.getContentPane().setLayout(new BorderLayout());
		jDialog.getContentPane().add(
				BorderUtil.borderize(new JLabel(t.getClass().getSimpleName() + ": " + t.getMessage(), JLabel.CENTER), 10),
				BorderLayout.NORTH);
		jDialog.getContentPane().add(new JScrollPane(new JTextArea(sw.getBuffer().toString())), BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.addActionListener(ae -> {
			jDialog.setVisible(false);
			jDialog.dispose();
		});
		jDialog.getContentPane().add(ok, BorderLayout.SOUTH);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialog.pack();
		jDialog.setVisible(true);
	}

	public static void main(String[] args) {
		displayExceptionMessage(new RuntimeException("Test"));
	}
}
