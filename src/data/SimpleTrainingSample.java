package data;

import java.io.Serializable;
import java.util.List;

public class SimpleTrainingSample implements TrainingSample, Serializable{

	private static final long serialVersionUID = 101L;
	List<Float> inputs;
	List<Float> outputs;
	
	public SimpleTrainingSample (List<Float> inputs, List<Float> outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}
	
	@Override
	public List<Float> getInputs() {
		return inputs;
	}

	@Override
	public List<Float> getOutputs() {
		return outputs;
	}

	@Override
	public int getSizeOfInputs() {
		return inputs.size();
	}

	@Override
	public int getSizeOfOutputs() {
		return outputs.size();
	}

}
