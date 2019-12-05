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
import java.io.IOException;

import output.Renderer;
import output.Window;
import server.Client;
import server.Server;

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
    private AudioListener audioListener;
    public static Server server;

    public CaptureManager(int targetFrames, AudioListener Al)
    {
        this.audioListener = Al;
        this.targetFrames = targetFrames;
        this.currentTime = System.currentTimeMillis();
        this.expectedTime = currentTime;
        this.deltaF = (long) 1000/targetFrames; //time delta between frames
        this.sleepTime = deltaF;
        this.sleepLoss = 0;
    
        server = new Server();

 
        graphicsConfig = graphicsInit(graphicsConfig);
        this.screenGraber = new ScreenGraber(graphicsConfig.getDevice());

        
    }

    @Override
    public void run()
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
            image = screenGraber.grabFrame();
            try {
                server.write(image);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
			}
            

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