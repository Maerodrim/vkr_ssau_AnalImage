import model.ImageModel;
import processors.GraphProcessor;
import processors.figure.AxiconProcessor;
import tools.FileHelper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
        /*FileHelper.getFile();
        processors.PixelProcessor pixelProcessor = new processors.PixelProcessor();
        ImageModel imageModel = new ImageModel(FileHelper.getFile().get(0).getAbsolutePath());
        pixelProcessor.process(imageModel.getImage());*/

        GraphProcessor graphProcessor = new GraphProcessor();

        JFrame frame = new JFrame();
        //frame.getContentPane().add(new Test());
        AxiconProcessor axiconProcessor = new AxiconProcessor();
        ImageModel imageModel = new ImageModel(FileHelper.getFile("axicon").get(1).getAbsolutePath());
        frame.getContentPane().add(graphProcessor.createDemoPanel(axiconProcessor.process(imageModel.getImage())));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}