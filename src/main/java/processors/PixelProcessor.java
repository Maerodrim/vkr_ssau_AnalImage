package processors;

import javafx.util.Pair;
import model.ImageModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PixelProcessor {

    private HashMap<Color, Double> etalonColor = new HashMap<Color, Double>();

    public PixelProcessor() {
        initEtalon();
    }

    public List<Pair<Integer, Integer>> process(BufferedImage image) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            List<Pair<Integer, Integer>> graphPoints = new LinkedList<>();
            for (int i = 0; i < height; i++) {
                Double ads = 0.;
                //for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(i, height / 2 + 25));
                ads += getAbs(c);
                //}
                graphPoints.add(new Pair<>(i, ads.intValue()));
            }
            return graphPoints;
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
            return new LinkedList<>();
        }
    }

    public void initEtalon() {
        ImageModel imageModel = new ImageModel(getFile().get(0).getAbsolutePath());
        processEtalon(imageModel.getImage());
    }

    public Double getAbs(Color c) {
        if (etalonColor.containsKey(c)) {
            return etalonColor.get(c);
        } else {
            Color minColor = c;
            BigDecimal minDistance = BigDecimal.valueOf(100000);
            for (Color color : etalonColor.keySet()) {
                if (minDistance.intValue() > distance(c, color).intValue()) {
                    minColor = color;
                    minDistance = distance(c, color);
                }
            }
            etalonColor.put(c, etalonColor.get(minColor) + 1);
            return etalonColor.get(c);
        }
    }

    private static List<File> getFile() {
        File dir = new File("image//etalon");
        List<File> imageList = new LinkedList<>();
        if (dir.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : dir.listFiles()) {

                if (item.isDirectory()) {

                    System.out.println(item.getName() + "  \t folder");
                } else {
                    imageList.add(item);
                    System.out.println(item.getName() + "\t file");
                }
            }
        }
        return imageList;
    }

    private void processEtalon(BufferedImage image) {
        try {
            int width = image.getWidth();
            Double height = Double.parseDouble(String.valueOf(image.getHeight()));
            System.out.println("width " + width + " height " + height);
            Double w = 100.0;
            for (int i = 0; i < height.intValue(); i++) {
                Color c = new Color(image.getRGB(width / 2, i));
                Double value = w * ((height - i) / height);
                System.out.println(c.toString() + "  " + value);
                if (!etalonColor.containsKey(c)) this.etalonColor.put(c, value);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
        }
    }

    public static BigDecimal distance(Color from, Color to) {
        return BigDecimal.valueOf(Math.sqrt(
                Math.pow(from.getRed() - to.getRed(), 2)
                        + Math.pow(from.getGreen() - to.getGreen(), 2)
                        + Math.pow(from.getBlue() - to.getBlue(), 2)
        ));
    }
}
