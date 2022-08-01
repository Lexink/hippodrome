import org.junit.jupiter.api.Test;

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
}
