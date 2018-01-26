package exception;

public class WrongSizeInInputLayerException extends Exception {
	public WrongSizeInInputLayerException() {
		super("Wrong size feed at input layer");
	}
	
	public WrongSizeInInputLayerException(int layerSize, int feedSize) {
		super("Wrong size feed at input layer. LayerSize: " + layerSize + " feedSize: " + feedSize);
	}
}
