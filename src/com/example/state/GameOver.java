package com.example.state;

import com.example.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static com.example.utils.Constants.SCREEN_HEIGHT;
import static com.example.utils.Constants.SCREEN_WIDTH;

public class GameOver extends State {
    private final GamePanel gamePanel;
    private boolean buttonsAdded;
    private JButton playAgain;
    private JButton quit;

    public GameOver(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    private void createButtons(){
        // Construct
        playAgain = new JButton("PLAY AGAIN");
        quit = new JButton("QUIT");

        // Set bounds
        playAgain.setBounds(0, SCREEN_HEIGHT - 60, 120, 50);
        quit.setBounds(SCREEN_WIDTH - 120, SCREEN_HEIGHT - 60, 120, 50);

        // Setup  ActionListeners
        playAgain.addActionListener(e -> {
            gamePanel.getGame().reset();
            destroyButtons();
        });

        quit.addActionListener(e -> {
            GameState.status = GameState.QUIT;
            destroyButtons();
        });

        // Add to Game Panel
        gamePanel.add(playAgain);
        gamePanel.add(quit);
        buttonsAdded = true;
    }

    private void destroyButtons(){
        gamePanel.remove(playAgain);
        gamePanel.remove(quit);
        buttonsAdded = false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN,60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String text = "GAME OVER";
        g.drawString(text, (SCREEN_WIDTH - metrics.stringWidth(text))/2, SCREEN_HEIGHT /2);

        if(!buttonsAdded) createButtons();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (buttonsAdded) {
            int key = e.getKeyCode();
            switch (key) {
                // Trigger Play Again
                case KeyEvent.VK_ENTER:
                    for (ActionListener a : playAgain.getActionListeners()) {
                        a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                    }
                    break;
                // Trigger Quit
                case KeyEvent.VK_ESCAPE:
                    for (ActionListener a : quit.getActionListeners()) {
                        a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
