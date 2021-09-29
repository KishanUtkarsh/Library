package com.example.myapplication;

public class Books {

    private int id;
    private String name;
    private String author;
    private int Pages;
    private String imageUrl;
    private String description;

    public Books(int id, String name, String author, int pages, String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.Pages = pages;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setname(String name){
        this.name = name;
    }
    public String getname(){
        return this.name;
    }

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        author = author;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int pages) {
        Pages = pages;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void setimageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        description = description;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", Pages=" + Pages +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
