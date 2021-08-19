package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.goalSwDAO;
import com.example.diary_0200.DAO.goalTDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        }
        model.addAttribute("numofgoal", numofgoal);
        return "home";
    }

    @RequestMapping(value = "/home/record/stopwatch")
    public String gorecording() {
        return "home_record_sw";
    }

    @RequestMapping(value = "/home/edit")
    public String gosetgoal() {return "home_edit";}

    @RequestMapping(value = "modify.do", method= RequestMethod.POST)
    public void modifygoal(Model model){



        gosetgoal();
    }

}
