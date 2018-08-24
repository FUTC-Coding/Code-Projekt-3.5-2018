package at.computercamp.utttai.game;

import at.computercamp.utttai.networking.Protocol;

import java.util.*;

public class Board {

    private ArrayList<Position> positions = new ArrayList<>();
    private ArrayList<Field> fields = new ArrayList<>();

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

                int fieldX;
                int fieldY;

                if(x < 3) {
                    fieldX = 0;
                } else  if (x >= 5) {
                    fieldX = 1;
                } else  {
                    fieldX = 2;
                }

                if(y < 3) {
                    fieldY = 0;
                } else  if (y >= 5) {
                    fieldY = 1;
                } else  {
                    fieldY = 2;
                }

                Field field = new Field(fieldX,fieldY,protocol.getBig_board()[fieldX][fieldY]);
                fields.add(field);

                Position position = new Position(x, y, protocol.getCells()[x][y],field);
                positions.add(position);

            }
        }


        activeFieldX = protocol.getActive_field()[0];
        activeFieldY = protocol.getActive_field()[1];
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

    public ArrayList<Position> getAvailablePositions() {
        ArrayList<Position> aviable = new ArrayList<>();
        if(activeFieldX == 3 && activeFieldY == 3) {
            for (Position position : positions) {
                if(position.getUsed() != 0) {
                    aviable.add(position);
                }
            }
            return aviable;
        }


        for (Position position : positions) {
            if (position.getParent().getX() == activeFieldX && position.getParent().getY() == activeFieldY && position.getUsed() == 0) {
                aviable.add(position);
            }
        }

        return aviable;
    }

    public void move(int playerNr, Position position) {

        if (position.getParent().getX() != activeFieldX || position.getParent().getY() != activeFieldY) {
            System.out.println("Invalid Move");
            return;
        }

        if(position.getUsed() != 0)
            return;

        int nextActiveX = position.getX() % 3;
        int nextActiveY = position.getY() % 3;

        System.out.println(nextActiveX);
        System.out.println(nextActiveY);


        if (getFieldByCoordinates(nextActiveX, nextActiveY).getUsed() != 0) {
            nextActiveX = 3;
            nextActiveY = 3;
        }

        activeFieldX = nextActiveX;
        activeFieldY = nextActiveY;

        position.setUsed(activePlayer);



        //Check if Player has won the Field

        if(getPositionByCoordinates(position.getX() +1 , position.getY()).isOwnCell(activePlayer) && getPositionByCoordinates(position.getX() +2 , position.getY()).isOwnCell(activePlayer)) {
            getFieldByCoordinates(activeFieldX,activeFieldY).setUsed(activePlayer);
        }

        if(getPositionByCoordinates(position.getX() , position.getY() + 2).isOwnCell(activePlayer) && getPositionByCoordinates(position.getX(), position.getY()+2).isOwnCell(activePlayer)) {
            getFieldByCoordinates(activeFieldX,activeFieldY).setUsed(activePlayer);
        }

        if(getPositionByCoordinates(position.getX() +1, position.getY() + 1).isOwnCell(activePlayer) && getPositionByCoordinates(position.getX() +2 , position.getY()+2).isOwnCell(activePlayer)) {
            getFieldByCoordinates(activeFieldX,activeFieldY).setUsed(activePlayer);
        }

    }


    public ArrayList<Position> getPositionsInField(int x, int y) {
        ArrayList<Position> list = new ArrayList<>();
        for (Position position : positions) {
            if (position.getParent().getX() == activeFieldX && position.getParent().getY() == activeFieldY && position.getUsed() == 0) {
                list.add(position);

            }
        }
        return list;
    }

    public Position getPositionByCoordinates(int x, int y) {
        for (Position position : positions) {
            if (position.getX() == x && position.getY() == y) {
                return position;
            }
        }
        return null;
    }

    public Field getFieldByCoordinates(int x, int y) {
        for (Field field : fields) {
            if (field.getX() == x && field.getY() == y) {
                return field;
            }
        }
        return null;
    }

    public void togglePlayer() {
        activePlayer = 3 - activePlayer;

    }




}
