package functions;

/**
 * An enumerator for all activation functions. These are the functions a neuron applies after the summing function.
 * @author Pedro Silvestre
 *
 */
public enum ActivationFunction {
	SIGMOID {
		public float apply(float sumOfInputs) {
			return 1.0f / ((float) (1.0f + Math.pow(Math.E, -sumOfInputs)));
		}

		public float applyDeriv(float value) {
			return apply(value) * (1 - apply(value));
		}

	},
	TANH {
		public float apply(float sumOfInputs) {
			return (float) Math.tanh(sumOfInputs);
		}

		public float applyDeriv(float value) {
			return (float) (1 - Math.tanh(value)* Math.tanh(value));
		}
	},
	RELU {
		public float apply(float sumOfInputs) {
			return Math.max(0, sumOfInputs);
		}

		public float applyDeriv(float value) {
			if(value <= 0) return 0;
			return 1;
			
		}
	};
	
	/**
	 * Computes the result of this Activation function.
	 * @param sumOfInputs the result of applying the summing function of a neuron.
	 * @return the result.
	 */
	public float apply(float sumOfInputs) {
		System.out.println("using default af");
		return 0;
	}

	/**
	 * Computes the result of the derivative of this Activation function.
	 * @param value the value on which to compute the derivative.
	 * @return the result.
	 */
	public float applyDeriv(float value) {
		System.out.println("using default af");
		return 0;
	}
}
