package model;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Image.SCALE_DEFAULT;

@Data
public class ImageModel {
    String fileName;
    BufferedImage image;

    public ImageModel(String fileName) {
        this.fileName = fileName;
        downloadImage(fileName);
    }

    public void downloadImage(String fileName) {
        try {
            this.image = ImageIO.read(new File(fileName));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public Image resizeImage(BufferedImage bufferedImage) throws IOException {
        return bufferedImage.getScaledInstance(256, 256, SCALE_DEFAULT);
    }
}
