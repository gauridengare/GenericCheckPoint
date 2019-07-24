package genericCheckpointing.util;

import java.text.DecimalFormat;

public class SerializeTypes {
	public String serializeInt(String fieldName, int value) {
		String line = null;
		if (value < 10) {
			line = "<" + fieldName + " xsi:type=\"xsd:int\"></" + fieldName + ">";
		} else
			line = "<" + fieldName + " xsi:type=\"xsd:int\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeLong(String fieldName, long value) {
		String line = null;
		if (value < 10) {
			line = "<" + fieldName + " xsi:type=\"xsd:long\"></" + fieldName + ">";
		} else
			line = "<" + fieldName + " xsi:type=\"xsd:long\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeBool(String fieldName, boolean value) {
		String line = "<" + fieldName + " xsi:type=\"xsd:boolean\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeDouble(String fieldName, double value) {
		String line = null;
		if (value < 10) {
			line = "<" + fieldName + " xsi:type=\"xsd:double\"></" + fieldName + ">";
		} else
			line = "<" + fieldName + " xsi:type=\"xsd:double\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeString(String fieldName, String value) {
		String line = "<" + fieldName + " xsi:type=\"xsd:string\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeShort(String fieldName, short value) {
		String line = "<" + fieldName + " xsi:type=\"xsd:short\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeChar(String fieldName, char value) {
		String line = "<" + fieldName + " xsi:type=\"xsd:char\">" + value + "</" + fieldName + ">";
		return line;
	}

	public String serializeFloat(String fieldName, float value) {
		String line = "<" + fieldName + " xsi:type=\"xsd:float\">" + value + "</" + fieldName + ">";
		return line;
	}
}
