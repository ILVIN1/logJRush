import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void constructorArgListIsNullException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorArgIsEmptyException() {
        List<Horse> horses = new ArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesReturnListIsAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i + 0.1, i + 1));
        }
        Hippodrome h = new Hippodrome(horses);

        assertNotNull(h.getHorses());
        assertEquals(30, h.getHorses().size());
        for (int i = 0; i < 30; i++) {
            assertEquals("Horse" + i, h.getHorses().get(i).getName());
        }
    }

    @Test
    void moveMethodUsesAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome h = new Hippodrome(horses);
        h.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinnerRetutnsMaxDistanceHorse() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("horse1", 1, 5),
                new Horse("horse2", 2, 10),
                new Horse("horse3", 3, 15),
                new Horse("horse4", 3, 20),
                new Horse("horse5", 4, 25),
                new Horse("horse6", 5, 30)
        ));
        assertEquals("horse6", hippodrome.getWinner().getName());
    }
}