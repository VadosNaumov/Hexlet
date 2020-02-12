package Hexlet.Concurrency.T1;

import Hexlet.Concurrency.T1.victims.RunnableCaller;
import Hexlet.Concurrency.T1.victims.SumRunnable;
import Hexlet.Concurrency.T1.victims.SumThread;
import Hexlet.Concurrency.T1.victims.ThreadCaller;

import java.util.Arrays;
import java.util.Random;

public class Main {
    static int secondsThread;
    static int secondsRunnable;
    public static void main(String... args) throws Exception {
        RandomPair p1 = new RandomPair();
        RandomPair p2 = new RandomPair();
        RandomPair p3 = new RandomPair();

        calculateWithoutThreads(p3);
        testComputingInSumThread(p1);
        testComputingInSumRunnable(p2);
    }

    /**
     * The control method without any threads.
     * @param p an object that contains an array and its sum.
     */
    private static void calculateWithoutThreads(RandomPair p) {
        long before = System.currentTimeMillis();

        int actualResult = 0;
        for (int i : p.array) {
            actualResult += i;
        }
        long after = System.currentTimeMillis();
        System.out.printf("\nTime delta: %d milliseconds for calculateWithoutThreads method\n", (after - before));

        if (actualResult != p.sum) {
            throw new RuntimeException(String.format("victims.SumThread calculates" +
                            "incorrect sum for the input: %s," +
                            " expected result: %d, actual" +
                            " result: %d",
                    Arrays.toString(p.array),
                    p.sum,
                    actualResult));
        }
    }

    /**
     * Testing:
     * - creation and call of a thread;
     * - the accuracy of the calculation of the amount.
     * @param pair an object that contains an array and its sum.
     */
    private static void testComputingInSumThread(RandomPair pair) {
        System.gc();
        SumThread t = null;

        long before = System.currentTimeMillis();
        try {
            t = ThreadCaller.getResultFromSumThread(pair.array);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.currentTimeMillis();

        secondsThread = (int) (after - before);
        System.out.printf("\nTime delta: %d milliseconds for testSumThread\n", secondsThread);

        final int actualResult = t.getResult();
        if (actualResult != pair.sum) {
            throw new RuntimeException(String.format("victims.SumThread calculates" +
                            "incorrect sum for the input: %s," +
                            " expected result: %d, actual" +
                            " result: %d",
                    Arrays.toString(pair.array),
                    pair.sum,
                    actualResult));
        }
        t = null;
        System.gc();
    }

    /**
     * Testing:
     * - Create and call Runnable;
     * - the accuracy of the calculation of the amount.
     * @param pair an object that contains an array and its sum.
     */
    private static void testComputingInSumRunnable(RandomPair pair) {

        SumRunnable r = null;
        long before = System.currentTimeMillis();
        try {
            r = RunnableCaller.getResultFromSumRunnable(pair.array);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.currentTimeMillis();
        secondsRunnable = (int) (after - before);
        System.out.printf("\nTime delta: %d milliseconds for testSumRunnable\n", secondsRunnable);

        final int actualResult = r.getResult();
        if (actualResult != pair.sum) {
            throw new RuntimeException(String.format("victims.SumRunnable calculates" +
                            "incorrect sum for the input: %s," +
                            " expected result: %d, actual" +
                            " result: %d",
                    Arrays.toString(pair.array),
                    pair.sum,
                    actualResult));
        }
        r = null;
        System.gc();
    }

    /**
     * A class that generates an array of numbers and immediately calculates their sum.
     */
    private static class RandomPair {
        private final int[] array;
        private int sum;

        Random random = new Random();

        RandomPair() {
            int ARRAY_SIZE = 14_000;
            array = new int[ARRAY_SIZE];
            for (int i = 0; i < ARRAY_SIZE; i++) {
                sum += array[i] = random.nextInt(10);
            }
        }
    }
}
