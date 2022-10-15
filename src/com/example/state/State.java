package com.example.state;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class State extends JComponent {
    public abstract void render(Graphics g);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
}
