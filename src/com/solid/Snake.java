package com.solid;

import java.util.ArrayList;

public class Snake {

    static public int gameSize = 10;
    public int direction = 0; //0 = north, 1 = east, 2 = south, 3 = west
    int headx;
    int heady;
    int bodycount = 3;
    int lastdir = 0;
    ArrayList<int[]> turns = new ArrayList<int[]>();

    public Snake() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String headc = "(" + headx + ", " + heady + ")";
        return "Snake_" + this.hashCode() + "{" + headc + ", bc: " + bodycount + ", trns: " + turns.size() + "}";
    }

    public void update() {

        if (lastdir != direction) {
            int[] newturn = {headx, heady};
            turns.add(newturn);
        }

        switch (direction) {
            case 0:
                heady += 1;
                break;
            case 1:
                headx += 1;
                break;
            case 2:
                heady += 1;
                break;
            case 3:
                headx += 1;
                break;
        }

        int distance = 0;
        for (int i = turns.size() - 1; i >= 0; i--) {
            int tempdistance = 0;
            int x = turns.get(i)[0];
            int y = turns.get(i)[1];
            if (x == headx) {
                tempdistance = Math.abs(Math.abs(y) - Math.abs(heady));
            } else {
                tempdistance = Math.abs(Math.abs(x) - Math.abs(headx));
            }
            distance += tempdistance;
            System.out.println(distance);
            if (distance >= bodycount) {
                for (int f = i; i >= 0; i--) {
                    turns.remove(i);
                }
            }
        }

        lastdir = direction;

    }
}
