package com.solid;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    static public int gameSize = 20;
    int direction = 0; //0 = north, 1 = east, 2 = south, 3 = west
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

    public List<int[]> getBody() {
        List<int[]> parts = new ArrayList<int[]>();
        for (int i = turns.size() - 1; i > 0; i--) {
            int[] head = {this.headx, this.heady};
            switch (findDirection(head, turns.get(i))) {
                case 0:
                    for (int f = this.heady; f <= turns.get(i)[1]; f++) { //X is the same, Y is increasing from head
                        int[] newPart = {this.headx, f};
                        parts.add(newPart);
                    }
                    break;
                case 1:
                    for (int f = this.headx; f <= turns.get(i)[0]; f++) { //Y is the same, X is increasing from head
                        int[] newPart = {f, this.heady};
                        parts.add(newPart);
                    }
                    break;
                case 2:
                    for (int f = this.heady; f >= turns.get(i)[1]; f--) { //X is the same, Y is decreasing from head
                        int[] newPart = {this.headx, f};
                        parts.add(newPart);
                    }
                    break;
                case 3:
                    for (int f = this.headx; f >= turns.get(i)[0]; f--) { //Y is the same, X is decreasing from head
                        int[] newPart = {f, this.heady};
                        parts.add(newPart);
                    }
                    break;
            }
        }
        for (int[] arr : parts) {
            System.out.println(arr[0] + arr[1]);
        }
        return parts;
    }

    private int findDirection(int[] first, int[] second) {
        int objDirection;
        if (first[0] == second[0]) {
            if (Math.abs(first[1]) - Math.abs(second[1]) > 0) {
                objDirection = 0;
            } else {
                objDirection = 2;
            }
        } else {
            if (Math.abs(first[0]) - Math.abs(second[0]) > 0) {
                objDirection = 3;
            } else {
                objDirection = 1;
            }
        }
        return objDirection;
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
                heady -= 1;
                break;
            case 3:
                headx -= 1;
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
