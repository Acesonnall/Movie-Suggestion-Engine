package project1;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author Omar Taylor
 *         3/4/2016
 * BUGS: getTopX() is not able to list top movies in ascending order without a NULL value for the element in the last index
 */
public class SuggestionEngine extends Database {

    private double[] movies;

    public SuggestionEngine() throws FileNotFoundException {
        generateMatrix();
    }

    private static boolean contains(final int[] array, final int v) {
        for (final int e : array)
            if (e == v)
                return true;

        return false;
    }

    /**
     * Calculates averages of each movie and stores the result in an array. Index of the array movieID + 1.
     */
    private void getMovieAverages() {
        double sum = 0;
        double count = 0;
        movies = new double[matrix[0].length - 1];
        for (int i = 0; i < movies.length; i++) {
            movies[i] = (double) matrix[0][i + 1];
        }

        // Iterate over each column, getting the content from the row.
        for (int j = 1; j < matrix[0].length; j++) { // Cannot do matrix[i].length because there are more columns than rows, thus causing an out of bounds error
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][j] > 0 && matrix[i][j] < 6) {
                    sum += matrix[i][j];
                    count++;
                }
            }
            movies[j - 1] = sum / count;
            sum = 0;
            count = 0;
        }
    }

    /**
     * Prints out top X movies from the database.
     */
    public void getTopX(int amount) {
        getMovieAverages();
        if (amount > movies.length) amount = movies.length;

        // Scramble 1-to-1 [movie (array index) to rating] movie array to sort ratings

        double[] sortedMovies = new double[movies.length];
        System.arraycopy(movies, 0, sortedMovies, 0, sortedMovies.length);
        Arrays.sort(sortedMovies);

        // Re-sort dataset to be 1-to-1
        String[] topX = new String[amount]; // List of top movies
        int[] topXMovies = new int[amount]; // For easy comparison purposes

        int index = 0;
        for (int i = sortedMovies.length - 1; i > sortedMovies.length - (amount + 1); i--) { // Start from opposite end of sorted list
            for (int j = 0; j < movies.length; j++) { // Start at beginning of original unsorted list
                if (movies[j] == sortedMovies[i] && index < amount && !contains(topXMovies, j)) { // Insert into top movies list
                    topXMovies[index] = j;
                    topX[index++] = j + " -- " + String.format("%.2f", sortedMovies[i]);
                    break;
                }
            }
        }

        // The following is for testing purposes.
        int rank = 0;
        System.out.println("Actual Top " + amount + " Ratings (w/ matching movie ID)");
        for (String aTopX : topX) {
            System.out.printf("%d. %s%n", ++rank, aTopX);
        }

        System.out.println("\nExpected Top " + amount + " Ratings (w/o matching movie ID)");
        for (int i = sortedMovies.length - 1; i > sortedMovies.length - (amount + 1); i--) {
            System.out.printf("%.2f%n", sortedMovies[i]);
        }
    }
}
