package learning;

import data.Dataset;
import exception.DatasetSizeDifferentFromNetException;
import network.Network;

public interface LearningMethod {
	/**
	 * Applies this learning method to the <class>Network</class> on the <class>Dataset</class> examples.
	 * @param data the dataset to train the net with.
	 * @param net the network to be trained.
	 * @throws DatasetSizeDifferentFromNetException Dataset input/output size is different from Networks input/output.
	 */
	void apply(Dataset data, Network net) throws DatasetSizeDifferentFromNetException;
}
