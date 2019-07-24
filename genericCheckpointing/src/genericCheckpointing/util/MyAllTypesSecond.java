package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	double myDoubleT;
	float myFloatT;
	short myShortT;
	char myCharT = 'B';

	public MyAllTypesSecond() {

	}

	public MyAllTypesSecond(double myDoubleT1, float myFloatT1, short myShortT1, char myCharT1) {
		myDoubleT = myDoubleT1;
		myFloatT = myFloatT1;
		myShortT = myShortT1;
		myCharT = myCharT1;
	}

	public double getmyDoubleT() {
		return myDoubleT;
	}

	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	public float getmyFloatT() {
		return myFloatT;
	}

	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	public short getmyShortT() {
		return myShortT;
	}

	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	public char getmyCharT() {
		return myCharT;
	}

	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.myCharT == ((MyAllTypesSecond) obj).getmyCharT()
				&& this.myDoubleT == ((MyAllTypesSecond) obj).getmyDoubleT()
				&& this.myFloatT == ((MyAllTypesSecond) obj).getmyFloatT()
				&& this.myShortT == ((MyAllTypesSecond) obj).getmyShortT() 
				&& this.hashCode() == obj.hashCode()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int code = 0;
		code = (int) (this.getmyDoubleT() * 10);
		return code;
	}

	@Override
	public String toString() {
		return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT
				+ ", myCharT=" + myCharT + "]";
	}

}
