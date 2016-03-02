package project1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Implementation {

	public static void main(String[] args) throws FileNotFoundException {
		Database d = new Database();
		d.buildDynamic();
		String separator = ", ";
		StringBuffer result = new StringBuffer();
		PrintWriter pw = new PrintWriter("result.txt");

		// iterate over the first dimension
		for (int i = 0; i < d.matrix.length; i++) {
			// iterate over the second dimension
			for (int j = 0; j < d.matrix[i].length; j++) {
				result.append(d.matrix[i][j]);
				result.append(separator);
			}
			// remove the last separator
			result.setLength(result.length() - separator.length());
			// add a line break.
			result.append("\n");
		}

		pw.write(result.toString());
		pw.close();
	}
}
