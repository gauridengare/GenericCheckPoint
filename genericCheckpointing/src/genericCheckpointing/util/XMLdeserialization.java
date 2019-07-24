package genericCheckpointing.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.driver.Driver;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class XMLdeserialization implements SerStrategy {

	@Override
	public SerializableObject processInput(SerializableObject sObject) {

		DeserializeTypes d = new DeserializeTypes();
		String s1 = Driver.handler.lineFromFile(); // <DPSerialization ignored
		String s2 = Driver.handler.lineFromFile();

		// getting class name - MyAllTypesSecond or MyAllTypesFirst
		String[] className1 = s2.split("\"");
		String className = className1[1];

		// creating object of className
		try {
			sObject = (SerializableObject) Class.forName(className).newInstance();
		}

		catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		while (!(s2 = Driver.handler.lineFromFile()).equals("</DPSerialization>")) {
			if (s2.equals("</complexType>")) {
				continue;
			}

			// getting field name
			String[] list = s2.split("<");
			String[] list2 = list[1].split(" ");
			String fldName = list2[0];

			// getting value of the above field
			String value = list2[1].substring(list2[1].indexOf(">") + 1, list2[1].length());

			try {
				// getting field declared in respective class using fieldName
				Field fld = Class.forName(className).getDeclaredField(fldName);
				// getting type of the declared filed
				Class fldType = fld.getType();
				// getting setter setterMethod of above fileName
				Method setterMethod = Class.forName(className).getMethod("set" + fldName, fldType);

				try {
					if (fldType == Integer.TYPE) {
						int val;
						if (value.equals("")) { // for the value not being serialized as it was less than 10
							val = 0;
						} else {
							val = d.deserializeInt(value);
						}
						setterMethod.invoke(sObject, val);
					} else if (fldType == Float.TYPE) {
						float val = d.deserializeFloat(value);
						setterMethod.invoke(sObject, val); // invoking setter setterMethod on object sObject and setting it to val
					} else if (fldType == String.class) {
						setterMethod.invoke(sObject, value);
					} else if (fldType == Character.TYPE) {
						char val = value.charAt(0);
						setterMethod.invoke(sObject, val);
					} else if (fldType == Long.TYPE) {
						long val;
						if (value.equals("")) { // for the value not being serialized as it was less than 10
							val = 0;
						} else {
							val = d.deserializeLong(value);
						}
						setterMethod.invoke(sObject, val);
					} else if (fldType == Short.TYPE) {
						short val = d.deserializeShort(value);
						setterMethod.invoke(sObject, val);
					} else if (fldType == Double.TYPE) {
						double val;
						if (value.equals("")) { // for the value not being serialized as it was less than 10
							val = 0.0;
						} else {
							val = d.deserializeDouble(value);
						}
						setterMethod.invoke(sObject, val);
					} else if (fldType == Boolean.TYPE) {
						boolean val = d.deserializeBool(value);
						setterMethod.invoke(sObject, val);
					}

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			} catch (NoSuchFieldException e) {
				continue; // if there exists a field in deserialization input file that does not exists in
							// MyAllTypesSecond or MyAllTypesFirst, ignore that field
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				System.out.println("setterMethod does not exists");
				System.exit(1);
			} catch (ClassNotFoundException e1) {
				System.out.println("class not found");
				System.exit(1);
			}

		}
		System.out.println(sObject);
		return sObject;
	}

}
