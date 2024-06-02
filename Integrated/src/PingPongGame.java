import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PingPongGame extends JFrame implements ActionListener {
    private JButton startButton;
    private GamePanel gamePanel;

    public PingPongGame() {
        setTitle("Ping Pong Game");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        startButton = new JButton("Start Game");
        startButton.setBounds(350, 250, 100, 50);
        startButton.addActionListener(this);
        add(startButton);

        gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, 800, 600);
        gamePanel.setVisible(false);
        add(gamePanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startButton.setVisible(false);
        gamePanel.setVisible(true);
        gamePanel.startGame();
    }
}
