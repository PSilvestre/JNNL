package neurons;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import connection.Connection;
import exception.NoActivationFunctionException;
import exception.NoInputConnectionsException;
import exception.NoSumOfInputsException;
import exception.NoSummingFunctionException;
import exception.NotOutputNeuronException;
import functions.ActivationFunction;
import functions.SummingFunction;

public class BiasNeuron implements Neuron, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 21L;
	private float value;
	List<Connection> outputs;
	
	public BiasNeuron(float value) {
		this.value = value;
		outputs = new LinkedList<Connection>();
	}
	
	public BiasNeuron() {
		this.value = 1;//(float) (Math.random()*2 -1);
		outputs = new LinkedList<Connection>();
	}
	
	@Override
	public ActivationFunction getActivationFunction() throws NoActivationFunctionException {
		throw new NoActivationFunctionException();
	}

	@Override
	public SummingFunction getSummingFunction() throws NoSummingFunctionException {
		throw new NoSummingFunctionException();
	}

	@Override
	public void calculateOutput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getOutput() {
		return value;
	}

	@Override
	public float getSumOfInputs() throws NoSumOfInputsException {
		throw new NoSumOfInputsException();
	}

	@Override
	public void setInputConnections(List<Connection> inputs) throws NoInputConnectionsException {
		throw new NoInputConnectionsException();
	}

	@Override
	public void setOutputConnections(List<Connection> outputs) {
		this.outputs = outputs;
	}

	@Override
	public void addInputConnection(Connection c) throws NoInputConnectionsException {
		throw new NoInputConnectionsException();
	}

	@Override
	public void addOutputConnection(Connection c) {
		outputs.add(c);
	}

	@Override
	public List<Connection> getInputs() throws NoInputConnectionsException {
		throw new NoInputConnectionsException();
	}

	@Override
	public List<Connection> getOutputs() {
		return outputs;
	}

	@Override
	public float getDelta() {
		
		System.out.println("THIS SHOULDNT HAVE BEEN CALLED");
		return 0;
	}

	@Override
	public void setDelta(float delta) {
		//do nothing
	}

}
