package com.example.project.controllers;

import com.example.project.dop.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.Map;

public class ColViewController {
    private final Calculator calculator = new Calculator();
    @FXML
    private BarChart<String, Double> colChart;
    @FXML
    private TextField meanField;
    @FXML
    private TextField deviationField;
    @FXML
    private TextField stepField;

    // Метод, вызываемый автоматически при запуске окна
    public void initialize() {

        // Запрет на изменение полей для отображения среднего значения и отклонения
        meanField.setEditable(false);
        deviationField.setEditable(false);

        fillChart();    // Вызов метода для заполнения графика
    }

    @FXML
    void gen(ActionEvent event) {
        fillChart();    // Заполнение графика по нажатию кнопки
    }

    private void fillChart() {

        // Очищение графика от предыдущих значений
        colChart.getData().clear();

        // Получение шага по разделеию элементов
        // (step == 100    ==>    Все значения в промежутке от 0 до 100 объединяются в одно значение, и суммируется частота)
        double step = Double.parseDouble(stepField.getText());

        // Получение массива из 10000 значений Коллективной эквивалетной дозы
        double[] doubles = calculator.calcArrayCdo();

        // Заполнение поля Среднего значения выборки с помощью StatUtils.mean()
        meanField.setText(String.format("%.2f", StatUtils.mean(doubles)));


        // Заполнение поля Отклонения выборки с помощью класса StandardDeviation
        StandardDeviation sd = new StandardDeviation();
        deviationField.setText(String.format("%.2f", sd.evaluate(doubles)));

        // Создание карты из массива значений выборки
        // Key   - значение по оси Х графика, является значением Коллективной эквивалетной дозы отдельного значения в выборке
        // Value - значение по оси У графика, является частотой  Коллективной эквивалетной дозы отдельного значения в выборке
        Map<Double, Double> map = calculator.calcMap(doubles, step);

        // Создание хранилища значений для графика
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        // Заполнение хранилища для графика из карты, путем сортировки и последующим заполнением
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach((item) -> series.getData().add(new XYChart.Data<>(Double.toString(item.getKey()), item.getValue())));

        // Передача данных в график
        colChart.getData().addAll(series);

    }

}
