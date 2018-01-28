package network;

import java.util.LinkedList;
import java.util.List;

import exception.NoInputConnectionsException;
import exception.NoInputInInputLayerException;
import exception.NotHiddenLayerException;
import exception.NotInputLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import exception.WrongSizeInInputLayerException;
import layer.HiddenLayer;
import layer.InputLayer;
import layer.Layer;
import layer.OutputLayer;

public class Network {

	private List<Layer> layers;
	private int[] numNeuronsPerLayer;
	
	public Network(int[] numNeuronsPerLayer) throws NoInputConnectionsException {
		this.numNeuronsPerLayer = numNeuronsPerLayer;
		layers = new LinkedList<Layer>();
		layers.add(new InputLayer(numNeuronsPerLayer[0]));
		for(int i = 1; i < numNeuronsPerLayer.length-1; i++)
			layers.add(new HiddenLayer(numNeuronsPerLayer[i]));
		layers.add(new OutputLayer(numNeuronsPerLayer[numNeuronsPerLayer.length-1]));
		for(int i = 0; i < layers.size()-1; i++) {
			layers.get(i).connectTo(layers.get(i+1));
		}
	}
	
	public List<Float> compute(List<Float> inputs){
		try {
			layers.get(0).feedForward(inputs);
			for(int i = 1; i < layers.size(); i++)
				layers.get(i).feedForward();
		} catch (WrongSizeInInputLayerException | NotInputLayerException e) {
			e.printStackTrace();
		} catch (NoInputInInputLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return layers.get(layers.size()-1).getOutputs();
	}
	
	public int numberOfLayers() {
		return layers.size();
	}
	
	public int getSizeOfLayer(int layerNum) {
		return numNeuronsPerLayer[layerNum];
	}
	
	public Layer getLayer(int layerNum) {
		return layers.get(layerNum);
	}
	
	public void BackProp(List<Float> guesses, List<Float> answers) throws NotOutputNeuronException, OutputNeuronException, NotHiddenLayerException {
		layers.get(layers.size()-1).backProp(guesses, answers);
		for(int i = layers.size()-2; i > 0; i--) {
			layers.get(i).backProp();
		}
		
	}
}
