package com.example.demo.Service;

import com.example.demo.Model.Movie;

@Service
public class MovieService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final File file = new File("data/movies.json");

    public List<Movie> getAllMovies() {
        return readMovies();
    }
    public Movie getMovieById(String id){
        List<Movie> movies = readMovies();
        for (Movie movie:movies){
            if (movie.getId().equals(id)){
                return movie;
            }
        }
        return null;
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
            throw new RuntimeException("Could not read movies.json", e);
        }
    }

}