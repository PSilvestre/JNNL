package layer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import connection.Connection;
import connection.NeuronConnection;
import exception.NoInputConnectionsException;
import exception.NoInputInInputLayerException;
import exception.NotHiddenLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import exception.WrongSizeInInputLayerException;
import neurons.BiasNeuron;
import neurons.InputNeuron;
import neurons.Neuron;
import neurons.SimpleInputNeuron;

public class InputLayer implements Layer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private List<InputNeuron> neurons;
	private Neuron bias;
	public InputLayer(int numNeurons) {
		neurons = new LinkedList<InputNeuron>();
		for(int i = 0; i < numNeurons; i++)
			neurons.add(new SimpleInputNeuron());
		bias = new BiasNeuron();
	}
	
	@Override
	public void feedForward() throws NoInputInInputLayerException {
		throw new NoInputInInputLayerException();
	}

	@Override
	public void feedForward(List<Float> inputs) throws WrongSizeInInputLayerException{
		if(inputs.size() != neurons.size()) 
			throw new WrongSizeInInputLayerException(neurons.size(), inputs.size());
		for(int i = 0; i < neurons.size(); i++)
			neurons.get(i).feedInput(inputs.get(i));

	}

	

	@Override
	public List<Float> getOutputs() {
		List<Float> outputs = new ArrayList<Float>();
		for(Neuron n : neurons)
			outputs.add(n.getOutput());
		return outputs;
	}

	@Override
	public void connectTo(Layer layer) throws NoInputConnectionsException {
		Connection c;
		for(Neuron n : neurons) {
			for(Neuron on : layer.getNeurons()) {
				c = new NeuronConnection(n, on);
				n.addOutputConnection(c);
				on.addInputConnection(c);
			}
		}
		for(Neuron on : layer.getNeurons()) {
			c = new NeuronConnection(bias, on);
			bias.addOutputConnection(c);
			on.addInputConnection(c);
		}
		
	}

	@Override
	public List<Neuron> getNeurons() {
		List<Neuron> neuronsTemp = new LinkedList<Neuron>();
		for(Neuron n : neurons)
			neuronsTemp.add(n);
		return neuronsTemp;
	}

	@Override
	public int size() {
		return neurons.size();
	}

}
