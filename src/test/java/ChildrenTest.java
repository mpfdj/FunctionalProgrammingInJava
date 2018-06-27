import model.Child;
import model.ChildShortSummary;
import model.ChildrenResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChildrenTest {

    private Optional<ChildrenResponse> createChildrenResponse() {
        Child child1 = new Child();
        child1.setDateOfBirth("24-08-2009");
        child1.setCustomerId("1");
        child1.setSalutationName("Kim de Jaeger");
        child1.setGender("F");

        Child child2 = new Child();
        child2.setDateOfBirth("03-05-2014");
        child2.setCustomerId("2");
        child2.setSalutationName("Isabella de Jaeger");
        child2.setGender("F");

        ChildrenResponse response = new ChildrenResponse();
        response.setChildren(Arrays.asList(child1, child2));
        response.setCount(2);

        Optional<ChildrenResponse> childrenResponse = Optional.ofNullable(response);
        return childrenResponse;
    }

    @Test
    public void test() {
        Optional<ChildrenResponse> childrenResponse = createChildrenResponse();

        List<Child> childList = childrenResponse
                .map(cr -> cr.getChildren())
                .orElse(Collections.emptyList());

//        System.out.println(childList);

        List<ChildShortSummary> childList2 = childrenResponse
                .map(cr -> cr.getChildren())
                .orElse(Collections.emptyList())
                .stream()
                .map(child -> convertChild.apply(child))
                .collect(Collectors.toList());

        System.out.println(childList2);
    }

    Function<Child, ChildShortSummary> convertChild = (Child child) -> {
        ChildShortSummary childShortSummary = new ChildShortSummary();
        childShortSummary.setName(child.getSalutationName());
        return childShortSummary;
    };

}
