package exception;

public class NoInputConnectionsException extends Exception {

	public NoInputConnectionsException() {
		super("Neuron cant have input connections.");
	}

}
