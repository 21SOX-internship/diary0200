package com.example.diary_0200.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FriendController {

    @RequestMapping(value = "/friend/main")
    public String gofriend() {
        return "friend";
    }

    @RequestMapping(value = "/friend/edit")
    public String gofriendmanage() {
        return "friend_edit";
    }
}
