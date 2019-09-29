package x.mvmn.jhexedit.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class FileDropTransferHandler extends TransferHandler {
	private static final long serialVersionUID = -3917969466507836476L;
	private final Consumer<File> fileConsumer;
	private final Consumer<Throwable> exceptionHandler;

	public FileDropTransferHandler(Consumer<File> fileConsumer, Consumer<Throwable> exceptionHandler) {
		if (fileConsumer == null) {
			throw new IllegalArgumentException("Parameter fileConsumer cannot be null");
		}
		this.fileConsumer = fileConsumer;
		this.exceptionHandler = exceptionHandler;
	}

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
		for (DataFlavor df : transferFlavors) {
			if (df.getMimeType().equals(DataFlavor.javaFileListFlavor.getMimeType())) {
				return true;
			}
		}
		return super.canImport(comp, transferFlavors);
	}

	@Override
	public boolean importData(JComponent comp, Transferable transferable) {
		boolean result = false;
		try {
			Object drop = transferable.getTransferData(DataFlavor.javaFileListFlavor);
			if (drop instanceof Collection) {
				Collection<?> dropCollection = (Collection<?>) drop;
				Iterator<?> iterator = dropCollection.iterator();
				while (iterator.hasNext()) {
					Object singleDrop = iterator.next();
					if (singleDrop instanceof File) {
						fileConsumer.accept((File) singleDrop);
						result = true;
					}
				}
			}
		} catch (Throwable t) {
			if (exceptionHandler != null) {
				exceptionHandler.accept(t);
			} else {
				t.printStackTrace();
			}
		}
		return result;
	}
}
