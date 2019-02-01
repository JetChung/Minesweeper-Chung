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
            String x = String.valueOf(neighborMines);
            if (neighborMines == 1){
                g2.setColor(Color.BLUE);
            }
            else if (neighborMines == 2){
                g2.setColor(Color.GREEN);
            }
            else if (neighborMines == 3){
                g2.setColor(Color.RED);
            }
            else if (neighborMines == 4){
                g2.setColor(new Color(113, 0, 192));

            }
            else if (neighborMines == 5){
                g2.setColor(new Color(146, 0, 34));
            }
            else if (neighborMines == 6){
                g2.setColor(new Color(28, 138, 142));

            }
            else if (neighborMines == 7){
                g2.setColor(new Color(0, 0, 0));

            }
            else if (neighborMines == 8){
                g2.setColor(Color.darkGray);

            }
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2.drawString(x, c * size+10, r * size+20);

        }
    }

    public void click() {
        isRevealed = true;
    }

    public boolean getIsMine() {
        return isMine;
    }




    }

