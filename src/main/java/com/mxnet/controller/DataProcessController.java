package com.mxnet.controller;

import com.mxnet.pojo.DataCode;
import com.mxnet.service.DataPreprocessService;
import com.mxnet.tools.ExtractFun;
import com.mxnet.tools.FlaskInterfaceTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DataProcessController {

    @Autowired
    DataPreprocessService dataPreprocessService;

    @RequestMapping("/toAst")
    public String toAst() {
        return "/preprocess/ast";
    }

    @RequestMapping("/toCfg")
    public String toCfg() {
        return "/preprocess/cfg";
    }

    @RequestMapping("/toCpg")
    public String toCpg() {
        return "/preprocess/cpg";
    }

    @RequestMapping("/toExtractFun")
    public String toExtractFun(Model model) {
        List<DataCode> dataCodes = dataPreprocessService.selectAllDataCode();
        model.addAttribute("dataCodes", dataCodes);
        return "/preprocess/extract_fun";
    }

    @RequestMapping("/toPdg")
    public String toPdg() {
        return "/preprocess/pdg";
    }

    @RequestMapping("/toToken")
    public String toToken() {
        return "/preprocess/token";
    }

    @RequestMapping("/toNormalize")
    public String toTokenization() {
        return "/preprocess/normalization";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String extractFunFromFile(@RequestParam("multipartFile") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                return "/preprocess/extract_fun";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
//            System.out.println("上传的文件名：" + fileName);            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            System.out.println("文件后缀名：" + suffixName);            //设置文件存储路径
//            String filePath = "e:/upload/";
            //获取跟目录(绝对路径)
            File root = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(root.getAbsolutePath(), "static/upload/");
            if (!upload.exists()) upload.mkdirs();
            String path = upload.getAbsolutePath() + "\\" + fileName;
            System.out.println(path);
            File dest = new File(path);
            //检测是否存在该目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            //写入文件
            file.transferTo(dest);
//            String res = FlaskInterfaceTool.getResByUrl("http://127.0.0.1:5000/preprocess/" + path);
//            String[] split = res.split("####");
//            List<String> strings = Arrays.asList(split);
            List<String> strings = null;
            try {
                strings = ExtractFun.extractFun(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            List<DataCode> dataCodes = new ArrayList<>();
            for (int i = 1; i <= strings.size(); i++) {
                dataCodes.add(new DataCode(i, strings.get(i - 1)));
                dataPreprocessService.insertFunExtractedFromFile(new DataCode(i, strings.get(i - 1)));
            }
            model.addAttribute("dataCodes", dataCodes);
            return "/preprocess/extract_fun";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/preprocess/extract_fun";

    }

    @RequestMapping(value = "/normalizeCode", method = RequestMethod.POST)
    public String normalizationFunFromFile(@RequestParam("multipartFile") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                return "normalization";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
//            System.out.println("上传的文件名：" + fileName);            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            System.out.println("文件后缀名：" + suffixName);            //设置文件存储路径
//            String filePath = "e:/upload/";
            //获取跟目录(绝对路径)
            File root = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(root.getAbsolutePath(), "static/upload/");
            if (!upload.exists()) upload.mkdirs();
            String path = upload.getAbsolutePath() + "\\" + fileName;
//            System.out.println(path);
            File dest = new File(path);
            //检测是否存在该目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            //写入文件
            file.transferTo(dest);
            List<String> strings = null;
            try {
                strings = ExtractFun.extractFun(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = "";
            for (int i = 0; i < strings.size(); i++) {
                str += strings.get(i) + "####";
            }
            String res = FlaskInterfaceTool.getResByUrl("http://127.0.0.1:5000/normalize?normalize=" + "\""+ str + "\"");
            String[] split = res.split("####");
            strings = Arrays.asList(split);


//            model.addAttribute("dataCodesTokenization", dataCodes);
            for (String s : split) {
                System.out.println(s);
            }
            return "normalization";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "normalization";

    }


}
