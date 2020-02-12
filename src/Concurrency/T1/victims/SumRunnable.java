package Hexlet.Concurrency.T1.victims;
/*
Класс SumRunnable наследуется от Runnable, его API состоит из следующих методов:

SumRunnable(...) - конструктор который принимает на вход массив,
                который потом будет просуммирован;
getResult() - метод, который используется для того,
            что бы получить результат суммирования,
            после того как поток закончил свое выполнение;
этот класс, тоже, должен вычислить сумму элементов массива в переопределяемом методе.
 */

import java.util.Arrays;

public class SumRunnable implements Runnable{

    private int[] arr;

    int resultSum = -1;

    public SumRunnable(int[] mas) {
        this.arr = mas;
    }

    public int getResult(){
        return resultSum;
    }

    @Override
    public void run() {
        resultSum = Arrays.stream(arr).sum();
    }
}
