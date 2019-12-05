package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Client
{
    private Socket socket;
    private InetAddress targetAddress;

    public Client()
    {

        try {
            socket = new Socket("2604:3d08:1581:1f00:dd1a:cc4b:2d91:c2f9", 9910);
            System.out.println(socket.isConnected());
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        }
        
        
    }
    
    public void read()
    {
        Scanner scanner = null;
        try {
            scanner = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println(scanner.next());
    }
}