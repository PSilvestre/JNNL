import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * args - 1 - file with inputs outputs
	 * 
	 */
	public static void main(String[] args) {
		Network n;
		if(args.length == 1) {
			try {
				Scanner s = new Scanner(new File(args[0]));
				int numLayers = s.nextInt();
				List<Integer> numNeurons = new LinkedList<Integer>();
				for(int i = 0; i < numLayers;i++) {
					numNeurons.add(s.nextInt());
				}
				int[] neuronsPerLayer = new int[numNeurons.size()];
				
				for(int i = 0; i < numNeurons.size(); i++) {
					neuronsPerLayer[i] = numNeurons.get(i);
				}
				s.nextLine();
				s.nextLine();
				n = new Network(neuronsPerLayer);
				float[] inputs = new float[numNeurons.get(0)];
				//System.out.println(numNeurons.get(0));
				//System.out.println(numNeurons.get(numNeurons.size()-1));
				float[] outputs = new float[numNeurons.get(numNeurons.size()-1)];
				while(s.hasNextLine()) {
					String r = s.nextLine();
					StringTokenizer st = new StringTokenizer(r);
					int i = 0;
					while(st.hasMoreTokens()) {
						String token = st.nextToken();
						inputs[i++] = (Float.parseFloat(token));
					}
					r = s.nextLine();
					st = new StringTokenizer(r);
					i = 0;
					while(st.hasMoreTokens()) {
						String token = st.nextToken();
						outputs[i++] =(Float.parseFloat(token));
						
					}
					s.nextLine();
					System.out.println("hi");
					n.trainNet(inputs, outputs, 0.2f);
					s.close();
					console(n);
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
			
		}
	}

	private static void console(Network n) {
		String command = "";
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while(running) {
			printHelp();
			command = sc.nextLine();
			switch(command.toUpperCase()) {
			case "P":
				handlePredict(n, sc);
				break;
			case "Q":
				running = false;
				break;
			default:
				System.out.println("Unrecognized command, ignoring.");
				break;
				
			}
			sc.close();
		}
	}

	private static void handlePredict(Network n, Scanner sc) {
		int numInputs = n.layers[0].size();
		float[] inputs = new float[numInputs];
		System.out.println("Enter inputs - " + numInputs + " floats: ");
		for(int i = 0; i < numInputs; i++) {
			inputs[i] = sc.nextFloat();
		}
		float[] outs = n.compute(inputs);
		System.out.println("Outputs: ");
		for(int i = 0 ; i < outs.length; i++)
			System.out.println(outs[i]);
		
	}

	private static void printHelp() {
		System.out.println("P - Predict on inputs");
		System.out.println("Q - Quit");
	}
}
