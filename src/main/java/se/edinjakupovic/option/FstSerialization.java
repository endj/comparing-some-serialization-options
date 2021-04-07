package se.edinjakupovic.option;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import se.edinjakupovic.Serializer;
import se.edinjakupovic.TestObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Set;

public class FstSerialization implements Serializer {

    @Override
    public String writeObject(String fileName, Object object) {
        String file = "runs" + File.separator + fileName + ".fst";
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            FSTObjectOutput fstObjectOutput = new FSTObjectOutput(fileOutputStream);
            fstObjectOutput.writeObject(object);
            fstObjectOutput.close();
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
            FSTObjectInput fstObjectInput = new FSTObjectInput(fileInputStream);
            return (Set<TestObject>) fstObjectInput.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        throw new RuntimeException();
    }
}
