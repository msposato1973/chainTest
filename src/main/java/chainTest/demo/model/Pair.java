package chainTest.demo.model;

public class Pair {

	public String numTime;
	public int value;
     
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getNumTime() {
		return numTime;
	}
	public void setNumTime(String numTime) {
		this.numTime = numTime;
	}
	public Pair(int value, String numTime) {
		super();
		this.value = value;
		this.numTime = numTime;
	}
	@Override
	public String toString() {
		return "Pair [ value = " + value + ", numTime=" + numTime + "]";
	}
     
	
}
