package at.computercamp.utttai.game;

import java.util.ArrayList;
import java.util.List;

public class State {
    private Board board;
    private int playerNo;
    private int visitCount;
    private double winScore;

    public State() {
        board = new Board();
    }

    public State(State state){
        this.board = new Board(state.getBoard());
    }

    public State(Board board) {
        this.board = new Board();
        this.board.setPositions(board.getPositions());
        this.board.setActiveFieldX(board.getActiveFieldX());
        this.board.setActiveFieldY(board.getActiveFieldY());
        this.board.setActivePlayer(board.getActivePlayer());
        this.board.setFields(board.getFields());
    }

    public Board getBoard(){
        return board;
    }

     public void setBoard(Board board){
        this.board = board;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getOpponent() {
        return 3 - playerNo;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public double getWinScore() {
        return winScore;
    }

    public void setWinScore(double winScore) {
        this.winScore = winScore;
    }

    public void plusVisit() {
        this.visitCount++;
    }

    public void addScore(double score) {
        if (this.winScore != Integer.MIN_VALUE)
            this.winScore += score;
    }

    public void togglePlayer() {
        this.playerNo = 3 - this.playerNo;
    }

    public List<State> getAllPossibleStates(){
        List<State> possibleStates = new ArrayList<>();
        List<Position> availablePositions = this.board.getAviablePositions();

        //TODO: use method giving possible positions according to rules
        availablePositions.forEach(p -> {
            System.out.println(p.getX());
            System.out.println(p.getY());
            System.out.println("\n");
            State newState = new State(this.board);
            newState.setPlayerNo(3 - this.playerNo);
            newState.getBoard().move(newState.getPlayerNo(), p);
            possibleStates.add(newState);
        });
        return possibleStates;
    }


    public void randomPlay() {
        List<Position> availablePositions = this.board.getAviablePositions();
        for(Position position : availablePositions) {
            System.out.println("X:" + position.getX() + " Y:"+position.getY());
        }
        int totalPossibilities = availablePositions.size();

        int selectRandom = (int) (Math.random() * ((totalPossibilities - 1) + 1));

        this.board.move(this.board.getActivePlayer(), availablePositions.get(selectRandom));
    }
}
