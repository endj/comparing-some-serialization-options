package se.edinjakupovic;

import java.util.Objects;

public class Result {
    private final String implementation;
    private final int objectCount;
    private final long writeDurationMs;
    private final long readDurationMs;
    private final boolean valid;
    private final long fileSizeBytes;
    private final long fileSizeInKb;

    public Result(String implementation, int objectCount, long writeDurationMs, long readDurationMs, boolean valid, long fileSizeBytes) {
        this.implementation = implementation;
        this.objectCount = objectCount;
        this.writeDurationMs = writeDurationMs;
        this.readDurationMs = readDurationMs;
        this.valid = valid;
        this.fileSizeBytes = fileSizeBytes;
        this.fileSizeInKb = fileSizeBytes / 1024;
    }

    public String getImplementation() {
        return implementation;
    }

    public int getObjectCount() {
        return objectCount;
    }

    public long getWriteDurationMs() {
        return writeDurationMs;
    }

    public long getReadDurationMs() {
        return readDurationMs;
    }

    public boolean isValid() {
        return valid;
    }

    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public long getFileSizeInKb() {
        return fileSizeInKb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return objectCount == result.objectCount &&
                writeDurationMs == result.writeDurationMs &&
                readDurationMs == result.readDurationMs &&
                valid == result.valid &&
                fileSizeBytes == result.fileSizeBytes &&
                fileSizeInKb == result.fileSizeInKb &&
                Objects.equals(implementation, result.implementation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(implementation, objectCount, writeDurationMs, readDurationMs, valid, fileSizeBytes, fileSizeInKb);
    }

    @Override
    public String toString() {
        return "se.edinjakupovic.Result{" +
                "implementation='" + implementation + '\'' +
                ", objectCount=" + objectCount +
                ", writeDurationMs=" + writeDurationMs +
                ", readDurationMs=" + readDurationMs +
                ", valid=" + valid +
                ", fileSizeBytes=" + fileSizeBytes +
                ", fileSizeInKb=" + fileSizeInKb +
                '}';
    }
}
