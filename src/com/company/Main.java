package com.company;

import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {

        Protocol p = new Protocol();
        Connection con;
        String incomingMessage;

        //Connecting to server and receiving first message
        try {
            con = new Connection(InetAddress.getLocalHost());
            System.out.println("Awaiting Message from Server");
            incomingMessage = con.receiveMessage();
            System.out.println("Message: " + incomingMessage + "received");

            // test setByteArray
            p.setByteArray(incomingMessage);

            int ci = p.getClient_id();
            int[][] bb = new int[3][3];
            for (int y = 0; y < 3; y++){
                for (int x = 0; x < 3; x++){
                    bb[x][y] = p.getBig_board()[x][y];
                }
            }
            int[][] ce = new int[9][9];
            for (int y = 0; y < 9; y++){
                for (int x = 0; x < 9; x++){
                    ce[x][y] = p.getCells()[x][y];
                }
            }
            int[] af = new int[2];
            for (int i = 0; i < 2; i++){
                af[i] = p.getActive_field()[i];
            }


            // print

            System.out.println("client id");
            System.out.println(ci);

            System.out.println("big board");
            for (int y = 0; y < 3; y++){
                for (int x = 0; x < 3; x++){
                    System.out.print(bb[x][y]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            System.out.println("cells");
            for (int y = 0; y < 9; y++){
                for (int x = 0; x < 9; x++){
                    System.out.print(ce[x][y]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            System.out.println("active field");
            for (int i = 0; i < 2; i++){
                System.out.print(af[i]);
                System.out.print(" ");
            }
            System.out.println();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
