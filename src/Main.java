import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import connection.Connection;
import exception.NoInputConnectionsException;
import exception.NotHiddenLayerException;
import exception.NotOutputNeuronException;
import exception.OutputNeuronException;
import layer.Layer;
import network.Network;
import neurons.Neuron;

public class Main {

	/*
	 * args - 1 - file with inputs outputs
	 * 
	 */
	public static void main(String[] args) {
		Network n = null;
		if (args.length == 1) {
			try {
				Scanner s = new Scanner(new File(args[0]));
				int numLayers = s.nextInt();
				List<Integer> numNeurons = new LinkedList<Integer>();
				for (int i = 0; i < numLayers; i++) {
					numNeurons.add(s.nextInt());
				}
				int[] neuronsPerLayer = new int[numNeurons.size()];

				for (int i = 0; i < numNeurons.size(); i++) {
					neuronsPerLayer[i] = numNeurons.get(i);
				}
				s.nextLine();
				s.nextLine();
				try {
					n = new Network(neuronsPerLayer);
				} catch (NoInputConnectionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//trainNet(numNeurons, s);
				console(n);
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		}
	}

	private static void trainNet(Network n, Scanner sc) {
		int numInputs = n.getSizeOfLayer(0);
		List<Float> inputs = new LinkedList<Float>();
		System.out.println("Enter inputs - " + numInputs + " floats: ");
		for (int i = 0; i < numInputs; i++) {
			inputs.add(sc.nextFloat());
		}
		List<Float> outs = n.compute(inputs);
		List<Float> answers = new LinkedList<Float>();
		int numOutputs = n.getSizeOfLayer(n.numberOfLayers()-1);
		System.out.println("Enter correct outputs - " + numOutputs + " floats: ");
		for (int i = 0; i < numOutputs ; i++) {
			answers.add(sc.nextFloat());
		}
		try {
			n.BackProp(outs, answers);
			System.out.println("Training successful.");
		} catch (NotOutputNeuronException | OutputNeuronException | NotHiddenLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
			case "T":
				trainNet(n,sc);
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
			n = new Network(numNeuronsPerLayer);
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
		System.out.println("T - Train net");
		System.out.println("Q - Quit");
	}
}
