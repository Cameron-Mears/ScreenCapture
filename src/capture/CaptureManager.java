package capture;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import output.Renderer;
import output.Window;

public class CaptureManager extends Thread
{
    public static GraphicsConfiguration graphicsConfig;

    private int targetFrames;
    private long currentTime;
    private long expectedTime;
    private long sleepTime;
    private long deltaF;
    private long sleepLoss;
    private boolean running;
    private Renderer renderer;
    private Window window;
    private ScreenGraber screenGraber;

    public CaptureManager(int targetFrames)
    {
        this.targetFrames = targetFrames;
        this.currentTime = System.currentTimeMillis();
        this.expectedTime = currentTime;
        this.deltaF = (long) 1000/targetFrames; //time delta between frames
        this.sleepTime = deltaF;
        this.sleepLoss = 0;
        this.window = new Window("s", 1920, 1080, false, null,  GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]);
        this.renderer = new Renderer(window);
        window.getWindow().setVisible(true);
        graphicsConfig = graphicsInit(graphicsConfig);
        this.screenGraber = new ScreenGraber(graphicsConfig.getDevice());

        
    }

    @Override
    public void start()
    {
        currentTime = System.currentTimeMillis();
        running = true;
        int frames = 0;
        long timeCheck = 0;
        while (running)
        {
            if (currentTime + 1000 <= System.currentTimeMillis())
            {
                currentTime = System.currentTimeMillis();
                System.out.println(frames);
                frames = 0;
            }
            expectedTime = currentTime + deltaF;
        
            sleepLoss = currentTime - expectedTime;
            BufferedImage image = null;
            Graphics2D g = renderer.createGraphics();
            image = screenGraber.grabFrame();
            
            
            g.drawImage(image, 0, 0, null);
            renderer.show(g);
            frames++;

        }


        
    }

    private static GraphicsConfiguration graphicsInit(GraphicsConfiguration config)
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        config = new GraphicsConfiguration()
        {
        
            @Override
            public AffineTransform getNormalizingTransform() {
                return new AffineTransform();
            }
        
            @Override
            public GraphicsDevice getDevice() {
                return ge.getDefaultScreenDevice();
            }
        
            @Override
            public AffineTransform getDefaultTransform() {
                return new AffineTransform();
            }
        
            @Override
            public ColorModel getColorModel(int transparency) {
                return null;
            }
        
            @Override
            public ColorModel getColorModel() 
            {
                return ColorModel.getRGBdefault();
            }
        
            @Override
            public Rectangle getBounds() 
            {
                return ge.getMaximumWindowBounds();
            }
        };

        return config;
    }

   
    
}