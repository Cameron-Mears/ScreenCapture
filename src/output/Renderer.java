package output;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Renderer extends Canvas
{
    private BufferStrategy bs;
    private BufferedImage cursorImage;
    private Window window;
    
    public Renderer(Window window)
    {
        this.window = window;
        window.getWindow().add(this);
        File image = new File(System.getProperty("user.dir") + "\\ScreenCapture\\images\\cursor.png");
        try
        {
            cursorImage = ImageIO.read(image);
        } catch (IOException e) {e.printStackTrace();}

        
        
    }

    public Graphics2D createGraphics() 
    {
        bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        return g;

    } 

    public void show(Graphics2D g)  
    {

        PointerInfo cursor = MouseInfo.getPointerInfo();

        g.drawImage(cursorImage, (int) cursor.getLocation().getX(), (int) cursor.getLocation().getY(), null);
        bs.show();
    }
}