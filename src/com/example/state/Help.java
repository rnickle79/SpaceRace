package com.example.state;

import com.example.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static com.example.utils.Constants.SCREEN_HEIGHT;


public class Help extends State{
    private final GamePanel gamePanel;
    private boolean buttonsAdded;
    private JButton back;

    public Help(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN,20));
        g.drawString("Player 1 Controls: ",0,30);
        g.drawString(" W - Move Up",0,60);
        g.drawString(" S - Move Down",0,90);
        g.drawString("Player 2 Controls: ",0,160);
        g.drawString(" Up Arrow   - Move Up",0,190);
        g.drawString(" Down Arrow - Move Down",0,230);
        g.drawString("Objective:", 0, 300);
        g.drawString(" Reach the top without crashing to score.", 0, 330);
        g.drawString(" The player with the most points after 2 minutes wins.", 0, 360);
        if(!buttonsAdded) createButtons();
    }

    private void createButtons() {
        back = new JButton("BACK");
        back.setBounds(0,SCREEN_HEIGHT-60,120,50);
        back.addActionListener(e ->{
            GameState.status = GameState.MENU;
            destroyButtons();
        });
        gamePanel.add(back);
        buttonsAdded = true;
    }

    private void destroyButtons() {
        gamePanel.remove(back);
        buttonsAdded = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
