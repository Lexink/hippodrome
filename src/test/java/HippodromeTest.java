import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    public void whenConstructorHasNullParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void whenConstructorHasEmptyListParameter(){
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorsesCheck_trueIfReturnedListEqualsInputList(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse #" + i, i));
        }

        Hippodrome testHippodrome = new Hippodrome(horses);
        assertEquals(horses, testHippodrome.getHorses());
    }

    @Test
    public void moveCheckCountInvocations(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.spy(new Horse("Horse #" + i, i)));
        }

        Hippodrome testHippodrome = new Hippodrome(horses);
        testHippodrome.move();
        for (Horse hors : horses) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }

    }

    @Test
    public void getWinnerCheck(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horses.add(new Horse("Horse #" + i, 1, i));
        }

        Hippodrome testHippodrome = new Hippodrome(horses);
        assertEquals(horses.get(horses.size()-1), testHippodrome.getWinner());
    }
}
