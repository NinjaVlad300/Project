package com.example.project.dop;

import com.example.project.objects.Work;

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

    public double calcKdo() {
        double kdo = 0;
        for (Work work : Storage.getInstance().getWorks()) {
            work.setKdo();
            kdo += work.getKdo();
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


}
