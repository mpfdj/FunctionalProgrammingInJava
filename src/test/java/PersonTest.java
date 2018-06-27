import model.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static model.Person.createRoster;

/*

https://docs.oracle.com/javase/tutorial/collections/streams/
https://docs.oracle.com/javase/tutorial/collections/streams/examples/ReductionExamples.java

*/

public class PersonTest {

    private List<Person> roster = createRoster();

    @Test
    public void test() {
        System.out.println(roster);
    }

    @Test
    public void testPrintAllMalesFromRoster() {
        roster
                .stream()
                .filter(person -> person.getGender() == Person.Sex.MALE)
                .forEach(person -> System.out.println(person.getName()));
    }

    @Test
    public void testPrintAverageMaleAge() {
        double averageAge = roster
                .stream()
                .filter(person -> person.getGender() == Person.Sex.MALE)
                .mapToInt(person -> person.getAge())
                .average()
                .getAsDouble();

        Double averageAgeTruncated = BigDecimal.valueOf(averageAge)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        System.out.println("Average male age is " + averageAgeTruncated);

        // The JDK contains many terminal operations (such as average, sum, min, max, and count) that return one value by combining the contents of a stream.
    }

    @Test
    public void testGroupMembersByGender() {

        Map<Person.Sex, List<Person>> byGender = roster
                .stream()
                .collect(Collectors.groupingBy(person -> person.getGender()));

        System.out.println(byGender);
        System.out.println("Males: " + byGender.get(Person.Sex.MALE));
        System.out.println("Females: " + byGender.get(Person.Sex.FEMALE));



        List<Map.Entry<Person.Sex, List<Person>>> byGenderList = new ArrayList<>(byGender.entrySet());
//
//        byGenderList
//                .stream()
//                .forEach(e -> {
//                    System.out.println("Gender: " + e.getKey());
//                    e.getValue()
//                            .stream()
//                            .map(Person::getName)
//                            .forEach(f -> System.out.println(f));
//                });
    }

}
