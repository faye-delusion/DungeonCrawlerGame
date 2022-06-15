package com.alex.levels;

// imports
import com.alex.entities.LevelExit;
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
    
    // variables
    private Game game;
    private Timer timer;
    private BufferedImage background;
    private Player player;
    private Monster[] monsters;
    private Treasure[] treasures;
    private int timeAlive;
    private LevelExit door;
    
    private int number_of_treasures = 5;
    private int number_of_monsters = 5;
    
    private int level_width;
    private int level_height;
    
    boolean isGameEnded;
    boolean drawDoor;
    
    public Level1(Game game){
    
        // create level objects
        this.game = game;
        player = new Player();
        
        this.level_width = this.game.getWindowWidth();
        this.level_height = this.game.getWindowHeight();
        
        this.treasures = new Treasure[this.number_of_treasures];
        this.monsters = new Monster[this.number_of_monsters];
        
        this.door = new LevelExit();
        
        init();
        reset();
    
    }
    
    private void init(){
    
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        
        // load image
        try {
        
            background = ImageIO.read(getClass().getResourceAsStream("/Images/Level1Background.png"));
        
        } catch (Exception ex){
        
            System.err.println(String.format("Error loading background image for Level 1\n%s", ex.toString()));
        
        }
        
        this.timer = new Timer(1, this);
    
    }
    
    public void reset(){
    
        Random random = new Random();
        
        Treasure t = new Treasure();
        Monster m = new Monster();
        
        // reset score, lives, timer
        this.player.setScore(0);
        this.player.setLives(3);
        this.timeAlive = 0;
        
        // reset player position
        this.player.setPosition(new Vector(100,100));
        
        // randomise position of each treasure object
        for (int i = 0; i < this.number_of_treasures; i++){

            int x = random.nextInt(this.level_width - (t.getSpriteWidth() * 2));
            int y = random.nextInt(this.level_height - (t.getSpriteHeight() * 2));
             
            Vector pos = new Vector(x,y);
        
            this.treasures[i] = new Treasure(pos);
            System.out.println(String.format("Created new Treasure @ x%d, y%d", x, y));
        
        }
        
        // randomise position of each monster object
        for (int i = 0; i < this.number_of_monsters; i++) {
        
            int x = random.nextInt(this.level_width - (t.getSpriteWidth() * 2));
            int y = random.nextInt(this.level_height - (t.getSpriteHeight() * 2));
            
            Vector pos = new Vector(x,y);
            
            this.monsters[i] = new Monster(pos);
            System.out.println(String.format("Created new Monster @ x%d, y%d", x, y));
        }
        
        t = null;
        m = null;
    
    }
    
    @Override
    protected void paintComponent(Graphics graphic){
    
        super.paintComponent(graphic);
        
        Graphics2D g2d = (Graphics2D) graphic;
        
        g2d.drawImage(background,0,0,null);
        
        // draw door if drawDoor is true
        if (drawDoor == true){
        
            door.draw(g2d);
        
        }
        
        // draw player
        this.player.draw(g2d);
        
        // Draw treasure objects
        for (Treasure t : this.treasures) {
        
            t.draw(g2d);
        
        }
        
        // Draw monster objects
        for (Monster m : this.monsters) {
        
            m.draw(g2d);
        
        }
        
        // draw score, timer, lives display
        g2d.drawString(String.format("Score: %d", this.player.getScore()), 0, 150);
        
        g2d.drawString(String.format("Time: %d", this.timeAlive), 0, 170);
        
        g2d.drawString(String.format("Lives: %d", this.player.getLives()), 0, 190);
        
        
        // draw game over text, stop the game.
        if (isGameEnded == true){
        
            g2d.drawString("Game Over", 300,300);
            this.stop();
        
        }
        
        // destroy g2d object to save memory
        g2d.dispose();
    
    }
    
    public void collisions(){
    
        // check if player colliding with each treasure
        for (Treasure t : this.treasures) {
        
            this.player.checkCollision(t);
            
        }
        
        // check if player colliding with each monster
        for (Monster m : this.monsters) {
        
            this.player.checkCollision(m);
        
        }
        
        // check if player colliding with the door if the door is being drawn
        if (drawDoor == true){
        
            this.player.checkCollision(this.door, this.game);
        
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
    
        isGameEnded = this.game.checkIfGameEnd(this.player.getLives());
    
    }
    
    public void checkScore(){
    
        drawDoor = this.game.checkIfScoreMax(this.player.getScore(), 50 * this.number_of_treasures);
        
    }

    
    // run each frame
    @Override
    public void actionPerformed(ActionEvent e) {

        this.collisions();
        this.movement();
        this.repaint();
        this.checkLives();
        this.checkScore();
        this.timeAlive = this.timeAlive + 1;
        
        
    }
    
    private class TAdapter extends KeyAdapter{
    
        // detect key press
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
            
            player.move(move, level_width, level_height);
        
        }
        
        @Override
        public void keyReleased(KeyEvent e){
        
            player.stop();
        
        }
    
    }
    
    
}
