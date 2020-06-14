package hangmanGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


/*
 * Build a simple Hangman Game with Animals
 * @author Nhien Lam
 */
public class Hangman 
{
	//Max number of errors before the user lose
	public static final int MAX_ERRORS = 6;
	
	public static final String[] ANIMALS = {"panda", "housecat", "moth",
	                                        "water buffalo", "caterpillar",
	                                        "duck", "mouse", "goose", "rat",
	                                        "horse", "koala", "black bear",
	                                       "hippopotamus", "camel", "giraffe",
	                                       "bald eagle", "crocodile","dog", "bird", "penguin",
	                                        "bat", "dolphin", "hamster", "chameleon",
	                                        "swordfish", "groundhog", "cricket",
	                                        "clownfish", "leopard", "rabbit", "buffalo",
	                                        "shark", "wombat",
	                                        "kitten", "reindeer", "wolf",
	                                        "dragon", "blue whale",
	                                        "unicorn", "tuna", "platypus",
	                                       "basset", "hound", "shrimp", "skunk",
	                                        "bulldog", "ladybug", "beaver",
	                                        "tortoise", "jellyfish",
	                                        "puppy", "weasel", "owl", "chipmunk", "lamb", "tiger",
	                                       "ape", "sheep", "dog", "cow", "spider",
	                                      "turtle", "alligator", "seahorse", "llama", "lion", "armadillo","three toed sloth",
	                                       "elk", "fox", "guinea pig", "sea lion", "donkey", "frog", "crow",
	                                       };	
	
	
	public static final Random RANDOM = new Random();

	// The word to find
	private String wordToFind;
	
	// The progression of the user answer
	private char[] playerAnswer;
	
	// Letters already entered by user
	private Set<String> usedLetter  = new HashSet<>();
	
	// The number of errors that the user made
	private int errorsMade;
	
	
	/**
	 * 	Returns a random word that the user need to find
	 */
	public String nextWordToFind()
	{
		return ANIMALS[RANDOM.nextInt(ANIMALS.length)];
	}
	
	/**
	 * 	Starts a new game/question
	 */
	public void newGame()
	{
		// Computes the next word to find
		wordToFind = nextWordToFind();
		
		// Resets the number of errors to 0
		errorsMade = 0;
		
		// Resets the letters that the user used
		usedLetter.clear();
		
		// Initializes the playerAnswer to show the progression of the word
		playerAnswer = new char[wordToFind.length()];
		
		for(int i = 0; i < playerAnswer.length; i++)
		{
			playerAnswer[i] = '_';
		}
		
		System.out.println(answerProgression());
	}
	
	/**
	 * Checks if the answer of the user is the same as the finding word
	 * @return True if the answer of the user is the same as the finding word, False otherwise
	 */
	public boolean foundCorrectAnswer()
	{
		return wordToFind.contentEquals(new String(playerAnswer));
	}
	
	/**
	 * Returns the String that shows the progression of the user
	 */
	public String answerProgression()
	{
		StringBuilder  progression = new StringBuilder();
		
		for(int i = 0; i < playerAnswer.length; i++)
		{
			progression.append(playerAnswer[i]);
			
			// Adds space " " between each letter
			if(i < playerAnswer.length-1)
			{
				progression.append(" ");
			}
		}
		
		return progression.toString();
	}
	 
	/**
	 * Updates the state of the playerAnswer array when the user enters a letter.
	 * @param letter the letter that the user enters
	 */
	public void enter(String letter)
	{
		// we update only if c has not already been entered
		if(!usedLetter.contains(letter))
		{
			// Checks if wordToFind contains c
			if(wordToFind.contains(letter))
			{
				// if so, we replace _ by the character c
				int index = wordToFind.indexOf(letter);
				
				while(index >= 0)
				{
					playerAnswer[index] = letter.charAt(0);
					index = wordToFind.indexOf(letter, index + 1);
				}
			}
			else
			{
				System.out.println("Wrong");
				errorsMade++;
			}
		}
		else
		{
			System.out.println("'" + letter + "'" + " HAS ALREADY BEEN USED");
		}
		
		// Adds c into the list of used letters
		usedLetter.add(letter);
		System.out.println("Used Letters:  " + usedLetter.toString());
	}
	
	// The user plays
	public void play()
	{
		Scanner in = new Scanner(System.in);
		
		while(errorsMade < MAX_ERRORS)
		{
			System.out.println("Enter a letter: ");
			String str = in.nextLine();
			
			//Checks if the user input a valid character
			if(str.length()>1)
			{
				str = str.substring(0,1);
			}
			
			// Update the progression with the user's input
			enter(str);
			
		    // display current state
			System.out.println("\n"  + answerProgression());
			
			//Checks if the word is found
			if(foundCorrectAnswer())
			{
				System.out.println("Congratulations!!! You win!");
				break;
			}
			else
			{
				System.out.println("You still have " + (MAX_ERRORS-errorsMade) + " trials left");
			}
			
			if(errorsMade == MAX_ERRORS)
			{
				System.out.println("You Lose!!! Good luck next time!");
				System.out.println("The word to find was: " + wordToFind);
			}
			System.out.println("\n "+ "***********************" + "\n");
		}
		
	}

	
	public static void main(String[] args)
	{
		System.out.println("Welcome to the RKN's Hangman Game");
		System.out.println();
		Hangman hm = new Hangman();
		hm.newGame();
		hm.play();
	}
}
