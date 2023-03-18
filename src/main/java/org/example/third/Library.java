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
@NoArgsConstructor
@AllArgsConstructor
public class Library implements Externalizable {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(bookcases.size());
        for(Bookcase bookcase: bookcases) {
            bookcase.writeExternal(out);
        }
        out.writeInt(authors.size());
        for(Author author: authors) {
            author.writeExternal(out);
        }
        out.writeInt(books.size());
        for(Book book: books) {
            book.writeExternal(out);
        }
        out.writeInt(readers.size());
        for(Reader reader: readers) {
            reader.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int bookcasesSize = in.readInt();
        bookcases = new ArrayList<>();
        for(int i = 0; i < bookcasesSize; i ++ ){
            Bookcase bookcase = new Bookcase();
            bookcase.readExternal(in);
            bookcases.add(bookcase);
        }
        int authorsSize = in.readInt();
        authors = new ArrayList<>();
        for(int i = 0; i < authorsSize; i ++ ){
            Author author = new Author();
            author.readExternal(in);
            authors.add(author);
        }
        int booksSize = in.readInt();
        books = new ArrayList<>();
        for(int i = 0; i < booksSize; i ++ ){
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
        int readersSize = in.readInt();
        readers = new ArrayList<>();
        for(int i = 0; i < readersSize; i ++ ){
            Reader reader = new Reader();
            reader.readExternal(in);
            readers.add(reader);
        }
    }
}
