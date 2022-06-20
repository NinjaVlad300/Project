package com.example.project.controllers;

import com.example.project.dop.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class IndViewController implements Initializable {

    @FXML
    private BarChart<String, Double> colChart;

    @FXML
    private CategoryAxis colX;

    @FXML
    private NumberAxis colY;

    @FXML
    private TextField meanField;

    @FXML
    private TextField otkField;

    @FXML
    private TextField stepField;

    @FXML
    void gen(ActionEvent event) {
        generate();
    }

    private Calculator calculator = new Calculator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        meanField.setEditable(false);
        otkField.setEditable(false);

        generate();

    }

    Map calc(double[] d, double step) {

        Map<Double, Double> map = new HashMap<Double, Double>();
        double num = 0;

        while (num < d[0]) {
            num += step;
        }
        num = num - step;

        for (int i = 0; i < d.length; i++) {

            int j = 0;

            while (i + 1 != d.length && num <= d[i] && d[i] < num + step) {
                j++;
                i++;
            }

            num += step;

            while (i != d.length && num < d[i]) {
                num += step;
            }
            num = num - step;
            if (i + 1 != d.length) {
                i--;
            }

            if (i == d.length - 1) {
                j++;
            }

            if (j != 0) {

                map.put((double) num, (double) j / d.length);
            }

        }

        map.entrySet().stream().sorted(Map.Entry.comparingByKey());

        return map;

    }

    public void generate() {

        colChart.getData().clear();

        int size = 10000;
        double step = Double.parseDouble(stepField.getText());

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        double[] doubles = new double[size];

        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = calculator.calcIdo();
        }

        Arrays.sort(doubles);

        Map<Double, Double> map = calc(doubles, step);

        map.entrySet().stream().sorted(Map.Entry.<Double, Double>comparingByKey()).forEach((item) -> series.getData().add(new XYChart.Data<>(Double.toString(item.getKey()), item.getValue())));

        for (Map.Entry<Double, Double> item : map.entrySet()) {

            series.getData().add(new XYChart.Data<>(Double.toString(item.getKey()), item.getValue()));

        }

        colChart.getData().addAll(series);

        meanField.setText(Double.toString(StatUtils.mean(doubles)));

        StandardDeviation sd = new StandardDeviation();
        otkField.setText(Double.toString(sd.evaluate(doubles)));
    }
}
