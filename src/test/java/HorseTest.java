
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mockStatic;
import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    String name = "Default";
    double speed = 1.1; // Default
    double distance = 5.0; // Default


    @Test
    void constructor1ArgNameIsNullException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "  ", "\n", " ", "\n\n", "\t\t", "   "})
    void constructor1ArgNameIsNotEmptyException1(String argName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argName, speed, distance));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor2ArgSpeedIsNegativeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -1, distance));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor3ArgDistanceIsNegativeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameIsCorrectReturnName() {
        String actual = new Horse(name, speed, distance).getName();
        assertEquals(name, actual);
    }

    @Test
    void getSpeedIsCorrectReturnSpeed() {
        double actual = new Horse(name, speed, distance).getSpeed();
        assertEquals(speed, actual);
    }

    @Test
    void getDistanceIsCorrectReturnDistance() {
        double actual = new Horse(name, speed, distance).getDistance();
        assertEquals(distance, actual);
    }

    @Test
    void moveUseMethodIsCorrectAndArgument() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            Horse horse = new Horse(name, speed, distance);

            horse.move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void moveCheckDistanceCorrectMethodGetRandom(double random) {
        Horse horse = new Horse(name, speed, distance);
        double execute = distance + speed * random;

        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();
        }
        assertEquals(execute, horse.getDistance());

    }
}