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
    private ArrayList<Ship> players;
    private ScoreBoard scoreBoard;
    private GameTimer gameTimer;
    private GamePanel gamePanel;
    private Random random;
    private Ship player1;
    private Ship player2;
    private int asteroidSpeed = ASTEROID_INIT_SPEED;
    private char asteroidDirection = 'L';

    public Game() {
        initClasses();
        initWindow();
        GameSound.playMusic("/sound/Holly.wav");
    }

    private void initClasses(){
        player1 = new Ship(PLAYER1_X_START,1);
        player2 = new Ship(PLAYER2_X_START,2);
        scoreBoard = new ScoreBoard();
        gameTimer = new GameTimer();
        random = new Random();
        asteroids = new ArrayList<>();
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }

    private void initWindow(){
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);
    }

    private void spawnAsteroids(int count, int speed){
        for(int i=0; i< count; i++){
            int x = random.nextInt(SCREEN_WIDTH);
            int y = random.nextInt(SCREEN_HEIGHT - SHIP_HEIGHT *2);
            asteroids.add(new Asteroid(x, y, asteroidDirection, speed));
            // Change direction for next spawned asteroid
            asteroidDirection = (asteroidDirection == 'L') ? 'R' : 'L';
        }
    }

    private void gameOverCheck(){
        if(gameTimer.getGameTimeCor()>=SCREEN_HEIGHT){
            GameState.status = GameState.GAME_OVER;
        }
    }

    private void increaseDifficulty(){
        // Increase speed for new asteroids
        // Add an asteroid
        asteroidSpeed++;
        spawnAsteroids(1, asteroidSpeed);
    }

    private void scoreCheck(){
        for(Ship player: players){
            if(player.scored()){
                player.reset();
                switch(player.getId()){
                    case 1: scoreBoard.increaseScore1(); break;
                    case 2: scoreBoard.increaseScore2(); break;
                }
                increaseDifficulty();
            }
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
            for(Ship player: players){
                if(asteroid.checkCollision(player)) {
                    player.explode();
                    GameSound.playSound("/sound/explode.wav");
                }
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
        asteroidSpeed = ASTEROID_INIT_SPEED;
        spawnAsteroids(ASTEROID_COUNT, asteroidSpeed);
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
