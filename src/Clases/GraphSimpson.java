package Clases;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class GraphSimpson {
    public static ChartPanel generarGrafica(String funcion, double a, double b, int n) {

        // 1. Serie de la función suave (muchos puntos)
        XYSeries serieFuncionSuave = new XYSeries("f(x)");
        double hPlot = (b - a) / 300.0; // 300 puntos para una curva suave
        for (double x = a; x <= b; x += hPlot) {
            double y = Simpson13Calculador.evaluar(funcion, x);
            serieFuncionSuave.add(x, y);
        }

        // 2. Serie de los segmentos de Simpson (los puntos que tú calculas)
        XYSeries serieSegmentos = new XYSeries("Segmentos Simpson");
        double h = (b - a) / n;
        for (int i = 0; i <= n; i++) {
            double x = a + i * h;
            double y = Simpson13Calculador.evaluar(funcion, x);
            serieSegmentos.add(x, y);
        }

        // DATASETS SEPARADOS
        XYSeriesCollection datasetCurva = new XYSeriesCollection();
        datasetCurva.addSeries(serieFuncionSuave);

        XYSeriesCollection datasetSegmentos = new XYSeriesCollection();
        datasetSegmentos.addSeries(serieSegmentos);

        // GRÁFICO BASE
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Método Simpson 1/3 múltiple",
                "x",
                "f(x)",
                datasetCurva
        );

        XYPlot plot = chart.getXYPlot();

        // RENDERER 1: curva roja, gruesa
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        r1.setSeriesPaint(0, Color.RED);
        r1.setSeriesStroke(0, new BasicStroke(3.0f)); // línea gruesa
        r1.setSeriesShapesVisible(0, false); // sin puntos
        plot.setRenderer(0, r1);

        // Agregar dataset de segmentos
        plot.setDataset(1, datasetSegmentos);

        // RENDERER 2: segmentos y puntos azules
        XYLineAndShapeRenderer r2 = new XYLineAndShapeRenderer();
        r2.setSeriesPaint(0, Color.BLUE);
        r2.setSeriesStroke(0, new BasicStroke(2.0f)); // grosor de segmentos
        r2.setSeriesShapesVisible(0, true);
        r2.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6)); // punto azul
        plot.setRenderer(1, r2);

        return new ChartPanel(chart);
    }

}
