package jlan.lab.anotation;

public class LogParser {
	public static void main(String[] args) {
		
	}
	
	@Log(msg="testMethod",date="2018-12-06")
	public void testMethod() {
		System.out.println("testMethod");
	}
}
