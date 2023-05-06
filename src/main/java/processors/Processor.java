package processors;

import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Processor {
    List<List<Pair<Integer, Integer>>> process(BufferedImage image);
}
