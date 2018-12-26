package co.gov.antioquia;

public class Municipality {
	public Municipality(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	int code;
	String name;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
