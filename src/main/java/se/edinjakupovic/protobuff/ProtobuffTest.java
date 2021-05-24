package se.edinjakupovic.protobuff;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import se.edinjakupovic.BigDecimalKinda;
import se.edinjakupovic.TestObjectProto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProtobuffTest {
    public static final String FILE = "test.proto";

    public static void main(String[] args) throws Exception {
        TestObjectProto testObjectProto = TestObjectProto.newBuilder()
                .setA("a")
                .setB("b")
                .setC("c")
                .setD("d")
                .setE("e")
                .setF("f")
                .setD1(fromLocalDate(LocalDate.now()))
                .setD2(fromLocalDate(LocalDate.now()))
                .setB1(fromBigDecimal(BigDecimal.TEN))
                .setB2(fromBigDecimal(BigDecimal.ONE))
                .setI(123)
                .build();

        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE)) {
            testObjectProto.writeTo(fileOutputStream);
        }

        try (FileInputStream fileInputStream = new FileInputStream(FILE)) {
            TestObjectProto serialized = TestObjectProto.parseFrom(fileInputStream);
            System.out.println("equals? " + testObjectProto.equals(serialized));
        }
        Files.deleteIfExists(Path.of(FILE));
    }

    public static BigDecimalKinda fromBigDecimal(BigDecimal bigDecimal) {
        return BigDecimalKinda.newBuilder()
                .setScale(bigDecimal.scale())
                .setPrecision(bigDecimal.precision())
                .setValue(ByteString.copyFrom(bigDecimal.unscaledValue().toByteArray()))
                .build();
    }

    public static Timestamp fromLocalDate(LocalDate date) {
        LocalTime localTime = date.atStartOfDay().toLocalTime();
        return Timestamp.newBuilder()
                .setSeconds(localTime.getSecond())
                .setNanos(localTime.getNano())
                .build();
    }
}
