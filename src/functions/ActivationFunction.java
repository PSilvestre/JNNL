package functions;


public enum ActivationFunction {
	SIGMOID {
		public float apply(float sumOfInputs) {
			return 1 / ((float) (1 + Math.pow(Math.E, -sumOfInputs)));
		}
		
		public float applyDeriv(float value) {
			return apply(value) * (1 - apply(value));
		}
		
	};

	public float apply(float sumOfInputs) {
		System.out.println("using default af");
		return 0;
	}
	
	public float applyDeriv(float value) {
		System.out.println("using default af");
		return 0;
	}
}
