package com.example.main;

import java.awt.*;

import static com.example.utils.Constants.*;

public class GameTimer {
    private int gameTimeCor;
    private long lastTime;

    public GameTimer() {
        lastTime = System.currentTimeMillis() / 1000;
        reset();
    }

    public void reset(){
        gameTimeCor = SCREEN_HEIGHT - TIME_LIMIT;
    }


    public void update(){
        long currentTime = System.currentTimeMillis() / 1000;
        if(currentTime >lastTime){
            gameTimeCor++;
            lastTime = currentTime;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.drawLine(SCREEN_WIDTH /2, gameTimeCor,SCREEN_WIDTH /2, SCREEN_HEIGHT);
    }

    public int getGameTimeCor(){
        return gameTimeCor;
    }
}
