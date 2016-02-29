package project1;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Implementation {

	public static void main(String[] args) throws FileNotFoundException {
		Database d = new Database();
		d.buildDatabase("u_parsed.txt");
		System.out.println(Arrays.deepToString(d.database));
	}

}
