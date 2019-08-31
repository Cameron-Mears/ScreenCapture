package output;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter
{
    int x;
    int y;
    @Override
    public void mouseMoved(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}