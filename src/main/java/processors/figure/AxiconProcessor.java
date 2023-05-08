package processors.figure;

import javafx.util.Pair;
import processors.PixelProcessor;
import processors.Processor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class AxiconProcessor implements Processor {

    private PixelProcessor pixelProcessor = new PixelProcessor();

    @Override
    public List<List<Pair<Double, Double>>> process(BufferedImage image) {
        List<List<Pair<Double, Double>>> charts = new LinkedList<>();
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            List<Pair<Double, Double>> graphPoints = new LinkedList<>();
            Double max = 0.;
            for (int i = 0; i < height; i++) {
                Double ads = 0.;
                Color c = new Color(image.getRGB(width / 2, i));
                ads += pixelProcessor.getAbs(c);
                if (max < ads) max = ads;
                graphPoints.add(new Pair<Double, Double>(getX(i, height), ads));
            }
            charts.add(graphPoints);
            charts.add(getLine(max, height));
            charts.add(getLine(max / 2, height));
            return charts;
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
            return new LinkedList<>();
        }
    }

    Double getMaxHeight(List<Pair<Double, Double>> graphPoints) {
        Pair<Double, Double> maxHeight = null;
        for (Pair<Double, Double> point : graphPoints) {
            if (maxHeight == null || maxHeight.getValue() < point.getValue()) {
                maxHeight = point;
            }
        }
        return maxHeight.getKey();
    }

    List<Pair<Double, Double>> getLine(Double level, int longLine) {
        List<Pair<Double, Double>> graphPoints = new LinkedList<>();
        for (int i = 0; i < longLine; i++) {
            graphPoints.add(new Pair<Double, Double>(getX(i, longLine), level));
        }
        return graphPoints;
    }

    double getX(int i, int width) {
        return 5.0 * i / width - 2.5;
    }
}
