package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.goalSwDAO;
import com.example.diary_0200.DAO.goalTDAO;
import com.example.diary_0200.DAO.userDAO;
import com.example.diary_0200.DAO.userDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "TestController", value = "/TestController")
public class TestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goalTDAO goaltdao = new goalTDAO();
        goalSwDAO goalswdao = new goalSwDAO();

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        else{
            //로그인으로 돌아가는 코드.
        }

        int numofgoal = 0;
        if(goaltdao.istheregoal(seq) || goalswdao.istheregoal(seq)){
            numofgoal = 1;
        }
        request.setAttribute("result",numofgoal);

    }
}
