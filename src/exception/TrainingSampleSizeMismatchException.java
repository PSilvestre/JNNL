package exception;

public class TrainingSampleSizeMismatchException extends Exception {
	public TrainingSampleSizeMismatchException() {
		super("Training sample input or output size doesnt match datasets size.");
	}
}
