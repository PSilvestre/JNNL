package exception;

public class DatasetSizeDifferentFromNetException extends Exception {
	public DatasetSizeDifferentFromNetException() {
		super("Dataset input or output size is different from network");
	}
}
