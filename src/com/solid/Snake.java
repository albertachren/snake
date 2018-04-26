package com.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {

    static public int gameSize = 40;
    int direction = 0; //0 = north, 1 = east, 2 = south, 3 = west
    int headx;
    int heady;
    int lastdir = 0;
    static Random r = new Random();
    ArrayList<int[]> turns = new ArrayList<>();
    List<int[]> bodyparts = new ArrayList<>();
    List<int[]> food = new ArrayList<>();

    public Snake(int x, int y, int dir, int bodycount) {
        headx = x;
        heady = y;
        direction = dir;
        System.out.println(toString());
        for (int i = 0; i < bodycount; i++) {
            int newX = 0;
            int newY = 0;
            switch (direction) {
                case 0:
                    newX = headx;
                    newY = heady - 1 - i;
                    break;
                case 1:
                    newX = headx + 1 + i;
                    newY = heady;
                    break;
                case 2:
                    newX = headx;
                    newY = heady + 1 + i;
                    break;
                case 3:
                    newX = headx - 1 - i;
                    newY = heady;
                    break;
            }
            int[] temp = {newX, newY, direction};
            bodyparts.add(temp);
        }
        int[] newFood = {r.nextInt(40), r.nextInt(40)};
        food.add(newFood);
    }

    @Override
    public String toString() {
        String headc = "(" + headx + ", " + heady + ")";
        return "Snake_" + hashCode() + "{" + headc + ", bc: " + bodyparts.size() + ", trns: " + turns.size() + ", dir: " + direction + "}";
    }

    List<int[]> getBodyNew() {
        return bodyparts;
    }

    @Deprecated
    public List<int[]> getBody() {
        List<Integer> dirChanges = new ArrayList<Integer>();
        int bodyDirection = direction;
        List<int[]> parts = new ArrayList<int[]>();
        for (int i = 0; i < bodyparts.size(); i++) {
            int[] newPart = {0, 0};
            switch (bodyDirection) { // set bodypart coordinates
                case 0:
                    newPart[0] = headx;
                    newPart[1] = heady - i - 1;
                    break;
                case 1:
                    newPart[0] = headx - 1 - i;
                    newPart[1] = heady;
                    break;
                case 2:
                    newPart[0] = headx;
                    newPart[1] = heady + 1 + i;
                    break;
                case 3:
                    newPart[0] = headx + 1 + i;
                    newPart[1] = heady;
                    break;
            }
            List<Integer> turnIndexes = new ArrayList<Integer>();
            for (int[] turn : turns) {
                if (turn[0] == newPart[0] && turn[1] == newPart[1] && !turnIndexes.contains(turns.indexOf(turn))) { // if bodypart is on a turn, save index of said turn for the remaining bodyparts
                    turnIndexes.add(turns.indexOf(turn));
                    bodyDirection = turn[2]; //update direction
                }
            }
            parts.add(newPart);
        }
        /*for (int i = turns.size() - 1; i > 0; i--) {
            int[] head = {headx, heady};
            switch (findDirection(head, turns.get(i))) {
                case 0:
                    for (int f = heady; f <= turns.get(i)[1]; f++) { //X is the same, Y is increasing from head
                        int[] newPart = {headx, f};
                        parts.add(newPart);
                    }
                    break;
                case 1:
                    for (int f = headx; f <= turns.get(i)[0]; f++) { //Y is the same, X is increasing from head
                        int[] newPart = {f, heady};
                        parts.add(newPart);
                    }
                    break;
                case 2:
                    for (int f = heady; f >= turns.get(i)[1]; f--) { //X is the same, Y is decreasing from head
                        int[] newPart = {headx, f};
                        parts.add(newPart);
                    }
                    break;
                case 3:
                    for (int f = headx; f >= turns.get(i)[0]; f--) { //Y is the same, X is decreasing from head
                        int[] newPart = {f, heady};
                        parts.add(newPart);
                    }
                    break;
            }
        }*/
        for (int[] part : parts) {
            System.out.println(String.valueOf(part[0]) + String.valueOf(part[1]));
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
        for (int[] food : food) {
            if (headx == food[0] && heady == food[1]) {
                addBodypart(this);
                this.food.remove(food);
                int[] newFood = {r.nextInt(40), r.nextInt(40)};
                this.food.add(newFood);
            }
        }

        if (lastdir != direction) { // direction is changed from outside the method
            int[] newturn = {headx, heady, lastdir, direction};
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

        for (int[] part : bodyparts) {
            for (int[] turn : turns) {
                if (part[0] == turn[0] && part[1] == turn[1]) {
                    part[2] = turn[3];
                }
            }
            switch (part[2]) {
                case 0:
                    part[1] += 1;
                    break;
                case 1:
                    part[0] += 1;
                    break;
                case 2:
                    part[1] -= 1;
                    break;
                case 3:
                    part[0] -= 1;
                    break;
            }

        }


        int distance = 0;
        for (int i = turns.size() - 1; i >= 0; i--) { // remove passed turns
            int tempdistance = 0;
            int x = turns.get(i)[0];
            int y = turns.get(i)[1];
            if (x == headx) {
                tempdistance = Math.abs(Math.abs(y) - Math.abs(heady));
            } else {
                tempdistance = Math.abs(Math.abs(x) - Math.abs(headx));
            }
            distance += tempdistance;
            if (distance > bodyparts.size() + 2) {
                for (int f = i; i >= 0; i--) {
                    turns.remove(i);
                    System.out.println("turn removed");
                }
            }
        }

        lastdir = direction;

    }

    private void addBodypart(Snake snake) {
        int[] parentPart = this.bodyparts.get(bodyparts.size() - 1);
        int[] newPart = {parentPart[0], parentPart[1], parentPart[2]};
        switch (newPart[2]) {
            case 0:
                newPart[1] -= 1;
                break;
            case 1:
                newPart[0] -= 1;
                break;
            case 2:
                newPart[1] += 1;
                break;
            case 3:
                newPart[0] += 1;
                break;
        }
        bodyparts.add(newPart);
    }
}
