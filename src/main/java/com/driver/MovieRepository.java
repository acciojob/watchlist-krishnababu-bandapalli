package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMapping = new HashMap<>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            if(!directorMovieMapping.containsKey(director)) {
                directorMovieMapping.put(director, new ArrayList<>());
            }
            List<String> currentMovies = directorMovieMapping.get(director);
            currentMovies.add(movie);
            directorMovieMapping.put(director, currentMovies);
        }
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        if(directorMovieMapping.containsKey(director)) return directorMovieMapping.get(director);
        return new ArrayList<>();
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        List<String> movies;
        if(directorMovieMapping.containsKey(director)){
            movies = directorMovieMapping.get(director);
            for(String movie: movies){
                movieMap.remove(movie);
            }

            directorMovieMapping.remove(director);
        }

        directorMap.remove(director);
    }

    public void deleteAllDirector(){
        HashSet<String> moviesSet = new HashSet<>();
        for(String director: directorMovieMapping.keySet()){
            moviesSet.addAll(directorMovieMapping.get(director));
        }

        for(String movie: moviesSet){
            movieMap.remove(movie);
        }
        directorMovieMapping.clear();
    }
}