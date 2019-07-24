package com.example.ethiopis_kids;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Reading_content {
    String  book_title ;
     String book_cover ;
     String book_story ;
    public Reading_content() {
    }
    public Reading_content(String book_title, String book_story, String book_cover) {
        this.book_title = book_title;
        this.book_cover = book_cover;
        this.book_story = book_story;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_cover() {
        return book_cover;
    }

    public void setBook_cover(String book_cover) {
        this.book_cover = book_cover;
    }

    public String getBook_story() {
        return book_story;
    }

    public void setBook_story(String book_story) {
        this.book_story = book_story;
    }
}
