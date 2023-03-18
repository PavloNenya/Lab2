package org.example.second;

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
public class Bookcase implements Serializable {
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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book b : books) {
            writeBook(out, b);
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        books = new ArrayList<>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            books.add(readBook(in));
        }
    }

    private void writeBook(ObjectOutputStream out, Book b) throws IOException {
        out.writeInt(b.getId());
        out.writeUTF(b.getAuthor().getName());
        out.writeUTF(b.getAuthor().getSurname());
        out.writeInt(b.getPages());
        out.writeUTF(b.getName());
        out.writeUTF(b.getGenre());
    }

    private Book readBook(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int id = in.readInt();
        String authorName = (String) in.readUTF();
        String authorSurname = (String) in.readUTF();
        Author author = new Author(authorName, authorSurname);
        int pages = in.readInt();
        String bookName = (String) in.readUTF();
        String bookGenre = (String) in.readUTF();
        return new Book(id, author, pages, bookName, bookGenre);
    }
}
