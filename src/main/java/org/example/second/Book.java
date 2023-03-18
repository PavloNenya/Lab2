package org.example.second;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private Author author;
    private int pages;
    private String name;
    private String genre;

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }


}
