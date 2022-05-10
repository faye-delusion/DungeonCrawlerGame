/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.entities;

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
    
    private Vector position;
    private Vector displacement;
    
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private int score;
    
    public Player(){
    
        position = new Vector(100,100);
        displacement = new Vector(0,0);
        
        score = 0;
        
        init();
    
    }
    
    private void init(){
    
        try{
        
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/character.png"));
        
        } catch (Exception ex){
        
            System.out.println(ex);
        
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    
    }
    
    public Rectangle getBounds(){
    
        Rectangle bounds = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return bounds;
    
    }
    
    public boolean checkCollision(Treasure t){
        
        if (t.getBounds().intersects(getBounds())) {
        
            if (t.getVisible() == true) {
            
                score += t.getScore();
                t.setVisible(false);
            
            }
            
            return true;
        
        }
    
        return false;
    
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
    
    public void move(int direction){
    
        switch (direction){
            
            case 1:
                
                displacement.setY(-1);
                break;
            
            case 2:
                
                displacement.setY(1);
                break;
                
            case 3:
                
                displacement.setX(-1);
                break;
                
            case 4:
                
                displacement.setX(1);
                break;
                
            default:
                
                break;
                     
                 
        }
    
    }
    
    public void doMove(){
    
        position.add(displacement);
    
    }
    
    public void stop(){
    
        displacement.setX(0);
        displacement.setY(0);
    
    }
    
    public void draw(Graphics2D g){
    
        g.drawImage(sprite, position.getX(), position.getY(), null);
    
    }
    
}
