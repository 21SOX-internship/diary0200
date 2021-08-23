package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.*;
import org.json.JSONObject;
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

    @RequestMapping(value = "/home/edit")
    public String gosetgoal(HttpServletRequest request, Model model) throws IOException {
        String timetype="", goalName="";
        boolean modify;
        if(request.getParameter("goalName")!=null){
            request.setAttribute("modify",true);
            modify = true;
            timetype = request.getParameter("timeType");
            goalName = request.getParameter("goalName");
        }else{
            request.setAttribute("modify",false);
            modify = false;
        }

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        else{
            return "signin";
        }

        if(modify){
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
            }

            model.addAttribute("timetype", timetype);
        }


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
    public void updategoalsw(@RequestParam("goalName")String goalName,@RequestParam("tag")String goalTag,@RequestParam("beforeName")String beforeName, @RequestParam("beforeTag")String beforeTag, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        goalSwDTO goaldata = new goalSwDTO();
        goaldata.setGoalName(goalName);
        goaldata.setTag(goalTag);
        goalSwDTO beforedata = new goalSwDTO();
        beforedata.setTag(beforeTag);
        beforedata.setGoalName(beforeName);
        goaldata.setSeq(seq);
        goalSwDAO goalswdao = new goalSwDAO();
        response.getWriter().print(goalswdao.updategoal(beforedata, goaldata));
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

    @RequestMapping(value="updategoalt.do", method= RequestMethod.POST)
    public void updategoalt(@RequestParam("goalName")String goalName,@RequestParam("tag")String goalTag,@RequestParam("beforeName")String beforeName, @RequestParam("beforeTag")String beforeTag, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        goalTDTO goaldata = new goalTDTO();
        goaldata.setGoalName(goalName);
        goaldata.setTag(goalTag);
        goalTDTO beforedata = new goalTDTO();
        beforedata.setTag(beforeTag);
        beforedata.setGoalName(beforeName);
        goaldata.setSeq(seq);
        goalTDAO goaltdao = new goalTDAO();
        response.getWriter().print(goaltdao.updategoal(beforedata, goaldata));
    }

    @RequestMapping(value="record_sw.do", method= RequestMethod.POST)
    public void recordgoalsw(@RequestParam("goalName") String goalName, @RequestParam("goalTag") String goalTag, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        goalSwDAO goalswdao = new goalSwDAO();
        String time = goalswdao.getrecordingtime(seq, goalName, goalTag);
        response.getWriter().print(time);
    }

    @RequestMapping(value="record_t.do", method= RequestMethod.POST)
    public void recordgoalt(@RequestParam("goalName") String goalName, @RequestParam("goalTag") String goalTag, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        goalTDAO goaltdao = new goalTDAO();

        JSONObject jobj = goaltdao.getrecordingtime(seq, goalName, goalTag);

        response.getWriter().print(jobj);
    }


    @RequestMapping(value = "/home/record/stopwatch")
    public String gorecording_sw(HttpServletRequest request, Model model) throws IOException {
        String goalName="", goalTag="", time = "";
        if(request.getParameter("goalName")!=null){
            goalName = request.getParameter("goalName");
            goalTag = request.getParameter("goalTag");
            time = request.getParameter("time");
        }

        model.addAttribute("goalName",goalName);
        model.addAttribute("goalTag",goalTag);
        model.addAttribute("time",time);

        return "home_record_sw";
    }

    @RequestMapping(value = "/home/record/timer")
    public String gorecording_t(HttpServletRequest request, Model model) throws IOException {
        String goalName="", goalTag="", time = "", endTime = "";
        if(request.getParameter("goalName")!=null){
            goalName = request.getParameter("goalName");
            goalTag = request.getParameter("goalTag");
            time = request.getParameter("time");
            endTime = request.getParameter("endtime");
        }

        String h = time.substring(0,2);
        String m = time.substring(3,5);
        String s = time.substring(6,8);

        int recording = (Integer.parseInt(h) * 3600) + (Integer.parseInt(m) * 60) + Integer.parseInt(s);

        h = endTime.substring(0,2);
        m = endTime.substring(3,5);
        s = endTime.substring(6,8);

        int goaltime = (Integer.parseInt(h) * 3600) + (Integer.parseInt(m) * 60) + Integer.parseInt(s);

        int mm = (int)Math.floor((goaltime - recording)/60);
        int hh = (int)Math.floor((mm)/60);
        int ss = (goaltime - recording)%60;
        mm = mm % 60;

        model.addAttribute("goalName",goalName);
        model.addAttribute("goalTag",goalTag);
        model.addAttribute("hh",addZero(hh));
        model.addAttribute("mm",addZero(mm));
        model.addAttribute("ss",addZero(ss));
        model.addAttribute("endtime",goaltime);

        return "home_record_t";
    }

    @RequestMapping(value = "update_record_sw.do")
    public void updaterecord_sw(@RequestParam("time") int time,@RequestParam("goalName") String goalName, @RequestParam("goalTag") String goalTag, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        int h,m,s;
        m = (int)Math.floor(time / 60);
        h = (int)Math.floor(m / 60);
        s = time % 60;
        m = m % 60;

        String record = addZero(h)+":"+addZero(m)+":"+addZero(s);

        goalSwDAO goalswdao = new goalSwDAO();
        if(goalswdao.updaterecord(record, seq, goalName, goalTag) == 0){
            response.getWriter().print(0);
        }else{
            response.getWriter().print(1);
        }
    }

    //해야함.
    @RequestMapping(value = "update_record_t.do")
    public void updaterecord_t(@RequestParam("time") String time,@RequestParam("goalName") String goalName, @RequestParam("goalTag") String goalTag, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        goalTDAO goaltdao = new goalTDAO();
        if(goaltdao.updaterecord(time, seq, goalName, goalTag) == 0){
            response.getWriter().print(0);
        }else{
            response.getWriter().print(1);
        }
    }

    public String addZero(int num){
        return (num < 10 ? '0' + Integer.toString(num) : Integer.toString(num));

    }

}