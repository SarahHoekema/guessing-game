//Sarah Hoekema
//CS 145
//September 27, 2022
//Lab 1: Guessing Game

import java.util.*;

//This program allows the user to play a simple guessing game where
//the program produces a random integer within a given range and allows
//the user to make guesses until the user guesses the correct integer.
//The user is then given the option to play more games or stop playing
//and view their results.
public class GuessingGame {
   private static final int MAX_GUESS = 100;
   public static void main (String[] args){
      //construct objects
      Scanner console = new Scanner(System.in);
      Random rand = new Random();
            
      //initialize variables
      int totalGames = 0;
      int totalGuesses = 0;
      int bestGame = 9999;

      //output introduction
      introduction();
      
      //enter a round of the guessing game
      do{
         //increment total games and reset guess number
         totalGames++;                        
         int guesses = 0;
         
         //play the game
         guesses = playGame(guesses, rand, console);
         
         //add guess number to total guess number
         totalGuesses += guesses;
         
         //evaluate if latest game was a new best game
         if(guesses < bestGame){
            bestGame = guesses;
         }  
         
      } while(promptReplay(console).equals("y")); 
      
      //print results of game
      printResults(totalGames, totalGuesses, bestGame);      
   }
   //outputs the introduction to the guessing game
   public static void introduction(){
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and ");
      System.out.println(MAX_GUESS + " and will allow you to guess until");
      System.out.println("you get it.  For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
      System.out.println();
   }
   //passes in the integer guesses and plays a single round of the guessing game,
   //then returns the number of guesses made in that game
   public static int playGame(int guesses, Random rand, Scanner console){
      int guessNum = 0;
      System.out.println("I'm thinking of a number between 1 and " + MAX_GUESS + "...");
      int answer = rand.nextInt(MAX_GUESS)+1;

      while(guessNum != answer && guesses != 9999){
         guesses++;
         System.out.print("Your guess? ");
         guessNum = console.nextInt();
         if(guessNum > answer){
            System.out.println("It's lower.");
         } else if(guessNum < answer){
            System.out.println("It's higher.");
         }           
      }
      
      if(guesses == 1){
         System.out.println("You got it right in 1 guess");        
      } else if(answer == guessNum){
         System.out.println("You got it right in " + guesses + " guesses");
      } else{
         System.out.println("You have reached 9,999 guesses, which is the maximum amount allowed.");
      }

      return guesses;
   }
   //passes in the Scanner console, prompts the user if they want to play the game again,
   //and accepts answers starting with "y" or "n"
   public static String promptReplay(Scanner console){   
      System.out.print("Do you want to play again? ");
      String firstLetter = console.next().substring(0,1).toLowerCase();  
      while(!firstLetter.equals("y") && !firstLetter.equals("n")){
         System.out.print("Answer not accepted. Please try again. ");
         firstLetter = console.next().substring(0,1).toLowerCase();
      }
      System.out.println();
      return firstLetter;
   }
   //passes in the integers totalGames, totalGuesses and bestGame and
   //prints the results of the guessing game
   public static void printResults(int totalGames, int totalGuesses, int bestGame){
      System.out.println("Overall results:");  
      System.out.printf("    %-14s= %d%n    %-14s= %d%n    %-14s= %.1f%n    %-14s= %d%n",
                         "total games", totalGames,
                         "total guesses", totalGuesses, 
                         "guesses/game", ((double)totalGuesses/(double)totalGames),
                         "best game", bestGame);  
   }  
}