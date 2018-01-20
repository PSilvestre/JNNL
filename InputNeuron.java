import java.util.List;

public class InputNeuron implements Neuron{
	private ActivationFunctions af;
	private float value;
	private float sumOfInputs;
	private float delta;
	private int indexOfThisNeuronInLayer;
	public InputNeuron(ActivationFunctions f){
		this.af = f;
		this.value = 0.0f;
		
	}
	
	

	private void calculateValue(){
		
	}
	private void applyActivationFunc(){
		value = af.apply(value);
	}

	public void feedForward(){
		applyActivationFunc();
	}

	public void backProp(){
		//not implemented
	}


	public void feedInput(float in){
		value=in;
	}

	public float getValue(){
		return value;
	}



	@Override
	public ActivationFunctions getActFunc() {
		return af;
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
		for(Neuron n : next.getNeurons()) {
			if(n instanceof ComputingNeuron) {
				n.getWeights().set(indexOfThisNeuronInLayer, n.getWeights().get(indexOfThisNeuronInLayer) + learningRate*this.value*n.getDelta());
			}
		}
	}


}