package se.edinjakupovic.alternative;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import se.edinjakupovic.TestDataGenerator;
import se.edinjakupovic.TestObject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class MapDBWriteTest {
    private static final String WAL = "dbmapout/wal-file.db";
    private static final String NO_WAL = "dbmapout/no-wal.db";
    private static final String NO_WAL_MMAP = "dbmapout/mmap-no-wal.db";
    private static final String WAL_MMAP = "dbmapout/mmap-wal.db";

    public static void main(String[] args) {
        time(MapDBWriteTest::withWAL, "with transactions enabled");
        time(MapDBWriteTest::withoutWAL, "without transactions enabled");
        time(MapDBWriteTest::withMmapNoWal, "without transactions enabled mmap files");
        time(MapDBWriteTest::withMmapWal, "with transactions enabled mmap files");

        time(MapDBWriteTest::commitWithWal, "commit with transactions enabled");
        time(MapDBWriteTest::commitWithoutWal, "commit without transactions enabled");
        time(MapDBWriteTest::commitWithMmapNoWal, "commit without transactions enabled mmap files");
        time(MapDBWriteTest::commitWithMmapWal, "commit with transactions enabled mmap files");
    }

    private static void withWAL() {
        DB db = DBMaker
                .fileDB(WAL)
                .transactionEnable()
                .make();
        addData(db, "test");
        db.commit();
    }

    private static void withoutWAL() {
        DB db = DBMaker
                .fileDB(NO_WAL)
                .make();
        addData(db, "test2");
        db.commit();
    }

    private static void withMmapNoWal() {
        DB db = DBMaker
                .fileDB(NO_WAL_MMAP)
                .fileMmapEnable()
                .make();
        addData(db, "test3");
        db.commit();
    }

    private static void withMmapWal() {
        DB db = DBMaker
                .fileDB(WAL_MMAP)
                .fileMmapEnable()
                .transactionEnable()
                .make();
        addData(db, "test4");
        db.commit();
    }

    private static void commitWithWal() {
        DB db = DBMaker
                .fileDB(WAL)
                .transactionEnable()
                .make();
        commitTest(db, "test");
    }

    private static void commitWithoutWal() {
        DB db = DBMaker
                .fileDB(NO_WAL)
                .transactionEnable()
                .make();
        commitTest(db, "test2");
    }

    private static void commitWithMmapNoWal() {
        DB db = DBMaker
                .fileDB(NO_WAL_MMAP)
                .make();
        commitTest(db, "test3");
    }

    private static void commitWithMmapWal() {
        DB db = DBMaker
                .fileDB(WAL_MMAP)
                .make();
        commitTest(db, "test4");
    }

    private static void commitTest(DB db, String name) {
        DB.HashSetMaker<TestObject> test = db.hashSet(name, new MapDBSerialization());
        HTreeMap.KeySet<TestObject> orOpen = test.createOrOpen();
        IntStream.rangeClosed(0, 100).forEach(s -> {
            orOpen.add(TestDataGenerator.randomObject(s));
            db.commit();
        });
    }


    private static void addData(DB db, String name) {
        DB.HashSetMaker<TestObject> test = db.hashSet(name, new MapDBSerialization());
        HTreeMap.KeySet<TestObject> cache = test.createOrOpen();
        cache.addAll(TestDataGenerator.testDate(100_000));
    }


    private static void time(Runnable runnable, String impl) {
        long start = System.nanoTime();
        runnable.run();
        long stop = System.nanoTime();
        long duration = TimeUnit.MILLISECONDS.convert(stop - start, TimeUnit.NANOSECONDS);
        System.out.println("Duration: " + duration + "ms " + impl);
    }
}
