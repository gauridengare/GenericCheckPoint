package genericCheckpointing.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.driver.Driver;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class XMLSerialization implements SerStrategy {

	@Override
	public SerializableObject processInput(SerializableObject obj) {
		SerializeTypes p = new SerializeTypes();
		String s = obj.getClass().getName();
		Field[] list = obj.getClass().getDeclaredFields();
		Driver.handler.writeToFile("<DPSerialization>");
		Driver.handler.writeToFile("<complexType xsi:type=\"" + s + "\">");

		for (int i = 0; i < list.length; i++) {
			Field fld = list[i];

			String fieldName = fld.getName();
			String methodName = "get" + fieldName;

			try {
				Method getterMethod = obj.getClass().getMethod(methodName); // getter method of field name
				Object invokeRet = getterMethod.invoke(obj); // getting value of the fieldName
				if (fld.getType() == Integer.TYPE) {
					String line = p.serializeInt(fieldName, (int) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == String.class) {
					String line = p.serializeString(fieldName, (String) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == float.class) {
					String line = p.serializeFloat(fieldName, (float) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == double.class) {
					String line = p.serializeDouble(fieldName, (double) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == short.class) {
					String line = p.serializeShort(fieldName, (short) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == long.class) {
					String line = p.serializeLong(fieldName, (long) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == boolean.class) {
					String line = p.serializeBool(fieldName, (boolean) invokeRet);
					Driver.handler.writeToFile(line);
				} else if (fld.getType() == char.class) {
					String line = p.serializeChar(fieldName, (char) invokeRet);
					Driver.handler.writeToFile(line);
				}
			} catch (NoSuchMethodException e) {
				System.out.println("Getter Method does not exists");
				System.exit(1);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		Driver.handler.writeToFile("</complexType>");
		Driver.handler.writeToFile("</DPSerialization>");
		return obj;
	}

}
