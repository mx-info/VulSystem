package com.mxnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InformationController {

    @RequestMapping("/toUpdate")
    public String toUpdateInformation(){
        return "/information/update";
    }

}
