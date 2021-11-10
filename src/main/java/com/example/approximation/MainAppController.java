package com.example.approximation;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.javatuples.Pair;

import java.util.ArrayList;

public class MainAppController {

    @FXML protected LineChart<Double, Double> lineGraph;
    @FXML protected TextField polyDegree;

    private MyGraph mathsGraph;

    @FXML
    public void initialize() {
        mathsGraph = new MyGraph(lineGraph, 20);
        lineGraph.setVisible(true);
    }

    @FXML
    protected void getPointsButton_Click() {
        mathsGraph.plotLine(getPoints());
    }

    @FXML
    protected void polyApproxButton_Click() {
        int degree = 0;
        try {
            degree = Integer.parseInt(polyDegree.getText());
            if (degree >= getPoints().size()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка степени полинома");
                alert.setContentText("Степень полинома должна быть меньше количества аппроксимирующих точек.");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка парсинга числа");
            alert.setContentText("Введите целое число.");
            alert.showAndWait();
            return;
        }
        mathsGraph.plotLine(PolynomialApproximation.polynomialApproximation(getPoints(), degree));
    }

    @FXML
    protected void lagrangeButton_Click() {
        mathsGraph.plotLine(LagrangeInterpolation.lagrangeInterpolation(getPoints()));
    }

    @FXML
    protected void linearApproximationButton_Click() {
        mathsGraph.plotLine(LinearApproximation.linearApproximation(getPoints()));
    }

    private ArrayList<Pair<Double, Double>> getPoints() {
        ArrayList<Pair<Double, Double>> points = new ArrayList<>();
        points.add(new Pair<>(-8.0, -5.0));
        points.add(new Pair<>(1.0, -2.0));
        points.add(new Pair<>(5.0, 7.0));
        points.add(new Pair<>(9.0, 10.0));
        points.add(new Pair<>(12.0, 15.0));
        return points;
    }

    @FXML
    private void handleClearButtonAction() {
        clear();
    }

    private void clear() {
        mathsGraph.clear();
    }
}