package project1;

/**
 * Created by O on 3/5/2016.
 */
public class MovieInfo implements Comparable<MovieInfo> {

    private int movieID;

    private String movieTitle;
    private String releaseDate;
    private String vReleaseDate;
    private String IMBDUrl;

    private static final int[] genre = new int[19];

    public  MovieInfo(int movieID, String movieTitle, String releaseDate, String vReleaseDate, String IMBDUrl, int[] genre) throws IllegalArgumentException {
        if (movieID < 0 || genre.length > 19) {
            throw new IllegalArgumentException();
        }

        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.vReleaseDate = vReleaseDate;
        this.IMBDUrl = IMBDUrl;
        System.arraycopy(genre, 0, this.genre, 0, this.genre.length);
    }

    @Override
    public int compareTo(MovieInfo mI) {
        return this.getMovieTitle().compareTo(mI.getMovieTitle());
    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getvReleaseDate() {
        return vReleaseDate;
    }

    public String getIMBDUrl() {
        return IMBDUrl;
    }

    public int[] getGenre() {
        return genre;
    }
}
