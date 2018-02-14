package neurons;

public interface InputNeuron extends Neuron {
	/**
	 * Feeds input to a neuron. Sets the neurons output value to <b>input</b>.
	 * @param input the value to set the neuron to.
	 */
	void feedInput(float input);
}
