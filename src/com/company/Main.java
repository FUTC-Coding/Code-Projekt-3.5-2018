package com.company;

import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {
        
        Connection con;
        String incomingMessage;

        //Connecting to server and receiving first message
        try {
            con = new Connection(InetAddress.getLocalHost());
            System.out.println("Awaiting Message from Server");
            incomingMessage = con.receiveMessage();
            System.out.println("Message: " + incomingMessage + "received");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
