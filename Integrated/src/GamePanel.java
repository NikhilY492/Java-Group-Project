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
        try {
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);
            
            player1 = new Paddle(30, 250);
            player2 = new Paddle(740, 250);
            ball = new Ball(400, 300);
            
            timer = new Timer(DELAY, this);
        } catch (Exception e) {
            showError("Error initializing game panel: " + e.getMessage());
        }
    }

    public void startGame() {
        try {
            score1 = 0;
            score2 = 0;
            requestFocusInWindow();
            timer.start();
        } catch (Exception e) {
            showError("Error starting game: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("Player 1:   " + score1, 100, 50);
            g.drawString("Player 2:   " + score2, 600, 50);

            player1.draw(g);
            player2.draw(g);
            ball.draw(g);
        } catch (Exception e) {
            showError("Error painting component: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
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
            if (score1 == 5 || score2 == 5) {
                timer.stop();
                String winner = score1 == 5 ? "Player 1" : "Player 2";
                JOptionPane.showMessageDialog(this, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
            repaint();
        } catch (Exception ex) {
            showError("Error updating game state: " + ex.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            player1.keyPressed(e);
            player2.keyPressed(e);
        } catch (Exception ex) {
            showError("Error processing key press: " + ex.getMessage());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            player1.keyReleased(e);
            player2.keyReleased(e);
        } catch (Exception ex) {
            showError("Error processing key release: " + ex.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
