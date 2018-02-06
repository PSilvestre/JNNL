package data;

import java.util.Iterator;

import exception.TrainingSampleSizeMismatchException;

public interface Dataset {
	int getNumberInputs();
	
	int getNumberOutputs();
	
	void addTrainingSample(TrainingSample ts) throws TrainingSampleSizeMismatchException;
	
	Iterator<TrainingSample> getIterator();
}
