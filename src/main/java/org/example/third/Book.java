package org.example.third;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Externalizable {
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


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        author.writeExternal(out);
        out.writeInt(pages);
        out.writeUTF(name);
        out.writeUTF(genre);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        author = new Author();
        author.readExternal(in);
        pages = in.readInt();
        name = in.readUTF();
        genre = in.readUTF();
    }
}
