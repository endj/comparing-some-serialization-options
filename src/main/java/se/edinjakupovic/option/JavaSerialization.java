package se.edinjakupovic.option;

import se.edinjakupovic.Serializer;
import se.edinjakupovic.TestObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

public class JavaSerialization implements Serializer {
    @Override
    public String writeObject(String fileName, Object object) {
        String file = "runs" + File.separator + fileName + ".ser";
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(object);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return file;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<TestObject> readObject(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (Set<TestObject>) objectInputStream.readObject();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        throw new RuntimeException();
    }
}
