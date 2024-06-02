import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private JButton rockPaperScissorsButton;
    private JButton pingPongButton;

    public MainMenu() {
        setTitle("Game Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("Welcome! Choose a game to play:", JLabel.CENTER);
        add(welcomeLabel);

        rockPaperScissorsButton = new JButton("Rock, Paper, Scissors");
        pingPongButton = new JButton("Ping Pong");

        rockPaperScissorsButton.addActionListener(this);
        pingPongButton.addActionListener(this);

        add(rockPaperScissorsButton);
        add(pingPongButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rockPaperScissorsButton) {
            new RockPaperScissorsGUI();
        } else if (e.getSource() == pingPongButton) {
            new PingPongGame();
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
