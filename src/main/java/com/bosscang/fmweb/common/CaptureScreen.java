package com.bosscang.fmweb.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
;

public class CaptureScreen {
    private static void captureScreen(String floder, String fileName) throws Exception {
        floder = CaptureScreen.class.getResource("/") + floder;
        floder = floder.substring(floder.indexOf("C:"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        File screenFile = new File(floder);
        File f = new File(screenFile, fileName);
        ImageIO.write(image, "png", f);
    }

    public static void getScreen() {
            String rd="screen";
            try {
                captureScreen("/static/images/",rd+".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
