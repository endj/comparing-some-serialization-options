import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TestDataGenerator {
    private TestDataGenerator() {
    }

    public static Set<TestObject> testDate(int many) {

        return IntStream.rangeClosed(0, many)
                .mapToObj(TestDataGenerator::randomObject)
                .collect(Collectors.toSet());
    }

    private static TestObject randomObject(int seq) {
        TestObject testObject = new TestObject();
        testObject.setA("a".repeat((int) random()));
        testObject.setB("b".repeat((int) random()));
        testObject.setC("c".repeat((int) random()));
        testObject.setD("d".repeat((int) random()));
        testObject.setE("e".repeat((int) random()));
        testObject.setF("f".repeat((int) random()));
        testObject.setB1(BigDecimal.valueOf(random()));
        testObject.setB2(BigDecimal.valueOf(random()));
        testObject.setD1(LocalDate.now());
        testObject.setD2(LocalDate.now());
        testObject.setD3(LocalDate.now());
        testObject.setI(seq);
        return testObject;
    }

    private static long random() {
        return ThreadLocalRandom.current().nextLong(10, 100);
    }
}
