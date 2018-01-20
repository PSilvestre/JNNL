import java.util.List;

import java.util.ArrayList;

public class Layer {
	private static final int NUM_THREADS = 1;

	private List<Neuron> layerNeurons;
	private boolean outputLayer;

	public Layer(int numNeurons, Layer previous, boolean biased, boolean outputLayer) {
		this.outputLayer = outputLayer;
		if (biased) {
			this.layerNeurons = new ArrayList<Neuron>(numNeurons + 1);
			for (int i = 0; i < numNeurons; i++) {
				layerNeurons.add((new ComputingNeuron(ActivationFunctions.SIGMOID, previous, i)));
			}
			layerNeurons.add(new BiasNeuron(1.0f));
		} else {
			this.layerNeurons = new ArrayList<Neuron>(numNeurons);
			for (int i = 0; i < numNeurons; i++) {
				layerNeurons.add((new ComputingNeuron(ActivationFunctions.SIGMOID, previous, i)));
			}
		}
	}

	public Layer(int numNeurons) {
		this.layerNeurons = new ArrayList<Neuron>(numNeurons + 1);
		
		for (int i = 1; i < numNeurons + 1; i++) {
			layerNeurons.add((new InputNeuron(ActivationFunctions.SIGMOID)));
		}
		layerNeurons.add(new BiasNeuron(1.0f));
	}

	public void feedForward() {
		int neuronsPerThread = layerNeurons.size() / NUM_THREADS;
		int remainder = layerNeurons.size() % NUM_THREADS;
		ComputeThread threads[] = new ComputeThread[layerNeurons.size() / neuronsPerThread];
		for (int i = 0; i < NUM_THREADS; i++) {
			ComputeThread t = new ComputeThread(i * neuronsPerThread, (i + 1) * neuronsPerThread, layerNeurons);
			threads[i] = t;
			t.start();
		}
		for (int i = 0; i < remainder; i++)
			layerNeurons.get(neuronsPerThread * NUM_THREADS + i).feedForward();
		try {
			for (int i = 0; i < NUM_THREADS; i++)

				threads[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void backProp(Layer next, float learningRate, float[] expectedOut) {
		int neuronsPerThread = layerNeurons.size() / NUM_THREADS;
		int remainder = layerNeurons.size() % NUM_THREADS;
		BackPropThread threads[] = new BackPropThread[layerNeurons.size() / neuronsPerThread];
		for (int i = 0; i < NUM_THREADS; i++) {
			BackPropThread t = new BackPropThread(i * neuronsPerThread, (i + 1) * neuronsPerThread, layerNeurons, learningRate, outputLayer,
					next, expectedOut);
			threads[i] = t;
			t.start();
		}

		if (outputLayer) {
			for (int i = neuronsPerThread * NUM_THREADS; i < neuronsPerThread * NUM_THREADS + remainder; i++)
				layerNeurons.get(i).backPropOutputLayer(expectedOut[i], next, learningRate);

		} else {
			for (int i = neuronsPerThread * NUM_THREADS; i < neuronsPerThread * NUM_THREADS + remainder; i++)
				layerNeurons.get(i).backProp(next, learningRate);
		}

		try {
			for (int i = 0; i < NUM_THREADS; i++)

				threads[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void feedLayer(float[] in) {
		if (in.length == layerNeurons.size()) {
			for (int i = 0; i < in.length; i++) {
				layerNeurons.get(i).feedInput(in[i]);
			}
		} else {
			System.out.println("Wrong feed size!");
		}
	}

	private class ComputeThread extends Thread {
		private List<Neuron> neurons;
		private int startI;
		private int endI;

		public ComputeThread(int startI, int endI, List<Neuron> neurons) {
			this.startI = startI;
			this.endI = endI;
			this.neurons = neurons;
		}

		public void run() {
			for (int i = startI; i < endI; i++) {
				neurons.get(i).feedForward();
			}
		}

	}

	private class BackPropThread extends Thread {
		private List<Neuron> neurons;
		private int startI;
		private int endI;

		private float learningRate;
		private boolean outputLayer;
		private Layer next;
		private float[] expectedOut;

		public BackPropThread(int startI, int endI, List<Neuron> neurons, float learningRate, boolean outputLayer,
				Layer next, float[] expectedOut) {
			this.startI = startI;
			this.endI = endI;
			this.neurons = neurons;
			this.learningRate = learningRate;
			this.outputLayer = outputLayer;
			this.next = next;
			this.expectedOut = expectedOut;
		}

		public void run() {
			if (outputLayer) {
				for (int i = startI; i < endI; i++) {
					neurons.get(i).backPropOutputLayer(expectedOut[i], next, learningRate);
				}
			} else {
				for (int i = startI; i < endI; i++) {
					neurons.get(i).backProp(next, learningRate);
				}
			}

		}
	}

	public float[] layerValue() {
		float[] values = new float[layerNeurons.size()];
		int i = 0;
		for (Neuron n : layerNeurons) {
			values[i++] = n.getValue();
		}
		return values;
	}

	public int size() {
		return layerNeurons.size();
	}

	public Neuron getNeuron(int i) {
		return layerNeurons.get(i);
	}

	public List<Neuron> getNeurons() {
		return layerNeurons;
	}

}
