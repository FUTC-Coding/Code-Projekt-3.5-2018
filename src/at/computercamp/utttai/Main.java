package at.computercamp.utttai;

import at.computercamp.utttai.game.Board;
import at.computercamp.utttai.game.Position;
import at.computercamp.utttai.mcts.MCTS;
import at.computercamp.utttai.networking.Connection;
import at.computercamp.utttai.networking.Protocol;

import java.net.InetAddress;

public class Main {





    public static void main(String[] args) {

        Connection con;
        Protocol protocol = new Protocol();
        String incomingMsg;
        boolean gameFinished = false;

        Board board = new Board(protocol);
        MCTS monteCarloTreeSearch = new MCTS();
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
                Board test = monteCarloTreeSearch.findNextMove(board, protocol.getClient_id());


            }




        } catch (Exception e) {

            e.printStackTrace();
        }


    }

}
