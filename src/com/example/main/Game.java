package com.example.main;

import static com.example.utils.Constants.*;

import com.example.sound.GameSound;
import com.example.gameObjects.*;
import com.example.state.GameState;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Game extends Thread implements ActionListener {
    private ArrayList<Asteroid> asteroids;
    private ScoreBoard scoreBoard;
    private GameTimer gameTimer;
    private GamePanel gamePanel;
    private Random random;
    private Ship player1;
    private Ship player2;

    public Game() {
        initClasses();
        initWindow();
        startGame();
    }

    private void initClasses(){
        player1 = new Ship(PLAYER1_X_START);
        player2 = new Ship(PLAYER2_X_START);
        scoreBoard = new ScoreBoard();
        asteroids = new ArrayList<>();
        gameTimer = new GameTimer();
        random = new Random();
    }

    private void initWindow(){
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);
    }

    private void startGame(){
        GameSound.playMusic("/sound/Holly.wav");
        //spawnAsteroids();
        //GameState.status = GameState.RUNNING;
    }

    private void spawnAsteroids(){
        char direction = 'L';
        for(int i=0; i< ASTEROID_COUNT; i++){
            int x = random.nextInt(SCREEN_WIDTH);
            int y = random.nextInt(SCREEN_HEIGHT - SHIP_HEIGHT *2);
            asteroids.add(new Asteroid(x,y,direction));
            if (direction == 'L'){
                direction = 'R';
            }else {
                direction = 'L';
            }
        }
    }

    private void gameOverCheck(){
        if(gameTimer.getGameTimeCor()>=SCREEN_HEIGHT){
            GameState.status = GameState.GAME_OVER;
        }
    }

    private void scoreCheck(){
        if(player1.scored()){
            player1.reset();
            scoreBoard.increaseScore1();
        }
        if(player2.scored()){
            player2.reset();
            scoreBoard.increaseScore2();
        }
    }

    public void update(){
        gameTimer.update();
        gameOverCheck();
        player1.update();
        player2.update();
        scoreCheck();
        for(Asteroid asteroid: asteroids){
            asteroid.update();
            if(asteroid.checkCollision(player1)) {
                player1.explode();
                GameSound.playSound("/sound/explode.wav");
            }
            if(asteroid.checkCollision(player2)) {
                player2.explode();
                GameSound.playSound("/sound/explode.wav");
            }
        }
    }

    public void render(Graphics g) {
        gameTimer.render(g);
        player1.render(g);
        player2.render(g);
        for(Asteroid asteroid: asteroids) {
            asteroid.render(g);
        }
        scoreBoard.render(g);
    }

    public void reset() {
        scoreBoard.reset();
        player1.reset();
        player2.reset();
        asteroids.clear();
        spawnAsteroids();
        gameTimer.reset();
        gamePanel.requestFocusInWindow();
        GameState.status = GameState.RUNNING;
    }

    public void quit() {
        System.exit(0);
    }

    public Ship getPlayer1(){
        return player1;
    }

    public Ship getPlayer2(){
        return player2;
    }

    /*  Game Loop Methods */
    @Override
    public void run(){
        Timer timer = new Timer(75, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (GameState.status){
            case RUNNING: update(); break;
            case RESET: reset();break;
            case QUIT: quit(); break;
        }
        gamePanel.repaint();
    }
}
