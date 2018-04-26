package com.solid;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel {

    static Graphics2D g2d;
    static int rectSide = 400 / Snake.gameSize;

    public GPanel() {
        Timer timer = new Timer(500, e -> refresher());
        timer.start();
    }

    public void refresher() {
        this.requestFocusInWindow();
        for (Snake s : Interface.snakes) {
            s.update();
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
                //System.out.println(String.valueOf(part[0]) + String.valueOf(part[1]));
            }
        }
    }
}
