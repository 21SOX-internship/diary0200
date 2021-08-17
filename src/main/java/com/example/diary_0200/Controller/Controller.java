package com.example.diary_0200.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/start")
    public String gostart() {
        return "start";
    }

    @RequestMapping(value = "/login")
    public String gologin() {
        return "login";
    }

    @RequestMapping(value = "/nogoal")
    public String gonogoal() {
        return "nogoal";
    }

    @RequestMapping(value = "/home")
    public String gohome() {
        return "home";
    }

    @RequestMapping(value = "/recording")
    public String gorecording() {
        return "recording";
    }

    @RequestMapping(value = "/setgoal")
    public String gosetgoal() {
        return "setgoal";
    }

    @RequestMapping(value = "/signup")
    public String gosignup() {
        return "signup";
    }


}
