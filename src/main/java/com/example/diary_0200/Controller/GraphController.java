package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.goalSwDAO;
import com.example.diary_0200.DAO.goalSwDTO;
import com.example.diary_0200.DAO.goalTDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.lang.model.type.ArrayType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class GraphController {
    @RequestMapping(value = "/graph")
    public String gograph(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        ArrayList<goalSwDTO> weeklist = new ArrayList<goalSwDTO>();
        goalSwDAO goalswdao = new goalSwDAO();
        weeklist = goalswdao.thisweekgoal(seq);

        ArrayList<String> monthlist = new ArrayList<String>();
        monthlist = goalswdao.thismonthgoal(seq);

        ArrayList<String> todaylist = new ArrayList<String>();
        todaylist = goalswdao.thisdaygoal(seq);

        String month = changetimemonth(monthlist);
        String week = changetimeweek(weeklist);
        String today = changetimemonth(todaylist);

        if(weeklist == null){
            week = "00:00:00";
        }if(monthlist==null){
            month = "00:00:00";
        }if(todaylist==null){
            today = "00:00:00";
        }
        model.addAttribute("monthtime", month);
        model.addAttribute("weektime", week);
        model.addAttribute("todaytime", today);

        return "graph";
    }

    public String changetimemonth(ArrayList<String> monthlist){
        int monthtime = 0;

        for(int i = 0; i < monthlist.size(); i++){
            int h,m,s;
            String hs,ms,ss;
            String time = monthlist.get(i);
            hs = time.substring(0,2);
            ms = time.substring(3,5);
            ss = time.substring(6,8);

            h = Integer.parseInt(hs);
            m = Integer.parseInt(ms);
            s = Integer.parseInt(ss);

            h *= 3600;
            m *= 60;

            int thistime = h + m + s;
            monthtime += thistime;
        }

        int a,b,c;
        b = (int)Math.floor(monthtime / 60);
        a = (int)Math.floor(b / 60);
        c = monthtime % 60;
        b %= 60;

        String smonthtime = addZero(a)+":"+addZero(b)+":"+addZero(c);
        return smonthtime;
    }

    public String changetimeweek(ArrayList<goalSwDTO> monthlist){
        int monthtime = 0;

        for(int i = 0; i < monthlist.size(); i++){
            int h,m,s;
            String hs,ms,ss;
            goalSwDTO goals = monthlist.get(i);
            String time = goals.getTime();
            hs = time.substring(0,2);
            ms = time.substring(3,5);
            ss = time.substring(6,8);

            h = Integer.parseInt(hs);
            m = Integer.parseInt(ms);
            s = Integer.parseInt(ss);

            h *= 3600;
            m *= 60;

            int thistime = h + m + s;
            monthtime += thistime;
        }

        int a,b,c;
        b = (int)Math.floor(monthtime / 60);
        a = (int)Math.floor(b / 60);
        c = monthtime % 60;
        b %= 60;

        String smonthtime = addZero(a)+":"+addZero(b)+":"+addZero(c);
        return smonthtime;
    }

    public String addZero(int num){
        return (num < 10 ? '0' + Integer.toString(num) : Integer.toString(num));

    }
}
