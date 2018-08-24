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

    public Board(Board board) {
        //TODO FInish Constructor
    }

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


                Position position = new Position(x, y, protocol.getCells()[x][y],field);
                positions.add(position);

            }


        }


        for(int x1 = 0; x1 < 3; x1++) {
            for (int y1 = 0; y1 < 3; y1++) {
                Field field = new Field(x1,y1,protocol.getBig_board()[x1][y1]);
                fields.add(field);

            }
        }


        activeFieldX = protocol.getActive_field()[0];
        activeFieldY = protocol.getActive_field()[1];
        activePlayer = protocol.getClient_id() + 1;
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
        aviable.clear();

        if(activeFieldX == 3 && activeFieldY == 3) {
            for (Position position : positions) {
                if(position.getUsed() == 0) {
                    aviable.add(position);
                }
            }
            return aviable;
        }

        System.out.println("Active Field" + activeFieldX);

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




        if (getFieldByCoordinates(nextActiveX, nextActiveY).getUsed() != 0) {
            nextActiveX = 3;
            nextActiveY = 3;
        }

        if(checkIfFieldIsWon(getFieldByCoordinates(activeFieldX, activeFieldY)) != 0) {
            getFieldByCoordinates(activeFieldX, activeFieldY).setUsed(checkIfFieldIsWon(getFieldByCoordinates(activeFieldX, activeFieldY)));
        }

        activeFieldX = nextActiveX;
        activeFieldY = nextActiveY;

        position.setUsed(activePlayer);

        togglePlayer();

        //Check if Player has won the Field



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

    public int checkIfFieldIsWon(Field field) {

        boolean boardFull = true;

        for(int j = field.getX() * 3; j < field.getX() * 3 + 2; j++) {
            boolean rowFull = true;
            boolean columnFull = true;
            Position symbolRow = getPositionByCoordinates(0,j), symbolColumn = getPositionByCoordinates(j,0);
            for(int i = field.getY() * 3; i < field.getY() * 3 + 2; i++)
            {
                rowFull &= getPositionByCoordinates(i,j) == symbolRow;
                columnFull &= getPositionByCoordinates(j,i) == symbolColumn;
                boardFull &= getPositionByCoordinates(i,j) != null;
            }
            if(rowFull && symbolRow != null) return activePlayer;
            if(columnFull && symbolColumn != null) return activePlayer;
        }
        //Check diagonals
        boolean diagonal1Full = true;
        boolean diagonal2Full = true;
        Position symbol1 = getPositionByCoordinates(0,0), symbol2 = getPositionByCoordinates(0+2,0);
        for(int i = 0; i < 3; i++)
        {
            diagonal1Full &= getPositionByCoordinates(i,i) == symbol1;
            diagonal2Full &= getPositionByCoordinates(2-i,i) == symbol2;
        }
        if(diagonal1Full && symbol1 != null) return activePlayer;
        if(diagonal2Full && symbol2 != null) return activePlayer;

        if(boardFull) return 3;


        return 0;
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


    public int getActivePlayer() {
        return activePlayer;
    }

    //TODO CREATE FUNCTION IF GAME IS WON
    public int checkStatus() {

        boolean boardFull = true;

        for(int j = 0; j < 3; j++) {
            boolean rowFull = true;
            boolean columnFull = true;
            Field symbolRow = getFieldByCoordinates(0,j), symbolColumn = getFieldByCoordinates(j,0);
            for(int i = 0; i < 3; i++)
            {
                rowFull &= getFieldByCoordinates(i,j) == symbolRow;
                columnFull &= getFieldByCoordinates(j,i) == symbolColumn;
                boardFull &= getFieldByCoordinates(i,j) != null;
            }
            if(rowFull && symbolRow != null) return activePlayer;
            if(columnFull && symbolColumn != null) return activePlayer;
        }
        //Check diagonals
        boolean diagonal1Full = true;
        boolean diagonal2Full = true;
        Field symbol1 = getFieldByCoordinates(0,0), symbol2 = getFieldByCoordinates(0+2,0);
        for(int i = 0; i < 3; i++)
        {
            diagonal1Full &= getFieldByCoordinates(i,i) == symbol1;
            diagonal2Full &= getFieldByCoordinates(2-i,i) == symbol2;
        }
        if(diagonal1Full && symbol1 != null) return activePlayer;
        if(diagonal2Full && symbol2 != null) return activePlayer;

        if(boardFull) return 3;
        return 0;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setActiveFieldX(int activeFieldX) {
        this.activeFieldX = activeFieldX;
    }

    public void setActiveFieldY(int activeFieldY) {
        this.activeFieldY = activeFieldY;
    }

    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }

    public int getActiveFieldX() {
        return activeFieldX;
    }


    public int getActiveFieldY() {
        return activeFieldY;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}
