package com.example.diary_0200.Controller;


import com.example.diary_0200.DAO.mypageDAO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*50*5
)
//@WebServlet(value = "/mypage/edit/save")
public class MyPageEditController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        mypageDAO dao = new mypageDAO();
        String realPath = request.getServletContext().getRealPath("/upload");

//        String fileName = Integer.toString(seq)+".png";
//        com.oreilly.servlet.MultipartRequest multipartRequest1 = new com.oreilly.servlet.MultipartRequest(request, realPath, 1024*1024*10, "UTF-8", new FileUploadRename(fileName));
//        String message = multipartRequest1.getParameter("message");
        String message = request.getParameter("message");
        System.out.println("message : "+message);

        dao.saveMessage(seq, message);
        System.out.println(realPath);

        File currentDirPath = new File(realPath);
        System.out.println(currentDirPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(currentDirPath);
        factory.setSizeThreshold(1024*1024*5);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(request);
            FileItem fileItem = (FileItem) items.get(0);


            File uploadFile = new File(currentDirPath+"\\"+seq+".png");
//            File uploadFile = new File("\\이름.png");
            fileItem.write(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("uploadPath", realPath);


        response.sendRedirect("/mypage/main");

//        RequestDispatcher rd = request.getRequestDispatcher("/mypage/main");
//        rd.forward(request, response);

    }
}
