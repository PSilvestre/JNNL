package data;

import java.util.Iterator;

import exception.TrainingSampleSizeMismatchException;

/**
 * Represents a dataset, which is really just a  collection of training samples.
 * @author Pedro Silvestre
 *
 */
public interface Dataset {
	
	/**
	 * Returns the number of inputs in each training sample, as these have to be the same.
	 * @return an integer of the number of inputs.
	 */
	int getNumberInputs();
	
	/**
	 * Returns the number of ouputs in each training sample, as these have to be the same.
	 * @return an integer of the number of outputs.
	 */
	int getNumberOutputs();
	
	/**
	 * Adds a training sample to the dataset.
	 * @param ts the sample to be added.
	 * @throws TrainingSampleSizeMismatchException if the number of inputs/outputs of ts mismatches the inputs/outputs of <b>this</b>.
	 */
	void addTrainingSample(TrainingSample ts) throws TrainingSampleSizeMismatchException;
	
	/**
	 * Returns an iterator over the training samples.
	 * @return an iterator of training samples.
	 */
	Iterator<TrainingSample> getIterator();
}
