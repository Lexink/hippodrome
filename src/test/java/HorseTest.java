import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void whenConstructorHasNullFirstParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 0.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ", "\n", "\t"})
    public void whenConstructorHasBlankFirstParameter(String parameter) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(parameter, 0.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void whenConstructorHasNegativeSecondParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", -1.0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void whenConstructorHasNegativeThirdParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", 1.0, -1.0)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest(){
        String nameToReturn = "ReturnedName";
        Horse testHorse = new Horse(nameToReturn, 1.0);

        assertEquals(nameToReturn, testHorse.getName());
    }

    @Test
    public void getSpeedTest(){
        double speedToReturn = 10.0;
        Horse testHorse = new Horse("TestHorse", speedToReturn);

        assertEquals(speedToReturn, testHorse.getSpeed());
    }

    @Test
    public void getDistanceTestWithConstructorParameter(){
        double distanceToReturn = 10.0;
        Horse testHorse = new Horse("TestHorse", 1.0, distanceToReturn);

        assertEquals(distanceToReturn, testHorse.getDistance());
    }

    @Test
    public void getDistanceTestWithoutConstructorParameter(){
        Horse testHorse = new Horse("TestHorse", 1.0);

        assertEquals(0.0, testHorse.getDistance());
    }

    @Test
    void getRandomDoubleInvocationCheckWhenMoveInvoked() {
        try (MockedStatic<Horse> mockedHorse =  Mockito.mockStatic( Horse.class)) {
            Horse testHorse = new Horse("Test Horse", 10.0);
            testHorse.move();
            mockedHorse.verify(()->Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.32, 0.467, 0.881})
    void distanceValueCheckWhenMoveInvoked(double randomValue){
        try (MockedStatic<Horse> mockedHorse =  Mockito.mockStatic( Horse.class)) {
            double speed = 15.0;
            double distance = 0.0;
            mockedHorse.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            Horse testHorse = new Horse("Test Horse", speed, distance);
            double expectedDistance = distance + speed*Horse.getRandomDouble(0.2, 0.9);
            testHorse.move();
            assertEquals(expectedDistance,testHorse.getDistance());
        }
    }
}
