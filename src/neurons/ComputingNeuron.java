package neurons;
import java.util.LinkedList;
import java.util.List;

import connection.Connection;
import exception.NoInputConnectionsException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import functions.ActivationFunction;
import functions.SummingFunction;

public class ComputingNeuron implements Neuron{
	private ActivationFunction af;
	private SummingFunction sf;
	
	private float output;
	private float sumOfInputs;
	private float delta;
	private List<Connection> inputs;
	private List<Connection> outputs;
	
	public ComputingNeuron(ActivationFunction af, SummingFunction sf) {
		this.af = af;
		this.sf = sf;
		inputs = new LinkedList<Connection>();
		outputs = new LinkedList<Connection>();
		output = 0;
		sumOfInputs = 0;
	}
	
	public ComputingNeuron(ActivationFunction af, SummingFunction sf, List<Connection> inputs) {
		this(af, sf);
		this.inputs = inputs;
	}
	
	
	@Override
	public ActivationFunction getActivationFunction() {
		return af;
	}

	@Override
	public SummingFunction getSummingFunction() {
		return sf;
	}

	@Override
	public void calculateOutput() {
		sumOfInputs = sf.apply(inputs);
		output = af.apply(sumOfInputs);
	}

	@Override
	public float getOutput() {
		return output;
	}

	@Override
	public float getSumOfInputs() {
		return sumOfInputs;
	}

	@Override
	public void setInputConnections(List<Connection> inputs) {
		this.inputs = inputs;
	}

	@Override
	public void setOutputConnections(List<Connection> outputs) {
		this.outputs = outputs;
	}
	
	public List<Connection> getInputs(){
		return inputs;
	}
	
	public List<Connection> getOutputs(){
		return outputs;
	}

	@Override
	public void addInputConnection(Connection c) throws NoInputConnectionsException {
		inputs.add(c);
	}

	@Override
	public void addOutputConnection(Connection c) {
		outputs.add(c);
	}

	@Override
	public void backProp(float guess, float answer) throws NotOutputNeuronException {
		this.setDelta(af.applyDeriv(this.sumOfInputs)* guess - this.output);
		for(Connection c : inputs)
			c.setWeight((float) (c.getWeight() + 0.2 * this.output * delta));
	}

	@Override
	public void backProp() throws OutputNeuronException {
		float sumOfWeightedDeltas = 0;
		for(Connection c : outputs)
			sumOfWeightedDeltas += c.getReceiverNeuron().getDelta()*c.getWeight();
		this.setDelta(af.applyDeriv(sumOfInputs)*sumOfWeightedDeltas);
		for(Connection c : inputs)
			c.setWeight((float) (c.getWeight() + 0.2 * this.output * delta));
	}

	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}



}
