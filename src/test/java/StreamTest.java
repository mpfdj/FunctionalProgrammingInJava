import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void createStream() {
        Stream<String> empty = Stream.empty();
        Stream<Integer> singleElement = Stream.of(1);
        Stream<Integer> fromArray = Stream.of(1, 2, 3, 4, 5);

        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Stream<String> fromList = list.stream();
        Stream<String> fromListParalell = list.parallelStream();


        // Create an ArrayList
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(1);
        myList.add(5);
        myList.add(8);

        // Convert it into a Stream
        Stream<Integer> myStream = myList.stream();


        // Create an array
        Integer[] myArray = {1, 5, 8};

        // Convert it into a Stream
        Stream<Integer> myStream2 = Arrays.stream(myArray);
    }

    @Test
    public void testMin() {
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
        System.out.println(min.get());
    }

    @Test
    public void testMax() {
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> max = s.max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());
    }

    @Test
    public void testArrayMap() {
        String[] myArray = new String[]{"bob", "alice", "paul", "ellie"};
        String[] result = Arrays
                .stream(myArray)
                .map(s -> s.toUpperCase())
                .toArray(String[]::new);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testFilter() {
        String[] myArray = new String[]{"bob", "alice", "paul", "ellie"};
        String[] result = Arrays.stream(myArray)
                .filter(s -> s.length() > 4)
                .toArray(String[]::new);

        System.out.println(Arrays.toString(result));
    }
}
