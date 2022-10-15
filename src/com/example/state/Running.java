package com.example.state;

import com.example.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Running extends State{
    private final Game game;

    public Running(Game game){
        this.game = game;

    }
    @Override
    public void render(Graphics g) {
        game.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_W: game.getPlayer1().setUp(true); break;
            case KeyEvent.VK_S: game.getPlayer1().setDown(true); break;
            case KeyEvent.VK_UP: game.getPlayer2().setUp(true); break;
            case KeyEvent.VK_DOWN: game.getPlayer2().setDown(true); break;
            case KeyEvent.VK_SPACE: GameState.status = GameState.PAUSE; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_W: game.getPlayer1().setUp(false); break;
            case KeyEvent.VK_S: game.getPlayer1().setDown(false); break;
            case KeyEvent.VK_UP: game.getPlayer2().setUp(false); break;
            case KeyEvent.VK_DOWN: game.getPlayer2().setDown(false); break;
        }
    }

}
