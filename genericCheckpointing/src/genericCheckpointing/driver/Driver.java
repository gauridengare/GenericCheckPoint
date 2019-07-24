package genericCheckpointing.driver;

import java.lang.reflect.InvocationHandler;
import java.util.Random;
import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {
	public static StoreRestoreHandler handler;

	public static void validateCommandLine(String[] args) {

		if (args.length < 3) {
			System.out.println("invalid cmd line arguments");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		validateCommandLine(args);
		String mode = args[0];
		int n = Integer.parseInt(args[1]);
		String fileName = args[2];

		// creating proxy
		ProxyCreator pc = new ProxyCreator();
		handler = new StoreRestoreHandler();
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class }, handler);

		handler.setFileName(fileName);
		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;
		Vector<SerializableObject> ser_vector = new Vector<SerializableObject>();
		Vector<SerializableObject> deser_vector = null;
		SerializableObject myRecordRet = null;

		// serializing and deserializing
		if (mode.equals("serdeser")) {
			handler.openFile();
			boolean bool = false;
			Random random = new Random();
			for (int i = 0; i < n; i++) {
				char c = (char) (random.nextInt(25) + 'A');

				String temp = "DesignPattern" + Integer.toString(i);
				myFirst = new MyAllTypesFirst((int) (Math.random() * 200), (long) (Math.random() * 50), temp, bool,
						(int) (Math.random() * 600));
				mySecond = new MyAllTypesSecond(Math.random() * 180, (float) (Math.random() * 350),
						(short) (Math.random() * 700), c);
				ser_vector.add(myFirst);
				ser_vector.add(mySecond);
				((StoreI) cpointRef).writeObj(myFirst, 1, "XML");
				((StoreI) cpointRef).writeObj(mySecond, 1, "XML");
				bool = !bool;
			}
			handler.closeFile();
			handler.fileToRead(fileName);
			deser_vector = new Vector<SerializableObject>();
			System.out.println("Deserialized Objects : ");
			for (int j = 0; j < 2 * n; j++) {
				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
				deser_vector.add(myRecordRet);
			}
			int count = 0;
			for (int i = 0; i < ser_vector.size(); i++) {
				if (!ser_vector.get(i).equals(deser_vector.get(i))) {
					// System.out.println("Match");
					count++;
				}
			}
			System.out.println("\n Mismatched Objects : " + count);
		}

		// deserializing
		else if (mode.equals("deser")) {
			handler.fileToRead(fileName);
			deser_vector = new Vector<SerializableObject>();
			System.out.println("Deserialized Objects : ");
			for (int i = 0; i < n; i++) {
				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
				deser_vector.add(myRecordRet);
			}
		}

	}

}
