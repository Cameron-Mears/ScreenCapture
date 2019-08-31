package capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenGraber
{
    private BufferedImage capture;
    private Rectangle screenSize;
    private Robot robot;

    public ScreenGraber(GraphicsDevice gd)
    {
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        
        try{
            robot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    public BufferedImage grabFrame()
    {
        capture = robot.createScreenCapture(screenSize);
        return capture;
    }


}