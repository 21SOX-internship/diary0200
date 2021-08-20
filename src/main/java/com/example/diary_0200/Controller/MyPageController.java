package com.example.diary_0200.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyPageController {

    @RequestMapping(value = "/mypage/main")
    public String gomypage(HttpServletRequest request, Model model) {
        System.out.println("MyPageController");
        return "mypage";
    }
    @RequestMapping(value = "/mypage/edit")
    public String gomypageedit() {
        return "mypage_edit";
    }
}
