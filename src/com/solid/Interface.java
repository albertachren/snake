package com.solid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface {
    private static ArrayList<Snake> snakes = new ArrayList<Snake>();
    private JPanel MPanel;
    private JButton buttonleft;
    private JButton buttonright;
    private GPanel GPanel1;

    public Interface() {
        buttonleft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSnake(3);
            }
        });
        buttonright.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakes.get(0).direction += 1;
                if (snakes.get(0).direction > 3) snakes.get(0).direction = 0;
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

    private void createSnake(int length) {
        Snake boss = new Snake();
        snakes.add(boss);
        Timer timer = new Timer(1000, e -> refresher());
        timer.start();
    }

    public void refresher() {
        for (Snake s : snakes) {
            s.update();
            System.out.println(s.toString());
            GPanel1.repaint();
        }
    }

    private void createUIComponents() {
        GPanel1 = new GPanel();
    }
}
