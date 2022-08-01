import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void whenConstructorHasNullFirstParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 0.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t"})
    public void whenConstructorHasBlankFirstParameter(String parameter) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(parameter, 0.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
}
