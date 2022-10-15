package com.example.main;

import java.awt.*;
import java.text.DecimalFormat;

import static com.example.utils.Constants.SCREEN_WIDTH;

public class ScoreBoard {
    private final DecimalFormat df;
    private int score1;
    private int score2;

    public ScoreBoard() {
        df = new DecimalFormat("00");
        reset();
    }

    public void reset(){
        score1 = 0;
        score2 = 0;
    }

    public void increaseScore1(){
        score1++;
    }

    public void increaseScore2(){
        score2++;
    }

    public void render(Graphics g){
        g.setFont(new Font("Consolas", Font.PLAIN,60));
        g.drawString(df.format(score1),0,60);
        g.drawString(df.format(score2),SCREEN_WIDTH-70,60);
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }
}