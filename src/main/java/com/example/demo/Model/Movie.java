package com.example.demo.Model;

public class Movie{
    private String id;
    private String title;
    private String genre;
    private String rating;

    public Movie(String id, String title, String genre, String rating){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    // Getters
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
    public String getRating(){
        return rating;
    }

    // Setters
    public void setId(String id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public void setRating(String rating){
        this.rating = rating;
    }
}