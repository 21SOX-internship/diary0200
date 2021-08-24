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
        model.addAttribute("monthtime",smonthtime);
//
//        for(int i = 0; i < weeklist.size(); i++){
//            goalSwDTO goal = weeklist.get(i);
//            String tag = goal.getTag();
//        }


        return "graph";
    }

    public String addZero(int num){
        return (num < 10 ? '0' + Integer.toString(num) : Integer.toString(num));

    }
}
