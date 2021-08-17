package com.example.diary_0200.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/start")
    public String gostart() {return "start";}

    @RequestMapping(value = "/login")
    public String gologin() {return "signin";}

    @RequestMapping(value = "/home/nogoal")
    public String gonogoal() {
        return "home_nogoal";
    }

    @RequestMapping(value = "/home/main")
    public String gohome() {
        return "home";
    }

    @RequestMapping(value = "/home/record")
    public String gorecording() {
        return "home_record";
    }

    @RequestMapping(value = "/home/edit")
    public String gosetgoal() {
        return "home_edit";
    }

    @RequestMapping(value = "/signup")
    public String gosignup() {
        return "signup";
    }

    @RequestMapping(value = "/friend/main")
    public String gofriend() {
        return "friend";
    }

    @RequestMapping(value = "/friend/edit")
    public String gofriendmanage() {
        return "friend_edit";
    }

    @RequestMapping(value = "/mypage/main")
    public String gomypage() {
        return "mypage";
    }

    @RequestMapping(value = "/mypage/edit")
    public String gomypageedit() {
        return "mypage_edit";
    }


}
