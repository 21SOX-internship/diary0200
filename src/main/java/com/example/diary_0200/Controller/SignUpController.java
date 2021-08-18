package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.userDAO;
import com.example.diary_0200.DAO.userDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignUpController", value = "/SignUpController")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        userDTO userdto = new userDTO();
        userdto.setName(request.getParameter("username"));
        userdto.setId(request.getParameter("userid"));
        userdto.setPw(request.getParameter("userpw"));
        userdto.setEmail(request.getParameter("useremail"));
        userdto.setGender(request.getParameter("gender"));
        userdto.setTel(request.getParameter("userphone"));
        userdto.setAge(request.getParameter("userbirth"));

        userDAO userdao = new userDAO();
        userdao.signup(userdto);

        request.getRequestDispatcher("/login").forward(request, response);
    }
}
