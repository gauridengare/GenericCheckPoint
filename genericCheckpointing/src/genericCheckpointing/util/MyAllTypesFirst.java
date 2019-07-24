package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {
	int myInt;
	long myLong;
	String myString = "DesignPattern";
	boolean myBool = true;
	int myOtherInt;

	public MyAllTypesFirst() {

	}

	public MyAllTypesFirst(int myInt1, long myLong1, String myString1, boolean myBool1, int myOtherInt1) {
		myInt = myInt1;
		myLong = myLong1;
		myString = myString1;
		myBool = myBool1;
		myOtherInt = myOtherInt1;
	}

	public int getmyInt() {
		return myInt;
	}

	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getmyLong() {
		return myLong;
	}

	public void setmyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getmyString() {
		return myString;
	}

	public void setmyString(String myString) {
		this.myString = myString;
	}

	public boolean getmyBool() {
		return myBool;
	}

	public void setmyBool(boolean myBool) {
		this.myBool = myBool;
	}

	public int getmyOtherInt() {
		return myOtherInt;
	}

	public void setmyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.myInt == ((MyAllTypesFirst) obj).getmyInt() 
				&& this.myLong == ((MyAllTypesFirst) obj).getmyLong()
				&& this.myString.equals(((MyAllTypesFirst) obj).getmyString())
				&& this.myOtherInt == ((MyAllTypesFirst) obj).getmyOtherInt()
				&& this.myBool == ((MyAllTypesFirst) obj).getmyBool()
				&& this.hashCode() == obj.hashCode())

			return true;

		else
			return false;
	}

	@Override
	public int hashCode() {
		int code = 0;
		code = this.getmyInt() * 5;
		return code;
	}

	@Override
	public String toString() {
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong + ", myString=" + myString + ", myBool="
				+ myBool + ", myOtherInt=" + myOtherInt + "]";
	}

}
