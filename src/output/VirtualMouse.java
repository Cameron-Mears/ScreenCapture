package output;

import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VirtualMouse extends MouseAdapter
{
    private Robot robot;
    private int lastX;
    private int lastY;
    private int deltaX;
    private int deltaY;

    private boolean newPos;


    public VirtualMouse()
    {
        this.lastX = 0;
        this.lastY = 0;
        this.newPos = false;

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        newPos = true;
        int x, y;
        x = e.getX();
        y = e.getY();
        deltaX = x - lastX;
        deltaY = y - lastY;
        lastX = x;
        lastY = y;
        
    }

    public boolean hasNewPos()
    {
        return newPos;
    }
    
    public void createMousePress(int button)
    {

    }

    public void createMouseRelease(int button)
    {
        
    }

    public void createMouseWheelEvent(int ticks)
    {
        
    }

}