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

        neighborMines = 0;
    }



    public int getNeighborMines() {
        return neighborMines;
    }

    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }


    public void draw(Graphics2D g2) {
        int size = MineSweeper.SIZE;
        if (isRevealed) {
            if (isMine) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.lightGray);
            }
            g2.fillRect(c * size, r * size, size, size);
        } else {
            g2.setColor(Color.GRAY);
            g2.fillRect(c * size, r * size, size, size);
        }
        g2.setColor(Color.BLACK);
        g2.drawRect(c * size, r * size, size, size);
        if(isRevealed && !isMine) {
            String x = String.valueOf(neighborMines)
            if (neighborMines == 1){
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g2.drawString(x, c * size+10, r * size+20);
            }
            else if (neighborMines == 2){
                
            }

        }
    }

    public void click() {
        isRevealed = true;
    }

    public boolean getIsMine() {
        return isMine;
    }




    }

