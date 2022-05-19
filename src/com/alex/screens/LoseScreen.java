/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.screens;
import com.alex.game.Game;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 *
 * @author alex
 */
public class LoseScreen extends JPanel{
    
    private Game game;
    private BufferedImage background;
    
    public LoseScreen(Game game){
    
        this.game = game;
        init();
    
    }
    
    private void init(){
    
        try{
        
            this.background = ImageIO.read(getClass().getResourceAsStream("/Images/WinScreen.png"));
        
        } 
        catch(Exception ex){
        
            System.err.println(String.format("Error loading background image for Win Screen\n%s", ex.toString()));
        
        }
        
        setFocusable(true);
        addKeyListener(new TAdapter());
        
    }
    
    @Override
    public void paintComponent(Graphics g){
    
        super.paintComponent(g); // required
        
        g.drawImage(background, 0, 0, null);
    
    }
    
    private class TAdapter extends KeyAdapter{
    
        @Override
        public void keyReleased(KeyEvent e){
        
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                
                System.out.println("Game Restarted");
                game.start();
            
            }
        
        }
        
        @Override
        public void keyPressed(KeyEvent e){
        
            
        
        }

    
    }
    
}
