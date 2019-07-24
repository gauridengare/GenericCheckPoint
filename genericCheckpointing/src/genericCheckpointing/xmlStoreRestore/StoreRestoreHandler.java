package genericCheckpointing.xmlStoreRestore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.SerStrategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.XMLSerialization;
import genericCheckpointing.util.XMLdeserialization;

public class StoreRestoreHandler implements InvocationHandler {
	String fileName;
	FileWriter file = null;
	FileReader f;
	BufferedReader br;
	public static StoreRestoreHandler h = new StoreRestoreHandler();

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		String method = arg1.getName(); // getting method name writeObj or readObj
		if (method.equals("writeObj")) {
			if (arg2[2].equals("XML")) {
				SerStrategy sStrategy = new XMLSerialization();
				serializeData((SerializableObject) arg2[0], sStrategy);
			} else {
				System.out.println("format is not XML");
				System.exit(1);
			}
		} else if (method.equals("readObj")) {
			if (arg2[0].equals("XML")) {
				SerStrategy dStrategy = new XMLdeserialization();
				Object obj = deserializeData(dStrategy);
				return obj;
			} else {
				System.out.println("format is not XML");
				System.exit(1);
			}
		}
		return arg0;
	}

	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject);
	}

	public Object deserializeData(SerStrategy dStrategy) {
		SerializableObject sObject = null;
		return sObject = dStrategy.processInput(sObject);
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void openFile() {
		try {
			file = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(String line) {
		try {
			file.write(line);
			file.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeFile() {
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fileToRead(String fileName) {
		try {
			f = new FileReader(fileName);
			br = new BufferedReader(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		}
		catch (IOException e) {
			System.out.println("File is empty");
			System.exit(1);
		}
	}

	public String lineFromFile() {
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
