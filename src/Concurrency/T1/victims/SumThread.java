package Hexlet.Concurrency.T1.victims;
/*
Класс SumThread наследуется от Thread, его API состоит из следующих методов:
SumThread(...) - конструктор который принимает на вход массив,
                    который потом будет просуммирован;
getResult() - метод, который используется для того, что бы
        получить результат суммирования,
        после того как поток закончил свое выполнение;
вычисление суммы класс должен произвести в переопределяемом методе.
 */

import java.util.Arrays;

public class SumThread extends Thread {

    private int[] arr;

    int resultSum = -1;

    public SumThread(int[] mas) {
        this.arr = mas;
    }

    @Override
    public void run() {
        resultSum = Arrays.stream(arr).sum();
    }

    public int getResult(){
        return resultSum;
    }

}
