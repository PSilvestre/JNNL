package exception;

public class GuessAnswerSizeMismatchException extends Exception {
	public GuessAnswerSizeMismatchException() {
		super("Guess and Answer Sets sizes are mismatched");
	}
}
