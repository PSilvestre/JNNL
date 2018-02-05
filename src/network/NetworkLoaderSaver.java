package network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class NetworkLoaderSaver {

	/**
	 * Loads a Network from a file.
	 * @param file the file to read a Network from.
	 * @return The network to be read. Or null if not found.
	 */
	public static Network loadNetwork(String file) {
		
		Network n = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			n = (Network) ois.readObject();
			ois.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
		
	}
	
	/**
	 * Writes a Network object to a file.
	 * @param file to store Network object in.
	 * @param n Network to be stored.
	 * @return true if successfully stored, false otherwise.
	 */
	public static boolean saveNetwork(String file, Network n) {
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(n);
			oos.close();
			fos.close();
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
