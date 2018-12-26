package jlan.test.strange;

public class EqualsOnAssign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 1;
        int y = 3;

        System.out.println(x == (x = y)); // false
        x = 1; // reset
        System.out.println((x = y) == x); // true
	}

}
