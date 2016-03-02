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

	String[][] staticDb = new String[5][5]; // ItemID is the first index and
											// UserID is the second

	public int[][] matrix;
	private int[] movies;
	private int[] users;

	private int M; // number of rows
	private int N; // number of columns

	/**
	 * Create empty database
	 * @throws FileNotFoundException 
	 */
	public Database() throws FileNotFoundException {
		separateCols();
	}

	/**
	 * Create M-by-N matrix of 0's
	 * 
	 * @throws FileNotFoundException
	 */
	public Database(int M, int N) {
		this.M = M;
		this.N = N;
		matrix = new int[M][N];
	}

	/**
	 * Initialize database based on previous database
	 * 
	 * @param data
	 */
	public Database(int[][] matrix) {
		M = matrix.length;
		N = matrix[0].length;
		this.matrix = new int[M][N];
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				this.matrix[i][j] = matrix[i][j];
	}

	/**
	 * Work in progress... POSSIBLE SOLUTION: I could not properly sort the
	 * database in the static version because I did not know to properly sort
	 * the data without duplication. However, it is possible to sort the matrix
	 * by the bucket sort method and checking for duplicates either via checking
	 * if the element at the index is not null or by creating an array of
	 * counters set to zero
	 * 
	 * @throws FileNotFoundException
	 */
	public void buildDynamic() throws FileNotFoundException {
		setUp();
		Scanner sc = new Scanner(new File("u_parsed.txt"));
		
		while (sc.hasNextInt())
		{
			matrix[sc.nextInt()][sc.nextInt()] = sc.nextInt();
		}
		sc.close();
	}

	/**
	 * Searches through given file and sorts the data in a 2D array (matrix)
	 * called the database.
	 *
	 * 
	 * @param data
	 * @throws FileNotFoundException
	 */
	public void buildStatic() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("u_parsed.txt"));

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
				staticDb[0][i++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 302 && j < 5) {
				staticDb[1][j++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 377 && k < 5) {
				staticDb[2][k++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 51 && l < 5) {
				staticDb[3][l++] = userID + " " + itemID + " " + rating;
			} else if (itemID == 346 && m < 5) {
				staticDb[4][m++] = userID + " " + itemID + " " + rating;
			} else {
				continue;
			}
		}
		sc.close();
	}

	/**
	 * This function separates each column into their own respective files
	 * 
	 * @param data
	 * @throws FileNotFoundException
	 */
	private void separateCols() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("u.txt"));
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

	private int[] removeDuplicates(File in, String out, int l) throws FileNotFoundException {
		Scanner sc = new Scanner(in);
		PrintWriter pw = new PrintWriter(out);
		int[] counters = new int[l];
		int length = 0;

		while (sc.hasNextInt()) {
			int temp = sc.nextInt();
			counters[temp] = 1;
		}
		
		for (int i = 0; i < counters.length; i++)
		{
			if (counters[i] == 1)
			{
				length++;
			}
		}
		
		sc.close();
		int[] array = new int[length+1];
		
		for (length = 0; length <= array.length; length++) {
			if (counters[length] == 0) {
				continue;
			}
			pw.write(length + "\n");
			array[length] = length;
		}
		pw.close();
		return array;
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
	 * Creates and array of movies, users, and ratings. listRatings, listMovies,
	 * and listUsers first
	 * 
	 * @param data
	 * @param array
	 * @throws FileNotFoundException
	 */
	private void setUp() throws FileNotFoundException {
		Scanner sc;
		int mLength = 0;
		int uLength = 0;
		File f1 = new File("u_moviesonly.txt");
		File f2 = new File("u_usersonly.txt");

		for (sc = new Scanner(f1); sc.hasNextInt(); sc.nextInt())
			mLength++;
		sc.close();
		for (sc = new Scanner(f2); sc.hasNextInt(); sc.nextInt())
			uLength++;
		sc.close();
		this.movies = removeDuplicates(f1, "u_movies_nodup.txt", mLength);
		this.users = removeDuplicates(f2, "u_users_nodup.txt", uLength);
		matrix = new int[this.users.length][this.movies.length]; // when uLength and mLenth are
											// 100000, this operation allocates
											// a 10,000,000,000 element two
											// dimensional int array. That's
											// ~40gbs of memory! Please remove
											// duplicate entries from the length
											// calculation!

		for (mLength = 0; mLength < matrix[0].length; mLength++)
			matrix[0][mLength] = this.movies[mLength];
		for (uLength = 0; uLength < matrix.length; uLength++)
			matrix[uLength][0] = this.users[uLength];
	}
}
