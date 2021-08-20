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
import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping(value = "/home/main")
    public String gohome(HttpServletRequest request, Model model) {
        goalTDAO goaltdao = new goalTDAO();
        goalSwDAO goalswdao = new goalSwDAO();

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        else{
            return "signin";
        }

        int numofgoal = 0;
        if(goaltdao.istheregoal(seq) || goalswdao.istheregoal(seq)){
            numofgoal = 1;

            ArrayList<goalSwDTO> listsw = goalswdao.gettoodaysgoal(seq);
            model.addAttribute("goallistsw", listsw);

            ArrayList<goalTDTO> listt = goaltdao.gettoodaysgoal(seq);
            model.addAttribute("goallistt", listt);

        }
        model.addAttribute("numofgoal", numofgoal);

        return "home";
    }

    @RequestMapping(value = "/home/record/stopwatch")
    public String gorecording() {
        return "home_record_sw";
    }

    @RequestMapping(value = "/home/edit")
    public String gosetgoal(HttpServletRequest request, Model model) throws IOException {
        String timetype="", goalName="";

        if(request.getParameter("goalName")!=null){
            request.setAttribute("modify",true);
            timetype = request.getParameter("timeType");
            goalName = request.getParameter("goalName");
        }else{
            request.setAttribute("modify",false);
        }

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        else{
            return "signin";
        }

        if(timetype.equals("stopwatch")){
            goalSwDAO goalswdao = new goalSwDAO();
            String goalTag = goalswdao.getthisgoal(seq, goalName);
            model.addAttribute("goalName", goalName);
            model.addAttribute("goalTag", goalTag);
        }else{
            goalTDAO goaltdao = new goalTDAO();
            goalTDTO goaldata = goaltdao.getthisgoal(seq, goalName);
            model.addAttribute("goalName",goalName);
            model.addAttribute("goalTag",goaldata.getTag());
            model.addAttribute("endTime",goaldata.getEndTime());
            model.addAttribute("timertime",goaltdao);
        }

        model.addAttribute("timetype", timetype);

        return "home_edit";
    }

    @RequestMapping(value="savegoalsw.do", method= RequestMethod.POST)
    public void savegoalsw(@ModelAttribute goalSwDTO goaldata, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        goaldata.setSeq(seq);
        goalSwDAO goalswdao = new goalSwDAO();
        response.getWriter().print(goalswdao.savegoal(goaldata));
    }

    @RequestMapping(value="updategoalsw.do", method= RequestMethod.POST)
    public void updategoalsw(@ModelAttribute goalSwDTO goaldata, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        goaldata.setSeq(seq);
        goalSwDAO goalswdao = new goalSwDAO();
        response.getWriter().print(goalswdao.savegoal(goaldata));
    }

    @RequestMapping(value="savegoalt.do", method= RequestMethod.POST)
    public void savegoalt(@ModelAttribute goalTDTO goaldata, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        goaldata.setSeq(seq);
        goalTDAO goaltdao = new goalTDAO();
        response.getWriter().print(goaltdao.savegoal(goaldata));
    }
}
