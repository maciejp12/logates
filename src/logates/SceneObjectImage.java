package logates;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SceneObjectImage {

    public BufferedImage image;
    
    public SceneObjectImage(String filename) {
        loadImage(filename);
    }

    public void loadImage(String filename) {
        try {
            image = ImageIO.read(new File("./resources/" + filename));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Rotate the image display after scene object rotation
     */
    public void rotate() {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.PI / 2, image.getWidth() / 2.f,
                         image.getHeight() / 2.f);
        AffineTransformOp op = new AffineTransformOp(transform,
                                               AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(image, null);
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
}
