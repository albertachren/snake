package com.solid;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel {

    static Graphics2D g2d;
    int rectSide = 400 / Snake.gameSize;

    public GPanel() {
    }

    private void drawRectCoords(Graphics2D g2d, int[] coords) {
        g2d.fillRect(coords[0] * rectSide, coords[1] * rectSide, rectSide, rectSide);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        for (Snake snake : Interface.snakes) {
            int[] headCoords = {snake.headx, snake.heady};
            drawRectCoords(g2d, headCoords);
            System.out.println("bodyupdate");
            for (int[] part : snake.getBody()) {
                drawRectCoords(g2d, part);
            }
        }
    }
}
