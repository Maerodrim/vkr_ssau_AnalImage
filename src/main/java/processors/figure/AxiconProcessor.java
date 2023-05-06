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
    public List<List<Pair<Integer, Integer>>> process(BufferedImage image) {
        List<List<Pair<Integer, Integer>>> charts = new LinkedList<>();
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            List<Pair<Integer, Integer>> graphPoints = new LinkedList<>();
            for (int i = 0; i < height; i++) {
                Double ads = 0.;
                //for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(width / 2, i));
                ads += pixelProcessor.getAbs(c);
                //}
                graphPoints.add(new Pair<>(i, ads.intValue()));
            }
            charts.add(graphPoints);
            int maxHeight = getMaxHeight(graphPoints);
            List<Pair<Integer, Integer>> graphPointsNew = new LinkedList<>();
            for (int j = 0; j < width; j++) {
                Double ads = 0.;
                //for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, maxHeight));
                ads += pixelProcessor.getAbs(c);
                //}
                graphPointsNew.add(new Pair<>(j, ads.intValue()));
            }
            charts.add(graphPointsNew);
            return charts;
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
            return new LinkedList<>();
        }
    }

    int getMaxHeight(List<Pair<Integer, Integer>> graphPoints) {
        Pair<Integer, Integer> maxHeight = null;
        for (Pair<Integer, Integer> point : graphPoints) {
            if (maxHeight == null || maxHeight.getValue() < point.getValue()) {
                maxHeight = point;
            }
        }
        return maxHeight.getKey();
    }
}
