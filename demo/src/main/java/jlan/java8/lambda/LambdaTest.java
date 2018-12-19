package jlan.java8.lambda;

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Print 接口中只能含有一个方法
		Print print = () -> System.out.println("Hello Lambda!");
		print.log();
		
		new Thread(()-> System.out.println("New Thread!")).start();
	}
	
}
