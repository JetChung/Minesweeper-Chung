import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//TODO Put your name here.

public class MineSweeper extends JPanel {
    private Square[][] board;

    public static final int SIZE = 30;

    public int numbBombs = 20;
    public int maxR = 15;
    public int maxC = 15;

    public MineSweeper(int width, int height) {
        setSize(width, height);

        board = new Square[maxR][maxC];
        int currentNumBombs = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                board[i][j] = new Square(false, i, j, board);

            }
        }

        while (currentNumBombs <= numbBombs) {
            int x = (int) (Math.random() * maxR);
            int y = (int) (Math.random() * maxC);
            if (!board[x][y].getIsMine()){
                board[x][y] = new Square(true, x, y, board);
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
                for (int i = rowOfCell-1; i <= rowOfCell + 1; i++) {
                    for (int j = colOfCell - 1; j <= colOfCell + 1; j++) {
                        if (i >= 0 && i < maxR)  {
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


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++) {
                board[r][c].draw(g2);
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

                board[r][c].click();

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
        JFrame window = new JFrame("Minesweeper");
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
