
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


//TODO: add the timer/counter, (don't) fix bug w/ flags
public class Square {

    private boolean isMine, isRevealed;
    private int neighborMines;
    private int r, c;
    private int numbBombs;
    private boolean isThisLoss = false;
    private boolean flagged = true;
    private boolean smileMore = true;
    private boolean iAmTheOne = false;
    private boolean wrong = false;
    private boolean readyToDie = false;

    public boolean isReadyToDie() {
        return readyToDie;
    }

    public void setReadyToDie(boolean readyToDie) {
        this.readyToDie = readyToDie;
    }

    public Square(boolean isMine, int r, int c, Square[][] board, boolean flagged) {
        this.isMine = isMine;
        this.r = r;
        this.c = c;
        this.isRevealed = false;
        this.flagged = flagged;

        neighborMines = 0;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public int getNeighborMines() {
        return neighborMines;
    }

    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }

    public boolean isiAmTheOne() {
        return iAmTheOne;
    }

    public void setiAmTheOne(boolean iAmTheOne) {
        this.iAmTheOne = iAmTheOne;
    }

    public void drawRedSquare(Graphics2D g2, int r, int c){
        int size = MineSweeper.SIZE;
        g2.setColor(Color.RED);
        g2.fillRect(c * size, r * size, size, size);

    }
    public void draw(Graphics2D g2) {
        int size = MineSweeper.SIZE;


        if (wrong && readyToDie){
            System.out.println("spagetti");
            g2.setColor(Color.lightGray);
            g2.fillRect(c * size, r * size, size, size);
            g2.setColor(Color.RED);
            g2.drawLine(c*size, r*size, c*size+size, r*size+size);
            g2.drawLine(r*size, c*size, r*size+size, c*size+size);
        }
        if (isRevealed) {

            if (isMine) {
                smileMore = false;
                if (iAmTheOne) {
                    g2.setColor(Color.RED);
                }
                else{
                    g2.setColor(Color.lightGray);
                }
                try {
                    BufferedImage image1;
                    smileMore = false;

                    image1 = ImageIO.read(new File("res/" + "dab.jpg"));
                    g2.drawImage(image1, 195, 465, null);
                    smileMore = false;

                } catch (Exception e) {
                    e.printStackTrace();
                }
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


        if (isRevealed && !isMine) {
            if (neighborMines > 0) {
                String x = String.valueOf(neighborMines);
                if (neighborMines == 1) {
                    g2.setColor(Color.BLUE);
                } else if (neighborMines == 2) {
                    g2.setColor(Color.GREEN);
                } else if (neighborMines == 3) {
                    g2.setColor(Color.RED);
                } else if (neighborMines == 4) {
                    g2.setColor(new Color(113, 0, 192));

                } else if (neighborMines == 5) {
                    g2.setColor(new Color(146, 0, 34));
                } else if (neighborMines == 6) {
                    g2.setColor(new Color(28, 138, 142));

                } else if (neighborMines == 7) {
                    g2.setColor(new Color(0, 0, 0));

                } else if (neighborMines == 8) {
                    g2.setColor(Color.darkGray);

                }
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g2.drawString(x, c * size + 10, r * size + 20);



            }
        }
    }


    public void click() {
        isRevealed = true;

    }


    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged() {
        flagged = !flagged;
    }

    public void revealCell(Square[][] board) {

        isRevealed = true;
        if(getIsMine()){
            isThisLoss = true;

        }
        if (getNeighborMines() == 0) {
            for (int row = r - 1; row <= r + 1; row++) {
                for (int col = c - 1; col <= c + 1; col++) {
                    if (row < board.length && row >= 0) {
                        if (col < board[0].length && col >= 0) {
                            if (!board[row][col].isRevealed) {
                                board[row][col].revealCell(board);

                            }
                        }
                    }
                }
            }
        }



    }


    public boolean getIsMine() {
        return isMine;
    }

    public boolean isWrong() {
        return wrong;
    }

    public void setWrong(boolean wrong) {
        this.wrong = wrong;
    }
}

