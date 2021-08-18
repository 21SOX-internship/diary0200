package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.userDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpCheckController", value = "/SignUpCheckController")
public class SignUpCheckController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        userDAO userdao = new userDAO();
        boolean isDuplicated = userdao.isDuplicatedUserId(userId);

        StringBuffer json = new StringBuffer();
        json.append(" { ");
        json.append(" \"status\" : \"success\", ");
        json.append(" \"duplicated\" : " + isDuplicated);
        json.append(" } ");
        PrintWriter writer = response.getWriter();
        writer.write(json.toString());
        writer.flush();
        writer.close();
    }
}
