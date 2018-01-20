
public class Network{
	Layer layers[];
	public Network(int numNeuronsPerLayer[]){
		layers = new Layer[numNeuronsPerLayer.length];
		layers[0] = new Layer(numNeuronsPerLayer[0]);
		for(int i = 1; i < numNeuronsPerLayer.length-1; i++){
			layers[i] = new Layer(numNeuronsPerLayer[i], layers[i-1],true,false);
		}
		layers[layers.length-1] = new Layer(numNeuronsPerLayer[numNeuronsPerLayer.length-1], layers[layers.length-1],false,true);
	}

	public float[] compute(float[] in){
		layers[0].feedLayer(in);
		for(int i = 1; i < layers.length; i++){
			layers[i].feedForward();
		}
		return layers[layers.length].layerValue();
	}
	
	public void trainNet(float[] input, float[] expectedOut, float learningRate) {
		compute(input);
		layers[layers.length-1].backProp(null, learningRate, expectedOut);
		for(int i = layers.length-2; i > 0; i--){
			layers[i].backProp(layers[i+1], learningRate, expectedOut);
		}
		
	}
}
