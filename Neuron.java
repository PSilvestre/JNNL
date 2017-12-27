public interface Neuron{
	void feedInput(float in);
	void feedForward();
	void backProp();
	float getValue();
}
