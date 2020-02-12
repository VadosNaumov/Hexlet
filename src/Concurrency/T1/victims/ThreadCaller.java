package Hexlet.Concurrency.T1.victims;
/*
Класс ThreadCaller требует написания метода с именем getResultFromSumThread,
который создаст, запустит, подождет завершения и вернет объект класса SumThread.
Этот метод на вход принимает целочисленный массив.
 */

public class ThreadCaller {

    public static SumThread getResultFromSumThread(int[] mas) throws InterruptedException {
        SumThread sumThread = new SumThread(mas);
        sumThread.run();
        sumThread.join();
        return sumThread;
    }
}
