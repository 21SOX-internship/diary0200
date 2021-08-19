package com.example.diary_0200.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {
    @RequestMapping(value = "/mypage/main")
    public String gomypage() {
        return "mypage";
    }

    @RequestMapping(value = "/mypage/edit")
    public String gomypageedit() {
        return "mypage_edit";
    }


}
