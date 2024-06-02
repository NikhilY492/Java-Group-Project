import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private JButton rockPaperScissorsButton;
    private JButton pingPongButton;

    public MainMenu() {
        try {
            setTitle("DuoDash");
            setSize(500, 400);
               setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JPanel titlePanel = new JPanel();
            titlePanel.setBackground(Color.DARK_GRAY);
            JLabel titleLabel = new JLabel("DuoDash");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
            titleLabel.setForeground(Color.WHITE);
            titlePanel.add(titleLabel);
            add(titlePanel, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            rockPaperScissorsButton = new JButton("Rock, Paper, Scissors");
            pingPongButton = new JButton("Ping Pong");

            rockPaperScissorsButton.setFont(new Font("Arial", Font.PLAIN, 18));
            pingPongButton.setFont(new Font("Arial", Font.PLAIN, 18));

            rockPaperScissorsButton.setPreferredSize(new Dimension(250, 50));
            pingPongButton.setPreferredSize(new Dimension(250, 50));

            rockPaperScissorsButton.addActionListener(this);
            pingPongButton.addActionListener(this);

            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(rockPaperScissorsButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            buttonPanel.add(pingPongButton, gbc);

            add(buttonPanel, BorderLayout.CENTER);

            JPanel footerPanel = new JPanel();
            footerPanel.setBackground(Color.DARK_GRAY);
            JLabel footerLabel = new JLabel("Select a game to play");
            footerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            footerLabel.setForeground(Color.WHITE);
            footerPanel.add(footerLabel);
            add(footerPanel, BorderLayout.SOUTH);

            setVisible(true);
        } catch (Exception e) {
            showError("Error initializing main menu: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == rockPaperScissorsButton) {
                new RockPaperScissorsGUI();
            } else if (e.getSource() == pingPongButton) {
                new PingPongGame();
            }
        } catch (Exception ex) {
            showError("Error starting game: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
