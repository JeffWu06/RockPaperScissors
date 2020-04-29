/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jeff
 */
public class RockPaperScissors {
    
    public static void main(String[] args) {
        // Variable declarations
        final int MAX_ROUNDS = 10;
        final int MIN_ROUNDS = 1;
        Scanner in = new Scanner(System.in);
        Random chooser = new Random();
        boolean inputValid; // To use in validating user input
        boolean keepPlaying = true; // Initiate the game loop or not
        String userInput; // To initially store user input as String
        int numberOfRounds = 0; // Number of Rounds user desires to play
        /* Counters for rounds played, what the user and computer choose in a 
        round, the result of a given round,
        and the number of wins in a round for each the user and computer, and 
        the number of ties.
        */          
        int roundsPlayed, userWins, computerWins, noWins, userChoice = 0, 
                computerChoice, roundResult;
      
        // Array to get the String version of numeric choice
        final String [] choiceNames = {"ROCK", "PAPER", "SCISSORS"};
        
        /*Enter game loop. Will continue until keepPlaying is false (which can 
        be changed by user prompt at end of loop code block.
        */        
        do {
            inputValid = false;
            System.out.println("***LET'S PLAY ROCK, PAPER, SCISSORS!***\n");
        
        /* Ask user to specify how many rounds to play in the game. User must 
            enter an integer greater than or equal to 1 and less than or equal 
            to 10, or else the program will print an error message and end.
        */
        while (!inputValid) {
            System.out.println("How many rounds would you like to play? (Can play at"
                    + " least 1, but no more than 10.)");
            userInput = in.nextLine();
            if (!checkIfInt(userInput)) {
                System.out.println("Error: Invalid input.");
            } else {
                numberOfRounds = Integer.parseInt(userInput);
                if (numberOfRounds < 1 || numberOfRounds > 10) {
                    System.out.println("Error: Invalid input.");
                } else {
                    inputValid = true;
                }
            }
        }
        
        // Print message indicating game start. Initialize win counters to 0.
        System.out.println("Okay, will play " + numberOfRounds + " rounds this game.\n");
        System.out.println("LET'S PLAAAAAAY!");
        userWins = 0;
        computerWins = 0;
        noWins = 0;
        
        // Play the game. For loop to iterate numberOfRounds times.
        for (roundsPlayed = 0; roundsPlayed < numberOfRounds; roundsPlayed++) {
            System.out.println("\n***ROUND " + (roundsPlayed + 1) + "***\n");
                        
            /*Calls validateAndProcessUserInput method to ask user to enter 1, 
            2, or 3 (for rock, paper, or scissors, respectively). Resulting 
            value stored to userChoice.
            */
            userChoice = validateAndProcessUserInput(inputValid, "Which would "
                    + "you like to play? (enter 1 for Rock, 2 for Paper, 3 for "
                    + "Scissors)", choiceNames.length, 1);
            
            /*Print corresponding name of userChoice. Generate value from 1-3
            for computerChoice and calculate gameResult.
            */
            System.out.println("\nPlayer has selected..." + 
                    choiceNames[userChoice - 1]);
            computerChoice = chooser.nextInt(3) + 1;
            System.out.println("Computer has selected..." + 
                    choiceNames[computerChoice - 1] + "\n");
            roundResult = userChoice - computerChoice;
            
            /*Based on value of roundResult, declare who wins the round or if 
            a tie, and increment win counters accordingly.
            */
            switch (roundResult) {
                case -1: // rock v paper 
                case 2: // paper v scissors
                    computerWins++;
                    System.out.println("Computer wins Round " + (roundsPlayed 
                            + 1) + "!");                    
                    break;
                case -2: // rock v scissors
                case 1: // scissors v paper
                    userWins++;
                    System.out.println("Player wins Round " + (roundsPlayed 
                            + 1) + "!");                    
                    break;
                default: // rock v rock, paper v paper, scissors v scissors
                    noWins++;
                    System.out.println("No winner this round.");                    
                    break;
            }
        }
        
            // Print the results of the game and declare an overall winner.
            System.out.println("\nRESULTS:");
            System.out.println(userWins + " rounds were taken by the Player,");
            System.out.println(computerWins + " rounds were taken by the Computer,");
            System.out.println(noWins + " rounds had no winner.\n");
            System.out.println("***" + declareWinner(userWins, computerWins) + 
                    " wins the game***");
            
            /* Ask user if they want to play again. If not, change keepPlaying 
            to false to exit the game while loop.
            */
            inputValid = false;
            while(!inputValid) {
                System.out.println("Good game! Would you like to play again? "
                        + "(Yes or no): ");
                userInput = in.nextLine();
                
                if (userInput.equalsIgnoreCase("No")) {
                    keepPlaying = false;
                    inputValid = true;
                } else if (userInput.equalsIgnoreCase("Yes")) {
                    inputValid = true;
                } else {
                    inputValid = false;
                    System.out.println("\n Invalid response. Please try again.\n");
                }
            }
        } while (keepPlaying);
        
        System.out.println("Thanks for playing!");
    }
    
    /** 
     *Determines if String input can be parsed to type int.
     * @param input
     * @return true if input can be parsed as int, false if not.
     */
    public static boolean checkIfInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException x) {
            return false;
        }
    }
    
    /**
     * Determines if userScore or computerScore is greater.
     * @param userScore
     * @param computerScore
     * @return Game winner String name
     */
    public static String declareWinner(int userScore, int computerScore) {
        if (userScore > computerScore) {
            return "Player";
        } else if (userScore < computerScore) {
            return "Computer";
        } else {
            return "No one";
        }
    }
    
    /**
     *Checks if user String input can be converted to int and if so, whether 
     * int is within the acceptable range of values (between upperLimit and 
     * lowerLimit. If not, prompts user to provide new input which will be
     * tested against parameters again.
     * @param inputStatus
     * @param userPrompt
     * @param upperLimit
     * @param lowerLimit
     * @return User's original console input as int type.
     */
    public static int validateAndProcessUserInput(boolean inputStatus, 
            String userPrompt, int upperLimit, int lowerLimit) {
        Scanner sc = new Scanner(System.in);
        String userInfo;
        int relevantInt = 0;        
        do {
            System.out.println(userPrompt);
            userInfo = sc.nextLine();
            if (!checkIfInt(userInfo)) {
                System.out.println("\n Invalid response. Please try again.\n");
            } else {
                relevantInt = Integer.parseInt(userInfo);
                if (relevantInt > upperLimit || relevantInt <= (lowerLimit - 1)) {
                    System.out.println("\n Invalid response. Please try again.\n");
                } else {
                    inputStatus = true;
                }
            }
        } while (!inputStatus);
        return relevantInt;
    }
}
