package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.friendDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FriendController {

    @RequestMapping(value = "/friend/main")
    public String gofriend(HttpServletRequest request, Model model) {
        friendDAO dao = new friendDAO();


        return "friend";
    }

    @RequestMapping(value = "/friend/edit")
    public String gofriendedit() {
        return "friend_edit";
    }
}
