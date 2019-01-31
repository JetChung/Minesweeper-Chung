import java.awt.*;

public class Square {

    private boolean isMine, isRevealed;
    private int neighborMines;
    private int r, c;
    private int numbBombs;

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

    public boolean getIsAlive(){
      return isMine;
    }

    public int numNeighbors(Square[][] squares) {

            int num = 0;
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    if (i >= 0 && i <= 15-1) {
                        if (j >= 0 && j <= 15-1) {
                            if (squares[i][j].getIsAlive()) {
                                num += 1;
                            }
                        }
                    }
                }
            }
            if (squares[r][c].getIsAlive()) {
                num -= 1;
            }

            neighborMines = num;
            return num;

        }
}
