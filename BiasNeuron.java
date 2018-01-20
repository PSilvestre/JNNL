import java.util.List;

public class BiasNeuron implements Neuron {
	float value;
	public BiasNeuron(float value) {
		this.value = value;
	}
	@Override
	public void feedInput(float in) {
		// TODO Auto-generated method stub

	}

	@Override
	public void feedForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public float getValue() {
		return value;
	}
	@Override
	public ActivationFunctions getActFunc() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public float getSumOfInputs() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getDelta() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Float> getWeights() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void backPropOutputLayer(float expectedOut, Layer next, float learningRate) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void backProp(Layer next, float learningRate) {
		// TODO Auto-generated method stub
		
	}

}
