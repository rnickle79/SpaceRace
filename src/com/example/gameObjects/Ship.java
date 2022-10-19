package com.example.gameObjects;

import static com.example.utils.Constants.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
import java.net.URL;

public class Ship extends GameObject{
    private final int xDrawOffset = 10;
    private final int speed = 10;
    private final int id;
    private boolean exploding;
    private boolean up;
    private boolean down;
    private int explodeIndex;
    BufferedImage idle_img;
    BufferedImage thrust_img;
    BufferedImage[] explode;
    Rectangle hitbox;

    public Ship(int x, int id) {
        super(x, SHIP_Y_START, SHIP_WIDTH, SHIP_HEIGHT);
        this.id = id;
        loadSprites();
        initHitBox();
    }

    private void loadSprites(){
        try {
            URL shipURL = getClass().getResource("/sprites/ship2.ss.png");
            BufferedImage img = ImageIO.read(shipURL);
            idle_img = img.getSubimage(0,0,32,32);
            thrust_img = img.getSubimage(32,0,32,32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           URL explodeURL = getClass().getResource("/sprites/explode.ss.png");
           BufferedImage img = ImageIO.read(explodeURL);
           explode = new BufferedImage[4];
           for(int i=0; i<explode.length; i++){
               explode[i] = img.getSubimage(i*32, 0, 32,32);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initHitBox() {
        hitbox = new Rectangle(x,y,13,27);
    }

    @Override
    public Rectangle getBounds() {
        return hitbox;
    }

    @Override
    public void update() {
        if(up && !down && !exploding){
            hitbox.y -= speed;
        }
        if(down && !up && !exploding){
            hitbox.y += speed;
        }
        if(hitbox.y >= SCREEN_HEIGHT - SHIP_HEIGHT) hitbox.y = SCREEN_HEIGHT - SHIP_HEIGHT;

    }

    private void explodeUpdate(){
        if(exploding){
            explodeIndex++;
            if(explodeIndex > 3){
                exploding = false;
                explodeIndex = 0;
                reset();
            }
        }
    }


    @Override
    public void render(Graphics g) {
        if(up && !exploding) {
            g.drawImage(thrust_img, hitbox.x - xDrawOffset, hitbox.y, w, h,null);
        } else if (exploding){
            g.drawImage(explode[explodeIndex], hitbox.x, hitbox.y, w, h, null);
            explodeUpdate();
        } else {
            g.drawImage(idle_img, hitbox.x - xDrawOffset, hitbox.y, w, h, null);
        }
    }

    public boolean scored(){
        return hitbox.y <= -10;
    }

    public void reset(){
        hitbox.y = SHIP_Y_START;
        up = false;
        down = false;
        exploding = false;
    }

    public void explode(){
        exploding = true;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getId(){
        return id;
    }
}
