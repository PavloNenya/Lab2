package org.example.first;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bookcase implements Serializable {
    private List<Book> books;
    private int capacity;

    @Override
    public String toString() {
        return "Bookcase{" +
                "books=" + books +
                ", capacity=" + capacity +
                '}';
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBook(int id) {
        return books.stream()
                .filter((book) -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
