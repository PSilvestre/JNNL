package network;

import java.util.List;

import layer.Layer;
import learning.LearningMethod;

/**
 * Represents a whole network.
 * @author Pedro Silvestre
 *
 */
public interface Network {

	/**
	 * Computes the result on the inputs given.
	 * @param inputs the inputs to feed the network.
	 * @return the result.
	 */
	 List<Float> compute(List<Float> inputs);
	
	 /**
	  * Getter for the number of layers.
	  * @return the number of layers in the network.
	  */
	 int numberOfLayers();
	 
	 /**
	  * Getter for the size of a layer.
	  * @param layerNum number of the layer. 0 is input, numberOfLayers()-1 is output.
	  * @return the size of the layer.
	  */
	 int getSizeOfLayer(int layerNum);
	 
	 /**
	  * Getter for the layer.
	  * @param layerNum number of the layer. 0 is input, numberOfLayers()-1 is output.
	  * @return the layer requested.
	  */
	 Layer getLayer(int layerNum);
	 
	 /**
	  * Returns the learning method of the network.
	  * @return the LearningMethod object.
	  */
	 LearningMethod getLearningMethod();
	 
	 /**
	  * Getter for the output layer.
	  * @return the output layer of the network.
	  */
	 Layer getOutputLayer();
	 
	 /**
	  * Getter for the input layer.
	  * @return the input layer of the network.
	  */
	 Layer getInputLayer();

	 /**
	  * Returns the list of layers.
	  * @return the list of layers.
	  */
	List<Layer> getLayers();
	 
	 
}
