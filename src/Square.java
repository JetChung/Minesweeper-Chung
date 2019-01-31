import java.awt.*;

public class Square {

    private boolean isMine, isRevealed;
    private int neighborMines;
    private int r, c;

    public Square(boolean isMine, int r, int c, Square[][] board) {
        this.isMine = isMine;
        this.r = r;
        this.c = c;
        this.isRevealed = false;

        neighborMines = 0;  //you'll want to code this properly.
                            //probably a numNeighbors method - probably similar to Life...
    }

    public void draw(Graphics2D g2){
        int size = MineSweeper.SIZE;
        if (isRevealed) {
            if(isMine) {
                g2.setColor(Color.RED);
            }else{
                g2.setColor(Color.BLACK);
            }
            g2.fillRect(c * size, r * size, size, size);
        }else{
            g2.setColor(Color.GRAY);
            g2.fillRect(c * size, r * size, size, size);
        }
        g2.setColor(Color.BLACK);
        g2.drawRect(c * size, r * size, size, size);

    }

    public void click(){
        isRevealed = true;
    }

}
