package exception;

public class NoSumOfInputsException extends Exception {
	public NoSumOfInputsException(){
		super("Neuron has no sum of inputs.");
	}
}
