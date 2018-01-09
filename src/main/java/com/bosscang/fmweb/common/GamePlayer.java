package com.bosscang.fmweb.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class GamePlayer {

    @Value("${WEB.IMG_WIDTH}")
    private int webWidth;
    @Value("${WEB.IMG_HEIGHT}")
    private int webHeight;
    @Value("${img.cap-path}")
    private String path;

    private int screenWidth;
    private int screenHeight;

    private Robot robot;

    @Autowired
    public GamePlayer() {
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click(Integer x, Integer y, Integer button) {
        //如果button=0（鼠标左键），button=2（鼠标右键）
        getScreenSize();
        move2Point(x, y);
        if (button == 0) {
            leftClick();
        }else {
            rightClick();
        }
    }

    private void getScreenSize() {
        try {
            File file = new File(path,"screen.png");
            BufferedImage image = ImageIO.read(file);
            screenWidth = image.getWidth();
            screenHeight = image.getHeight();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void move2Point(int x, int y) {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        int move2x = (int) ( x * screenWidth * 1.0 / webWidth);
        int move2y = (int) ( y * screenHeight * 1.0 / webHeight);
        Point move2point = new Point(move2x, move2y);
        int re = 0;
        while (closeEnough(mousePoint, move2point) && re++ < 100) {
            robot.mouseMove(move2x, move2y);
            mousePoint = MouseInfo.getPointerInfo().getLocation();
        }
    }

    private void leftClick() {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
    }

    private void rightClick() {
        robot.mousePress(KeyEvent.BUTTON3_MASK);
        robot.mouseRelease(KeyEvent.BUTTON3_MASK);
    }
    private static boolean closeEnough(Point a, Point b) {
        int dest = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        return dest > 1;
    }
}
