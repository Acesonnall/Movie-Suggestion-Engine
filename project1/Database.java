package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Omar Taylor
 *
 */
public class Database {

	String[][] database = new String[5][5]; // ItemID is the first index and UserID is the second

	/**
	 * Initialize the Database
	 */
	public Database() {

	}

	/**
	 * Searches through given file and sorts the data in a 2D array
	 * ([userID][itemID])
	 * 
	 * @param data
	 * @throws FileNotFoundException
	 */
	public void buildDatabase(String data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(data));

		int userID = 0;
		int itemID = 0;
		int rating = 0;
		
		/*
		 * UserIDs are hard-coded i, j, k, l, and m.
		 * ItemIDs are hard-coded as 0, 1, 2, 3, and 4.
		 * Have not yet found a way to process both counts automatically
		 * rather than manually
		 */
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int m = 0;
		while (sc.hasNextInt()) {
			userID = sc.nextInt(); // Get userID
			itemID = sc.nextInt(); // Get itemID
			rating = sc.nextInt(); // Get rating

			// Stores the database input in the following format: [UserID ItemID Rating].
			// String consists of numbers for easy processing
			if (itemID == 242 && i < 5) {
				database[0][i++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 302 && j < 5) {
				database[1][j++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 377 && k < 5) {
				database[2][k++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 51 && l < 5) {
				database[3][l++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 346 && m < 5) {
				database[4][m++] = userID + " " + itemID + " " + rating;
			} else {
				continue;
			}
		}
		sc.close();
	}

	/**
	 * Method scans in a file and writes to a new file without the time stamp
	 * @param data
	 *    The file
	 * @throws FileNotFoundException
	 */
	public void removeTimeStamp(String data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(data));
		PrintWriter pw = new PrintWriter("u_parsed.txt");

		while (sc.hasNextInt()) {
			pw.write(sc.nextInt() + " " + sc.nextInt() + " " + sc.nextInt() + "\n");
			sc.nextInt(); // throw away time stamp
		}
		pw.close();
		sc.close();
	}
}
