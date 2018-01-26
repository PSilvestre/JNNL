package connection;
import neurons.Neuron;

public interface Connection {
	Neuron getSourceNeuron();
	Neuron getReceiverNeuron();
	float getWeight();
	void setWeight(float weight);
	float getWeightedInput();
}
