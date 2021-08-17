package com.example.diary_0200.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class MyPageEditController {

    @PostMapping(value="/mypage/myPageEditRequest")
    public String requestUpload(MultipartHttpServletRequest mtfRequest) {
        String message = mtfRequest.getParameter("message");
        System.out.println("message value : " + message);
        MultipartFile photo = mtfRequest.getFile("photo");

        String path = "C:\\image\\";

        String originFileName = photo.getOriginalFilename(); // 원본 파일 명
        long fileSize = photo.getSize(); // 파일 사이즈

        System.out.println("originFileName : " + originFileName);
        System.out.println("fileSize : " + fileSize);

        String safeFile = path + originFileName;

        try {
            photo.transferTo(new File(safeFile));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/mypage/main";

    }
}
