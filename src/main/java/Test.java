import model.ImageModel;
import processors.GraphProcessor;
import processors.figure.AxiconProcessor;
import processors.figure.PlateProcessor;
import tools.FileHelper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

    public void paint(Graphics g) {
        Image img = createImageWithText();
        g.drawImage(img, 20, 20, this);
    }

    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        g.drawString("www.tutorialspoint.com", 20, 20);
        g.drawString("www.tutorialspoint.com", 20, 40);
        g.drawString("www.tutorialspoint.com", 20, 60);
        g.drawString("www.tutorialspoint.com", 20, 80);
        g.drawString("www.tutorialspoint.com", 20, 100);
        return bufferedImage;
    }

    public static void main(String[] args) {
        GraphProcessor graphProcessor = new GraphProcessor();
        String plate = "plate";
        String axicon = "axicon";
        String waveguide = "waveguide";
        PlateProcessor plateProcessor = new PlateProcessor();
        AxiconProcessor axiconProcessor = new AxiconProcessor();

        for (File file : FileHelper.getFile(plate)) {
            JFrame frame = new JFrame();
            ImageModel imageModel = new ImageModel(file.getAbsolutePath());
            frame.getContentPane().add(graphProcessor.createDemoPanel(plateProcessor.process(imageModel.getImage())));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);
            frame.setVisible(true);
        }
    }
}