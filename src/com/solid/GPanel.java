package com.solid;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel {

    static Graphics2D g2d;
    static int rectSide = 400 / Snake.gameSize;

    public GPanel() {
        Timer timer = new Timer(150, e -> refresher());
        timer.start();
    }

    public void refresher() {
        this.requestFocusInWindow();
        boolean kill = false;
        for (int i = 0; i < Interface.snakes.size(); i++) {
            for (int[] bodypart : Interface.snakes.get(i).bodyparts) {
                if (Interface.snakes.get(i).headx == bodypart[0] && Interface.snakes.get(i).heady == bodypart[1]) {
                    kill = true;
                }
            }
            if (!kill) {
                Interface.snakes.get(i).update();
            } else {
                Interface.snaaakes.add(Interface.snakes.get(i));
                Interface.snakes.remove(i);
            }
            //System.out.println(s.toString());
        }
        EventQueue.invokeLater(() -> {
            this.validate();
            this.repaint();
        });
    }

    private void drawRectCoords(Graphics2D g2d, int[] coords, Color color) {
        g2d.setColor(Color.gray);
        g2d.drawRect(coords[0] * rectSide, coords[1] * rectSide, rectSide, rectSide);
        g2d.setColor(color);
        g2d.fillRect(coords[0] * rectSide, coords[1] * rectSide, rectSide, rectSide);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        for (Snake snake : Interface.snakes) {
            int[] headCoords = {snake.headx, snake.heady};
            drawRectCoords(g2d, headCoords, Color.gray);
            for (int[] part : snake.getBodyNew()) {
                drawRectCoords(g2d, part, Color.green);
            }
            for (int[] food : snake.food) {
                drawRectCoords(g2d, food, Color.BLUE);
            }
        }
        for (Snake snaake : Interface.snaaakes) {
            for (int[] part : snaake.getBodyNew()) {
                drawRectCoords(g2d, part, Color.RED);
            }
            int[] headCoords = {snaake.headx, snaake.heady};
            drawRectCoords(g2d, headCoords, Color.BLACK);
        }
    }
}
