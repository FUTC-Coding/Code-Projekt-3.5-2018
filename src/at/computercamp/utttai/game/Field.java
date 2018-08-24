package at.computercamp.utttai.game;

public class Field {

    private int x;
    private int y;

    private int used;

    public Field(int x, int y, int won) {
        this.x = x;
        this.y = y;
        this.used = used;
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



    public boolean isWon(int clientId) {
        if(getUsed() == clientId)
            return true;
        return false;
    }

    public  boolean isEmpty() {
        if (getUsed() == 0)
            return true;
        return false;
    }

}
