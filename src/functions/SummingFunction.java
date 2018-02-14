package functions;
import java.util.List;

import connection.Connection;

/**
 * An enumerator for all summing functions. These are the functions a neuron applies to collect from its input connections.
 * @author Pedro Silvestre
 *
 */
public enum SummingFunction {
	/**
	 * Weighted summing function, adds the output of a neuron times the connection weight.
	 */
	WEIGHTED {
		public float apply(List<Connection> inputConnections) {
			float sum = 0;
			for(Connection c : inputConnections)
				sum += c.getWeightedInput();
			return sum;
		}
		
	};

	/**
	 * Compute the result of the summing function on a set of input connections. All summing functions must override the apply method.
	 * @param inputConnections the List of Connections to which we will apply the summing function.
	 * @return the result of the summing.
	 */
	public float apply(List<Connection> inputConnections) {
		System.out.println("NEVER CALL THIS");
		return 0.0f;
	}
}
