package com.bosscang.fmweb.common;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePlay {
    private static int REAL_WEIGHT = 1920;
    private static int REAL_HIGH = 1080;
    private static int WEB_WEIGHT = 1920;
    private static int WEB_HIGH = 1080;
    private static Robot robot;


    public static void click(Integer x, Integer y, Integer button) {
        //如果button=0（鼠标左键），button=2（鼠标右键）
        if (robot == null) {
            try {
                robot = new Robot();//创建Robot对象
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        move2Point(x, y);
        if (button == 0) {
            leftClick();
        }else {
            rightClick();
        }
    }

    private static void move2Point(int x, int y) {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        int move2x = (int) ( x * REAL_WEIGHT * 1.0 / WEB_WEIGHT );
        int move2y = (int) ( y * REAL_HIGH * 1.0 / WEB_HIGH);
        Point move2point = new Point(move2x, move2y);
        int re = 0;
        while (closeEnough(mousePoint, move2point) && re++ < 100) {
            robot.mouseMove(move2x, move2y);
            mousePoint = MouseInfo.getPointerInfo().getLocation();
        }
    }

    private static void leftClick() {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
    }

    private static void rightClick() {
        robot.mousePress(KeyEvent.BUTTON3_MASK);
        robot.mouseRelease(KeyEvent.BUTTON3_MASK);
    }
    private static boolean closeEnough(Point a, Point b) {
        int dest = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        return dest > 1;
    }
}
