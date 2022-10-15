package com.example.main;

import static com.example.utils.Constants.*;
import com.example.inputs.KeyInput;
import com.example.state.*;
import com.example.state.Menu;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final Game game;
    private GameOver gameOver;
    private Running running;
    private Pause pause;
    private Menu menu;
    private Help help;

    public GamePanel(Game game) {
        this.game = game;
        initPanel();
        initStates();
    }

    private void initStates(){
        gameOver = new GameOver(this);
        running = new Running(game);
        pause = new Pause();
        menu = new Menu(this);
        help = new Help(this);
    }

    private void initPanel(){
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        addKeyListener(new KeyInput(this));
        setBackground(Color.black);
        setFocusable(true);
        setLayout(null);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(GameState.status){
            case RUNNING:
                running.render(g);
                break;
            case MENU:
                menu.render(g);
                break;
            case HELP:
                help.render(g);
                break;
            case GAME_OVER:
                running.render(g);
                gameOver.render(g);
                break;
            case PAUSE:
                running.render(g);
                pause.render(g);
                break;
        }
    }

    public Game getGame() {
        return game;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public Running getRunning() {
        return running;
    }

    public Menu getMenu() {
        return menu;
    }

    public Pause getPause() {
        return pause;
    }
}
