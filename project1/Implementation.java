package project1;

import java.io.FileNotFoundException;

/**
 *
 * @author Omar Taylor
 *
 * This class implements the project.
 */
public class Implementation {

	public static void main(String[] args) throws FileNotFoundException {
		Database d = new Database();
		d.generateMatrix();
	}
}
