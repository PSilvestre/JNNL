package layer;
import java.util.List;

import exception.NoInputConnectionsException;
import exception.NoInputInInputLayerException;
import exception.NotInputLayerException;
import exception.WrongSizeInInputLayerException;
import neurons.Neuron;

public interface Layer {
	/**
	 * Calculate this layers values.
	 * @throws NoInputInInputLayerException
	 */
	void feedForward() throws NoInputInInputLayerException ;
	
	/**
	 * Feeds inputs. Use only on input layers.
	 * @param inputs - a list of inputs to feed to layer.
	 * @throws WrongSizeInInputLayerException
	 * @throws NotInputLayerException
	 */
	void feedForward(List<Float> inputs) throws WrongSizeInInputLayerException, NotInputLayerException;
	
	/**
	 * 
	 * @return a list of outputs. Doesnt include bias.
	 */
	List<Float> getOutputs();
	
	void connectTo(Layer layer) throws NoInputConnectionsException;

	List<Neuron> getNeurons();
}