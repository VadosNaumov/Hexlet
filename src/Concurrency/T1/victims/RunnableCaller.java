package Hexlet.Concurrency.T1.victims;

/*
RunnableCaller требует написания метода с именем getResultFromSumRunnable,
который создаст, запустит, подождет завершения
и вернет объект класса SumRunnable.
Этот метод на вход принимает целочисленный массив.
 */

public class RunnableCaller {

    public static SumRunnable getResultFromSumRunnable(int[] mas) throws InterruptedException {
        SumRunnable sumRunnable = new SumRunnable(mas);
        Thread thread = new Thread(sumRunnable);
        thread.run();
        thread.join();
        return sumRunnable;
    }
}