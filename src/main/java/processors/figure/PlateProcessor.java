package processors.figure;

import javafx.util.Pair;
import processors.PixelProcessor;
import processors.Processor;

import java.awt.image.BufferedImage;
import java.util.List;

public class PlateProcessor implements Processor {
    private PixelProcessor pixelProcessor = new PixelProcessor();

    @Override
    public List<List<Pair<Double, Double>>> process(BufferedImage image) {
        return null;
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
