package com.example.diary_0200.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

    @RequestMapping(value="/mypage/myPageController")
    public String gomypageedit() {
        return "mypage_edit";
    }
}
