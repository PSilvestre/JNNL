package exception;

public class NotOutputNeuronException extends Exception {
	public NotOutputNeuronException() {
		super("Not an output neuron, used wrong backprop function most likely.");
	}
}
