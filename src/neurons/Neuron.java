package neurons;
import java.util.List;

import connection.Connection;
import exception.NoActivationFunctionException;
import exception.NoInputConnectionsException;
import exception.NoSumOfInputsException;
import exception.NoSummingFunctionException;
import functions.ActivationFunction;
import functions.SummingFunction;

public interface Neuron {
	
	ActivationFunction getActivationFunction() throws NoActivationFunctionException;
	SummingFunction getSummingFunction() throws NoSummingFunctionException;
	
	void calculateOutput();
	float getOutput();
	float getSumOfInputs() throws NoSumOfInputsException;
	
	void setInputConnections(List<Connection> inputs) throws NoInputConnectionsException;
	void setOutputConnections(List<Connection> outputs);
	
	void addInputConnection(Connection c)throws NoInputConnectionsException;
	void addOutputConnection(Connection c);
	
	List<Connection> getInputs() throws NoInputConnectionsException;
	
	List<Connection> getOutputs();
}
