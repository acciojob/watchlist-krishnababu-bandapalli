package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movies;
    Map<String, Director> directors;
    Map<String, List<String>> moviesMappedWithDirector;

    public MovieRepository() {
        this.movies = new HashMap<>();
        this.directors = new HashMap<>();
        this.moviesMappedWithDirector = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }
    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }
    public void addMovieDirectorPair(String movieName, String directorName) {
        if(moviesMappedWithDirector.containsKey(directorName)) {
            if(movies.containsKey(movieName) && directors.containsKey(directorName)) {
                movies.put(movieName, movies.get(movieName));
                directors.put(directorName, directors.get(directorName));
            }
            List<String> currentMovies;
            currentMovies = moviesMappedWithDirector.get(directorName);
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
        if (moviesMappedWithDirector.containsKey(directorName)) {
            return moviesMappedWithDirector.get(directorName);
        }
        return new ArrayList<>();
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
        HashSet<String> moviesList = new HashSet<>();
        for (String director: moviesMappedWithDirector.keySet()) {
            for (String movie: moviesMappedWithDirector.get(director)) {
                if(movies.containsKey(movie)) movies.remove(movie);
            }
        }
//        moviesMappedWithDirector.clear();
    }
}
