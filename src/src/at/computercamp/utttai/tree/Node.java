package at.computercamp.utttai.tree;

import at.computercamp.utttai.game.Board;
import at.computercamp.utttai.game.State;

import java.util.ArrayList;
import java.util.Random;

public class Node {

    private ArrayList<Node> childs;
    private Node parent;
    private State state;

    public Node() {
        state = new State();
        childs = new ArrayList<>();
    }

    public ArrayList<Node> getChilds() {
        return childs;
    }

    public Node getRandomChild() {
        Random random = new Random();
        return childs.get(random.nextInt(childs.size()));
    }

}
