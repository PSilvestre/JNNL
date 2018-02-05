import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Data.Dataset;
import Data.DatasetLoaderSaver;
import Data.SimpleDataset;
import Data.SimpleTrainingSample;
import Data.TrainingSample;
import Learning.Backpropagation;
import connection.Connection;
import exception.DatasetSizeDifferentFromNetException;
import exception.GuessAnswerSizeMismatchException;
import exception.NoInputConnectionsException;
import exception.NotHiddenLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import exception.TrainingSampleSizeMismatchException;
import layer.Layer;
import network.MultilayerFeedForwardNetwork;
import network.Network;
import network.NetworkLoaderSaver;
import neurons.Neuron;

public class Main {

	/*
	 * args - 1 - file with inputs outputs
	 * 
	 */
	public static void main(String[] args) {
		Network n = null;
		if (args.length == 1) {
			n = NetworkLoaderSaver.loadNetwork(args[0]);
		}
		console(n);
	}


	private static void console(Network n) {
		String command = "";
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while (running) {
			printHelp();
			System.out.println();
			command = sc.next();
			switch (command.toUpperCase()) {
			case "M":
				n = makeNetwork(sc);
				break;
			case "P":
				handlePredict(n, sc);
				break;
			case "S":
				showNetStructure(n);
				break;
			case "L":
				n = handleLoad(sc);
				break;
			case "ST":
				handleSaveNet(n, sc);
				break;
			case "TDS":
				handleTrainFromDataset(n, sc);
				break;
			case "TDSR":
				handleTrainFromDatasetReadable(n, sc);
				break;
			case "MDS":
				handleMakeDataset(sc);
				break;
			case "Q":
				running = false;
				break;
			default:
				System.out.println("Unrecognized command, ignoring.");
				break;

			}
			
		}
		sc.close();
	}

	private static void handleMakeDataset(Scanner sc) {
		System.out.println("Number of inputs: ");
		int numInputs = sc.nextInt();
		System.out.println("Number of outputs: ");
		int numOutputs = sc.nextInt();
		System.out.println("How many samples will be entered: ");
		int numSamples = sc.nextInt();
		Dataset ds = new SimpleDataset(numInputs, numOutputs);
		List<Float> inputs;
		List<Float> outputs;
		for(int i = 0; i < numSamples; i++) {
			System.out.println("--------Sample number " + i + ":");
			inputs = new LinkedList<Float>();
			outputs = new LinkedList<Float>();

			for(int in = 0; in < numInputs; in++) {
				System.out.println("Input number " + in + ":");
				inputs.add(sc.nextFloat());
			}
			System.out.println("Outputs: ");
			for(int out = 0; out < numOutputs; out++) {
				System.out.println("Output number " + out + ":");
				outputs.add(sc.nextFloat());
			}
			try {
				ds.addTrainingSample(new SimpleTrainingSample(inputs, outputs));
			} catch (TrainingSampleSizeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Filename: ");
		String file = sc.next();
		DatasetLoaderSaver.saveDataset(file, ds);
		
		
	}

	private static void handleTrainFromDataset(Network n, Scanner sc) {
		System.out.println("What file should the network be trained with?");
		String file = sc.next();
		Dataset ds = DatasetLoaderSaver.loadDataset(file);
		try {
			for(int i = 0; i < 100; i++)
				n.getLearningMethod().apply(ds, n);
		} catch (DatasetSizeDifferentFromNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void handleTrainFromDatasetReadable(Network n,Scanner sc) {
		System.out.println("What file should the network be trained with?");
		String file = sc.next();
		Dataset ds = DatasetLoaderSaver.loadDatasetReadableFile(file);
		try {
			for(int i = 0; i < 100; i++)
				n.getLearningMethod().apply(ds, n);
		} catch (DatasetSizeDifferentFromNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void handleSaveNet(Network n, Scanner sc) {
		System.out.println("Name of the file to save to: ");
		String file = sc.next();
		if(NetworkLoaderSaver.saveNetwork(file, n))
			System.out.println("Network successfully saved.");
		else
			System.out.println("Network load failed.");
	}

	private static Network handleLoad(Scanner sc) {
		System.out.println("What is the name of the file?");
		String file = sc.next();
		return NetworkLoaderSaver.loadNetwork(file);
		
	}

	private static Network makeNetwork(Scanner sc) {
		Network n = null;
		System.out.println("How many hidden layers?");
		int numLayers = sc.nextInt() + 2;
		System.out.println("How many neurons in input layer?");
		int[] numNeuronsPerLayer = new int[numLayers];
		numNeuronsPerLayer[0] = sc.nextInt();
		for(int i = 1; i < numLayers-1; i++) {
			System.out.println("How many neurons in hidden layer " + i );
			numNeuronsPerLayer[i] = sc.nextInt();
		}
		System.out.println("How many neurons in output layer?");
		numNeuronsPerLayer[numNeuronsPerLayer.length-1] = sc.nextInt();
		try {
			n = new MultilayerFeedForwardNetwork(numNeuronsPerLayer, new Backpropagation(0.2f));
			System.out.println("Network created successfully.");
		} catch (NoInputConnectionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Network creation failed.");
		}
		return n;
		
	}

	private static void showNetStructure(Network n) {
		for(int i = 0; i < n.numberOfLayers(); i++) {
			System.out.println("Layer "+ i + ":");
			System.out.println("    " + n.getSizeOfLayer(i) + " neurons.");
			Layer l = n.getLayer(i);
			int neuronNum = 0;
			for(Neuron neu : l.getNeurons()) {
				System.out.println("    Neuron " + neuronNum++ +" :");
				for(Connection c : neu.getOutputs())
					System.out.println("        Connection weight: " + c.getWeight());
			}
		}
	}

	private static void handlePredict(Network n, Scanner sc) {
		int numInputs = n.getSizeOfLayer(0);
		List<Float> inputs = new LinkedList<Float>();
		System.out.println("Enter inputs - " + numInputs + " floats: ");
		for (int i = 0; i < numInputs; i++) {
			inputs.add(sc.nextFloat());
		}
		List<Float> outs = n.compute(inputs);
		System.out.println("Outputs: ");
		for (Float f : outs) {
			System.out.println(f);
		}

	}

	private static void printHelp() {
		System.out.println("M - Make network");
		System.out.println("P - Predict on inputs");
		System.out.println("S - Show Network Architecture");
		System.out.println("L - Load network");
		System.out.println("ST - Store network");
		System.out.println("MDS - Make Dataset");
		System.out.println("TDS - Train on Dataset");
		System.out.println("TDSR - Train on Dataset Readable");
		System.out.println("Q - Quit");
	}
	
	
}
