package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.goalSwDAO;
import com.example.diary_0200.DAO.goalTDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GraphController {
    @RequestMapping(value = "/graph")
    public String gograph() {

        return "graph";
    }
}
