import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The ImagePanel is a panel to keep an Image in
 */
public class ImagePanel extends JPanel {

    private Image img;

    /**
     * Constructor for objects of class ImagePanel
     * @param img The relative address of the image (img/filename.img)
     */
    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    /**
     * Constructor for objects of class ImagePanel
     * @param img An Image from java.awt.Image
     */
    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
    
    /**
     * Draws a component, such as an image
     * @param g The Graphics element to be drawn
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}