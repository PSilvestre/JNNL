import java.util.List;
import java.util.ArrayList;

public class ComputingNeuron implements Neuron{
	private ActivationFunction af;
	private Layer previous;
	private List<Float> weightsIn;
	private float value;
	public ComputingNeuron(ActivationFunction f, Layer previous){
		this.af = f;
		this.previous = previous;
		this.weightsIn = new ArrayList<Integer>(previous.size());
		this.value = 0.0f;
	}

	private void calculateValue(){
		value = 0.0f;
		for(int i = 0; i < previous.size(); i++){
			value += previous.getNeuron(i).getValue() * weightsIn.get(i);
		}
	}
	private void applyActivationFunc(){
		value = af.apply(value);
	}

	public void feedForward(){
		calculateValue();	
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


}
