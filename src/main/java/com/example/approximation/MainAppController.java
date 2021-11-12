package com.example.approximation;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.javatuples.Pair;

import java.util.ArrayList;

public class MainAppController {

    @FXML
    protected LineChart<Double, Double> lineGraph;
    @FXML
    protected TextField polyDegree;

    private ILogger logger;
    private MyGraph mathsGraph;
    private ArrayList<LineSet> sets;
    private int currentSet = 0;

    @FXML
    public void initialize() {
        logger = new ConsoleLog();
        sets = new ArrayList<>();
        try {
            sets = DataBase.getLineSets();
        } catch (Exception e) {
            sets.add(getStandartLineSet());
            e.printStackTrace();
        }
        mathsGraph = new MyGraph(lineGraph, 20);
        lineGraph.setVisible(true);
    }

    @FXML
    protected void getNextLineSetButton_Click() {
        if (currentSet + 1 > sets.size() - 1) {
            currentSet = 0;
        } else {
            currentSet++;
        }
        clear();
    }

    @FXML
    protected void getPreviousLineSetButton_Click() {
        if (currentSet - 1 < 0) {
            currentSet = sets.size()-1;
        } else {
            currentSet--;
        }
        clear();
    }

    @FXML
    protected void getPointsButton_Click() {
        mathsGraph.plotLine(sets.get(currentSet));
    }

    @FXML
    protected void polyApproxButton_Click() {
        int degree;
        try {
            degree = Integer.parseInt(polyDegree.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка парсинга числа");
            alert.setContentText("Введите целое число.");
            alert.showAndWait();
            return;
        }
        try {
            if (degree >= sets.get(currentSet).getSize())
                throw new Exception("Ошибка степени полинома");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Степень полинома должна быть меньше количества аппроксимирующих точек.");
            alert.showAndWait();
            return;
        }
        mathsGraph.plotLine(PolynomialApproximation.polynomialApproximation(sets.get(currentSet), degree));
    }

    @FXML
    protected void lagrangeButton_Click() {
        mathsGraph.plotLine(LagrangeInterpolation.lagrangeInterpolation(sets.get(currentSet)));
    }

    @FXML
    protected void linearApproximationButton_Click() {
        mathsGraph.plotLine(LinearApproximation.linearApproximation(sets.get(currentSet)));
    }

    private LineSet getStandartLineSet() {
        LineSet lineSet = new LineSet();
        lineSet.addPoint(new Pair<>(-8.0, -5.0));
        lineSet.addPoint(new Pair<>(1.0, -2.0));
        lineSet.addPoint(new Pair<>(5.0, 7.0));
        lineSet.addPoint(new Pair<>(9.0, 10.0));
        lineSet.addPoint(new Pair<>(12.0, 15.0));
        return lineSet;
    }

    @FXML
    private void clearButton_Click() {
        clear();
    }

    private void clear() {
        mathsGraph.clear();
    }
}