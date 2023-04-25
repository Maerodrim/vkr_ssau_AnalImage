import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelProcessor {

    public void process(BufferedImage image) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();

            int count = 0;

            for (int i = 0; i < height; i++) {

                for (int j = 0; j < width; j++) {

                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    System.out.println("S.No: " + count + " Red: " + c.getRed() + "  Green: " + c.getGreen() + " Blue: " + c.getBlue());
                }
            }

        } catch (Exception e) {
        }
    }
}
