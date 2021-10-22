package com.hellonature.hellonature_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Value("${file.upload.directory}")
    private String uploadDir;
    @Autowired
    private HttpServletRequest request;

    public List<String> imagesUploads(List<MultipartFile> multipartFiles, String targetDir){


        List<String> pathList = new ArrayList<>();
        if (multipartFiles == null || multipartFiles.isEmpty()) return pathList;

        try {
            for (int i = 0; i < multipartFiles.size(); i++){
                if (multipartFiles.get(i).isEmpty()) pathList.add(null);
                else {

                    String path = request.getServletContext().getRealPath("/") + "/uploads/";

                    File files = new File(path);
                    if(!files.exists()){
                        try{
                            files.mkdirs();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                    String path2 = files.getPath() + File.separator + targetDir;
                    File files1 = new File(path2);
                    if(!files1.exists()){
                        try{
                            files1.mkdirs();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                    FileCopyUtils.copy(multipartFiles.get(i).getInputStream(), new FileOutputStream(files1.getPath()+File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + "_"
                            + StringUtils.cleanPath(multipartFiles.get(i).getOriginalFilename())));



                    Path copyOfLocation = Paths.get(uploadDir + File.separator + targetDir + File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + "_"
                            + StringUtils.cleanPath(multipartFiles.get(i).getOriginalFilename())).toAbsolutePath();
                    Files.copy(multipartFiles.get(i).getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
                    String temp =  copyOfLocation.toString().substring(copyOfLocation.toString().indexOf("\\upload"));
                    temp = temp.replace("\\","/");
                    pathList.add(temp);
                }
            }

            System.out.println(pathList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathList;
    }
}