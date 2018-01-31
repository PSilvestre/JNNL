package functions;
import java.util.List;

import connection.Connection;

public enum SummingFunction {
	WEIGHTED {
		public float apply(List<Connection> inputConnections) {
			float sum = 0;
			for(Connection c : inputConnections)
				sum += c.getWeightedInput();
			return sum;
		}
		
	};

	public float apply(List<Connection> inputConnections) {
		System.out.println("NEVER CALL THIS");
		return 0.0f;
	}
}
