package layer;
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
import neurons.InputNeuron;
import neurons.Neuron;
import neurons.SimpleInputNeuron;

public class InputLayer implements Layer {

	private List<InputNeuron> neurons;
	
	public InputLayer(int numNeurons) {
		neurons = new LinkedList<InputNeuron>();
		for(int i = 0; i < numNeurons; i++)
			neurons.add(new SimpleInputNeuron());
			
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
	}

	@Override
	public List<Neuron> getNeurons() {
		List<Neuron> neuronsTemp = new LinkedList<Neuron>();
		for(Neuron n : neurons)
			neuronsTemp.add(n);
		return neuronsTemp;
	}

	@Override
	public void backProp(List<Float> guesses, List<Float> answers) throws NotOutputNeuronException {
		throw new NotOutputNeuronException();
	}

	@Override
	public void backProp() throws OutputNeuronException, NotHiddenLayerException {
		for(Neuron n : neurons)
			n.backProp();
		
	}

}
