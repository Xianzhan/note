package xianzhan.note.algorithm.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

/**
 * @author xianzhan
 * @since 2024-04-29
 */
public class SorterTest {

    @BeforeEach
    public void foreach(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }

    private int[] unsortedArr() {
        return RandomGenerator.getDefault()
                .ints(10, 0, 10)
                .toArray();
    }

    private void test(Consumer<int[]> sort) {
        var unsorted = unsortedArr();
        var sorted = unsorted.clone();
        sort.accept(sorted);

        System.out.println(Arrays.toString(unsorted));
        System.out.println(Arrays.toString(sorted));

        var javaSorted = unsorted.clone();
        Arrays.sort(javaSorted);
        Assertions.assertArrayEquals(javaSorted, sorted);
    }

    @Test
    public void testBubble() {
        test(Sorter::bubble);
    }

    @Test
    public void testSelect() {
        test(Sorter::select);
    }
}
