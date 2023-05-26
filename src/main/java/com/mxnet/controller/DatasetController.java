package com.mxnet.controller;


import com.mxnet.mapper.DataSampleMapper;
import com.mxnet.pojo.DataSample;
import com.mxnet.service.DataSampleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DatasetController {

    @Autowired
    DataSampleServiceImpl dataSampleService;


    @RequestMapping("/toD2a")
    public String toD2a(Model model) {
        int startIndex = 1;
        int pageSize = 100;
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", (startIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<DataSample> dataSamples = dataSampleService.queryFunctionByLimit(map);
        model.addAttribute("d2a_train", dataSamples);

        return "/datasets/d2a";
    }

    @RequestMapping("/toReveal")
    public String toReveal(Model model) {
        int startIndex = 1;
        int pageSize = 100;
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", (startIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<DataSample> dataSamples = dataSampleService.queryFunctionByLimit(map);
        model.addAttribute("d2a_train", dataSamples);
        return "/datasets/reveal";
    }

    @RequestMapping("/toSard")
    public String toSard(Model model) {
        int startIndex = 1;
        int pageSize = 100;
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", (startIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<DataSample> dataSamples = dataSampleService.queryFunctionByLimit(map);
        model.addAttribute("d2a_train", dataSamples);
        return "/datasets/SARD";
    }

    @RequestMapping("/toVuldeepecker")
    public String toVuldeepecker(Model model) {
        int startIndex = 1;
        int pageSize = 100;
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", (startIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<DataSample> dataSamples = dataSampleService.queryFunctionByLimit(map);
        model.addAttribute("d2a_train", dataSamples);
        return "/datasets/vuldeepecker";
    }

    @RequestMapping("/saveD2a")
    public String saveD2a() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\vulsystem\\VulSystem\\src\\main\\java\\com\\mxnet\\service\\d2a.txt"));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("#");
//            int id = Integer.parseInt(split[1]);
            String code = split[2];
            String label = split[3];
            DataSample dataSample = new DataSample();
//            dataSample.setId(id);
            if (!(label.equals("0") || label.equals("1"))) {
                continue;
            }

            dataSample.setCode(code);
            dataSample.setLabel(label);
            System.out.println(dataSample);
            dataSampleService.insertSample(dataSample);
        }
        bufferedReader.close();
        return "/datasets/d2a";
    }

}
