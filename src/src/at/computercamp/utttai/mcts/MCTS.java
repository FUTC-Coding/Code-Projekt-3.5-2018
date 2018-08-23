package at.computercamp.utttai.mcts;


import at.computercamp.utttai.game.Board;
import at.computercamp.utttai.tree.Node;
import at.computercamp.utttai.tree.Tree;

import java.util.List;

public class MCTS {

    private static final int WIN_SCORE = 10;
    private int level;
    private int opponent;

    public MCTS() {
        this.level = 3;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int getMillisForCurrentLevel() {
        return 2 * (this.level - 1) + 1;
    }

    public Board findNextMove(Board board, int playerNo){
        long start = System.currentTimeMillis();
        long end = start + 60 * getMillisForCurrentLevel();

        opponent = 3 - playerNo;
        Tree tree = new Tree();
        Node rootNode = tree.getRoot();
        rootNode.getState().setBoard(board);
        rootNode.getState().setPlayerNo(opponent);


    }
}
