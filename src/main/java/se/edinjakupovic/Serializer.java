package se.edinjakupovic;

import java.util.Set;

public interface Serializer {
    String writeObject(String fileName, Object object);

    Set<TestObject> readObject(String fileName);
}
