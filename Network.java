
public class Network{
	Layer layers[];
	public Network(int numNeuronsPerLayer[]){
		layers = new Layer[numNeuronsPerLayer.length];
		layers[0] = new Layer(numNeuronsPerLayer[0]);
		for(int i = 1; i < numNeuronsPerLayer.length; i++){
			layers[i] = new Layer(numNeuronsPerLayer[i], layers[i-1]);
		}
	}

	public float[] compute(float[] in){
		layers[0].feedLayer(in);
		for(int i = 1; i < layers.length; i++){
			layers[i].feedForward();
		}
	}
}
