package org.example.first;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library implements Serializable {
    private List<Bookcase> bookcases;
    private List<Author> authors;
    private List<Book> books;
    private List<Reader> readers;

    @Override
    public String toString() {
        return "Library{" +
                "bookcases=" + bookcases +
                ", authors=" + authors +
                ", books=" + books +
                ", readers=" + readers +
                '}';
    }

}
