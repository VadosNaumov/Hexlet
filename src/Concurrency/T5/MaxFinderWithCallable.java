package src.Concurrency.T5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String... args) {
        testArray(new Integer[0], 0);
        testArray(new Integer[]{1, 4, 10, 2}, 10);
        testArray(new Integer[]{1, 4, -10, 2}, 4);
        testArray(new Integer[]{1}, 1);
    }

    private static void testArray(final Integer[] inputArray, final int expectedResult) {
        final int actualResult = new MaxFinder(inputArray).call();
        if (actualResult != expectedResult) {
            throw new RuntimeException(
                    String.format(
                            "Actual max: %d, expected max: %d",
                            actualResult,
                            expectedResult));
        }
    }

    public static class MaxFinder implements Callable<Integer> {

        public final Integer[] array;

        public MaxFinder(Integer[] array) {
            this.array = array;
        }

        @Override
        public Integer call() throws Exception {
            return Arrays.stream(array).max(Comparator.naturalOrder()).orElse(0);
        }
    }
}
