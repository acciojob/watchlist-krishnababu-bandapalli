package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String, Movie> movies;
    private Map<String, Director> directors;
    private Map<String, List<String>> moviesMappedWithDirector;

    public MovieRepository() {
        this.movies = new HashMap<String, Movie>();
        this.directors = new HashMap<String, Director>();
        this.moviesMappedWithDirector = new HashMap<String, List<String>>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }
    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }
    public void addMovieDirectorPair(String movieName, String directorName) {
        if(movies.containsKey(movieName) && directors.containsKey(directorName)){
            movies.put(movieName, movies.get(movieName));
            directors.put(directorName, directors.get(directorName));
            List<String> currentMovies = new ArrayList<String>();
            if(moviesMappedWithDirector.containsKey(directorName)) currentMovies = moviesMappedWithDirector.get(directorName);
            currentMovies.add(movieName);
            moviesMappedWithDirector.put(directorName, currentMovies);
        }
    }
    public Movie getMovieByName (String movieName) {
        return movies.get(movieName);
    }
    public Director getDirectorByName(String directorName) {
        return directors.get(directorName);
    }
    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> moviesList = new ArrayList<String>();
        if(moviesMappedWithDirector.containsKey(directorName)) moviesList = moviesMappedWithDirector.get(directorName);
        return moviesList;
    }
    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }
    public void deleteDirectorByName(String directorName) {
        if (moviesMappedWithDirector.containsKey(directorName)) {
            for (String movieName: moviesMappedWithDirector.get(directorName)) {
                if(movies.containsKey(movieName)) movies.remove(movieName);
            }
            moviesMappedWithDirector.remove(directorName);
        }
        if(directors.containsKey(directorName)) {
            directors.remove(directorName);
        }
    }
    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<>();
        for (String director: moviesMappedWithDirector.keySet()) {
            for (String movie: moviesMappedWithDirector.get(director)) {
                moviesSet.add(director);
            }
        }
        for (String movie: moviesSet) {
            if (movies.containsKey(movie)) movies.remove(movie);
        }
        moviesMappedWithDirector.clear();
    }
}
