

public enum ActivationFunctions {
	SIGMOID {
		public float apply(float value) {
			return 1 / ((float) (1 + Math.pow(Math.E, -value)));
		}
		
		public float applyDeriv(float value) {
			return apply(value) * (1 - apply(value));
		}
		
	};
	
	public float apply(float value) {
		return 0;
	}
	
	public float applyDeriv(float value) {
		return 0;
	}
}
