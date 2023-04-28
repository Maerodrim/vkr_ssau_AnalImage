import javafx.util.Pair;
import model.ImageModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import tools.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class GraphProcessor {

    private PixelProcessor pixelProcessor = new PixelProcessor();

    public JPanel createDemoPanel() {

        FileHelper.getFile();
        ImageModel imageModel = new ImageModel(FileHelper.getFile().get(0).getAbsolutePath());
        List<Pair<Integer, Integer>> points = pixelProcessor.process(imageModel.getImage());

        JFreeChart chart = createChart(createDataset(points));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    private XYSeries createSeries(List<Pair<Integer, Integer>> points) {
        XYSeries s1 = new XYSeries("График " + System.nanoTime());
        for (Pair point : points) {
            s1.add(Double.parseDouble(point.getKey().toString()), Double.parseDouble(point.getValue().toString()));
        }
        return s1;
    }

    private XYDataset createDataset(List<Pair<Integer, Integer>> points) {
        XYSeries s1 = new XYSeries("График №1");
        for (Pair point : points) {
            s1.add(Double.parseDouble(point.getKey().toString()), Double.parseDouble(point.getValue().toString()));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Мощность",  // title
                "координаты",                            // x-axis label
                "мощность",                      // y-axis label
                dataset,                       // data
                PlotOrientation.VERTICAL,
                true,                          // create legend
                true,                          // generate tooltips
                false                          // generate URLs
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }
        return chart;
    }
}
