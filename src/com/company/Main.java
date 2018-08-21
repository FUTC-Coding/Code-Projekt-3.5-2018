package com.company;

import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {
        Connection con;
        String incomingMessage;
        String sendingMessage = "Hello World!";

	// write your code here
        try {
            con = new Connection(InetAddress.getLocalHost());
            System.out.println("Awaiting Message from Server");
            incomingMessage = con.receiveMessage();
            System.out.println("Message: " + incomingMessage + "received");
            con.sendMessage(sendingMessage.getBytes());
            System.out.println("Message sent!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
