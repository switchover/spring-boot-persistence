package com.bookstore.view;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name="genre_and_title_view")
public class GenreAndTitleView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String title;

    private String genre;

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "AuthorBookView{" + "title=" + title + ", genre=" + genre + '}';
    }
}