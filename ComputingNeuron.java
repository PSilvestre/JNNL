import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputingNeuron implements Neuron{
	private ActivationFunctions af;
	private Layer previous;
	private List<Float> weightsIn;
	private float value;
	private float sumOfInputs;
	private float delta;
	private int indexOfThisNeuronInLayer;
	public ComputingNeuron(ActivationFunctions f, Layer previous, int indexOfThisNeuronInLayer){
		this.af = f;
		this.previous = previous;
		this.weightsIn = new ArrayList<Float>(0);
		if(previous != null) {
			this.weightsIn = new ArrayList<Float>(previous.size());
			Random r = new Random(System.currentTimeMillis());
			for(int i = 0; i < weightsIn.size(); i++) {
				weightsIn.add(i, r.nextFloat());
			}
		}
			
		this.value = 0.0f;
		this.delta = 0.0f;
		this.indexOfThisNeuronInLayer = indexOfThisNeuronInLayer;
	}
	
	private void calculateValue(){
		sumOfInputs = 0.0f;
		for(int i = 0; i < previous.size(); i++){
			sumOfInputs += previous.getNeuron(i).getValue() * weightsIn.get(i);
		}
	}
	private void applyActivationFunc(){
		value = af.apply(sumOfInputs);
	}

	public void feedForward(){
		calculateValue();	
		applyActivationFunc();
	}

	public void backPropOutputLayer(float expectedOut, Layer next, float learningRate){
			delta = (expectedOut - value) * af.applyDeriv(sumOfInputs);
	}


	public void feedInput(float in){
		value=in;
	}

	public float getValue(){
		return value;
	}
	
	public float getSumOfInputs() {
		return sumOfInputs;
	}

	@Override
	public ActivationFunctions getActFunc() {
		return af;
	}

	@Override
	public float getDelta() {
		return delta;
	}

	@Override
	public List<Float> getWeights() {
		return weightsIn;
	}

	@Override
	public void backProp(Layer next, float learningRate) {
		delta = 0.0f;
		for(Neuron n : next.getNeurons()) {
			if(n instanceof ComputingNeuron) {
				delta += n.getDelta() * n.getWeights().get(indexOfThisNeuronInLayer);
				
			}
		}
		delta *= this.af.applyDeriv(sumOfInputs);
		
		for(Neuron n : next.getNeurons()) {
			if(n instanceof ComputingNeuron) {
				n.getWeights().set(indexOfThisNeuronInLayer, n.getWeights().get(indexOfThisNeuronInLayer) + learningRate*this.value*n.getDelta());
			}
		}
	}


}
