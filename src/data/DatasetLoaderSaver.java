package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import exception.TrainingSampleSizeMismatchException;

public class DatasetLoaderSaver {
	public static Dataset loadDataset(String file) {
		Dataset d = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			d = (Dataset) ois.readObject();
			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	public static Dataset loadDatasetReadableFile(String file) {
		Dataset d = null;

		SimpleTrainingSample ts = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
		
		Scanner sc = new Scanner(fis);
		int numInputs = sc.nextInt();
		int numOutputs = sc.nextInt();
		d = new SimpleDataset(numInputs, numOutputs);
		List<Float> inputs;
		List<Float> outputs;
		
			while (sc.hasNext()) {
				inputs = new LinkedList<Float>();
				outputs = new LinkedList<Float>();
				for (int in = 0; in < numInputs; in++) {
					inputs.add(sc.nextFloat());
				}
				for (int out = 0; out < numOutputs; out++) {
					outputs.add(sc.nextFloat());
				}
				ts = new SimpleTrainingSample(inputs, outputs);

				d.addTrainingSample(ts);

			}
		} catch (TrainingSampleSizeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return d;
	}

	public static boolean saveDataset(String file, Dataset d) {

		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(d);
			oos.close();
			fos.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
