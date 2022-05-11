package com.alex.levels;

import com.alex.entities.Monster;
import com.alex.util.Vector;
import com.alex.entities.Treasure;
import com.alex.entities.Player;
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
    
    private Game game;
    private Timer timer;
    private BufferedImage background;
    private Player player;
    private Monster[] monsters;
    private Treasure[] treasures;
    private int timeAlive;
    
    private int number_of_treasures = 5;
    private int number_of_monsters = 5;
    
    public Level1(Game game){
    
        this.game = game;
        player = new Player();
        
        this.treasures = new Treasure[this.number_of_treasures];
        this.monsters = new Monster[this.number_of_monsters];
        
        init();
        reset();
    
    }
    
    private void init(){
    
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        
        try {
        
            background = ImageIO.read(getClass().getResourceAsStream("/Images/Level1Background.png"));
        
        } catch (Exception ex){
        
            System.err.println(String.format("Error loading background image for Level 1\n%s", ex.toString()));
        
        }
        
        this.timer = new Timer(1, this);
    
    }
    
    private void reset(){
    
        Random random = new Random();
        
        Treasure t = new Treasure();
        Monster m = new Monster();
        
        for (int i = 0; i < this.number_of_treasures; i++){

            int x = random.nextInt(this.game.getWindowWidth() - (t.getSpriteWidth() * 2));
            int y = random.nextInt(this.game.getWindowHeight() - (t.getSpriteHeight() * 2));
             
            Vector pos = new Vector(x,y);
        
            this.treasures[i] = new Treasure(pos);
            System.out.println(String.format("Created new Treasure @ x%d, y%d", x, y));
        
        }
        
        for (int i = 0; i < this.number_of_monsters; i++) {
        
            int x = random.nextInt(this.game.getWindowWidth() - (t.getSpriteWidth() * 2));
            int y = random.nextInt(this.game.getWindowHeight() - (t.getSpriteHeight() * 2));
            
            Vector pos = new Vector(x,y);
            
            this.monsters[i] = new Monster(pos);
            System.out.println(String.format("Created new Monster @ x%d, y%d", x, y));
        }
        
        t = null;
    
    }
    
    @Override
    protected void paintComponent(Graphics graphic){
    
        super.paintComponent(graphic);
        
        Graphics2D g2d = (Graphics2D) graphic;
        
        g2d.drawImage(background,0,0,null);
        
        this.player.draw(g2d);
        
        // Draw treasure
        for (Treasure t : this.treasures) {
        
            t.draw(g2d);
        
        }
        
        // Draw monsters
        for (Monster m : this.monsters) {
        
            m.draw(g2d);
        
        }
        
        g2d.drawString(String.format("Score: %d", this.player.getScore()), 0, 150);
        
        g2d.drawString(String.format("Time: %d", this.timeAlive), 0, 170);
        
        g2d.drawString(String.format("Lives: %d", this.player.getLives()), 0, 190);
        
        g2d.dispose();
    
    }
    
    public void collisions(){
    
        for (Treasure t : this.treasures) {
        
            this.player.checkCollision(t);
            
        }
        
        for (Monster m : this.monsters) {
        
            boolean collide = this.player.checkCollision(m);
            
//            if (collide == true) {
//            
//                System.out.println(1);
//                
//                this.game.removeLife();
//            
//            }
        
        }
    
    }
    
    public void movement(){
    
        this.player.doMove();
    
    }
    
    public void start(){
    
        this.timer.start();
    
    }
    
    public void stop(){
    
        this.timer.stop();
    
    }
    
    public void checkLives(){
    
        this.game.checkIfGameEnd(this.player.getLives());
    
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        this.collisions();
        this.movement();
        this.repaint();
        this.checkLives();
        this.timeAlive = this.timeAlive + 1;
        
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
