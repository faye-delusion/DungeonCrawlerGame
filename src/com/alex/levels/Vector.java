/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.levels;

/**
 *
 * @author alex
 */
public class Vector {
    
    private int x;
    private int y;
    
    public Vector(){
    
        x=0;
        y=0;
    
    }
    
    public Vector(int newX, int newY){
    
        x = newX;
        y = newY;
    
    }
    
    public Vector(Vector v){
    
        x = v.getX();
        y = v.getY();
    
    }
    
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
