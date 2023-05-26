package com.mxnet.upload;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        //获取跟目录(绝对路径)
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");
        System.out.println("path:" + path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(), "static/upload/");
        if (!upload.exists()) upload.mkdirs();
        System.out.println("upload url:" + upload.getAbsolutePath());
//在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
//在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/


    }
}
