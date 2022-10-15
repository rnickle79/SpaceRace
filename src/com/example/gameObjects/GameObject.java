package com.example.gameObjects;

import java.awt.*;

public abstract class GameObject {
    protected int x,y,w,h;

    public GameObject(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public int getY(){
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,w,h);
    }

    public abstract void update();
    public abstract void render(Graphics g);
}