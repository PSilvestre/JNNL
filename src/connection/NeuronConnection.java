package connection;
import java.io.Serializable;

import neurons.Neuron;

public class NeuronConnection implements Connection, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 40L;
	Neuron source;
	Neuron receiver;
	float weight;

	public NeuronConnection(Neuron source, Neuron receiver, float weight) {
		this.receiver = receiver;
		this.source = source;
		this.weight = weight;
	}
	public NeuronConnection(Neuron source, Neuron receiver) {
		this.receiver = receiver;
		this.source = source;
		this.weight = (float) (Math.random()*2 - 1);
	}
	
	@Override
	public Neuron getSourceNeuron() {
		return source;
	}

	@Override
	public Neuron getReceiverNeuron() {
		return receiver;
	}

	@Override
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public float getWeightedInput() {
		return source.getOutput() * this.weight;
	}

}
