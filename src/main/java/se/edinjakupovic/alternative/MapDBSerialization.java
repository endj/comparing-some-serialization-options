package se.edinjakupovic.alternative;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;
import se.edinjakupovic.TestObject;

import java.io.DataOutput;
import java.io.IOException;

public class MapDBSerialization implements Serializer<TestObject> {
    private static final ObjectMapper o = newMapper();

    @Override
    public void serialize(@NotNull DataOutput2 out, @NotNull TestObject value) throws IOException {
        ObjectWriter objectWriter = o.writerFor(TestObject.class);
        objectWriter.writeValue((DataOutput) out, value);
    }

    @Override
    public TestObject deserialize(@NotNull DataInput2 input, int available) throws IOException {
        ObjectReader objectReader = o.readerFor(TestObject.class);
        return objectReader.readValue(input);
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
