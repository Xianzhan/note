package xianzhan.note.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.random.RandomGenerator;

/**
 * @author xianzhan
 * @since 2024-04-29
 */
public class SorterTest {

    private int[] unsortedArr() {
        return RandomGenerator.getDefault()
                .ints(10, 0, 10)
                .toArray();
    }

    @Test
    public void testBubble() {
        var unsorted = unsortedArr();
        var sorted = unsorted.clone();
        Sorter.bubble(sorted);

        System.out.println(Arrays.toString(unsorted));
        System.out.println(Arrays.toString(sorted));
    }
}
