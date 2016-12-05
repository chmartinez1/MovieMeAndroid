package com.movie.me.android.domain;

/**
 * Created by hargueta on 10/27/16.
 */
public class Movie {

    private String IMDBID;

    private String TITLE;

    private String RATING;

    private String RATED;

    private String RELEASED;

    private String PLOT;

    private String WRITER;

    private String DIRECTOR;

    private String ACTORS;

    private String GENRE;

    private String RUNTIME;

    private String POSTER;
//
//    private Bitmap posterBitmap;
//
//    public Bitmap getPosterBitmap() {
//        return posterBitmap;
//    }
//
//    public void setPosterBitmap(Bitmap posterBitmap) {
//        this.posterBitmap = posterBitmap;
//    }

    public String getImdbid() {
        return IMDBID;
    }

    public void setImdbid(String IMDBID) {
        this.IMDBID = IMDBID;
    }

    public String getRated() {
        return RATED;
    }

    public void setRated(String RATED) {
        this.RATED = RATED;
    }

    public String getPlot() {
        return PLOT;
    }

    public void setPlot(String PLOT) {
        this.PLOT = PLOT;
    }

    public String getWriter() {
        return WRITER;
    }

    public void setWriter(String WRITER) {
        this.WRITER = WRITER;
    }

    public String getDirector() {
        return DIRECTOR;
    }

    public void setDirector(String DIRECTOR) {
        this.DIRECTOR = DIRECTOR;
    }

    public String getActors() {
        return ACTORS;
    }

    public void setActors(String ACTORS) {
        this.ACTORS = ACTORS;
    }

    public String getGenre() {
        return GENRE;
    }

    public void setGenre(String GENRE) {
        this.GENRE = GENRE;
    }

    public String getRuntime() {
        return RUNTIME;
    }

    public void setRuntime(String runtime) {
        this.RUNTIME = runtime;
    }

    public String getPoster() {
        return POSTER;
    }

    public void setPoster(String POSTER) {
        this.POSTER = POSTER;
    }

    public String getTitle() {
        return TITLE;
    }

    public void setTitle(String title) {
        this.TITLE = title;
    }

    public String getRating() {
        return RATING;
    }

    public void setRating(String RATING) {
        this.RATING = RATING;
    }

    public String getReleased() {
        return RELEASED;
    }

    public void setReleased(String RELEASED) {
        this.RELEASED = RELEASED;
    }

}
