import java.util.List;
import java.util.ArrayList;

public class Layer{
	
	private Layer previous;
	private List<Neuron> layerNeurons;
	public Layer(int numNeurons, Layer previous){
		this.previous = previous;
		this.layerNeurons = new ArrayList<Neuron>(numNeurons+1);
		layerNeurons.put(new BiasNeuron(1.0f));
		for(int i = 1; i < numNeurons+1; i++){
			layerNeurons.put(new ComputingNeuron(ActivationFunction.sigmoid, layerNeurons.get(i-1)));
		}
	}

	public feedForward(){
		for(Neuron n : layerNeurons){
			n.feedForward();
		}
	}

	public backProp(){
		//not implemented
	}
}
