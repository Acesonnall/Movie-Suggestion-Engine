package project1;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author Omar Taylor
 *         3/4/2016
 */
public class SuggestionEngine extends Database {

    private double[] movies;

    public SuggestionEngine() throws FileNotFoundException {
        generateMatrix();
    }

    /**
     * Calculates averages of each movie and stores the result in an array. Index of the array movieID + 1.
     */
    private void getMovieAverages() {
        double sum = 0;
        double count = 0;
        movies = new double[matrix.length - 1];
        for (int i = 0; i < movies.length; i++) {
            movies[i] = (double) matrix[0][i + 1];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0 && matrix[i][j] < 6) {
                    sum += matrix[i][j];
                    count++;
                }
            }
            movies[i - 1] = sum / count;
            sum = 0;
            count = 0;
        }
    }

    /**
     * Prints out top 5 movies from the database.
     */
    public void getTopFive() {
        getMovieAverages();

        // Scramble 1-to-1 movie array to sort ratings

        double[] sortedMovies = new double[movies.length];
        System.arraycopy(movies, 0, sortedMovies, 0, sortedMovies.length);
        Arrays.sort(sortedMovies);

        // Re-sort dataset to be 1-to-1
        String[] topFive = new String[5];

        for (int i = sortedMovies.length - 1; i > sortedMovies.length - 6; i--)
        {
            for (int j = 0; j < movies.length; j++)
            {
                if (movies[j] == sortedMovies[i])
                {
                    topFive[i % 5] = j + " -- " + sortedMovies[i];
                }
            }
        }

        System.out.println("Actual Top 5 Ratings (w/ matching movie ID)");
        for (int i = 0; i < topFive.length; i++)
        {
            System.out.printf("%s%n", topFive[i]);
        }

        System.out.println("\nExpected Top 5 Ratings (w/o matching movie ID)");
        for (int i = sortedMovies.length - 1; i > sortedMovies.length - 6; i--)
        {
            System.out.printf("%f%n", sortedMovies[i]);
        }
    }
}
