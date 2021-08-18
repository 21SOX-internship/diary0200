package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.userDAO;
import com.example.diary_0200.DAO.userDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpController", value = "/SignUpController")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        userDAO userdao = new userDAO();
        userDTO userdto = new userDTO();
        userdto.setId(request.getParameter("userid"));
        userdto.setPw(request.getParameter("userpw"));
        userdto.setGender(request.getParameter("usergender"));
        userdto.setEmail(request.getParameter("useremail"));
        userdto.setTel(request.getParameter("userphone"));
        userdto.setName(request.getParameter("username"));
        userdto.setAge(request.getParameter("userbirth"));
        int result = userdao.signup(userdto);

        StringBuffer json = new StringBuffer();
        json.append(" { ");
        json.append(" \"status\" : \"success\", ");
        json.append(" \"result\" : " + result);
        json.append(" } ");
        PrintWriter writer = response.getWriter();
        writer.write(json.toString());
        writer.flush();
        writer.close();
    }
}
