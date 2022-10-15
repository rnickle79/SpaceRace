package com.example.state;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.example.utils.Constants.SCREEN_HEIGHT;
import static com.example.utils.Constants.SCREEN_WIDTH;

public class Pause extends State {
    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Consolas", Font.PLAIN,60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String text = "P A U S E";
        g.drawString(text, (SCREEN_WIDTH - metrics.stringWidth(text))/2, SCREEN_HEIGHT /2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_SPACE:
                GameState.status = GameState.RUNNING;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
