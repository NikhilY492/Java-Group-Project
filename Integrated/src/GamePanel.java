import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private final int DELAY = 10;
    private Paddle player1, player2;
    private Ball ball;
    private int score1, score2;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        player1 = new Paddle(30, 250);
        player2 = new Paddle(740, 250);
        ball = new Ball(400, 300);

        timer = new Timer(DELAY, this);
    }

    public void startGame() {
        score1 = 0;
        score2 = 0;
        requestFocusInWindow();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawString("Player 1: " + score1, 100, 50);
        g.drawString("Player 2: " + score2, 600, 50);

        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (score1 >= 5 || score2 >= 5) {
            timer.stop();
            String winner = (score1 >= 5) ? "Player 1" : "Player 2";
            int option = JOptionPane.showOptionDialog(this,
                winner + " wins! Would you like to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                null);

            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) {
                    window.dispose();
                }
            }
        } else {
            player1.move();
            player2.move();
            ball.move();
            ball.checkCollision(player1, player2);
            if (ball.getX() < 0) {
                score2++;
                ball.resetPosition();
            }
            if (ball.getX() > getWidth() - ball.getSize()) {
                score1++;
                ball.resetPosition();
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player1.keyPressed(e);
        player2.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
        player2.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void resetGame() {
        score1 = 0;
        score2 = 0;
        ball.resetPosition();
        timer.start();
    }
}
