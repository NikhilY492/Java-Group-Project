import java.awt.*;

public class Ball {
    private int x, y;
    private int xVelocity = 5, yVelocity = 5;
    private final int SIZE = 20;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
        if (y < 0 || y > 580) {
            yVelocity = -yVelocity;
        }
    }

    public void checkCollision(Paddle p1, Paddle p2) {
        if (x < p1.getX() + p1.getWidth() && y > p1.getY() && y < p1.getY() + p1.getHeight()) {
            xVelocity = -xVelocity;
        }
        if (x > p2.getX() - p2.getWidth() && y > p2.getY() && y < p2.getY() + p2.getHeight()) {
            xVelocity = -xVelocity;
        }
    }

    public void resetPosition() {
        x = 400;
        y = 300;
        xVelocity = -xVelocity;
    }

    public int getX() {
        return x;
    }

    public int getSize() {
        return SIZE;
    }
}
