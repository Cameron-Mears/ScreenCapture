package server;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet6Address;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;


public class Server
{
    private ServerSocket serverSocket;
    private Socket localSocket;
    private Inet6Address inetAddress;
    private BufferedInputStream in;
    private ObjectOutputStream out;
    private int port;

    public Server() 
    {
        try {
            serverSocket = new ServerSocket(4646, 40, Inet6Address.getByName("2604:3d08:1581:1f00:7c09:cf64:5bbf:f1f"));
            System.out.println(serverSocket.getLocalPort());
            localSocket = serverSocket.accept();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {

            in = new BufferedInputStream(localSocket.getInputStream());
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void processIn()
    {
        
    }

    public void write(BufferedImage image) throws IOException
    {
        OutputStream outputStream = localSocket.getOutputStream();

        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();

    }



    
}

