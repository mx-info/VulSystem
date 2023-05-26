package com.mxnet.service;

import com.mxnet.tools.FlaskInterfaceTool;

import java.io.File;

public class ExtractFunService {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\PycharmProjects\\VulSystem\\preprocess\\test.cpp");
//        System.out.println(file.getAbsolutePath());
//        String res = FlaskInterfaceTool.getResByUrl("http://127.0.0.1:5000/preprocess/C:/Users/Administrator/PycharmProjects/VulSystem/preprocess/test.cpp");
        String res = FlaskInterfaceTool.getResByUrl("http://127.0.0.1:5000/preprocess/" + file.getAbsolutePath());
        String[] split = res.split("####");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
