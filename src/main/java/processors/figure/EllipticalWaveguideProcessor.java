package processors.figure;

import javafx.util.Pair;
import processors.PixelProcessor;
import processors.Processor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EllipticalWaveguideProcessor implements Processor {

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
            System.out.println("height ");
            analiseLine(graphPoints);
            graphPoints = new LinkedList<>();
            for (int j = 0; j < width; j++) {
                Double ads = 0.;
                Color c = new Color(image.getRGB(j, height / 2));
                ads += pixelProcessor.getAbs(c);
                if (max < ads) max = ads;
                graphPoints.add(new Pair<Double, Double>(getX(j, width), ads));
            }
            System.out.println("width ");
            analiseLine(graphPoints);
            charts.add(graphPoints);
            /*charts.add(getLine(max, height));
            charts.add(getLine(max / 2, height));*/
            return charts;
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
            return new LinkedList<>();
        }
    }

    void analiseLine(List<Pair<Double, Double>> graphPoints) {
        Double right = 0.0, left = 0.0;
        for (int i = 0; i < graphPoints.size(); i++) {
            if (i < (graphPoints.size() / 2))
                left += Math.abs(graphPoints.get(i).getKey() * graphPoints.get(i).getValue());
            else right += Math.abs(graphPoints.get(i).getKey() * graphPoints.get(i).getValue());
        }
        System.out.println("Энергия справа и слева right =" + right + "  left = " +
                left + "  right - left =" + Math.abs(right - left));
    }

    double getX(int i, int width) {
        return 20.0 * i / width - 10;
    }
}
