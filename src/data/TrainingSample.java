package data;

import java.util.List;

/**
 * A training sample is just a set of inputs and corresponding set of outputs,
 * used to train a network.
 * 
 * @author Pedro Silvestre
 *
 */
public interface TrainingSample {
	/**
	 * @return the inputs of this training sample.
	 */
	List<Float> getInputs();

	/**
	 * @return the outputs of this training sample.
	 */
	List<Float> getOutputs();

	/**
	 * @return equivalent to this.getInputs().size().
	 */
	int getSizeOfInputs();

	/**
	 * @return equivalent to this.getOutputs().size().
	 */
	int getSizeOfOutputs();
}
