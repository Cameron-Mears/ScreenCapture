package output;

import java.awt.GraphicsDevice;
import java.awt.Image;

import javax.swing.JFrame;

public class Window {
    private int width;
    private int height;
    private Image icon;
    private String title;
    private boolean fullscreen;

    private JFrame frame;

    public Window(String title, int width, int height, boolean fullscreen, Image icon, GraphicsDevice targetMonitor) {
        this.frame = new JFrame();
        this.title = title;
        this.width = width;
        this.height = height;
        this.icon = icon;
        this.fullscreen = fullscreen;

        frame.setTitle(title);
        frame.setLocation(-1920,0);
        frame.setSize(width, height);
        frame.setIconImage(icon);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JFrame getWindow() {
        return frame;
    }

}