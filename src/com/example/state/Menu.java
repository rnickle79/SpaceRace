package com.example.state;

import com.example.gameObjects.Asteroid;
import com.example.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import static com.example.utils.Constants.*;

public class Menu extends State {
    private final GamePanel gamePanel;
    private final Random random;
    private final ArrayList<Asteroid> asteroids;
    private boolean buttonsAdded;
    private JButton play;
    private JButton help;
    private JButton quit;

    public Menu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        random = new Random();
        asteroids = new ArrayList<>();
        spawnAsteroids();
    }

    private void spawnAsteroids(){
        char direction = 'L';
        for(int i=0; i< 100; i++){
            int x = random.nextInt(SCREEN_WIDTH);
            int y = random.nextInt(SCREEN_HEIGHT);
            asteroids.add(new Asteroid(x,y,direction, ASTEROID_INIT_SPEED));
            if (direction == 'L'){
                direction = 'R';
            }else {
                direction = 'L';
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Consolas", Font.PLAIN,60));
        FontMetrics metrics = getFontMetrics(g.getFont());

        for(Asteroid asteroid: asteroids)
            asteroid.render(g);

        // Draw moon
        g.setColor(Color.gray);
        g.fillOval(0,-SCREEN_WIDTH/2, SCREEN_WIDTH, SCREEN_WIDTH);

        // Draw sun
        g.setColor(Color.yellow);
        g.fillOval(SCREEN_WIDTH-100,SCREEN_WIDTH-100, 200, 200);

        // Draw title
        g.setColor(Color.black);
        String text = "S P A C E";
        g.drawString(text, (SCREEN_WIDTH - metrics.stringWidth(text))/2, 100);
        text = "R A C E";
        g.drawString(text, (SCREEN_WIDTH - metrics.stringWidth(text))/2,  200);

        if(!buttonsAdded) createButtons();
    }

    private void createButtons(){
        int buttonWidth = 120;
        int buttonHeight = 50;
        // Construct
        play = new JButton("PLAY");
        help = new JButton("HELP");
        quit = new JButton("QUIT");

        // Set bounds
        play.setBounds(SCREEN_WIDTH/2 - buttonWidth/2, SCREEN_HEIGHT - 250, buttonWidth, buttonHeight);
        help.setBounds(SCREEN_WIDTH/2 - buttonWidth/2, SCREEN_HEIGHT - 175, buttonWidth, buttonHeight);
        quit.setBounds(SCREEN_WIDTH/2 - buttonWidth/2, SCREEN_HEIGHT - 100 , buttonWidth, buttonHeight);

        // Setup  ActionListeners
        play.addActionListener(e -> {
            gamePanel.getGame().reset();
            destroyButtons();
        });

        help.addActionListener(e -> {
            GameState.status = GameState.HELP;
            destroyButtons();
        });

        quit.addActionListener(e -> {
            GameState.status = GameState.QUIT;
            destroyButtons();
        });

        // Add to Game Panel
        gamePanel.add(play);
        gamePanel.add(help);
        gamePanel.add(quit);
        buttonsAdded = true;
    }

    private void destroyButtons(){
        gamePanel.remove(play);
        gamePanel.remove(help);
        gamePanel.remove(quit);
        buttonsAdded = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
