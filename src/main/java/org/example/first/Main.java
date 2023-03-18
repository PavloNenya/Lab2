package org.example.first;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Author stephen_king = new Author("Stephen", "King");
        Book it = new Book(1, stephen_king, 256, "It", "Horror");
        Book pet_sematary = new Book(2, stephen_king, 331, "Pet sematary", "Horror");
        List<Book> list = new ArrayList<>();
        list.add(it);
        list.add(pet_sematary);
        Bookcase bookcase = new Bookcase(list, 250);
        Reader reader = new Reader(123, "Pavlo", "Nenia", List.of(pet_sematary));
        Library library = new Library(List.of(bookcase), List.of(stephen_king), list, List.of(reader));
        serializeObject("library1.txt", library);


        org.example.first.Library library1 = (org.example.first.Library) deSerializeObject("library1.txt");
        System.out.println(library1);
    }

    public static void serializeObject(String fileName, Object obj){
        try {
            ObjectOutputStream os = new ObjectOutputStream(new
                    FileOutputStream(fileName));
            os.writeObject(obj);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object deSerializeObject(String fileName){
        Object obj = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new
                    FileInputStream(fileName));
            obj = is.readObject();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}