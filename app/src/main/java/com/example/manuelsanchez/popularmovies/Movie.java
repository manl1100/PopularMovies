package com.example.manuelsanchez.popularmovies;

/**
 * Created by Manuel Sanchez on 9/23/15
 */
public class Movie {

    private String movieTitle;
    private String posterUrl;
    private String overview;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public static class Builder {

        Movie movie = new Movie();

        public Builder withMovieTitle(String title) {
            movie.setMovieTitle(title);
            return this;
        }

        public Builder withPosterUrl(String posterUrl) {
            movie.setPosterUrl(posterUrl);
            return this;
        }

        public Builder withOverview(String overview) {
            movie.setOverview(overview);
            return this;
        }

        public Movie build() {
            return movie;
        }
    }
}
