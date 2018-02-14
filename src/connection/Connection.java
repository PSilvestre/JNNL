package connection;

import neurons.Neuron;

/**
 * Represents a connection between two neurons.
 * 
 * @author Pedro Silvestre
 */
public interface Connection {
	/**
	 * Returns a <class>Neuron</class> object, that is the source.
	 * 
	 * @return the source neuron.
	 */
	Neuron getSourceNeuron();

	/**
	 * Returns a <class>Neuron</class> object, that is the receiver.
	 * 
	 * @return the receiver neuron.
	 */
	Neuron getReceiverNeuron();

	/**
	 * Returns a float that is the weight of the connection.
	 * 
	 * @return the weight of the connection.
	 */
	float getWeight();

	/**
	 * Setter for the weight of the connection.
	 * @param weight the new weight.
	 */
	void setWeight(float weight);

	/**
	 * Returns the activation of the source <class>Neuron</class>,
	 *  multiplied by the weight of <b>this</b> <class>Connection</class> 
	 * @return the activation of the source neuron times the weight of this.
	 */
	float getWeightedInput();
}
