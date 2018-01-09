package com.bosscang.fmweb.controller;

import com.bosscang.fmweb.common.CaptureScreen;
import com.bosscang.fmweb.common.GamePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Homepage {

    @Autowired
    CaptureScreen captureScreen;

    @Autowired
    GamePlayer gamePlayer;

    @RequestMapping(value = "/fmweb", method = RequestMethod.GET)
    public String home(@RequestParam(value = "x", required = false) Integer x,
                       @RequestParam(value = "y", required = false) Integer y,
                       @RequestParam(value = "button", required = false) Integer button) {
        if (x != null && y != null) {
            gamePlayer.click(x, y, button);
            try {
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        captureScreen.getScreen();
        return "hello";
    }
}
