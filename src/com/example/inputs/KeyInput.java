package com.example.inputs;

import com.example.main.GamePanel;
import com.example.state.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final GamePanel gamePanel;

    public KeyInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(GameState.status){
            case MENU: gamePanel.getMenu().keyPressed(e);break;
            case RUNNING: gamePanel.getRunning().keyPressed(e);break;
            case GAME_OVER: gamePanel.getGameOver().keyPressed(e);break;
            case PAUSE: gamePanel.getPause().keyPressed(e);break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameState.status){
            case MENU: gamePanel.getMenu().keyReleased(e);break;
            case RUNNING: gamePanel.getRunning().keyReleased(e);break;
            case GAME_OVER: gamePanel.getGameOver().keyReleased(e);break;
            case PAUSE: gamePanel.getPause().keyReleased(e);break;
        }
    }
}
