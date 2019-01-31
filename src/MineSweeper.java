import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//TODO Put your name here.

public class MineSweeper extends JPanel {
    private Square[][] board;

    public static final int SIZE = 30;

    public int numbBombs = 20;

    public MineSweeper(int width, int height) {
        setSize(width, height);

        board = new Square[15][15];
        int currentNumBombs = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = new Square(false, i, j, board);

            }
        }
        while (currentNumBombs <= numbBombs) {
            int x = (int) (Math.random() * 15);
            int y = (int) (Math.random() * 15);
            board[x][y] = new Square(true, x, y, board);


        }



        //Here is a good spot to calc each Square's neighborMines
        //Maybe write a method in Square that finds how many
        //mines are around it, and call that method on each Square here.


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
