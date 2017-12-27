import java.util.List;
import java.util.ArrayList;

public class Layer{
	
	private Layer previous;
	private List<Neuron> layerNeurons;
	public Layer(int numNeurons, Layer previous){
		this.previous = previous;
		Layer(numNeurons);
	}

	public Layer(int numNeurons){
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

	public void backProp(){
		//not implemented
	}

	public void feedLayer(float[] in){
		if(in.length == layerNeurons.length){
			for(int i = 0; i < in.length; i++){
				layerNeurons.get(i).feedInput(in[i]);
			}
		}else{
			System.out.println("Wrong feed size!");
		}	
	}

	private class computeThread extends Thread{
		private List<Neuron> neurons;
		private int startI;
		private int endI;
		
		public computeThread(int startI, int endI, List<Neuron> neurons)		{
			this.startI = startI;
			this.endI = endI;
			this.neurons = neurons;
		}
		public void run(){		
			for(int i = startI; i < endI; i++){
				neurons.get(i).feedForward();
			}
		}

	}
}
