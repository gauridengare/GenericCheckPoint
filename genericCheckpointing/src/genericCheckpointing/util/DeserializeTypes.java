package genericCheckpointing.util;

public class DeserializeTypes {
	public int deserializeInt(String val)
	{
		return Integer.parseInt(val);
	}
	public float deserializeFloat(String val)
	{
		return Float.parseFloat(val);
	}
	public double deserializeDouble(String val)
	{
		return Double.parseDouble(val);
	}
	public short deserializeShort(String val)
	{
		return Short.parseShort(val);
	}
	public long deserializeLong(String val)
	{
		return Long.parseLong(val);
	}
	public char deserializeChar(String val)
	{
		return 0;
		
	}
	public boolean deserializeBool(String val)
	{
		return Boolean.parseBoolean(val);
	}
	
	
}
