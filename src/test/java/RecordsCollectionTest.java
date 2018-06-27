import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.MyCollection;
import model.Record;
import model.RecordShort;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecordsCollectionTest {

    private static MyCollection myCollection;
    private static List<Record> records;

    public RecordsCollectionTest() {
        initializeRecordsCollection();
        initializeRecords();
    }

    @Test
    public void testMap() {
        List<RecordShort> recordShortList = records
                .stream()
                .map(mapToRecordShort)  // Transform the object here.
                .collect(Collectors.toList());
        System.out.println(recordShortList);
    }

    @Test
    public void testSorted() {
        List<Record> recordListSorted = myCollection.getCollection().getRecords()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(recordListSorted);
    }

    @Test
    public void testSortedReversed() {
        List<Record> recordListSortedReversed = myCollection.getCollection().getRecords()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(recordListSortedReversed);
    }

    @Test
    public void testFilter() {
        List<Record> recordListTechno = myCollection.getCollection().getRecords()
                .stream()
                .filter(technoFilter)
                .collect(Collectors.toList());
        System.out.println(recordListTechno);
    }

    @Test
    public void testAnyMatch() {
        boolean anyMatch = myCollection.getCollection().getRecords()
                .stream()
                .anyMatch(record -> 1997 == record.getReleaseDate());
        System.out.println("Match any record from 1997 is " + anyMatch);
    }

    @Test
    public void testAllMatch() {
        boolean allMatch = myCollection.getCollection().getRecords()
                .stream()
                .allMatch(technoFilter);
        System.out.println("All genres matching techno is " + allMatch);
    }

    @Test
    public void testNoneMatch() {
        boolean noneMatch = records
                .stream()
                .noneMatch(mellowFilter);
        System.out.println("No mellow records found is " + noneMatch);
    }

    @Test
    public void testForEachStream() {
        Stream<Record> stream = records.stream();
        stream.forEach(System.out::println);
    }

    @Test
    public void testForEachCollection() {
        records.forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        BigDecimal euroTotal = records
                .stream()
                .map(euroMapper)
                .reduce(BigDecimal.ZERO, sumEuro);
        System.out.println("Total euro is " + euroTotal);
    }

    @Test
    public void testReduceUsingMethodReference() {
        BigDecimal euroTotal = records
                .stream()
                .map(Record::getEuro)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("" + euroTotal);
    }

    @Test
    public void testToMap() {
        Map<Integer, Record> recordMap = records
                .stream()
                .collect(Collectors.toMap(getKey, getValue));

        System.out.println("Record from 1997 " + recordMap.get(1997));
        System.out.println("Record from 1999 " + recordMap.get(1999));
        System.out.println("Record from 2007 " + recordMap.get(2007));
    }

    Function<Record, Integer> getKey = record -> record.getReleaseDate();
    Function<Record, Record> getValue = record -> record;

    Function<Record, BigDecimal> euroMapper = record -> record.getEuro();

    BinaryOperator<BigDecimal> sumEuro = (euro1, euro2) -> euro1.add(euro2);

    Predicate<Record> technoFilter = record -> "techno".equals(record.getGenre());
    Predicate<Record> mellowFilter = record -> "mellow".equals(record.getGenre());

    Function<Record, RecordShort> mapToRecordShort = record -> {
        RecordShort recordShort = new RecordShort();
        recordShort.setName(record.getArtist() + " - " + record.getName());
        return recordShort;
    };

    private void initializeRecordsCollection() {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("RecordsCollection.json");
            ObjectMapper objectMapper = new ObjectMapper();
            this.myCollection = objectMapper.readValue(inputStream, MyCollection.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                safeClose(inputStream);
        }
    }

    private void initializeRecords() {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("Records.json");
            ObjectMapper objectMapper = new ObjectMapper();
            this.records = objectMapper.readValue(inputStream, new TypeReference<List<Record>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                safeClose(inputStream);
        }
    }

    public static void safeClose(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
