package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image {
    String fileName;
    BufferedImage image;

    public void downloadImage(String fileName) throws Exception {
        try {
            this.image = ImageIO.read(new File(fileName));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
