package neurons;
import java.util.List;

import connection.Connection;
import exception.NoActivationFunctionException;
import exception.NoInputConnectionsException;
import exception.NoSumOfInputsException;
import exception.NoSummingFunctionException;
import exception.NotHiddenLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import functions.ActivationFunction;
import functions.SummingFunction;

public interface Neuron {
	
	/**
	 * Getter for the activation function used by this neuron.
	 * @return the <class>ActivationFunction</class> that this neuron uses.
	 * @throws NoActivationFunctionException if neuron has no Activation function.
	 */
	ActivationFunction getActivationFunction() throws NoActivationFunctionException;
	
	/**
	 * Getter for the summing function used by this neuron.
	 * @return the <class>SummingFunction</class> that this neuron uses.
	 * @throws NoSummingFunctionException if neuron has no summing function.
	 */
	SummingFunction getSummingFunction() throws NoSummingFunctionException;
	
	/**
	 * Applies the summing function and then the activation function.
	 */
	void calculateOutput();
	
	/**
	 * Getter for the value this neuron has calculated.
	 * @return the output of the neuron.
	 */
	float getOutput();
	
	/**
	 * Getter for the sum of the inputs of the last computation of this neuron.
	 * @return the sum of the inputs of the last computation of this neuron.
	 * @throws NoSumOfInputsException if Bias or Input neuron.
	 */
	float getSumOfInputs() throws NoSumOfInputsException;
	
	/**
	 * Setter for the list of connections where this neuron receives.
	 * @param inputs the list of connections where this neuron should be a receiver.
	 * @throws NoInputConnectionsException if Bias or Input neuron.
	 */
	void setInputConnections(List<Connection> inputs) throws NoInputConnectionsException;
	
	/**
	 * Setter for the list of connections where this neuron is a source.
	 * @param outputs the list of connections where this neuron should be a source.
	 */
	void setOutputConnections(List<Connection> outputs);
	
	/**
	 * Adds a single input connection to a neuron.
	 * @param c the connection to be added.
	 * @throws NoInputConnectionsException if Bias or Input neuron.
	 */
	void addInputConnection(Connection c)throws NoInputConnectionsException;
	
	/**
	 * Adds a single output connection to a neuron.
	 * @param c the connection to be added.
	 */
	void addOutputConnection(Connection c);
	
	/**
	 * Getter for the list of input connections this neuron has. That is connections where this neuron is receiver.
	 * @return the list of input connections this neuron has.
	 * @throws NoInputConnectionsException if Bias or Input neuron.
	 */
	List<Connection> getInputs() throws NoInputConnectionsException;
	
	/**
	 * Getter for the list of output connections this neuron has. That is connections where this neuron is source.
	 * @return the list of output connections this neuron has.
	 */
	List<Connection> getOutputs();
	
	/**
	 * Getter for the delta of a neuron, that is the error, can be calculated in many ways.
	 * @return the delta of the neuron.
	 */
	float getDelta();
	
	/**
	 * Setter for the delta of a neuron, that is the error, can be calculated in many ways.
	 * @param delta the new delta to be set.
	 */
	void setDelta(float delta);
}
