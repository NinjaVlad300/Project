package com.example.project.dop;

import com.example.project.objects.Work;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public double calcTime() {
        double time = 0;
        for (Work work : Storage.getInstance().getWorks()) {
            work.setTime();
            time += work.getTime();
        }
        return time;
    }

    public double calcCost(double costT) {
        double cost = 0;
        for (Work work : Storage.getInstance().getWorks()) {
            work.setCost(costT);
            cost += work.getCost();
        }
        return cost;
    }

    public double calcCdo() {
        double kdo = 0;
        for (Work work : Storage.getInstance().getWorks()) {
            work.setKdo();
            kdo += work.getCdo();
        }
        return kdo;
    }

    public double calcIdo() {
        double ido = 0;
        for (Work work : Storage.getInstance().getWorks()) {
            work.setIdo();
            ido += work.getIdo();
        }
        return ido / Storage.getInstance().getWorks().size();
    }

    public double[] calcArrayCdo() {
        int size = 10000;
        double[] doubles = new double[size];

        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = calcCdo();
        }

        Arrays.sort(doubles);   // сортировка значений по возрастанию
        return doubles;
    }

    public double[] calcArrayIdo() {
        int size = 10000;
        double[] doubles = new double[size];

        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = calcIdo();
        }

        Arrays.sort(doubles);   // сортировка значений по возрастанию
        return doubles;
    }

    public Map<Double, Double> calcMap(double[] d, double step) {

        //  Число, в которое объединяются значения из массива по шагу
        //  (Шаг step = 100  ==>  num = 0, 100, 200, 300, ...)
        double num = 0;


        //  Карта<Key, Value> получающаяся из значений массива.
        //  Key - числа num, Value - вероятность встречи данных значений среди всей выборки
        Map<Double, Double> map = new HashMap<>();


        //  Пропуск значений, до ближайшего числа в массиве
        //  (d[0] = 939, шаг step = 100  ==>  пропуск значений, в итоге num = 900)
        while (num < d[0]) {
            num += step;
        }
        num = num - step;


        // Разбор значений массива по числам num
        for (int i = 0; i < d.length; i++) {

            //  Счетчик встречаемости чисел выборки в промежутке шага
            //  (num = 1000, в выборке числа 930, 1039, 1051, 1068, 1120  ==> j = 3)
            int j = 0;


            //  Если число из массива попадает в промежуток шага, увеличивается счетчик и переходим к следующему числу
            while (i + 1 != d.length && num <= d[i] && d[i] < num + step) {
                j++;
                i++;
            }
            num += step;    //  Увеличение шага, когда нет совпадений в выборке


            //  Уменьшение итератора; условие чтобы не было бесконечного цикла
            if (i + 1 != d.length) {
                i--;
            }


            //  Увеличение счётчика для последнего числа в выборке
            if (i == d.length - 1) {
                j++;
            }


            //  Помещение значений в карту
            map.put(num, (double) j / d.length);
            
        }
        return map;
    }

}
