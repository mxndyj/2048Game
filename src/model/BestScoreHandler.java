package model;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;


/**
 * The BestScoreHandler class manages the reading and writing of the best score
 * to a file.
 */

public class BestScoreHandler {
	
	 private static final String FILE_NAME = "score.txt";
	 
	    public static int getBestScore() {
	        int score = 0;
	        try {
	            Scanner reader = new Scanner(new File(FILE_NAME));
	            if (reader.hasNextInt()) {
	                score = reader.nextInt();
	            }
	            
	            reader.close();
	        } 
	        catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "No File Found To Read The Best Score.");
	        }
	        return score;
	    }
	    
	    /**
	     * 
	     * 
	     * @param score - players score from playing game 
	     * @pre score is a positive value
	     */
	    public static void setBestScore(int score) {
	    	
	        try {
	            FileWriter writer = new FileWriter(FILE_NAME);
	            writer.write("" + score);
	            writer.close();
	            
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "No File Found To Write The Best Score.");
	        }
	    }
}
