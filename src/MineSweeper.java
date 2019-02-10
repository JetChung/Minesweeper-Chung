import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

//TODO Put your name here.

public class MineSweeper extends JPanel {
    private Square[][] board;

    public static final int SIZE = 30;

    public int numbBombs = 20;
    public int maxR = 15;
    public int maxC = 15;
    public int[] bombsR = new int[numbBombs];
    public int[] bombsC = new int[numbBombs];

    public ArrayList<Integer> flagsR = new ArrayList<Integer>();
    public ArrayList<Integer> flagsC = new ArrayList<Integer>();

    private boolean smileMore = true;
    private boolean isThisLoss = false;

    public MineSweeper(int width, int height) {
        setSize(width, height);

        board = new Square[maxR][maxC];
        int currentNumBombs = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                board[i][j] = new Square(false, i, j, board,false);

            }
        }

        while (currentNumBombs < numbBombs) {
            int x = (int) (Math.random() * maxR);
            int y = (int) (Math.random() * maxC);
            if (!board[x][y].getIsMine()){
                board[x][y] = new Square(true, x, y, board,false);
                currentNumBombs++;

            }

        }



        //Here is a good spot to calc each Square's neighborMines
        //Maybe write a method in Square that finds how many
        //mines are around it, and call that method on each Square here.



        int[][] numNeighbors = new int[maxR][maxC];

        for (int rowOfCell = 0; rowOfCell < maxR; rowOfCell++) {
            for (int colOfCell = 0; colOfCell < maxC; colOfCell++) {
                int num = 0;
                for (int i = rowOfCell - 1; i <= rowOfCell + 1; i++) {
                    for (int j = colOfCell - 1; j <= colOfCell + 1; j++) {
                        if (i >= 0 && i < maxR) {
                            if (j >= 0 && j < maxC) {
                                if (board[i][j].getIsMine()) {
                                    num += 1;
                                }
                            }
                        }
                    }
                }
                if (board[rowOfCell][colOfCell].getIsMine()) {
                    num -= 1;
                }
                numNeighbors[rowOfCell][colOfCell] = num;
                board[rowOfCell][colOfCell].setNeighborMines(num);
            }
        }



        setupMouseListener();
    }


    public void revealAllBombs(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getIsMine() && !board[i][j].isFlagged()) {
                    board[i][j].click();
                    board[i][j].setReadyToDie(true);
                }
                if (!board[i][j].getIsMine() && board[i][j].isFlagged()){
                    System.out.println("wrong");
                    board[i][j].isWrong();
                    board[i][j].setReadyToDie(true);


                }
            }

        }



    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        for (int r: flagsR){
            for (int c: flagsC){
                g2.setColor(Color.RED);
                g2.drawLine(c*SIZE, r*SIZE, c*SIZE+SIZE, r*SIZE+SIZE);
                g2.drawLine(r*SIZE, c*SIZE, r*SIZE+SIZE, c*SIZE+SIZE);

            }
        }
        if (smileMore) {
            BufferedImage image;
            try {
                image = ImageIO.read(new File("res/" + "smile.png"));
                g2.drawImage(image, 195, 465, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{

        }

        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++) {
                board[r][c].draw(g2);
                if (board[r][c].getIsMine() && board[r][c].isRevealed()) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("res/" + "bomb.png"));
                        g2.drawImage(image, c * SIZE, r * SIZE, null);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (board[r][c].isFlagged()) {
                    BufferedImage image1;
                    try {
                        image1 = ImageIO.read(new File("res/" + "flag.png"));
                        g2.drawImage(image1, c * SIZE, r * SIZE, null);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }






    }





    public void setupMouseListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();

                int r = y / SIZE;
                int c = x / SIZE;

                if (e.getButton() == MouseEvent.BUTTON1) {

                    if (!isThisLoss) {
                        board[r][c].revealCell(board);
                        if (board[r][c].getIsMine()) {
                            isThisLoss = true;
                            board[r][c].setiAmTheOne(true);
                            revealAllBombs();
                        }
                    }
                } else {
                    if (!isThisLoss && !board[r][c].isRevealed()) {

                        board[r][c].setFlagged();
                        if (!board[r][c].getIsMine()){
                            flagsR.add(r);
                            flagsC.add(c);
                        }
                    }

                }
                repaint();

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    //sets ups the panel and frame.  Probably not much to modify here.
    public static void main(String[] args) {
        JFrame window = new JFrame("CHUNGULATION");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        MineSweeper panel = new MineSweeper(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }

}
