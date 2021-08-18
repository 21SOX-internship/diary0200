package com.example.diary_0200.Controller;


import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

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
@WebServlet(name = "MyPageEditController", value = "/MyPageEditController")
public class MyPageEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String realPath = request.getServletContext().getRealPath("/upload");
//        System.out.println(realPath);


        File currentDirPath = new File(realPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(currentDirPath);
        factory.setSizeThreshold(1024*1024*5);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(request);
            FileItem fileItem = (FileItem) items.get(0);
            File uploadFile = new File(currentDirPath+"\\이름.png");
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
