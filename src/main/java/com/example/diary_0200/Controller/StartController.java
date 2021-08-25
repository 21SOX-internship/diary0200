package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class StartController {

    @RequestMapping(value = "/")
    public String gostart() {return "start";}

    @RequestMapping(value = "/login")
    public String gologin() {return "signin";}

    @RequestMapping(value = "/signup")
    public String gosignup() {
        return "signup";
    }

    @RequestMapping(value="login.do", method= RequestMethod.POST)
    public void login(@RequestParam("userid") String userid, @RequestParam("userpw") String userpw, HttpServletResponse response, HttpServletRequest request) throws IOException {

        userDAO userdao = new userDAO();
        userDTO userdto = new userDTO();
        userdto.setId(userid);
        userdto.setPw(userpw);
        int result = userdao.signin(userdto);

        if(result == 1){
            HttpSession session = request.getSession();
            session.setAttribute("seq", userdao.getSeq(userdto.getId()));
        }
        response.getWriter().print(result);
    }

    @RequestMapping(value="signup.do", method= RequestMethod.POST)
    public void signup(@ModelAttribute userDTO userdto, HttpServletResponse response) throws IOException {
        userDAO userdao = new userDAO();
        int seq = userdao.generateSeq();
        userdto.setSeq(seq);
        int result = userdao.signup(userdto);
        mypageDAO mypagedao = new mypageDAO();
        mypagedao.newbe(seq);
        response.getWriter().print(result);
    }

    @RequestMapping(value="checkid.do", method= RequestMethod.POST)
    public void checkid(@RequestParam("userid") String userid, HttpServletResponse response) throws IOException {
        userDAO userdao = new userDAO();
        boolean result = userdao.isDuplicatedUserId(userid);
        response.getWriter().print(result);
    }

}
