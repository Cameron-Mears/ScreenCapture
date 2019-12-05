package output;

import java.awt.AWTException;
import java.awt.Robot;

public class VirtualKeyBoard
{
    public static Robot keyBoard;

    public VirtualKeyBoard()
    {
        try 
        {
            keyBoard = new Robot();

        } catch (AWTException e) {e.printStackTrace();}


    }

    public static void createKeyPress(int key)
    {
        keyBoard.keyPress(key);
    }

    public static void createKeyRelaese(int key)
    {
        keyBoard.keyRelease(key);
    }


}