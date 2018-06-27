import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

public class OptionalTest {

    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }

    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        String name = "baeldung";
        Optional.of(name);
    }


    @Test
    public void testToggleNullValue() {
        System.out.println(toggleNullValue(true));
        System.out.println(toggleNullValue(false));

        assertThat(toggleNullValue(false).get(), is("Hello World i'm not null."));
    }

    private Optional<String> toggleNullValue(boolean isNull) {
        if (isNull) return Optional.ofNullable(null);
        else return Optional.of("Hello World i'm not null.");
    }

}
