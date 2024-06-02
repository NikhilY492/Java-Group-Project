import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {
    private enum Choice {
        ROCK, PAPER, SCISSORS
    }

    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JLabel infoLabel;
    private int userWins = 0;
    private int computerWins = 0;
    private int rounds = 0;
    private final int TOTAL_ROUNDS = 5;
    private Random random = new Random();

    public RockPaperScissorsGUI() {
        try {
            setTitle("Rock, Paper, Scissors");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            infoLabel = new JLabel("Best of " + TOTAL_ROUNDS + " match", JLabel.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            add(infoLabel, gbc);

            resultLabel = new JLabel("Make your choice!", JLabel.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            add(resultLabel, gbc);

            scoreLabel = new JLabel("Score - You: 0 | Computer: 0", JLabel.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            add(scoreLabel, gbc);

            rockButton = new JButton("Rock");
            paperButton = new JButton("Paper");
            scissorsButton = new JButton("Scissors");

            rockButton.addActionListener(this);
            paperButton.addActionListener(this);
            scissorsButton.addActionListener(this);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            add(rockButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            add(paperButton, gbc);

            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            add(scissorsButton, gbc);

            setVisible(true);
        } catch (Exception e) {
            showError("Error initializing Rock, Paper, Scissors game: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Choice userChoice;
            if (e.getSource() == rockButton) {
                userChoice = Choice.ROCK;
            } else if (e.getSource() == paperButton) {
                userChoice = Choice.PAPER;
            } else {
                userChoice = Choice.SCISSORS;
            }

            Choice computerChoice = getComputerChoice();
            determineWinner(userChoice, computerChoice);
        } catch (Exception ex) {
            showError("Error processing choice: " + ex.getMessage());
        }
    }

    private Choice getComputerChoice() {
        int randomIndex = random.nextInt(Choice.values().length);
        return Choice.values()[randomIndex];
    }

    private void determineWinner(Choice userChoice, Choice computerChoice) {
        try {
            String resultMessage;

            rounds++;
            if (userChoice == computerChoice) {
                resultMessage = "It's a tie! Both chose " + userChoice;
            } else if (isUserWinner(userChoice, computerChoice)) {
                userWins++;
                resultMessage = "You win this round! " + userChoice + " beats " + computerChoice;
            } else {
                computerWins++;
                resultMessage = "Computer wins this round! " + computerChoice + " beats " + userChoice;
            }

            resultLabel.setText(resultMessage);
            scoreLabel.setText("Score - You: " + userWins + " | Computer: " + computerWins);

            if (rounds >= TOTAL_ROUNDS) {
                String finalResult = (userWins > computerWins) ? "Congratulations! You win the game!" : "Sorry! Computer wins the game!";
                JOptionPane.showMessageDialog(this, finalResult, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            }
        } catch (Exception e) {
            showError("Error determining winner: " + e.getMessage());
        }
    }

    private boolean isUserWinner(Choice userChoice, Choice computerChoice) {
        return (userChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
               (userChoice == Choice.PAPER && computerChoice == Choice.ROCK) ||
               (userChoice == Choice.SCISSORS && computerChoice == Choice.PAPER);
    }

    private void resetGame() {
        try {
            userWins = 0;
            computerWins = 0;
            rounds = 0;
            resultLabel.setText("Make your choice!");
            scoreLabel.setText("Score - You: 0 | Computer: 0");
        } catch (Exception e) {
            showError("Error resetting game: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
