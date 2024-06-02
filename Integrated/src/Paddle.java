import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y;
    private int yVelocity = 0;
    private final int WIDTH = 10, HEIGHT = 100;
    private final int SPEED = 5;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void move() {
        y += yVelocity;
        if (y < 0) y = 0;
        if (y > 500) y = 500;
    }

    public void keyPressed(KeyEvent e) {
        if (x < 400) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                yVelocity = -SPEED;
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                yVelocity = SPEED;
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                yVelocity = -SPEED;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                yVelocity = SPEED;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (x < 400) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                yVelocity = 0;
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                yVelocity = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
