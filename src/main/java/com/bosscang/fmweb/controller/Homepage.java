package com.bosscang.fmweb.controller;

import com.bosscang.fmweb.common.CaptureScreen;
import com.bosscang.fmweb.common.GamePlay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Homepage {
    @RequestMapping(value = "/fmweb", method = RequestMethod.GET)
    public String home(@RequestParam(value = "x", required = false) Integer x,
                       @RequestParam(value = "y", required = false) Integer y) {
        if (x != null && y != null) {
            GamePlay.click(x, y);
            try {
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        CaptureScreen.getScreen();
        return "/hello";
    }
}
