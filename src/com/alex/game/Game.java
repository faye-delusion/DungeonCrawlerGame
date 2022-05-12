/**
 * Documentation 'n Stuff
 * 
 * @author alex
 */

// Package and Imports
package com.alex.game;

// Library imports
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Dimension;

// Class imports
import com.alex.screens.WinScreen;
import com.alex.screens.LoadingScreen;
import com.alex.levels.Level1;

// Game class
public class Game {
    
    // Variables
    public static final int win_width = 800;
    public static final int win_height = 800;
    public static final String win_title = "Dungeon Crawler";
    
    // Game window
    private JFrame gameWindow;
    private LoadingScreen startScreen;
    
    private WinScreen winScreen;
    
    // Levels
    private Level1 lvl1;
    
    // Class constructor
    public Game(){
    
        initWindow();
        initScreens();
    
    }
    
    private void initScreens(){
    
        this.startScreen = new LoadingScreen(this);
        this.startScreen.setPreferredSize(new Dimension(this.win_width, this.win_height));
        
        this.winScreen = new WinScreen(this);
        this.winScreen.setPreferredSize(new Dimension(this.win_width, this.win_height));
        
        // Levels
        this.lvl1 = new Level1(this);
        this.lvl1.setPreferredSize(new Dimension(this.win_width, this.win_height));
        
        this.gameWindow.getContentPane().add(this.startScreen, "StartScreen");
        this.gameWindow.getContentPane().add(this.lvl1, "Level1");
        this.gameWindow.getContentPane().add(this.winScreen, "WinScreen");
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
    
//        this.winScreen.requestFocus();
//        this.gameWindow.setVisible(true);
    
        CardLayout cl = (CardLayout) this.gameWindow.getContentPane().getLayout();
        cl.next(this.gameWindow.getContentPane());
        this.winScreen.requestFocus();
//        this.lvl1.start();

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
    
    public void checkIfGameEnd(int lives){
    
        if (lives <= 0) {
        
            this.endGame();
            this.lvl1.stop();
        
        }
    
    }
    
}
