package com.example.approximation;

import javafx.scene.chart.XYChart;

public class MyGraph {

    private XYChart<Double, Double> graph;
    private double range;

    public MyGraph(final XYChart<Double, Double> graph, final double range) {
        this.graph = graph;
        this.range = range;
    }

    public void plotLine(LineSet lineSet) {
        var points = lineSet.getPoints();
        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        for (int i = 0; i < points.size(); i++) {
            plotPoint(points.get(i).getValue0(), points.get(i).getValue1(), series);
        }
        graph.getData().add(series);
    }

    public void addSeires(XYChart.Series<Double, Double> series) {
        graph.getData().add(series);
    }

    public void plotPoint(final double x, final double y,
                           final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<>(x, y));
    }

    public void clear() {
        graph.getData().clear();
    }
}