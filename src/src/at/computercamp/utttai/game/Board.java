package at.computercamp.utttai.game;

import at.computercamp.utttai.networking.Protocol;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class Board {

    private ArrayList<Position> positions = new ArrayList<>();
    private int activeFieldX;
    private int activeFieldY;
    private int activePlayer;


    public Board() {

    }

    public Board(Board board) { }

    public Board(Protocol protocol) {

    }

    public void updateBoardFromProtocol(Protocol protocol) {
        for (int x = 0; x < 9; x++ ) {
            for (int y = 0; y < 9; y++) {
                Position position = new Position(x, y, protocol.getCells()[x][y]);
                System.out.println(position.getX());
                System.out.println(position.getUsed());
            }
        }
        activeFieldX = protocol.getActive_field()[0];
        activeFieldX = protocol.getActive_field()[1];
        activePlayer = protocol.getClient_id();
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    public Board copyToTemporaryBoard() {
        Board temporary = this;
        return temporary;
    }

    public ArrayList<Position> getEmptyPositions() {
        ArrayList<Position> empty = new ArrayList<>();
        for (Position p : positions) {
            if (p.isEmpty())
                empty.add(p);
        }
        return empty;
    }

    public ArrayList<Position> getAviablePositions() {
        ArrayList<Position> aviable = new ArrayList<>();

        return aviable;
    }

    public void move(int playerNr, int x, int y) {
        for(Position p : positions) {
            if(p.getX() == x && p.getY() == y) {
                p.setUsed(playerNr);
                togglePlayer();
                return;
            }
        }

        System.out.println("Position not found. Please try again");
    }

    public void preformMove(int x, int y) {

    }

    public void togglePlayer() {
        activePlayer = 3 - activePlayer;
    }




}
