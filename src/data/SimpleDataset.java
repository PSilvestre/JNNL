package data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exception.TrainingSampleSizeMismatchException;

public class SimpleDataset implements Dataset, Serializable{

	
	private static final long serialVersionUID = 100L;
	List<TrainingSample> samples;
	int numInputs;
	int numOutputs;
	
	public SimpleDataset(int numInputs, int numOutputs) {
		this.numInputs = numInputs;
		this.numOutputs = numOutputs;
		samples = new LinkedList<TrainingSample>();
	}
	
	public void addTrainingSample(TrainingSample ts) throws TrainingSampleSizeMismatchException{
		if(ts.getSizeOfInputs() != this.numInputs || ts.getSizeOfOutputs() != this.numOutputs)
			throw new TrainingSampleSizeMismatchException();
		samples.add(ts);
	}
	
	@Override
	public int getNumberInputs() {
		return numInputs;
	}

	@Override
	public int getNumberOutputs() {
		return numOutputs;
	}

	@Override
	public Iterator<TrainingSample> getIterator() {
		return samples.iterator();
	}

}
