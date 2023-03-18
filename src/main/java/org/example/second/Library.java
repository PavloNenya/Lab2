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
@NoArgsConstructor
@AllArgsConstructor
public class Library implements Serializable {
    private List<Bookcase> bookcases;
    private transient List<Author> authors = new ArrayList<>();
    private transient List<Book> books = new ArrayList<>();
    private transient List<Reader> readers = new ArrayList<>();

    @Override
    public String toString() {
        return "Library{" +
                "bookcases=" + bookcases +
                ", authors=" + authors +
                ", books=" + books +
                ", readers=" + readers +
                '}';
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book b : books) {
            writeBook(out, b);
        }
        out.writeInt(authors.size());
        for (Author a : authors) {
            out.writeUTF(a.getName());
            out.writeUTF(a.getSurname());
        }
        out.writeInt(readers.size());
        for (Reader r : readers) {
            out.writeInt(r.getId());
            out.writeUTF(r.getName());
            out.writeUTF(r.getSurname());
            List<Book> bookList = r.getBooks();
            out.writeInt(bookList.size());
            for(Book b : bookList) {
                writeBook(out, b);
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        books = new ArrayList<>();
        int bookSize = in.readInt();
        for (int i = 0; i < bookSize; i++) {
            books.add(readBook(in));
        }
        int authorsSize = in.readInt();
        authors = new ArrayList<>();
        for (int i = 0; i < authorsSize; i++) {
            String name = (String) in.readUTF();
            String surname = (String) in.readUTF();
            authors.add(new Author(name, surname));
        }
        readers = new ArrayList<>();
        int readersSize = in.readInt();
        for (int i = 0; i < readersSize; i++) {
            int id = in.readInt();
            String name = (String) in.readUTF();
            String surname = (String) in.readUTF();
            int booksSize = in.readInt();
            List<Book> readersBooks = new ArrayList<>();
            for (int j = 0; j < booksSize; j++) {
                readersBooks.add(readBook(in));
            }
            readers.add(new Reader(id, name, surname, readersBooks));
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
