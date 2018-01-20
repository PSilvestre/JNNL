import java.util.List;

public interface Neuron{
	void feedInput(float in);
	void feedForward();
	void backPropOutputLayer(float expectedOut, Layer next,float learningRate);
	float getValue();
	ActivationFunctions getActFunc();
	float getSumOfInputs();
	float getDelta();
	List<Float> getWeights();
	void backProp( Layer next, float learningRate);
}
