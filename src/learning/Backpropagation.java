package learning;

import java.io.Serializable;
import java.util.Iterator;

import connection.Connection;
import data.Dataset;
import data.TrainingSample;
import exception.DatasetSizeDifferentFromNetException;
import exception.NoActivationFunctionException;
import exception.NoSumOfInputsException;
import layer.Layer;
import network.Network;
import neurons.Neuron;

public class Backpropagation implements LearningMethod, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 60L;
	private float learningRate;

	public Backpropagation(float learningRate) {

		this.learningRate = learningRate;
	}

	@Override
	public void apply(Dataset data, Network net) throws DatasetSizeDifferentFromNetException {
		if(data.getNumberInputs() != net.getInputLayer().size() || data.getNumberOutputs() != net.getOutputLayer().size())
			throw new DatasetSizeDifferentFromNetException();
		Iterator<TrainingSample> it = data.getIterator();
		try {
			while (it.hasNext()) {

				TrainingSample ts = it.next();
				net.compute(ts.getInputs());
				Layer output = net.getOutputLayer();
				int i = 0;
				for (Neuron n : output.getNeurons()) {
					n.setDelta(n.getActivationFunction().applyDeriv(n.getSumOfInputs())
							* (ts.getOutputs().get(i) - n.getOutput()));
					i++;
				}
				for (int j = net.numberOfLayers() - 2; j > 0; j--) {
					for (Neuron n : net.getLayer(j).getNeurons()) {
						float sumOfWeightedDeltas = 0;
						for (Connection c : n.getOutputs())
							sumOfWeightedDeltas += c.getReceiverNeuron().getDelta() * c.getWeight();
						n.setDelta(n.getActivationFunction().applyDeriv(n.getSumOfInputs()) * sumOfWeightedDeltas);
					}
				}
				
				for(Layer l : net.getLayers()) {
					for(Neuron n : l.getNeurons()) {
						for(Connection c : n.getOutputs())
							c.setWeight((float) (c.getWeight() + learningRate * n.getOutput() * c.getReceiverNeuron().getDelta()));
					}
				}
				
			}

		} catch (NoActivationFunctionException | NoSumOfInputsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
