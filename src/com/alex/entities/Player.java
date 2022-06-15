package com.alex.entities;

// imports
import com.alex.game.Game;
import com.alex.levels.Level1;
import com.alex.util.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author alex
 */
public class Player {
    
    // variables
    private Vector position;
    private Vector displacement;
    
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private int score;
    private int lives;
    
    public Player(){
    
        this.position = new Vector(100,100);
        this.displacement = new Vector(0,0);
        
        this.score = 0;
        this.lives = 3;
        
        this.init();
    
    }
    
    private void init(){
    
        // load image
        try{
        
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/Images/character.png"));
        
        } catch (Exception ex){
        
            System.out.println(ex);
        
        }
        
        this.spriteWidth = sprite.getWidth();
        this.spriteHeight = sprite.getHeight();
    
    }
    
    // return object boundaries
    public Rectangle getBounds(){
    
        Rectangle bounds = new Rectangle(this.position.getX(), this.position.getY(), this.spriteWidth, this.spriteHeight);
        return bounds;
    
    }
    
    // check for a collision with a treasure
    public boolean checkCollision(Treasure t){
        
        if (t.getBounds().intersects(getBounds())) {
        
            if (t.getVisible() == true) {
            
                this.score += t.getScore();
                t.setVisible(false);
            
            }
            
            return true;
        
        }
    
        return false;
    
    }
    
    // check for a collision with a monster
    public boolean checkCollision(Monster m){
        
        if (m.getBounds().intersects(this.getBounds())) {
        
            if (m.getVisible() == true) {
            
                m.setVisible(false);
                
                this.lives = this.lives - 1;
            
            }
            
            return true;
        
        }
    
        return false;
    
    }
    
    // check for a collision with a door
    public boolean checkCollision(LevelExit e, Game g){
        
        if (e.getBounds().intersects(getBounds())) {
        
            // end the game
            g.winGame();
            return true;
        
        }
    
        return false;
    
    }
    
    public int getLives(){
    
        return this.lives;
    
    }
    
    public void setPosition(Vector v){
    
        position = v;
    
    }
    
    public Vector getPosition(){
    
        return position;
    
    }
    
    public int getSpriteWidth(){
    
        return spriteWidth;
        
    }
    
    public int getSpriteHeight(){
    
        return spriteHeight;
    
    }
    
    public void setScore(int newScore){
    
        score = newScore;
    
    }
    
    public int getScore(){
    
        return score;
    
    }
    
    public BufferedImage getSprite(){
    
        return sprite;
    
    }
    
    // move player in specified direction
    public void move(int direction, int levelWidth, int levelHeight){
    
        int speed = 3;
        
        switch (direction){
            
            case 1:
                
                displacement.setY(-speed);
                break;
            
            case 2:
                
                displacement.setY(speed);
                break;
                
            case 3:
                
                displacement.setX(-speed);
                break;
                
            case 4:
                
                displacement.setX(speed);
                break;
                
            default:
                
                break;
                     
                 
        }
        
        // move player back in bounds if player escapes canvas boundary
        if (this.position.getX() <= 1) {
        
            displacement.setX(1);
        
        } else if (this.position.getX() > levelWidth - (this.spriteWidth)) {
        
            this.position.setX(levelWidth - this.spriteWidth);
        
        }
        
        if (this.position.getY() <= 1) {
        
            displacement.setY(1);
        
        } else if (this.position.getY() > levelHeight - (this.spriteHeight)) {
        
            this.position.setY(levelHeight - this.spriteWidth);
        
        }

    }
    
    public void doMove(){
    
        this.position.add(displacement);
    
    }
    
    public void stop(){
    
        displacement.setX(0);
        displacement.setY(0);
    
    }
    
    public void draw(Graphics2D g){
    
        g.drawImage(sprite, position.getX(), position.getY(), null);
    
    }
    
    public void setLives(int lives){
    
        this.lives = lives;
    
    }
    
}
