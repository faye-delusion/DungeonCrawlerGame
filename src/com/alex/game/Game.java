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
    public static final int win_width = 600;
    public static final int win_height = 600;
    public static final String win_title = "Awesome Game!";
    
    // Game window
    private JFrame gameWindow;
    private LoadingScreen startScreen;
    
    // Levels
    private Level1 lvl1;
    
    // Class constructor
    public Game(){
    
        initWindow();
        initScreens();
    
    }
    
    private void initScreens(){
    
        startScreen = new LoadingScreen(this);
        startScreen.setPreferredSize(new Dimension(win_width, win_height));
        
        // Levels
        lvl1 = new Level1(this);
        lvl1.setPreferredSize(new Dimension(win_width, win_height));
        
        gameWindow.getContentPane().add(startScreen, "StartScreen");
        gameWindow.getContentPane().add(lvl1, "Level1");
        gameWindow.setVisible(true);
    
    }
    
    private void initWindow(){
    
        // Initiate window + set properties
        gameWindow = new JFrame();
        
        gameWindow.setSize(win_width,win_height);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null);
        
        gameWindow.setTitle(win_title);
        gameWindow.setVisible(true);
    
    }
    
    public void start(){
    
        CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lvl1.requestFocus();
        lvl1.start();
        
    }
    
    public void showStartScreen(){
    
        startScreen.requestFocus();
        gameWindow.setVisible(true);
    
    }
    
    public static void main(String[] args){
        
        Game g = new Game();
        g.showStartScreen();
        
    }
    
    public int getWindowWidth(){
    
        return this.win_width;
    
    }
    
    public int getWindowHeight(){
    
        return this.win_height;
    
    }
    
}
