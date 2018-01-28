package exception;

public class NotHiddenLayerException extends Exception {
	public NotHiddenLayerException() {
		super("This neuron cant backprop");
	}
}
