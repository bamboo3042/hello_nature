package com.hellonature.hellonature_back.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RestController
@RequiredArgsConstructor

public class CkEditorsController {
    @PostMapping("/image/upload/product")
    @SneakyThrows
    public String upload(@RequestPart MultipartFile upload, HttpServletRequest request){
        OutputStream out = null;

        String filename = upload.getOriginalFilename();
        String filenameEx = FilenameUtils.getExtension(filename).toLowerCase();
        String randomfilename = RandomStringUtils.randomAlphanumeric(8) + "." + filenameEx;

        String path = request.getServletContext().getRealPath("/") + "/uploads/";
        File files = new File(path);
        if(!files.exists()){
            try{
                files.mkdirs();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        String path2 = files.getPath() + "/CKimg";
        File files1 = new File(path2);
        if(!files1.exists()){
            try{
                files1.mkdirs();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        String path3 = path2 + "/product/";
        File files2 = new File(path3);
        if(!files2.exists()){
            try{
                files2.mkdirs();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        FileCopyUtils.copy(upload.getInputStream(), new FileOutputStream(files2.getPath()+File.separator+randomfilename));

        String ckUploadPath = "C:/Seongyong/project/hello_nature6.0/src/main/resources/static/uploads/CKimg/product/" + randomfilename;
        upload.transferTo(new File(ckUploadPath));

        return "/uploads/CKimg/product/" + randomfilename;
    }
}