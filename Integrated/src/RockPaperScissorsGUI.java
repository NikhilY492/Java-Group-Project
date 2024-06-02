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
    private int userWins = 0;
    private int computerWins = 0;
    private Random random = new Random();

    public RockPaperScissorsGUI() {
        setTitle("Rock, Paper, Scissors");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change to DISPOSE_ON_CLOSE
        setLayout(new GridLayout(4, 1));

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        resultLabel = new JLabel("Make your choice!", JLabel.CENTER);
        scoreLabel = new JLabel("Score - You: 0 | Computer: 0", JLabel.CENTER);

        add(resultLabel);
        add(rockButton);
        add(paperButton);
        add(scissorsButton);
        add(scoreLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    private Choice getComputerChoice() {
        int randomIndex = random.nextInt(Choice.values().length);
        return Choice.values()[randomIndex];
    }

    private void determineWinner(Choice userChoice, Choice computerChoice) {
        String resultMessage;

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

        if (userWins == 3 || computerWins == 3) {
            String finalResult = (userWins > computerWins) ? "Congratulations! You win the game!" : "Sorry! Computer wins the game!";
            JOptionPane.showMessageDialog(this, finalResult, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }

    private boolean isUserWinner(Choice userChoice, Choice computerChoice) {
        return (userChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
               (userChoice == Choice.PAPER && computerChoice == Choice.ROCK) ||
               (userChoice == Choice.SCISSORS && computerChoice == Choice.PAPER);
    }

    private void resetGame() {
        userWins = 0;
        computerWins = 0;
        resultLabel.setText("Make your choice!");
        scoreLabel.setText("Score - You: 0 | Computer: 0");
    }
}
