package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Omar Taylor This class scans through a data set of 100000 ratings by
 *         943 users on 1682 items and sorts them into a database.
 *         <p>
 *         Known Issues:
 */
public class Database {
    // ItemID is the first index and UserID is the second
    public int[][] matrix;
    String[][] staticMatrix = new String[5][5];
    private int M; // number of rows
    private int N; // number of columns

    /**
     * Create empty database
     *
     * @throws FileNotFoundException
     */
    public Database() throws FileNotFoundException {
        separateCols();
        removeTimeStamp();
    }

    /**
     * Create M-by-N matrix of 0's
     */
    public Database(int M, int N) {
        this.M = M;
        this.N = N;
        matrix = new int[M][N];
    }

    /**
     * Initialize database based on previous database
     *
     * @param matrix Existing matrix to be scanned in.
     */
    public Database(int[][] matrix) {
        M = matrix.length;
        N = matrix[0].length;
        this.matrix = new int[M][N];
        for (int i = 0; i < M; i++)
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, N);
    }

    /**
     * Searches through given file and sorts the data in a dynamically
     * implemented 2D array (matrix) called the database.
     *
     * @throws FileNotFoundException
     */
    private void buildDynamic() throws FileNotFoundException {
        setUp();
        Scanner sc = new Scanner(new File("u_parsed.txt"));

        while (sc.hasNextInt()) {
            matrix[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        }
        sc.close();
    }

    /**
     * Searches through given file and sorts the data in a statically
     * implemented 2D array (matrix) called the database.
     *
     * @throws FileNotFoundException
     */
    private void buildStatic() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("u_parsed.txt"));

        int userID;
        int itemID;
        int rating;

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
                staticMatrix[0][i++] = userID + " " + itemID + " " + rating;
            }
            if (itemID == 302 && j < 5) {
                staticMatrix[1][j++] = userID + " " + itemID + " " + rating;
            }
            if (itemID == 377 && k < 5) {
                staticMatrix[2][k++] = userID + " " + itemID + " " + rating;
            }
            if (itemID == 51 && l < 5) {
                staticMatrix[3][l++] = userID + " " + itemID + " " + rating;
            }
            if (itemID == 346 && m < 5) {
                staticMatrix[4][m++] = userID + " " + itemID + " " + rating;
            }
        }
        sc.close();
    }

    /**
     * This function separates each column into their own respective files
     *
     * @throws FileNotFoundException
     */
    private void separateCols() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("u.txt"));
        PrintWriter users = new PrintWriter("u_usersonly.txt");
        PrintWriter movies = new PrintWriter("u_moviesonly.txt");
        PrintWriter ratings = new PrintWriter("u_ratingsonly.txt");
        while (sc.hasNextInt()) {
            users.println(sc.nextInt());
            movies.println(sc.nextInt());
            ratings.println(sc.nextInt());
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
     * @throws FileNotFoundException
     */
    private void removeTimeStamp() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("u.txt"));
        PrintWriter pw = new PrintWriter("u_parsed.txt");

        while (sc.hasNextInt()) {
            pw.println(sc.nextInt() + " " + sc.nextInt() + " " + sc.nextInt());
            sc.nextInt(); // throw away time stamp
        }
        pw.close();
        sc.close();
    }

    /**
     * This function creates the row and column headers for the matrix. Does this by removing duplicate entries in the scanned in file.
     * Called twice to return array for row and column
     *
     * @param in
     * @param out
     * @param l
     * @return array of headers to be used for the row and column of matrix
     * @throws FileNotFoundException
     */
    private int[] createHeaders(File in, String out, int l) throws FileNotFoundException {
        Scanner sc = new Scanner(in);
        PrintWriter pw = new PrintWriter(out);
        int[] counters = new int[l];
        int length = 0;

        while (sc.hasNextInt()) {
            int temp = sc.nextInt();
            counters[temp] = 1;
        }

        for (int counter : counters) {
            if (counter == 1) {
                length++;
            }
        }

        sc.close();
        int[] array = new int[length + 1];

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
     * Creates a matrix of movies (columns), users (rows), and ratings (data).
     *
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
        int[] movies = createHeaders(f1, "u_movies_nodup.txt", mLength);
        int[] users = createHeaders(f2, "u_users_nodup.txt", uLength);
        matrix = new int[users.length][movies.length];

        for (mLength = 0; mLength < matrix[0].length; mLength++)
            matrix[0][mLength] = movies[mLength];
        for (uLength = 0; uLength < matrix.length; uLength++)
            matrix[uLength][0] = users[uLength];
    }

    /**
     * Generates and prints database to file. Must call buildDynamic (or buildStatic) to work.
     *
     * @throws FileNotFoundException
     */
    public void generateMatrix() throws FileNotFoundException {
        buildDynamic();
        PrintWriter pw = new PrintWriter("result.txt");

        // iterate over the first dimension
        for (int[] aMatrix : matrix) {
            // iterate over the second dimension
            for (int j = 0; j < aMatrix.length; j++) {
                if (j != aMatrix.length - 1) pw.printf("%4d| ", aMatrix[j]);
                else pw.printf("%4d", aMatrix[j]);
            }
            pw.println(); // add a line break.
        }
        pw.close();
    }
}
