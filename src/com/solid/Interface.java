package com.solid;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Interface {
    public static ArrayList<Snake> snakes = new ArrayList<>();
    private JPanel MPanel;
    private GPanel GPanel1;

    public Interface() {

        GPanel1.setFocusable(true);
        GPanel1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                switch (key) {
                    case 37: //left
                        for (Snake snake : snakes) {
                            snake.direction = 3;
                        }
                        break;
                    case 38: //up
                        for (Snake snake : snakes) {
                            snake.direction = 2;
                        }
                        break;
                    case 39: //right
                        for (Snake snake : snakes) {
                            snake.direction = 1;
                        }
                        break;
                    case 40: //down
                        for (Snake snake : snakes) {
                            snake.direction = 0;
                        }
                        break;
                    case 32: //space
                        createSnake(5, false);
                        break;
                    case 83: //s
                        snakes.clear();
                        for (int i = 0; i < 25; i++) {
                            createSnake(5, true);
                        }
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface");
        frame.setContentPane(new Interface().MPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("LFEx");
        }

        frame.pack();
        frame.setVisible(true);
    }

    private void createSnake(int length, boolean random) {
        Random r = new Random();
        if (random) {
            Snake boss = new Snake(r.nextInt(1920 / GPanel.rectSide), r.nextInt(1080 / GPanel.rectSide), r.nextInt(4), length);
            snakes.add(boss);
        } else {
            Snake boss = new Snake(0, 0, 0, length);
            snakes.add(boss);
        }
    }


    private void createUIComponents() {
        GPanel1 = new GPanel();
    }
}
