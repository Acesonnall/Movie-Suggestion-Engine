package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Omar Taylor This class scans through a data set of 100000 ratings by
 *         943 users on 1682 items and sorts them into a database.
 * 
 *         Issues: The buildDatabase(String data) function is statically
 *         implemented. It needs to be dynamic.
 */
public class Database {

	String[][] database = new String[5][5]; // ItemID is the first index and
											// UserID is the second
	int[] movies;
	int[] users;
	int[] ratings;

	/**
	 * Initialize the Database
	 * @throws FileNotFoundException 
	 */
	public Database() throws FileNotFoundException {
		writeToArray(0);
	}

	/**
	 * Searches through given file and sorts the data in a 2D array
	 * ([userID][itemID])
	 * 
	 * @param data
	 * @throws FileNotFoundException
	 */
	public void buildDatabaseStatic(String data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(data));

		int userID = 0;
		int itemID = 0;
		int rating = 0;

		/*
		 * UserIDs are hard-coded i, j, k, l, and m. ItemIDs are hard-coded as
		 * 0, 1, 2, 3, and 4. Have not yet found a way to process both counts
		 * automatically rather than manually
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

			// Stores the database input in the following format: [UserID ItemID
			// Rating].
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
	 * 
	 */
	public void buildDatabaseDynamic()
	{
		
	}
	
	/**
	 * This function separates each column into their own respective files
	 * @param data
	 * @throws FileNotFoundException
	 */
	private void separateCols(String data) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(data));
		PrintWriter users = new PrintWriter("u_usersonly.txt");
		PrintWriter movies = new PrintWriter("u_moviesonly.txt");
		PrintWriter ratings = new PrintWriter("u_ratingsonly.txt");
		while (sc.hasNextInt()) {
			users.write(sc.nextInt() + "\n");
			movies.write(sc.nextInt() + "\n");
			ratings.write(sc.nextInt() + "\n");
			sc.nextInt(); // throw away time stamp
		}
		users.close();
		movies.close();
		ratings.close();
		sc.close();
	}

	/**
	 * This function scans in u.txt and writes to a new file without the time
	 * stamp
	 * 
	 * @param data
	 *            The file
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

	/**
	 * Creates and array of movies, users, and ratings.
	 * listRatings, listMovies, and listUsers first
	 * 
	 * @param data
	 * @param array
	 * @throws FileNotFoundException
	 */
	private int[] writeToArray(int array) throws FileNotFoundException {
		separateCols("u.txt");
		if (array == 0) {
			Scanner sc;
			int length = 0;
			for (sc = new Scanner(new File("u_moviesonly.txt")); sc.hasNextInt(); sc.nextInt()) {
				length++;
			}

			movies = new int[length];

			length = 0;
			for (sc = new Scanner(new File("u_moviesonly.txt")); sc.hasNextInt(); length++) {
				movies[length] = sc.nextInt();
			}
			sc.close();

			return movies;
		} else if (array == 1) {
			Scanner sc;
			int length = 0;
			for (sc = new Scanner(new File("u_usersonly.txt")); sc.hasNextInt(); sc.nextInt()) {
				length++;
			}

			users = new int[length];

			length = 0;
			for (sc = new Scanner(new File("u_usersonly.txt")); sc.hasNextInt(); length++) {
				users[length] = sc.nextInt();
			}
			sc.close();

			return users;
		} else if (array == 2) {
			Scanner sc;
			int length = 0;
			for (sc = new Scanner(new File("u_ratingsonly.txt")); sc.hasNextInt(); sc.nextInt()) {
				length++;
			}

			ratings = new int[length];

			length = 0;
			for (sc = new Scanner(new File("u_ratingsonly.txt")); sc.hasNextInt(); length++) {
				ratings[length] = sc.nextInt();
			}
			sc.close();

			return ratings;
		} else {
			System.out.println("Invalid entry!");
			return null;
		}
	}
}
