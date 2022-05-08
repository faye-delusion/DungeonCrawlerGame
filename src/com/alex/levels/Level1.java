/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.levels;

import com.alex.characters.Treasure;
import com.alex.characters.player;
import com.alex.game.Game;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author alex
 */
public class Level1 extends JPanel implements ActionListener{
    
    private Game g;
    private Timer timer;
    private BufferedImage background;
    private player player;
    private Treasure treasure;
    private Treasure[] treasures;
    private int timeAlive;
    
    public Level1(Game g){
    
        this.g = g;
        player = new player();
        treasure = new Treasure();
        
        Random random = new Random();
        
        for (int i = 0; i > 4; i++){
            
            int x = random.nextInt(this.g.getWindowWidth());
            int y = random.nextInt(this.g.getWindowHeight());
            
            Vector pos = new Vector(x,y);
        
            treasures[i] = new Treasure(pos);
        
        }
        
        init();
    
    }
    
    private void init(){
    
        addKeyListener(new TAdapter());
        
        setFocusable(true);
        setDoubleBuffered(true);
        
        try{
        
            background = ImageIO.read(getClass().getResourceAsStream("/Images/background.png"));
        
        } catch (Exception ex){
        
            System.err.println(ex);
        
        }
        
        timer = new Timer(10, this);
    
    }
    
    @Override
    protected void paintComponent(Graphics g){
    
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(background,0,0,null);
        
        int score = player.getScore();
        
        String str = String.format("Score: %o", score);
        
        g2d.drawString(str, 0, 150);
        
        player.draw(g2d);
        
        treasure.draw(g2d);
        
        g2d.dispose();
    
    }
    
    public void collisions(){
    
        player.checkCollision(treasure);
    
    }
    
    public void movement(){
    
        player.doMove();
    
    }
    
    public void start(){
    
        timer.start();
    
    }
    
    public void stop(){
    
        timer.stop();
    
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        collisions();
        movement();
        repaint();
        timeAlive = timeAlive + 10;
        
    }
    
    private class TAdapter extends KeyAdapter{
    
        @Override
        public void keyPressed(KeyEvent e){
        
            int move = 0;
            
            switch (e.getKeyCode())
            {
            
                case KeyEvent.VK_UP:
                    
                    move = 1;
                    break;
                    
                case KeyEvent.VK_DOWN:
                    
                    move = 2;
                    break;
                    
                case KeyEvent.VK_LEFT:
                    
                    move = 3;
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    
                    move = 4;
                    break;
            
            }
            
            player.move(move);
        
        }
        
        @Override
        public void keyReleased(KeyEvent e){
        
            player.stop();
        
        }
    
    }
    
    
}
