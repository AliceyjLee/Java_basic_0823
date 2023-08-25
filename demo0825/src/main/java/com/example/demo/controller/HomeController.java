package com.example.demo.controller;

import com.example.demo.vo.DataVO;
import com.example.demo.vo.NumVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/a")
    public String test01(Model model) {
        model.addAttribute(
                "name", "sjb");
        System.out.println("test01 메소드 실행");
        return "qwer01";
    }

    @GetMapping("/b")
    public String test02(DataVO dataVO){
        System.out.println(dataVO.getData1());
        System.out.println(dataVO.getData2());
        return "qwer02";
    }

    @PostMapping("/c")
    public String test03(DataVO dataVO){
        System.out.println(dataVO.getData1());
        System.out.println(dataVO.getData2());
        return "qwer03";
    }

    @GetMapping("/d")
    public String test04(DataVO dataVO, Model model){
        System.out.println(dataVO.getData1());
        System.out.println(dataVO.getData2());
        return "qwer04";
    }

    @PostMapping("/sum")
    public String sum(NumVO numVO, Model model){
        model.addAttribute("result", numVO.getNum1() + numVO.getNum2() );
        return "result";
    }
}
