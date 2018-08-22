package at.computercamp.utttai;

import at.computercamp.utttai.game.Board;
import at.computercamp.utttai.networking.Connection;
import at.computercamp.utttai.networking.Protocol;

import java.net.InetAddress;

public class Main {



    public static void main(String[] args) {

        Protocol protocol = new Protocol();
        Connection con;
        String incomingMsg;
        boolean gameFinished = false;

        Board board = new Board(protocol);
        //Starting Client
        try {
            //Create Connection to Server
            con = new Connection(InetAddress.getLocalHost());

            while (!gameFinished) {
                incomingMsg = con.receiveMessage();
                protocol.setByteArray(incomingMsg);
                if(protocol.getWinner() != -1) {
                    gameFinished = true;
                    //Add Function to get the winner
                }
                board.updateBoardFromProtocol(protocol);


            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }

}
