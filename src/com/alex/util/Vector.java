package com.alex.util;

/**
 *
 * @author alex
 */
public class Vector {
    
    // variables
    private int x;
    private int y;
    
    // default constructor, no arguments
    public Vector(){
    
        x=0;
        y=0;
    
    }
    
    // constructor with X and Y argument
    public Vector(int newX, int newY){
    
        x = newX;
        y = newY;
    
    }
    
    // constructor with Vector argument
    public Vector(Vector v){
    
        x = v.getX();
        y = v.getY();
    
    }
    
    // add X and Y of another Vector to this.x, this.y
    public void add(Vector v){
    
        x += v.getX();
        y += v.getY();
    
    }
    
    public void toVector(Vector v){
    
        x = v.getX();
        y = v.getY();
    
    }
    
    public void setX(int x){
    
        this.x = x;
    
    }
    
    public void setY(int y){
    
        this.y = y;
    
    }
    
    public int getX(){
    
        return x;
        
    }
    
    public int getY(){
    
        return y;
    
    
    }
    
}
