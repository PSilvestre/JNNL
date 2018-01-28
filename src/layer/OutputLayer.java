package layer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import connection.Connection;
import connection.NeuronConnection;
import exception.NoInputConnectionsException;
import exception.NoInputInInputLayerException;
import exception.NotInputLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import exception.WrongSizeInInputLayerException;
import functions.ActivationFunction;
import functions.SummingFunction;
import neurons.BiasNeuron;
import neurons.ComputingNeuron;
import neurons.InputNeuron;
import neurons.Neuron;
import neurons.SimpleInputNeuron;

public class OutputLayer implements Layer {

	private List<Neuron> neurons;
	
	public OutputLayer(int numberOfNeurons) {
		neurons = new LinkedList<Neuron>();
		for(int i = 0; i < numberOfNeurons; i++)
			neurons.add(new ComputingNeuron(ActivationFunction.SIGMOID, SummingFunction.WEIGHTED));
	}
	
	@Override
	public void feedForward() throws NoInputInInputLayerException {
		for(Neuron n : neurons)
			n.calculateOutput();
	}

	@Override
	public void feedForward(List<Float> inputs) throws WrongSizeInInputLayerException, NotInputLayerException {
		throw new NotInputLayerException();
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
		return neurons;
	}

	@Override
	public void backProp(List<Float> guesses, List<Float> answers) throws NotOutputNeuronException {
		for(int i = 0; i < guesses.size(); i++)
			neurons.get(i).backProp(guesses.get(i), answers.get(i));
			
	}

	@Override
	public void backProp() throws OutputNeuronException {
		throw new OutputNeuronException();
	}

}
