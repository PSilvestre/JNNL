package Data;

import java.util.List;

public interface TrainingSample {
	List<Float> getInputs();
	List<Float> getOutputs();
	
	int getSizeOfInputs();
	int getSizeOfOutputs();
}
