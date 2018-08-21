package com.company;
import java.net.*;
import java.io.*;

public class Connection {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private int port = 3141;

    public Connection(InetAddress url) {
        try {
            socket = new Socket(url, this.port);
            System.out.println("Socket created! ");

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());
        }
        catch (SocketException e1){
            e1.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Connected to Server! ");
    }
    public void sendMessage (byte[] msg){
        output.print(new String(msg));
        output.flush();
        System.out.println("Message sent!");
    }
    public String receiveMessage(){
        char[] charBuffer = new char[120];
        String info;
        try {
            System.out.println("Awaiting message!");
            input.read(charBuffer);
            info = new String(charBuffer);
            return info;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Receiving of message failed.");
        return null;

    }
}