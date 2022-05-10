/**
 * Documentation 'n Stuff
 * 
 * @author alex
 */

// Package and Imports
package com.alex.game;

import javax.swing.JFrame;
import java.awt.CardLayout;
import com.alex.screens.LoadingScreen;
import com.alex.levels.Level1;
import java.awt.Dimension;


// Game class
public class Game {
    
    // Variables
    public static final int win_width = 800;
    public static final int win_height = 800;
    public static final String win_title = "Dungeon Crawler";
    
    // Game window
    private JFrame gameWindow;
    private LoadingScreen startScreen;
    
    // Levels
    private Level1 lvl1;
    
    // Variables to track over different levels
    private int lives;
    
    // Class constructor
    public Game(){
    
        initWindow();
        initScreens();
        
        this.lives = 3;
    
    }
    
    private void initScreens(){
    
        this.startScreen = new LoadingScreen(this);
        this.startScreen.setPreferredSize(new Dimension(this.win_width, this.win_height));
        
        // Levels
        this.lvl1 = new Level1(this);
        this.lvl1.setPreferredSize(new Dimension(this.win_width, this.win_height));
        
        this.gameWindow.getContentPane().add(this.startScreen, "StartScreen");
        this.gameWindow.getContentPane().add(this.lvl1, "Level1");
        this.gameWindow.setVisible(true);
    
    }
    
    private void initWindow(){
    
        // Initiate window + set properties
        this.gameWindow = new JFrame();
        
        this.gameWindow.setSize(this.win_width,this.win_height);
        this.gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameWindow.getContentPane().setLayout(new CardLayout());
        this.gameWindow.setResizable(false);
        this.gameWindow.setLocationRelativeTo(null);
        
        this.gameWindow.setTitle(this.win_title);
        this.gameWindow.setVisible(true);
    
    }
    
    public void start(){
    
        CardLayout cl = (CardLayout) this.gameWindow.getContentPane().getLayout();
        cl.next(this.gameWindow.getContentPane());
        this.lvl1.requestFocus();
        this.lvl1.start();
        
    }
    
    public void showStartScreen(){
    
        this.startScreen.requestFocus();
        this.gameWindow.setVisible(true);
    
    }
    
    public void endGame(){
    
        System.out.println("This is there the game will end.");
    
    }
    
    public static void main(String[] args){
        
        Game game = new Game();
        game.showStartScreen();
        
    }
    
    public int getWindowWidth(){
    
        return this.win_width;
    
    }
    
    public int getWindowHeight(){
    
        return this.win_height;
    
    }
    
    public int getLives(){
    
        return this.lives;
        
    }
    
    public void removeLife(){
    
        this.lives = this.lives - 1;
        
        if (this.lives < 1) {
        
            this.endGame();
        
        }
    
    }
    
}
