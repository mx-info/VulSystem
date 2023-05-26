package com.mxnet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VulDetectController {

    @RequestMapping("/toAstpath")
    public String toAstpath(){
        return "/detectmethod/astpath";
    }
    @RequestMapping("/toAstree")
    public String toAstree(){
        return "/detectmethod/astree";
    }

    @RequestMapping("/toCfgnn")
    public String toCfgnn(){
        return "/detectmethod/cfgnn";
    }

    @RequestMapping("/toOnlytree")
    public String toOnlytree(){
        return "/detectmethod/onlytree";
    }

    @RequestMapping("/toSysevr")
    public String toSysevr(){
        return "/detectmethod/sysevr";
    }

    @RequestMapping("/toTokensequence")
    public String toTokensequence(){
        return "/detectmethod/tokensequence";
    }

}
