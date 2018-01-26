package neurons;
import java.util.LinkedList;
import java.util.List;

import connection.Connection;
import exception.NoInputConnectionsException;
import functions.ActivationFunction;
import functions.SummingFunction;

public class ComputingNeuron implements Neuron{
	private ActivationFunction af;
	private SummingFunction sf;
	
	private float output;
	private float sumOfInputs;
	
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

}
