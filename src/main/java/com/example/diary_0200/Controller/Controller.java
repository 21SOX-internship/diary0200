package com.example.diary_0200.Controller;


import com.example.diary_0200.DAO.mypageDAO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/start")
    public String gostart() {
        return "start";
    }

    @RequestMapping(value = "/login")
    public String gologin() {
        return "signin";
    }

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
    public String gofriendlist() {
        return "friend";
    }

    @RequestMapping(value = "/friend/edit")
    public String gofriendmanage() {
        return "friend_edit";
    }

    @RequestMapping(value = "/mypage/main")
<<<<<<< Updated upstream
    public String goprofilepmain() {
=======
    public String gomypage() {
//        mypageDAO dao = new mypageDAO();
//        ResultSet rs = dao.loadMyPageInfo();

>>>>>>> Stashed changes
        return "mypage";
    }

    @RequestMapping(value = "/mypage/edit")
    public String goprofilepedit() {
        return "mypage_edit";
    }

    @RequestMapping(value = "/test")
    public String gotest() {
        return "test";
    }


}
