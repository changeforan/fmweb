package com.bosscang.fmweb.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class CaptureScreen {
    @Value("${img.cap-path}")
    private String path;
    private void captureScreen(String floder, String fileName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        File screenFile = new File(floder);
        File f = new File(screenFile, fileName);
        ImageIO.write(image, "png", f);
    }

    public void getScreen() {
            String rd="screen";
            try {
                captureScreen(path,rd+".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
