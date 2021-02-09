import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JacksonSerialization implements Serializer {

    private static final ObjectMapper OBJECT_MAPPER = newMapper();
    private static final TypeReference<Set<TestObject>> TYPE_REFERENCE = new TypeReference<>() {
    };

    @Override
    public String writeObject(String fileName, Object object) {
        ObjectWriter writer = OBJECT_MAPPER.writer();
        String file = "runs" + File.separator + fileName + ".json";

        try {
            writer.writeValue(new File(file), object);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return file;
    }

    @Override
    public Set<TestObject> readObject(String fileName) {
        try {
            return OBJECT_MAPPER.readValue(new File(fileName), TYPE_REFERENCE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return Collections.emptySet();
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
