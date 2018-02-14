package layer;
import java.util.List;

import exception.NoInputConnectionsException;
import exception.NoInputInInputLayerException;
import exception.NotHiddenLayerException;
import exception.NotInputLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import exception.WrongSizeInInputLayerException;
import neurons.Neuron;

public interface Layer {
	/**
	 * Calculate this layers values. Must call previous layers feedForward first.
	 * @throws NoInputInInputLayerException if called on an input layer.
	 */
	void feedForward() throws NoInputInInputLayerException ;
	
	/**
	 * Feeds inputs. Use only on input layers.
	 * @param inputs - a list of inputs to feed to layer.
	 * @throws WrongSizeInInputLayerException if the size of <b>inputs</b> differs from layer size.
	 * @throws NotInputLayerException if called on a non-input layer.
	 */
	void feedForward(List<Float> inputs) throws WrongSizeInInputLayerException, NotInputLayerException;
	
	/**
	 * Returns the outputs of a layer. Generally should be called after feeding forward.
	 * @return a list of outputs. Doesnt include bias.
	 */
	List<Float> getOutputs();
	
	/**
	 * Fully connects this layer to the next. Does not however connect this layers neurons to other layers bias neuron.
	 * @param layer the layer to connect to.
	 * @throws NoInputConnectionsException if you attempt to connect to a layer containing input neurons.
	 */
	void connectTo(Layer layer) throws NoInputConnectionsException;

	/**
	 * Getter for the neurons in this layer. Does not include bias neuron.
	 * @return a list of neurons of this layer.
	 */
	List<Neuron> getNeurons();

	/**
	 * Returns the number of neurons in this layer, excluding bias. 
	 * @return the number of neurons in this layer.
	 */
	int size();
}
