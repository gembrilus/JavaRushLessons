package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {

    private View view;
    private EventListener eventListener;

    public Field(View view){
        this.view = view;
        addKeyListener(new KeyHandler());
        setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 500, 500);
        view.getGameObjects().getAll().forEach(o -> o.draw(g));
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT: eventListener.move(Direction.LEFT);
                case KeyEvent.VK_RIGHT: eventListener.move(Direction.RIGHT);
                case KeyEvent.VK_UP: eventListener.move(Direction.UP);
                case KeyEvent.VK_DOWN: eventListener.move(Direction.DOWN);
                case KeyEvent.VK_R: eventListener.restart();
            }
        }
    }
}
