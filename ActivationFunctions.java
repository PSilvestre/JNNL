public class ActivationFunctions{
	public enum ActivationFunction{
		SIGMOID(SigAF);

		private AF af;
		ActivationFunction(AF af){
			this.af = af;
		}

		public apply(float value){af.apply(value);}
		public applyDeriv(float value){af.applyDeriv(value);}
	}

	private interface AF{
		float apply(float value);
		float applyDeriv(float value);
	}

	private static class SigAF implements AF{
		public static float apply(float value){
			return 1/(1+Math.pow(Math.E, -value);
		}

		public static float applyDeriv(float value){
			return apply(value)*(1- apply(value));
		}
	}
}
