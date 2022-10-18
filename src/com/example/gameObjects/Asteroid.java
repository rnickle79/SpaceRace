package com.example.gameObjects;

import static com.example.utils.Constants.*;

import java.awt.*;

public class Asteroid extends GameObject{
    private final int speed;
    private final char direction;

    public Asteroid(int x, int y, char direction, int speed) {
        super(x, y, ASTEROID_WIDTH , ASTEROID_HEIGHT);
        this.direction = direction;
        this.speed = speed;
    }

    public boolean checkCollision(Ship player){
        return this.getBounds().intersects(player.getBounds());
    }


    @Override
    public void update() {
        if ( direction == 'L'){
            x -= speed;
            if(x <= 0) x = SCREEN_WIDTH;
        } else {
            x += speed;
            if(x >= SCREEN_WIDTH) x = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x,y,w,h);
    }
}
