package se.edinjakupovic;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.edinjakupovic.option.FstSerialization;
import se.edinjakupovic.option.JacksonSerialization;
import se.edinjakupovic.option.JavaSerialization;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestingSerialization {
    public static void main(String[] args) {
        Serializer oldSerializer = new JavaSerialization();
        runTests(oldSerializer, 100, 500, 1000, 5000, 10_000, 15_000, 100_000);

        Serializer jsonSerialization = new JacksonSerialization();
        runTests(jsonSerialization, 100, 500, 1000, 5000, 10_000, 15_000, 100_000);

        Serializer fstSerialization = new FstSerialization();
        runTests(fstSerialization, 100, 500, 1000, 5000, 10_000, 15_000, 100_000);
    }

    private static void runTests(Serializer serializer, int... sizes) {
        ObjectMapper objectMapper = new ObjectMapper();
        runTest(serializer, 10000);
        List<Result> results = Arrays.stream(sizes)
                .mapToObj(size -> runTest(serializer, size))
                .sorted(Comparator.comparingInt(Result::getObjectCount))
                .collect(Collectors.toList());

        try {
            objectMapper.writer().writeValue(new File(serializer.getClass().getSimpleName() + "-result.json"), results);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    private static Result runTest(Serializer serializer, int size) {
        Set<TestObject> testObjects = TestDataGenerator.testDate(size);
        String s = size + "-" + UUID.randomUUID();

        String file;
        long start = System.nanoTime();
        file = serializer.writeObject(s, testObjects);
        long stop = System.nanoTime();
        long writeDuration = TimeUnit.MILLISECONDS.convert(stop - start, TimeUnit.NANOSECONDS);
        System.out.println(serializer.getClass().getSimpleName() + " wrote " + size + " objects in " + writeDuration + " ms ");

        start = System.nanoTime();
        Set<TestObject> read = serializer.readObject(file);
        stop = System.nanoTime();
        long readDuration = TimeUnit.MILLISECONDS.convert(stop - start, TimeUnit.NANOSECONDS);
        System.out.println(serializer.getClass().getSimpleName() + " read " + size + " objects in " + readDuration + " ms");

        return new Result(serializer.getClass().getSimpleName(), size, writeDuration, readDuration, testObjects.equals(read), new File(file).length());
    }

}
