package org.example.third;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bookcase implements Externalizable {
    private transient List<Book> books = new ArrayList<>();
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(books.size());
        for(Book book : books) {
            book.writeExternal(out);
        }
        out.writeInt(capacity);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        books = new ArrayList<>();
        for(int i = 0; i < size; i ++ ){
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
        capacity = in.readInt();
    }
}
