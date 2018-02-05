package Learning;

import Data.Dataset;
import exception.DatasetSizeDifferentFromNetException;
import network.Network;

public interface LearningMethod {
	void apply(Dataset data, Network net) throws DatasetSizeDifferentFromNetException;
}
