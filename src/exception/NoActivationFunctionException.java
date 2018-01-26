package exception;

public class NoActivationFunctionException extends Exception {
	public NoActivationFunctionException() {
		super("Neuron has no Activation Function");
	}
}
