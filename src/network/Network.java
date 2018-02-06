package network;

import java.util.List;

import layer.Layer;
import learning.LearningMethod;

public interface Network {

	 List<Float> compute(List<Float> inputs);
	
	 int numberOfLayers();
	 
	 int getSizeOfLayer(int layerNum);
	 
	 Layer getLayer(int layerNum);
	 
	 LearningMethod getLearningMethod();
	 
	 Layer getOutputLayer();
	 Layer getInputLayer();

	List<Layer> getLayers();
	 
	 
}
