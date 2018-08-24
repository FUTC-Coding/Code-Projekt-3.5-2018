package at.computercamp.utttai.tree;

import at.computercamp.utttai.game.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Comparator;
import java.util.Collections;

public class Node {

    private List<Node> children;
    private Node parent;
    private State state;

    public Node() {
        state = new State();
        children = new ArrayList<>();
    }

    public Node(State state) {
        this.state = new State();
        children = new ArrayList<>();
    }

    public Node(State state, Node parent, List<Node> children){
        this.state = state;
        this.parent = parent;
        this.children = children;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getRandomChild() {
        Random random = new Random();
        return children.get(random.nextInt(children.size()));
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public List<Node> getChildList(){
        return children;
    }

    public void setChildList(List<Node> children){
        this.children = children;
    }

    public Node getRandomChildNode() {
        int noOfPossibleMoves = this.children.size();
        int selectRandom = (int) (Math.random() * noOfPossibleMoves);
        return this.children.get(selectRandom);
    }

    public Node getChildWithMaxScore() {
        return Collections.max(this.children, Comparator.comparing(c -> {
            return c.getState().getVisitCount();
        }));
    }



}
