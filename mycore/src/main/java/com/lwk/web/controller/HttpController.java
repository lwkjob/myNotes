package com.lwk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liwenke on 16/5/12.
 */
@Controller
@RequestMapping("/lwk")
public class HttpController {
    @RequestMapping("/fuck")
    public String fuck(){
        System.out.println("你大爷fuck");
        return "fuck";
    }


    @RequestMapping
    public String fuck2(){
        System.out.println("你大爷fuck2");
        return "fuck";
    }
}
