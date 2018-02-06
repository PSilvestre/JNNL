package image;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class pgmImage {
	String type;
	int width;
	int height;
	int maxVal;
	List<Float> data;

	public pgmImage(String type, int width, int height, int maxVal, List<Float> data) {
		this.type = type;
		this.width = width;
		this.height = height;
		this.maxVal = maxVal;
		this.data = data;
	}

	public pgmImage(String fileName) {
		data = new LinkedList<Float>();
		BufferedInputStream stream = null;
		try {
			stream = new BufferedInputStream(new FileInputStream(fileName));
			if (!next(stream).equals("P5"))
				throw new IOException("File " + fileName + " is not a binary PGM image.");
			this.type = "P5";
			this.width = Integer.parseInt(next(stream));
			this.height = Integer.parseInt(next(stream));
			this.maxVal = Integer.parseInt(next(stream));
			if (maxVal < 0 || maxVal > 255)
				throw new IOException("The image's maximum gray value must be in range [0, " + maxVal + "].");
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					int p = stream.read();
					if (p == -1)
						throw new IOException("Reached end-of-file prematurely.");
					else if (p < 0 || p > maxVal)
						throw new IOException("Pixel value " + p + " outside of range [0, " + maxVal + "].");
					data.add((float) ((((float) p) / (255.0 / 2.0) - 1)));

				}
			}
			stream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Finds the next whitespace-delimited string in a stream, ignoring any
	 * comments.
	 * 
	 * @param stream
	 *            the stream read from
	 * @return the next whitespace-delimited string
	 * @throws IOException
	 */
	private static String next(final InputStream stream) throws IOException {
		final List<Byte> bytes = new ArrayList<Byte>();
		while (true) {
			final int b = stream.read();

			if (b != -1) {

				final char c = (char) b;
				if (c == '#') {
					int d;
					do {
						d = stream.read();
					} while (d != -1 && d != '\n' && d != '\r');
				} else if (!Character.isWhitespace(c)) {
					bytes.add((byte) b);
				} else if (bytes.size() > 0) {
					break;
				}

			} else {
				break;
			}

		}
		final byte[] bytesArray = new byte[bytes.size()];
		for (int i = 0; i < bytesArray.length; ++i)
			bytesArray[i] = bytes.get(i);
		return new String(bytesArray);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	public List<Float> getData() {
		return data;
	}

	public void setData(List<Float> data) {
		this.data = data;
	}
}
