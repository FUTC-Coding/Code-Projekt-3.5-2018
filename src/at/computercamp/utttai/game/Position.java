package at.computercamp.utttai.game;

public class Position {

    private int x;
    private int y;

    private int used;

    private Field parent;

    public Position(int x, int y, int used, Field parent) {
        this.x = x;
        this.y = y;
        this.used = used;
        this.parent = parent;
    }


    public int getUsed() {
        return used;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOwnCell(int clientId) {
        if(getUsed() == clientId)
            return true;
        return false;
        }

public Field getParent() {
        return parent;
        }

public  boolean isEmpty() {
        if (getUsed() == 0)
        return true;
        return false;
        }
        }

