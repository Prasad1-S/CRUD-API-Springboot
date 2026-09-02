package com.example.demo.Service;

import com.example.demo.Model.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final File file = new File("data/movies.json");

    public Movie createMovie(Movie movie) {

        List<Movie> movies = readMovies();

        int nextId = getNextId(movies);

        movie.setId(String.valueOf(nextId));

        movies.add(movie);

        writeMovies(movies);

        return movie;
    }

    public List<Movie> getAllMovies() {
        return readMovies();
    }

    public Movie getMovieById(String id) {

        List<Movie> movies = readMovies();

        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }

        return null;
    }

    public Movie updateMovie(String id, Movie updatedMovie) {

        List<Movie> movies = readMovies();

        for (int i = 0; i < movies.size(); i++) {

            Movie movie = movies.get(i);

            if (movie.getId().equals(id)) {

                updatedMovie.setId(id);

                movies.set(i, updatedMovie);

                writeMovies(movies);

                return updatedMovie;
            }
        }

        return null;
    }

    public boolean deleteMovie(String id) {

        List<Movie> movies = readMovies();

        boolean removed = movies.removeIf(
                movie -> movie.getId().equals(id)
        );

        if (removed) {
            writeMovies(movies);
        }

        return removed;
    }

    private int getNextId(List<Movie> movies) {

        int nextId = 1;

        for (Movie movie : movies) {

            try {
                int currentId = Integer.parseInt(movie.getId());

                if (currentId >= nextId) {
                    nextId = currentId + 1;
                }

            } catch (NumberFormatException e) {
                // Ignore non-numeric IDs
            }
        }

        return nextId;
    }

    private List<Movie> readMovies() {

        try {

            if (!file.exists()) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(
                    file,
                    new TypeReference<List<Movie>>() {}
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Could not read movies.json",
                    e
            );
        }
    }

    private void writeMovies(List<Movie> movies) {

        try {

            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(file, movies);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Could not write movies.json",
                    e
            );
        }
    }
}
